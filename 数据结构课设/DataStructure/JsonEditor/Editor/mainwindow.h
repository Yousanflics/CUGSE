#ifndef MAINWINDOW_H
#define MAINWINDOW_H

//#if QT_VERSION >= 0x050000
#include <QWidget>
#include<QMainWindow>
//#else
//#include <QtGui/QMainWindow>
//#endif

#include <QFileDialog>
#include <QTreeWidgetItem>
#include <QDebug>
#include <QFile>

#include "syntaxhighlighter.h"

#include "../Json/json.h"


namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

protected:
    void changeEvent(QEvent *e);

private slots:
    void on_action_Open_triggered();

    void on_cmbInputFile_currentIndexChanged(int index);

    void on_pushButton_clicked();

    void on_txtJson_textChanged();

    void on_action_Save_triggered();

private:
    Ui::MainWindow *ui;
    QList<QTreeWidgetItem *>  createConfigTree(QMap<QString, QVariant> tree);

    Json iJson;
    QMap<QString, QVariant> jSonParsed;
    SyntaxHighlighter* iSyntaxHighlighter;
};

#endif // MAINWINDOW_H
