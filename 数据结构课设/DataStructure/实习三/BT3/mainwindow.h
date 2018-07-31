#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QString>
#include <QFileDialog>
#include <QMessageBox>
#include <huffman.h>
#include <QFile>
#include <QCryptographicHash>

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
    void on_pushButton_OpenFile_clicked();
    void on_pushButton_Compress_clicked();
    void on_pushButton_Decompress_clicked();
    void message(QString);
    void error(QString);
    void on_pushButton_Save_clicked();
    void on_checkBox_clicked(bool checked);

private:
    bool check();
    Ui::MainWindow *ui;
    Huffman huffman;
};

#endif // MAINWINDOW_H
