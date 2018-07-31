#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ui->progressBar->setVisible(false);
    connect(&huffman,SIGNAL(message(QString)),this,SLOT(message(QString)));
    connect(&huffman,SIGNAL(error(QString)),this,SLOT(error(QString)));
    connect(&huffman,SIGNAL(progress(int)),ui->progressBar,SLOT(setValue(int)));
    ui->lineEdit_PassWord->setDisabled(true);
    ui->pushButton_Compress->setDisabled(true);
    ui->pushButton_Decompress->setDisabled(true);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_OpenFile_clicked()
{
    QString fileName=QFileDialog::getOpenFileName(this,tr("Open File"), "/", "");
    ui->label_FilePath->setText(fileName);
    QFile file;
    file.setFileName(fileName);
    file.open(QIODevice::ReadOnly);
    QByteArray header=file.read(4);
    if(header==huffman.GetCompressHeader())
    {
        this->message("A huffman file is selected! You can decompress it!");
        fileName=fileName.mid(0,fileName.lastIndexOf("."));
        ui->lineEdit->setText(fileName+".dec.txt");
        ui->pushButton_Decompress->setEnabled(true);
        ui->pushButton_Compress->setDisabled(true);
        ui->checkBox->setChecked(false);
        ui->lineEdit_PassWord->setDisabled(true);
    }
    else if(header==huffman.GetEncryptHeader())
    {
        this->message("An encrypted huffman file is selected! You can decompress it with password!");
        fileName=fileName.mid(0,fileName.lastIndexOf("."));
        ui->lineEdit->setText(fileName+".dec.txt");
        ui->pushButton_Decompress->setEnabled(true);
        ui->pushButton_Compress->setDisabled(true);
        QString passWord= QInputDialog::getText(this, "Please input password!","An encrypted huffman file is selected!\nPlease input password:", QLineEdit::Password);
        ui->checkBox->setChecked(true);
        ui->lineEdit_PassWord->setEnabled(true);
        ui->lineEdit_PassWord->setText(passWord);
        QByteArray hash= QCryptographicHash::hash(passWord.toLocal8Bit(),QCryptographicHash::Md5);
        header=file.read(16);
        if(hash!=header)
        {
            emit error("wrong password!");
        }
    }
    else
    {
        this->message("A normal file is selected! You can compress it!");
        fileName=fileName.mid(0,fileName.lastIndexOf("."));
        ui->lineEdit->setText(fileName+".huf");
        ui->pushButton_Compress->setEnabled(true);
        ui->pushButton_Decompress->setDisabled(true);
    }
    file.close();
}

void MainWindow::message(QString m)
{
    ui->textBrowser->append(m);
}
void MainWindow::error(QString m)
{
    QMessageBox::warning(this,"error",m);
    this->message("task failed!");

}

void MainWindow::on_pushButton_Compress_clicked()
{
    if(check())
    {
        this->message("start to Compress!");
        ui->progressBar->setVisible(true);
        ui->progressBar->setValue(0);
        huffman.Compress(ui->label_FilePath->text(),ui->lineEdit->text(),ui->lineEdit_PassWord->text(),ui->checkBox->isChecked());
    }
}

void MainWindow::on_pushButton_Decompress_clicked()
{
    if(check())
    {


        this->message("start to Decompress!");
        ui->progressBar->setVisible(true);
        ui->progressBar->setValue(0);
        huffman.Decompress(ui->label_FilePath->text(),ui->lineEdit->text(),ui->lineEdit_PassWord->text());
    }
}
bool MainWindow::check()
{
    if(ui->label_FilePath->text().isEmpty())
    {
        this->error("file name can't be empty!");
        return false;
    }
    return true;
}

void MainWindow::on_pushButton_Save_clicked()
{
    QString fileName=QFileDialog::getSaveFileName(this,tr("Save File"), "/", ".huf");
    ui->lineEdit->setText(fileName+".huf");
}

void MainWindow::on_checkBox_clicked(bool checked)
{
    ui->lineEdit_PassWord->setEnabled(checked);
}
