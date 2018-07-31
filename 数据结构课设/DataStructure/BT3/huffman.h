#ifndef HUFFMAN_H
#define HUFFMAN_H
#include <QThread>
#include <QFile>
#include <QMessageBox>
#include <QMap>
#include <QCryptographicHash>
#include <QInputDialog>
#include <hufftree.h>
#include <QVector>
#include <QDebug>
class Huffman : public QThread
{
    Q_OBJECT
public:
    explicit Huffman(QThread *parent = 0);
    void Compress(QString comFile,QString after,QString passWord="",bool needPass=false);
    void Decompress(QString decomFile, QString after,QString passWord);
    void run();
    const QByteArray GetCompressHeader(){return CompressHeader;}
    const QByteArray GetEncryptHeader(){return EncryptHeader;}
signals:
    void message(QString);
    void error(QString);
    void progress(int);
public slots:
private:
    bool CheckFile();
    void ComAnalyse();
    void DecomAnalyse();
    void GenerateCompressFile();
    void GenerateDecompressFile(QByteArray content, int zero);
    void EncryptOrDecrypt(QByteArray &content, QByteArray &pass, int &posOfPass);
    bool TryToDecode(QByteArray &in, int &start, int length, QByteArray &out);
    QByteArray formatCodeMap(QByteArray s);
    QFile inFile,outFile;
    QMap <char,int> statisticMap;
    QMap <char,QByteArray> codeMap;
    QVector<QMap <QByteArray,char> *> decodeMap;
    QVector<int> decodeBitMap;
    HuffTree tree;
    bool isCompress;
    QByteArray CompressHeader;
    QByteArray EncryptHeader;
    QString passWord;
    bool needPass;
    int mask[8];
};

#endif // HUFFMAN_H
