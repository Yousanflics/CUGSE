
#ifndef COMPRESS_H
#define COMPRESS_H

#include<QProgressBar>
#include<iostream>
#include<fstream>
#include<string>
#include<stack>
using namespace std;

typedef unsigned long       DWORD;
typedef int                 BOOL;
typedef unsigned char       BYTE;
typedef unsigned short      WORD;
typedef float               FLOAT;
typedef long                LONG;

typedef struct tagBITMAPFILEHEADER {
        WORD    bfType;
        DWORD   bfSize;
        WORD    bfReserved1;
        WORD    bfReserved2;
        DWORD   bfOffBits;
} BITMAPFILEHEADER;

typedef struct tagBITMAPINFOHEADER{
        DWORD      biSize;
        LONG       biWidth;
        LONG       biHeight;
        WORD       biPlanes;
        WORD       biBitCount;
        DWORD      biCompression;
        DWORD      biSizeImage;
        LONG       biXPelsPerMeter;
        LONG       biYPelsPerMeter;
        DWORD      biClrUsed;
        DWORD      biClrImportant;
} BITMAPINFOHEADER;

typedef struct tagRGBQUAD {
        BYTE    rgbBlue;
        BYTE    rgbGreen;
        BYTE    rgbRed;
        BYTE    rgbReserved;
} RGBQUAD;

struct Result
{
    unsigned int Len;//每组有Len个元素
    unsigned int Bt;//每个元素占Bt位
};

class MyBitmap
{
protected:
    QProgressBar* m_Progress;


    //文件头，位图头，调色板，位图数据
    BITMAPFILEHEADER FileHeader;
    BITMAPINFOHEADER BitmapHeader;
    RGBQUAD ipRGB[256];
    //存放位图矩阵
    unsigned char * * ImgData;
    //点阵高宽，没有它无法释放矩阵内存
    int Height;
    int Width;
    //存放S型点阵
    unsigned int* dataPoint;
public:
    //压缩分段函数
    void Compress(unsigned int n, unsigned int p[], unsigned int s[], unsigned int l[], unsigned int b[]);
    //压缩函数
    void CompressBitmap(istream& fin, ostream& fout, QProgressBar& m_Progress);
    //求某一数据占多少位
    unsigned int length(int i);
    //解压函数
    void DeCompressBitmap(istream& fin, ostream& fout, QProgressBar& m_Progress);
    //析构函数，用于释放内存
    ~MyBitmap();
};

#endif // COMPRESS_H


