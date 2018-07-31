#include "huffman.h"

Huffman::Huffman(QThread *parent) :
    QThread(parent)
{
    mask[1]=0x80;
    mask[2]=0xc0;
    mask[3]=0xe0;
    mask[4]=0xf0;
    mask[5]=0xf8;
    mask[6]=0xfc;
    mask[7]=0xfe;
    CompressHeader=QByteArray::fromHex("ff00ff00");
    EncryptHeader=QByteArray::fromHex("ff00ff01");
}
void Huffman::run()
{//compress or decompress in another thread
    if(isCompress)
    {
        this->ComAnalyse();
        this->GenerateCompressFile();
        emit message("Compress Complete!");
    }
    else
    {
        this->DecomAnalyse();
    }
}

void Huffman::Compress(QString comFile, QString after, QString passWord, bool needPass)
{
    if(comFile==after)
    {
        emit error("The input file name and the output file name cann't be the same!");
        return;
    }
    this->passWord=passWord;
    this->needPass=needPass;
    inFile.setFileName(comFile);
    outFile.setFileName(after);
    if(!CheckFile()) return;
    isCompress=true;
    this->start();

}
void Huffman::Decompress(QString decomFile, QString after, QString passWord)
{
    if(decomFile==after)
    {
        emit error("The input file name and the output file name cann't be the same!");
        return;
    }
    this->passWord=passWord;
    inFile.setFileName(decomFile);
    outFile.setFileName(after);
    if(!CheckFile()) return;
    isCompress=false;
    this->start();
}

bool Huffman::CheckFile()
{
    if(!inFile.exists())
    {
        emit error("File doesn't exist!");
        return false;
    }
    if(outFile.exists())
    {
        return QMessageBox::question(NULL,"OverWrite?","File exists! OverWrite it?");
    }
    return true;
}

void Huffman::EncryptOrDecrypt(QByteArray &content,QByteArray &pass,int &posOfPass)
{//content XOR password
    for(int i=0;i<content.size();i++,posOfPass++)
    {
        posOfPass=posOfPass%pass.size();
        content[i]=content[i]^pass[posOfPass];
    }
}

void Huffman::ComAnalyse()
{
    statisticMap.clear();
    char c;
    inFile.open(QIODevice::ReadOnly);
    emit message("Analysing File!");
    while(!inFile.atEnd())
    {//statistics
        inFile.getChar(&c);
        if(statisticMap.contains(c)) statisticMap[c]++;
        else statisticMap[c]=0;
    }
    emit message("Generating Huffman Tree!");
    tree.Generate(statisticMap,codeMap);
    inFile.close();
}
void Huffman::GenerateCompressFile()
{
    emit message("Generating compressed file!");
    outFile.open(QIODevice::WriteOnly);
    QByteArray header,content;
    QByteArray pass=passWord.toLocal8Bit();
    if(needPass)
    {
        header.append(EncryptHeader);
        header.append(QCryptographicHash::hash(pass,QCryptographicHash::Md5));
    }
    else    header.append(CompressHeader);
    outFile.write(header);
    header.clear();
    char c;
    int mapSize=codeMap.size();
    int zero=0;
    inFile.open(QIODevice::ReadOnly);
    while(!inFile.atEnd())
    {//code content with huffman code
        inFile.getChar(&c);
        content.append(codeMap[c]);
    }
    zero=8-content.size()%8;
    if(zero==8) zero=0;
    for(int i=0;i<zero;i++) content.append("0");
    header.append((char)zero);
    header.append((char)mapSize);
    QMap<char,QByteArray>::iterator it;
    for(it=codeMap.begin();it!=codeMap.end();it++)
    {
        header.append(it.key());
        header.append(this->formatCodeMap(it.value()));
    }
    int passPos=0;
    if(needPass) this->EncryptOrDecrypt(header,pass,passPos);
    outFile.write(header);
    bool ok;
    QByteArray after;
    after.clear();
    for(int i=0;i<content.size();i=i+8)
    {//transfer string to binary file
        after.append((char)content.mid(i,8).toInt(&ok,2));
        emit progress((i*100)/content.size());
    }
    emit progress(100);
    if(needPass) this->EncryptOrDecrypt(after,pass,passPos);
    outFile.write(after);
    inFile.close();
    outFile.close();
}
QByteArray Huffman::formatCodeMap(QByteArray s)
{
    //format::key.length.huffmancode
    // bytes:: 1    1        -
    int l=s.size();
    QByteArray r;
    r.append((char)l);
    while(s.size()%8!=0) s.append("0");
    bool ok;
    for(int i=0;i<s.size();i=i+8)
    {
        r.append((char)s.mid(i,8).toInt(&ok,2));
    }
    return r;
}

