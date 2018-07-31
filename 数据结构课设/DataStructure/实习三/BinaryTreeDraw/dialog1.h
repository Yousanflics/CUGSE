#ifndef DIALOG1_H
#define DIALOG1_H

#include "bintree.h"
#include <QString>
#include <QDialog>

namespace Ui {
class Dialog1;
}

class Dialog1 : public QDialog
{
    Q_OBJECT

public:
    explicit Dialog1(QWidget *parent = 0);
    ~Dialog1();
signals:
    void paintTree(BinTree<char> *p);
private slots:
    void CreateTree();
    void SwapTree();
    void OutputAns();
    void JudgeOn(QString x);
    void GetNewTree();
    void BuildNewT();
    static void opea(BinTreeNode<char> *subTree);
    void GetCurData();
    void paint();
    void find();

private:
    Ui::Dialog1 *ui;
    QString str;
    QString Ans;
    BinTree<char> *tree;
};

#endif // DIALOG1_H
