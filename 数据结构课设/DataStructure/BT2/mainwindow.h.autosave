#ifndef MAINWINDOW_H
#define MAINWINDOW_H
#include <QMainWindow>
#include <QLineEdit>
#include <QPushButton>

#include "draw.h"

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    QLineEdit * elem;
    QPushButton *insert;

    QLineEdit * todelete;
    QPushButton *buttondelete;


    DrawWidget *d;
    MainWindow(QWidget *parent = 0);
    ~MainWindow();
public slots:
    void insertclicked();
    void deleteclicked();
};

#endif // MAINWINDOW_H
