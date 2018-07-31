#include "draw.h"

#include <QPainter>

DrawWidget::DrawWidget(QWidget *p):QWidget(p)
{
    bst = new BST<int>;
}

void DrawWidget::paintEvent(QPaintEvent *)
{
    QPainter painter(this);
    if (!bst->empty())
    {
        bst->display(); //调用命令行显示 从而计算结点位置

        //层次遍历第一遍 画线
        Queue<BinNodePosi(int)> q;
        BinNodePosi(int) x = bst->root();

        q.enqueue(x);
        while (!q.empty())
        {
            x = q.dequeue();
            if (x)
            {
                if (x && x->parent)
                    painter.drawLine(30*x->horizontal_position+30, 30+100 * x->distance_to_root, 30*x->parent->horizontal_position+30, 30+100 * x->parent->distance_to_root);
            }
            if (x->lchild) q.enqueue(x->lchild);
            if (x->rchild) q.enqueue(x->rchild);
        }

        //层次遍历第二遍 画结点
        x = bst->root();
        q.enqueue(x);
        while (!q.empty())
        {
            x = q.dequeue();
            if (x)
            {
                painter.setBrush(Qt::yellow);
                painter.drawEllipse(30*x->horizontal_position, 100 * x->distance_to_root, 60, 60);
                painter.drawText(30*x->horizontal_position+20, 100 * x->distance_to_root+30, QString::number(x->data));
            }
            if (x->lchild) q.enqueue(x->lchild);
            if (x->rchild) q.enqueue(x->rchild);
        }
    }

}
