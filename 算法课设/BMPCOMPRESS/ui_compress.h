/********************************************************************************
** Form generated from reading UI file 'compress.ui'
**
** Created by: Qt User Interface Compiler version 4.8.6
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_COMPRESS_H
#define UI_COMPRESS_H

#include <QtCore/QVariant>
#include <QtGui/QAction>
#include <QtGui/QApplication>
#include <QtGui/QButtonGroup>
#include <QtGui/QHeaderView>
#include <QtGui/QLabel>
#include <QtGui/QPushButton>
#include <QtGui/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Compress
{
public:
    QPushButton *open_btn;
    QPushButton *compress_btn;
    QLabel *label;

    void setupUi(QWidget *Compress)
    {
        if (Compress->objectName().isEmpty())
            Compress->setObjectName(QString::fromUtf8("Compress"));
        Compress->resize(671, 457);
        open_btn = new QPushButton(Compress);
        open_btn->setObjectName(QString::fromUtf8("open_btn"));
        open_btn->setGeometry(QRect(220, 210, 201, 51));
        open_btn->setStyleSheet(QString::fromUtf8("color: rgb(0, 0, 0);\n"
"font: 12pt \"\345\215\216\346\226\207\351\232\266\344\271\246\";"));
        compress_btn = new QPushButton(Compress);
        compress_btn->setObjectName(QString::fromUtf8("compress_btn"));
        compress_btn->setGeometry(QRect(220, 310, 201, 51));
        label = new QLabel(Compress);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(50, 40, 431, 81));
        label->setStyleSheet(QString::fromUtf8("color: rgb(255, 170, 0);\n"
"font: 20pt \"\345\215\216\346\226\207\350\241\214\346\245\267\";"));

        retranslateUi(Compress);

        QMetaObject::connectSlotsByName(Compress);
    } // setupUi

    void retranslateUi(QWidget *Compress)
    {
        Compress->setWindowTitle(QApplication::translate("Compress", "Form", 0, QApplication::UnicodeUTF8));
        open_btn->setText(QApplication::translate("Compress", "Open  File", 0, QApplication::UnicodeUTF8));
        compress_btn->setText(QApplication::translate("Compress", "ComPress", 0, QApplication::UnicodeUTF8));
        label->setText(QApplication::translate("Compress", "\346\254\242\350\277\216\346\235\245\345\210\260\345\216\213\347\274\251\347\225\214\351\235\242", 0, QApplication::UnicodeUTF8));
    } // retranslateUi

};

namespace Ui {
    class Compress: public Ui_Compress {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_COMPRESS_H
