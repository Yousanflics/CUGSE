#include "mainwindow.h"

#include <QHBoxLayout>
#include <QVBoxLayout>

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
{
    elem = new QLineEdit(this);
    insert = new QPushButton(this);
    insert->setText("Insert");
    connect(insert, SIGNAL(clicked()), this, SLOT(insertclicked()));

    QHBoxLayout * hlayout1 = new QHBoxLayout;
    hlayout1->addWidget(elem);
    hlayout1->addWidget(insert);


    todelete = new QLineEdit(this);
    buttondelete = new QPushButton(this);
    buttondelete->setText("Remove");
    connect(buttondelete, SIGNAL(clicked()), this, SLOT(deleteclicked()));

    QHBoxLayout * hlayout2 = new QHBoxLayout;
    hlayout1->addWidget(todelete);
    hlayout1->addWidget(buttondelete);


    QVBoxLayout *vlayout = new QVBoxLayout;
    d = new DrawWidget(this);
    vlayout->addLayout(hlayout1);
    vlayout->addLayout(hlayout2);
    vlayout->addWidget(d);

    QWidget *widget = new QWidget(this);
    widget->setLayout(vlayout);

    this->setCentralWidget(widget);
}

MainWindow::~MainWindow()
{

}

void MainWindow::insertclicked()
{
    QString num = elem->text();
    int n = num.toInt();
    d->bst->insert(n);
    d->update();
}
void MainWindow::deleteclicked()
{
    QString num = todelete->text();
    int n = num.toInt();
    d->bst->remove(n);
    d->update();
}
