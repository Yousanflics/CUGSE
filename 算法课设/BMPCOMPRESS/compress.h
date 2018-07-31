#ifndef COMPRESS_H
#define COMPRESS_H

#include <QWidget>
#include <QString>
#include <QProgressBar>
#include <QMessageBox>

namespace Ui {
class Compress;
}

class Compress : public QWidget
{
    Q_OBJECT

public:
    explicit Compress(QWidget *parent = 0);
    ~Compress();

private slots:
    void on_open_btn_clicked();

    void on_compress_btn_clicked();

private:
    Ui::Compress *ui;

private:
    QString m_DeIn, m_DeOut;
    QProgressBar* m_bar;

};

#endif // COMPRESS_H
