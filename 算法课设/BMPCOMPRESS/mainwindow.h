#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
class Compress;
class DeCompress;
QT_END_NAMESPACE

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:

    void on_compress_btn_clicked();

    void on_depress_btn_clicked();

private:
    Ui::MainWindow *ui;

private:

    Compress* compressWin;
    DeCompress* decompressWin;

};

#endif // MAINWINDOW_H
