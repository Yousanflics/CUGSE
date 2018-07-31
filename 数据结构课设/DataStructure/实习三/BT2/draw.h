#ifndef DRAW_H
#define DRAW_H

#include "DS/BST.h"
#include <QWidget>
#include <QPaintEvent>

class DrawWidget : public QWidget
{
    Q_OBJECT
public:
    BST<int> *bst;

    DrawWidget(QWidget *p = 0);
    void paintEvent(QPaintEvent *);
};

#endif // DRAW_H
