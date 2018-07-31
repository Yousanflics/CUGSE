#include "compress.h"
#include "ui_compress.h"
#include <QFileDialog>
#include <QGridLayout>
#include "graph.h"

Compress::Compress(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Compress)
{
    ui->setupUi(this);
    m_bar = new QProgressBar(this);
    m_bar->setAlignment(Qt::AlignBottom|Qt::AlignVCenter);
    m_bar->setValue(0);
}

Compress::~Compress()
{
    delete ui;
}

void Compress::on_open_btn_clicked()
{
    QString path = QFileDialog::getOpenFileName(this,"选择文件","/home","image(*.bmp)");
        if (path!=NULL)
        {
            m_DeIn = path;
            m_DeOut = m_DeIn;
            m_DeOut.insert(m_DeIn.indexOf('.'), tr("_compress"));
        }
        else
            QMessageBox::information(this,tr("notice"), tr("error"), QMessageBox::Ok);
}

void Compress::on_compress_btn_clicked()
{
    if(!m_DeIn.size())
        QMessageBox::information(this,tr("please input the path of file!"), tr("notice"), QMessageBox::Ok);

    m_bar->setRange(0,100);
    m_bar->setWindowTitle(tr("progress!"));

    QMessageBox::information(this,"####",m_DeIn);

    CGrayZip myCom(m_DeIn.toStdString(),m_DeOut.toStdString());
    myCom.Compress(m_bar);

    QMessageBox::information(this,tr("notice"), tr("success!"), QMessageBox::Ok);

}
