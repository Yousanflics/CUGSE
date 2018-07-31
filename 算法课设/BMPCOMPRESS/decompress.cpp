#include "decompress.h"
#include "ui_decompress.h"
#include <QFileDialog>
#include "graph.h"

DeCompress::DeCompress(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::DeCompress)
{
    ui->setupUi(this);
    m_bar = new QProgressBar(this);
    m_bar->setValue(0);
}

DeCompress::~DeCompress()
{
    delete ui;
}

void DeCompress::on_deopen_btn_clicked()
{
    QString path = QFileDialog::getOpenFileName(this,"选择文件","/home","image(*.bmp)");
        if (path!=NULL)
        {
            m_NameIn = path;
            m_NameOut = m_NameIn;
            m_NameOut.insert(m_NameIn.indexOf('.'), tr("_decompress"));
        }
        else
            QMessageBox::information(this,tr("notice"), tr("error"), QMessageBox::Ok);
}

void DeCompress::on_deCompress_btn_clicked()
{
    if(!m_NameIn.size())
        QMessageBox::information(this,tr("please input the path of file!"), tr("notice"), QMessageBox::Ok);

    m_bar->setRange(0,100);

    QString In=m_NameIn;
    In.remove(m_NameIn.indexOf('_'),9);
    QMessageBox::information(this,"####",In);
    CGrayZip myCom(m_NameIn.toStdString(),m_NameOut.toStdString());

    myCom.UnCompress(m_bar);

    QMessageBox::information(this,tr("notice"), tr("success!"), QMessageBox::Ok);

}
