#include "graph.h"
#include <QFile>
#include <QMessageBox>
CGrayZip::CGrayZip(string EncodeFile, string DecodeFile)
{
    m_EncodeFile = EncodeFile;
    m_DecodeFile = DecodeFile;
}

void CGrayZip::copy()
{
    QFile::copy(QString::fromStdString(m_EncodeFile),QString::fromStdString(m_DecodeFile));
}

//压缩
void CGrayZip::Compress(QProgressBar* bar)
{
    //读取原位图文件
    FileIn.open(m_EncodeFile.c_str(), ios::in | ios::binary);

    FileIn.read((char*)&bmFH, sizeof(bmFH));
    FileIn.read((char*)&bmIH, sizeof(bmIH));
    FileIn.read((char*)&rgbp,sizeof(RGBQUAD)* 256);

    bar->setValue(10);
    bar->show();

    int width = bmIH.biWidth;
    width = (width * bmIH.biBitCount / 8 + 3) / 4 * 4;		//图像每一行的字节数必须是4的整数倍
    int height = bmIH.biHeight;
    unsigned int len = width*height;

    int i, j, k;
    bitMapInfo2D = new unsigned char*[height];
    for (k = 0; k < height; k++)
        bitMapInfo2D[k] = new unsigned char[width];
    bitMapInfo1D = new unsigned char[len + 1];
    for (i = 0; i < height; i++)
    for (j = 0; j < width; j++)
        FileIn.read((char*)&bitMapInfo2D[i][j], sizeof(unsigned char));
    FileIn.close();

    bar->setValue(20);
    //处理读取到的数据
    j = 0, k = 1;
    for (i = 0; i < height; i++)
    {
        if (j == 0)
        {
            for (; j < width; j++)
                bitMapInfo1D[k++] = bitMapInfo2D[i][j];
            j--;
        }
        else
        {
            for (; j >= 0; j--)
                bitMapInfo1D[k++] = bitMapInfo2D[i][j];
            j++;
        }
    }

    int *minBits;		//每段存储的最小位数
    int *tempStore;		//临时存储，将逆向求解的段长度lS再逆向过来
    int header = 11;
    int maxLS = 256;		//最大段的长度为256，更长时就从256处拆分
    len = width*height;
    lS = new unsigned int[len + 1];		//段长度
    bS = new unsigned int[len + 1];		//段位数
    minBits = new int[len + 1];
    tempStore = new int[len + 1];		//存储最优分段的段长，为逆向存储
    minBits[0] = 0;         //minBits[i]代表前i个元素压缩后存储所需的最少位数

    bar->setValue(50);
    //动态规划寻找最优分段策略
    for (i = 1; i <= len; i++)
    {
        //int lsum = 1;
        bS[i] = validBits(int(bitMapInfo1D[i]));           //记录第i个元素的有效长度b[i]
        int bmax = bS[i];
        minBits[i] = minBits[i - 1] + bmax;                 //当前压缩后存储所需最少位数(不包括11位的段标题）
        lS[i] = 1;	//当前像素段为1
        int k;		//当i大于256时，k最大只能取256
        if (i > maxLS)
            k = maxLS;
        else
            k = i;
        for (int j = 2; j <= k; j++)
        {
            //枚举当前第i位和它之前的总计j个元素组成一段的最小位数
            if (bmax<bS[i - j + 1])           //当前位数小于之前的位数
                bmax = bS[i - j + 1];
            if (minBits[i]>minBits[i - j] + j*bmax)
            {
                //当前存储所需的位数大于 当前元素与之前组成一段后的所需位数
                minBits[i] = minBits[i - j] + j*bmax;
                lS[i] = j;		//j段像素
            }
        }
        minBits[i] = minBits[i] + header;    //当前的最优解加上段标题的11位
    }

    bar->setValue(75);
    //反向求解最优分段的段长
    int Segment = 1;
    int n = len;
    while (n > 0)
    {
        tempStore[Segment++] = lS[n];
        n -= lS[n];
    }
    //逆置数组lS
    for (int k = 1; k < Segment; k++)
        lS[k] = tempStore[Segment - k];

    //将压缩后的数据写入文件
    FileOut.open(m_DecodeFile.c_str(), ios::out | ios::binary);
    //压缩文件信息头
    FileOut.write((char*)&bmFH, sizeof(bmFH));
    FileOut.write((char*)&bmIH, sizeof(bmIH));
    FileOut.write((char*)&rgbp, sizeof(RGBQUAD)*256);

    bar->setValue(85);
    //压缩bmp数据
    unsigned int writeBuffer = 0, bitCount = 0;
    i = 1;
    unsigned int temp = 0;
    int bits, L;
    for (int k = 1; k < Segment; k++)
    {
        //写入8位像素分段数
        lS[k] -= 1;		//以二进制写入文件时，lS[k]只能表示0-7,bS[k]只能表示0-2;
        bS[k] -= 1;
        bits = 8;
        if (bitCount + bits < 32)
        {
            writeBuffer <<= bits;
            writeBuffer |= lS[k];
            bitCount += bits;
        }
        else if (bitCount + bits == 32)
        {
            writeBuffer <<= bits;
            writeBuffer |= lS[k];
            FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
            writeBuffer = 0;
            bitCount = 0;
        }
        else
        {
            temp = lS[k];
            writeBuffer <<= (32 - bitCount);
            temp >>= bits - (32 - bitCount);
            writeBuffer |= temp;
            FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
            writeBuffer = (lS[k] << (64 - bits - bitCount)) >> (64 - bits - bitCount);
            bitCount = bits - (32 - bitCount);
        }
        //写入3位像素分段位数
        bits = 3;
        if (bitCount + bits < 32)
        {
            writeBuffer <<= bits;
            writeBuffer |= bS[k];
            bitCount += bits;
        }
        else if (bitCount + bits == 32)
        {
            writeBuffer <<= bits;
            writeBuffer |= bS[k];
            FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
            writeBuffer = 0;
            bitCount = 0;
        }
        else
        {
            temp = bS[k];
            writeBuffer <<= (32 - bitCount);
            temp >>= bits - (32 - bitCount);
            writeBuffer |= temp;
            FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
            writeBuffer = (bS[k] << (64 - bits - bitCount)) >> (64 - bits - bitCount);
            bitCount = bits - (32 - bitCount);
        }
        //写入压缩后的像素数据
        L = i + lS[k] + 1;
        for (; i < L; i++)
        {
            bits = bS[k] + 1;
            if (bitCount + bits < 32)
            {
                writeBuffer <<= bits;
                writeBuffer |= int(bitMapInfo1D[i]);
                bitCount += bits;
            }
            else if (bitCount + bits == 32)
            {
                writeBuffer <<= bits;
                writeBuffer |= int(bitMapInfo1D[i]);
                FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
                writeBuffer = 0;
                bitCount = 0;
            }
            else
            {
                temp = int(bitMapInfo1D[i]);
                writeBuffer <<= (32 - bitCount);
                temp >>= bits - (32 - bitCount);
                writeBuffer |= temp;
                FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
                writeBuffer = (int(bitMapInfo1D[i]) << (64 - bits - bitCount)) >> (64 - bits - bitCount);
                bitCount = bits - (32 - bitCount);
            }
        }
    }
    if (bitCount > 0)		//不足32位的补0
    {
        writeBuffer <<= 32 - bitCount;
        FileOut.write((char*)&writeBuffer, sizeof(unsigned int));
    }
    FileOut.close();

    bar->setValue(100);
    delete[]tempStore;
    delete[]minBits;
    delete[]bS;
    delete[]lS;
    delete[]bitMapInfo1D;
    for (int k = 0; k < height; k++)
        delete[]bitMapInfo2D[k];
    delete[]bitMapInfo2D;
}

