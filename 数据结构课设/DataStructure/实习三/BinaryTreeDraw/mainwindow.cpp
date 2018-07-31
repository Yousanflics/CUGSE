#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QQueue>
#include <qmath.h>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow),
    vis(false)
{
    ui->setupUi(this);
    dia = new Dialog1(parent);
    connect(dia , SIGNAL(paintTree(BinTree<char>*)) , this , SLOT(Paint(BinTree<char>*)));
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_actionQuestion1_triggered()
{
    dia->show();
}

void MainWindow::paintEvent(QPaintEvent *event)
{
    if(!vis)return;
    int size = painttree->height()+1;
    int y = this->height()/size;
    int x = this->width()/pow(2.0,(size-1)*1.0);
    int ell_x_size = x/3,ell_y_size = y/5;

    QQueue<BinTreeNode<char> *> q;
    QQueue<point> p;
    q.enqueue(painttree->getroot());
    point cur(this->width()/2 ,ell_y_size+50);
    p.enqueue(cur);

    int cnum = 1,fusize = 1,nnum =0;

    while(!q.empty()){
        BinTreeNode<char> *current = q.dequeue();
        point currentpt = p.dequeue();
        cnum--;
        if(current->lChild){
            int next_x = currentpt.x - (x/2*(pow(2.0,size-fusize-1)));
            int next_y = currentpt.y + y;
            point nextpt(next_x,next_y);
            p.enqueue(nextpt);
            q.enqueue(current->lChild);
            drawLine(currentpt,nextpt);
            nnum++;
        }else{
            int next_x = currentpt.x - (x/2*(pow(2.0,size-fusize-1)));
            int next_y = currentpt.y + y;
            QPainter painter(this);
            QPen pen(Qt::red,3,Qt::DashLine,Qt::RoundCap,Qt::RoundJoin);
            painter.setPen(pen);
            painter.drawLine(QPoint(next_x,next_y),QPoint(currentpt.x,currentpt.y));
            painter.setBrush(Qt::green);
            painter.drawEllipse(QPoint(next_x,next_y),ell_x_size,ell_x_size);
            drawText("#",next_x-ell_x_size,next_y-ell_x_size,2*ell_x_size);
            painter.end();
        }
        if(current->rChild){
            int next_x = currentpt.x + (x/2*(pow(2.0,size-fusize-1)));
            int next_y = currentpt.y + y;
            point nextpt(next_x,next_y);
            p.enqueue(nextpt);
            q.enqueue(current->rChild);
            drawLine(currentpt,nextpt);
            nnum++;
        }else{
            int next_x = currentpt.x + (x/2*(pow(2.0,size-fusize-1)));
            int next_y = currentpt.y + y;
            QPainter painter(this);
            QPen pen(Qt::red,3,Qt::DashLine,Qt::RoundCap,Qt::RoundJoin);
            painter.setPen(pen);
            painter.drawLine(QPoint(next_x,next_y),QPoint(currentpt.x,currentpt.y));
            painter.setBrush(Qt::green);
            painter.drawEllipse(QPoint(next_x,next_y),ell_x_size,ell_x_size);
            drawText("#",next_x-ell_x_size,next_y-ell_x_size,2*ell_x_size);
            painter.end();
        }

        drawellipse(currentpt.x,currentpt.y,ell_x_size,ell_x_size);
        drawText(change_to_qs(current->data),currentpt.x-ell_x_size,currentpt.y-ell_x_size,2*ell_x_size);

        if(!cnum){
            cnum = nnum;
            nnum = 0;
            fusize++;
        }
    }
}

void MainWindow::drawLine(point pt1, point pt2)
{
    QPainter painter(this);
    QPen pen(Qt::blue,3,Qt::SolidLine,Qt::RoundCap,Qt::RoundJoin);
    painter.setPen(pen);
    painter.drawLine(QPoint(pt1.x,pt1.y),QPoint(pt2.x,pt2.y));
    painter.end();
}

void MainWindow::drawellipse(int x,int y, int x_size, int y_size)
{
    QPainter painter(this);
    painter.setPen(Qt::black);
    painter.setBrush(Qt::yellow);
    painter.drawEllipse(QPoint(x,y),x_size,y_size);
    painter.end();
}

void MainWindow::drawText(QString m, int x,int y,int len)
{
    QRect rect(x,y,len,len);
    QFont font(m,len/2,QFont::Bold,false);
    QPainter painter(this);
    painter.setFont(font);
    painter.setPen(Qt::black);
    painter.drawText(rect,Qt::AlignHCenter,m);
    painter.end();
}

QString MainWindow::change_to_qs(char m)
{
    char fu[2];fu[0]= m;fu[1]='\0';
    return QString("%1").arg(fu);
}

void MainWindow::Paint(BinTree<char> *p)
{
    painttree = p;
    vis = true;
    this->update();

}
