#-------------------------------------------------
#
# Project created by QtCreator 
#
#-------------------------------------------------

QT       += core gui

greaterThan(QT_MAJOR_VERSION, 4): QT += widgets

TARGET = HuffmanCompress
TEMPLATE = app


SOURCES += main.cpp\
        mainwindow.cpp \
    huffman.cpp \
    hufftree.cpp

HEADERS  += mainwindow.h \
    huffman.h \
    hufftree.h

FORMS    += mainwindow.ui