int CGrayZip::validBits(int Integer)
{
    int bits = 1;
    while (Integer>1)
    {
        Integer /= 2;
        bits++;
    }
    return bits;
}

//解压
void CGrayZip::UnCompress(QProgressBar* bar)
{

    //m_DecodeFile = DecodeFile;
    //从压缩文件中读取被解压的数据
    FileIn.open(m_EncodeFile.c_str(), ios::in | ios::binary);
    //从压缩文件中读取bmp文件信息头
    FileIn.read((char*)&bmFH, sizeof(bmFH));
    FileIn.read((char*)&bmIH, sizeof(bmIH));
    FileIn.read((char*)&rgbp, sizeof(RGBQUAD)*256);

    int width = bmIH.biWidth;
    width = (width * (bmIH.biBitCount) / 8 + 3) / 4 * 4;		//图像每一行的字节数必须是4的整数倍
    int height = bmIH.biHeight;
    unsigned int len = width*height;
    unsigned int readBuffer = 0;
    int i = 1, bitCount = 0;

    bar->setValue(10);
    //将压缩后的数据写入文件
    unsigned int temp = 0;
    int bits, L;
    bitMapInfo2D = new unsigned char*[height];
    int k;
    for (k = 0; k < height; k++)
        bitMapInfo2D[k] = new unsigned char[width];
    lS = new unsigned int[len + 1];
    bS = new unsigned int[len + 1];
    bitMapInfo1D = new unsigned char[len + 1];
    bar->setValue(30);

    QString encode = QString::fromStdString(m_EncodeFile);
    QString decode = QString::fromStdString(m_DecodeFile);
    encode.remove(encode.indexOf('_'),9);
    QFile::copy(encode,decode);
    k = 1;
    bool k0=0;
    while (!FileIn.eof())
    {
        //先读取段长，再读取段长中的位数，最后读取位图信息
        if (bitCount == 0)
        {
            FileIn.read((char*)&readBuffer, sizeof(unsigned int));
            bitCount = 32;
        }
        if (bitCount > 0 && readBuffer == 0)
            break;		//读取到了末尾补零处，退出循环
        //读取8位段长
        bits = 8;
        if (bitCount - bits > 0)
        {
            temp = readBuffer;
            lS[k] = temp >> (bitCount - bits);
            bitCount -= bits;
            readBuffer = (readBuffer << (32 - bitCount)) >> (32 - bitCount);
        }
        else if (bitCount - bits == 0)
        {
            lS[k] = readBuffer;
            FileIn.read((char*)&readBuffer, sizeof(unsigned int));
            bitCount = 32;
        }
        else
        {
            lS[k] = readBuffer;
            lS[k] <<= (bits - bitCount);
            FileIn.read((char*)&readBuffer, sizeof(unsigned int));
            temp = readBuffer;
            temp = temp >> (32 - bits + bitCount);
            lS[k] |= temp;
            readBuffer = (readBuffer << (bits - bitCount)) >> (bits - bitCount);
            bitCount = 32 - bits + bitCount;
        }

        bar->setValue(50);

        FileOut.open(m_DecodeFile.c_str(), ios::out | ios::binary);
       //读取该段的位数，将二进制转换为unsigned int bS
        bits = 3;
        if (bitCount - bits > 0)
        {
            temp = readBuffer;
            bS[k] = temp >> (bitCount - bits);
            bitCount -= bits;
            readBuffer = (readBuffer << (32 - bitCount)) >> (32 - bitCount);
        }
        else if (bitCount - bits == 0)
        {
            bS[k] = readBuffer;
            FileIn.read((char*)&readBuffer, sizeof(unsigned int));
            bitCount = 32;
        }
        else
        {
            bS[k] = readBuffer;
            bS[k] <<= (bits - bitCount);
            FileIn.read((char*)&readBuffer, sizeof(unsigned int));
            temp = readBuffer;
            temp = temp >> (32 - bits + bitCount);
            bS[k] |= temp;
            readBuffer = (readBuffer << (bits - bitCount)) >> (bits - bitCount);
            bitCount = 32 - bits + bitCount;
        }
        if(k0)
        {

            FileOut.write((char*)&bmFH, sizeof(bmFH));
            FileOut.write((char*)&bmIH, sizeof(bmIH));
            FileOut.write((char*)&rgbp, sizeof(RGBQUAD)*256);
        }
        bar->setValue(60);

        //读取bmp压缩数据
        bits = bS[k] + 1;		//数据的位数
        L = i + lS[k] + 1;
        for (; i < L; i++)
        {
            if (bitCount>bits)
            {
                temp = readBuffer;
                bitMapInfo1D[i] = temp >> (bitCount - bits);
                bitCount -= bits;
                readBuffer = (readBuffer << (32 - bitCount)) >> (32 - bitCount);
            }
            else if (bitCount == bits)
            {
                bitMapInfo1D[i] = readBuffer;
                bitCount = 32;
                FileIn.read((char*)&readBuffer, sizeof(unsigned int));
            }
            else
            {
                bitMapInfo1D[i] = readBuffer;
                bitMapInfo1D[i] <<= (bits - bitCount);
                FileIn.read((char*)&readBuffer, sizeof(unsigned int));
                temp = readBuffer;
                temp = temp >> (32 - bits + bitCount);
                bitMapInfo1D[i] |= temp;
                readBuffer = (readBuffer << (bits - bitCount)) >> (bits - bitCount);
                bitCount = 32 - bits + bitCount;
            }
        }
        k++;
    }
    FileIn.close();

    bar->setValue(85);
    int j = 0;
    k = 1;
    for (i = 0; i < height; i++)
    {
        if (j == 0)
        {
            for (; j < width; j++)
                bitMapInfo2D[i][j] = bitMapInfo1D[k++];
            j--;
        }
        else
        {
            for (; j >= 0; j--)
                bitMapInfo2D[i][j] = bitMapInfo1D[k++];
            j++;
        }
    }

    FileOut.close();

    bar->setValue(100);

    //释放动态申请的空间
    delete[]bitMapInfo1D;
    delete[]bS;
    delete[]lS;
    for (int k = 0; k < height; k++)
        delete[]bitMapInfo2D[k];
    delete[]bitMapInfo2D;
}

CGrayZip::~CGrayZip()
{

}

