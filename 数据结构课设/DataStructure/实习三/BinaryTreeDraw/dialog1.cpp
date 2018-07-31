#include "dialog1.h"
#include "ui_dialog1.h"
#include <QDebug>
#include <QLayout>
#include <fstream>
#include <sstream>
using namespace std;

Dialog1::Dialog1(QWidget *parent) :
    QDialog(parent),
    ui(new Ui::Dialog1),
    Ans(""),
    str("")
{
    ui->setupUi(this);
    ui->groupBox_3->hide();
    layout()->setSizeConstraint(QLayout::SetFixedSize);

    tree = new BinTree<char>('#');
    ui->Find->setEnabled(false);
    ui->Swap->setEnabled(false);
    ui->Output->setEnabled(false);
    ui->comboBox->setEnabled(false);

    connect(ui->Create , SIGNAL(clicked(bool)) , this , SLOT(CreateTree()));
    connect(ui->Swap , SIGNAL(clicked(bool)) , this , SLOT(SwapTree()));
    connect(ui->Output , SIGNAL(clicked(bool)) ,this , SLOT(OutputAns()));
    connect(ui->getNewTree , SIGNAL(clicked(bool)) , this , SLOT(GetNewTree()));
    connect(ui->apply , SIGNAL(clicked(bool)) , this , SLOT(BuildNewT()));
    connect(ui->get , SIGNAL(clicked(bool)) , this , SLOT(GetCurData()));
    connect(ui->paint , SIGNAL(clicked(bool)) , this , SLOT(paint()));
    connect(ui->Find , SIGNAL(clicked(bool)) , this , SLOT(find()));
}

Dialog1::~Dialog1()
{
    delete ui;
}

void Dialog1::CreateTree()
{
    str = ui->Input->text();
    QByteArray ba = str.toLatin1();
    char *pp = ba.data();
    int vis = 0;
    tree->CreateBinTree(pp,vis,strlen(pp));
    ui->Find->setEnabled(true);
    ui->Swap->setEnabled(true);
    ui->Output->setEnabled(true);
    ui->comboBox->setEnabled(true);
}

void Dialog1::SwapTree()
{
    tree->SwapTree();
}

void Dialog1::OutputAns()
{
    fstream output("fu.txt",ios::out);
    output.close();
    QString item = ui->comboBox->currentText();
    JudgeOn(item);
    fstream input("fu.txt",ios::in);
    char *p = new char(sizeof(input));
    input>>p;
    Ans = QString("%1").arg(p);
    input.close();
    ui->Out->setText(Ans);
}

void Dialog1::opea(BinTreeNode<char> *subTree){
    fstream output("fu.txt",ios::out|ios::app);
    output<<subTree->data;
    output.close();
}

void Dialog1::GetCurData()
{
    fstream output("fu.txt",ios::out);
    output.close();
    tree->preorder(opea);
    fstream input("fu.txt",ios::in);
    char *p = new char(sizeof(input));
    input>>p;
    QString mm = QString("%1").arg(p);
    input.close();
    ui->lineEdit->setText(mm);

    fstream output2("fu.txt",ios::out);
    output2.close();
    tree->inorder(opea);
    fstream input2("fu.txt",ios::in);
    char *q = new char(sizeof(input2));
    input2>>q;
    mm = QString("%1").arg(q);
    input2.close();
    ui->lineEdit_2->setText(mm);
}

void Dialog1::paint()
{
    emit paintTree(tree);
}

void Dialog1::find()
{
    QString fin = ui->findnum->text();
    QByteArray ba = fin.toLatin1();
    char *pp = ba.data();
    char m = pp[0];

    fstream output("fu.txt",ios::out);
    output.close();
    tree->Find(m,opea);
    fstream input("fu.txt",ios::in);
    char *p = new char(sizeof(input));
    input>>p;
    QString mm = QString("%1").arg(p);
    input.close();
    ui->Out->setText(mm);
}

void Dialog1::JudgeOn(QString x)
{
    if(x=="前序遍历"){
        tree->preorder(opea);
    }
    else if(x == "中序遍历"){
        tree->inorder(opea);
    }
    else if(x=="后序遍历"){
        tree->postorder(opea);
    }
    else {

    }
}

void Dialog1::GetNewTree()
{
    if(ui->groupBox_3->isVisible()){
        ui->groupBox_3->hide();
        ui->getNewTree->setText(">>>");
    }
    else{
        ui->groupBox_3->show();
        ui->getNewTree->setText("<<<");
    }
}

void Dialog1::BuildNewT()
{
    QString x = ui->lineEdit->text();
    QString y = ui->lineEdit_2->text();
    QByteArray ba = x.toLatin1();
    char *xx = ba.data();
    QByteArray b = y.toLatin1();
    char *yy = b.data();
    tree->GetTree(xx,yy);
}


