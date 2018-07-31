#include "mainwindow.h"
#include "ui_mainwindow.h"

#include "compress.h"
#include "decompress.h"


MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    compressWin = new Compress;
    decompressWin = new DeCompress;

}

MainWindow::~MainWindow()
{
    delete ui;
    delete compressWin;
    delete decompressWin;
}

void MainWindow::on_depress_btn_clicked()
{
    decompressWin->show();
}

void MainWindow::on_compress_btn_clicked()
{
    compressWin->show();
}
