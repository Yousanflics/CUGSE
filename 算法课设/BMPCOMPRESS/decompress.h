#ifndef DECOMPRESS_H
#define DECOMPRESS_H

#include <QWidget>
#include <QString>
#include <QMessageBox>
#include <QProgressBar>

namespace Ui {
class DeCompress;
}

class DeCompress : public QWidget
{
    Q_OBJECT

public:
    explicit DeCompress(QWidget *parent = 0);
    ~DeCompress();

private slots:
    void on_deopen_btn_clicked();

    void on_deCompress_btn_clicked();

private:
    Ui::DeCompress *ui;

private:
    QString m_NameIn, m_NameOut;
    QProgressBar* m_bar;

};

#endif // DECOMPRESS_H