void Huffman::DecomAnalyse()
{
    QByteArray content;
    QByteArray pass=passWord.toLocal8Bit();
    inFile.open(QIODevice::ReadOnly);
    content=inFile.readAll();
    inFile.close();
    if(content.startsWith(CompressHeader)) content.remove(0,4);
    else if(content.startsWith(EncryptHeader))
    {
        content.remove(0,4);
        if(content.mid(0,16)==QCryptographicHash::hash(pass,QCryptographicHash::Md5))
        {
            content.remove(0,16);
            int pos=0;
            this->EncryptOrDecrypt(content,pass,pos);
        }
        else
        {
            emit error("wrong password!");
            return;
        }
    }
    else
    {
        emit error("file error!");
        return;
    }
    int zero=content[0]&0x000000ff;
    int mapSize=content[1]&0x000000ff;
    if(mapSize==0) mapSize=256;
    decodeMap.clear();
    int i,decodeLength;
    char key;
    QByteArray value;
    emit message("Generating decoding map!");
    for(i=2;mapSize>0;mapSize--)
    {//read decode map
        key=content[i];
        decodeLength=content[i+1]&0xff;
        while(decodeMap.length()<=decodeLength) decodeMap.append(new QMap <QByteArray,char> ());
        value.clear();
        int j=(decodeLength+7)/8;
        value=content.mid(i+2,j);
        i=i+j+2;
        decodeMap.at(decodeLength)->insert(value,key);
    }
    for(int i=0;i<decodeMap.size();i++)
    {
        if(decodeMap[i]->size()==0) decodeBitMap.push_back(0);
        else decodeBitMap.push_back(1);
    }
    this->GenerateDecompressFile(content.mid(i),zero);
    emit message("Decompress complete!");
}
void Huffman::GenerateDecompressFile(QByteArray content,int zero)
{
    emit message("Generating decompressed file!");
    outFile.open(QIODevice::WriteOnly);
    QByteArray out;
    int start=0,i=0;
    int size=content.size();
    while(true)
    {
        if(content.size()==1&&start==8-zero) break;
        else if(content.size()==0) break;
        for(int j=1;!this->TryToDecode(content,start,j,out);j++);
        if(i%1000==0)
        {
            emit progress((size-content.size())*100/size);
            outFile.write(out);
            out.clear();
        }
        i++;
    }
    emit progress(100);
    outFile.write(out);
    outFile.close();
}
bool Huffman::TryToDecode(QByteArray &in,int &start,int length,QByteArray &out)
{
    QByteArray tmp;
    if(length>=decodeBitMap.size()) return false;
    if(decodeBitMap[length]==0) return false;
    int index=length;
    char t;
    int i=0;
    if(start>0)
        while(true)
        {
            if(length+start>8)
            {
                t=in[i]<<start;
                t+=(in[i+1]&mask[start])>>(8-start);
                if(length<8) t=t&mask[length];
                tmp.append(t);
                length-=8;
                i++;
            }
            else
            {
                if(length<=0)break;
                t=in[i]<<start;
                t=t&mask[length];
                tmp.append(t);
                if(length+start==8) i++;
                break;
            }

        }
    else
    {
        while(true)
        {
            if(length>=8)
            {
                t=in[i];
                tmp.append(t);
                length-=8;
                i++;
            }
            else
            {
                if(length==0) break;
                t=in[i];
                t=t&mask[length];
                tmp.append(t);
                break;
            }
        }
    }
    if(decodeMap[index]->contains(tmp))
    {
        out.append(decodeMap[index]->value(tmp));
        start=(start+length+8)%8;
        in.remove(0,i);
        return true;
    }
    else return false;
}
