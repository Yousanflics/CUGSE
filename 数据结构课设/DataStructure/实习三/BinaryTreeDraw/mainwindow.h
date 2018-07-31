#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QPainter>
#include "dialog1.h"
#include <QMainWindow>
#include <QString>

struct point{
    int x,y;
    point():x(0),y(0){}
    point(int x1,int y1):x(x1),y(y1){}
};

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
    void on_actionQuestion1_triggered();
    void Paint(BinTree<char> *p);

private:
    Ui::MainWindow *ui;
    Dialog1 *dia;
    char *p;
    BinTree<char> *painttree;
    void paintEvent(QPaintEvent *event);
    bool vis;
    void drawLine(point pt1,point pt2);
    void drawellipse(int x,int y, int x_size, int y_size);
    void drawText(QString m, int x,int y,int len);
    QString change_to_qs(char m);
};

#endif // MAINWINDOW_H
