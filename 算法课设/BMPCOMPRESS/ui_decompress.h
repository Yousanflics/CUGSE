/********************************************************************************
** Form generated from reading UI file 'decompress.ui'
**
** Created by: Qt User Interface Compiler version 4.8.6
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DECOMPRESS_H
#define UI_DECOMPRESS_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QPushButton>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_DeCompress
{
public:
    QPushButton *deopen_btn;
    QPushButton *deCompress_btn;

    void setupUi(QWidget *DeCompress)
    {
        if (DeCompress->objectName().isEmpty())
            DeCompress->setObjectName(QString::fromUtf8("DeCompress"));
        DeCompress->resize(675, 460);
        deopen_btn = new QPushButton(DeCompress);
        deopen_btn->setObjectName(QString::fromUtf8("deopen_btn"));
        deopen_btn->setGeometry(QRect(220, 190, 211, 51));
        deCompress_btn = new QPushButton(DeCompress);
        deCompress_btn->setObjectName(QString::fromUtf8("deCompress_btn"));
        deCompress_btn->setGeometry(QRect(220, 300, 211, 51));

        retranslateUi(DeCompress);

        QMetaObject::connectSlotsByName(DeCompress);
    } // setupUi

    void retranslateUi(QWidget *DeCompress)
    {
        DeCompress->setWindowTitle(QApplication::translate("DeCompress", "Form", 0, QApplication::UnicodeUTF8));
        deopen_btn->setText(QApplication::translate("DeCompress", "DeOpen File", 0, QApplication::UnicodeUTF8));
        deCompress_btn->setText(QApplication::translate("DeCompress", "deCompress", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class DeCompress: public Ui_DeCompress {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DECOMPRESS_H
