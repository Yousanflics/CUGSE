#ifndef GRAPH_H
#define GRAPH_H

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

class CGrayZip
{
private:
    string m_EncodeFile;
    string m_DecodeFile;
    ifstream FileIn;		//input file stream
    ofstream FileOut;		//output file stream
    unsigned char **bitMapInfo2D;		//two dimension bitmap infomation array
    unsigned char *bitMapInfo1D;		//one dimension bitmap information array
    tagBITMAPFILEHEADER bmFH;		//bmp文件头
    tagBITMAPINFOHEADER bmIH;		//bmp信息头
    tagRGBQUAD rgbp[256];		//调色板
    int validBits(int Integer);
    unsigned int *lS, *bS;		//段长，段位
public:
    CGrayZip(string EncodeFile, string DecodeFile);
    ~CGrayZip();
    void Compress(QProgressBar* bar);
    void UnCompress(QProgressBar* bar);
    void copy();
};

#endif // GRAPH_H
