/********************************************************************************
** Form generated from reading UI file 'dialog1.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_DIALOG1_H
#define UI_DIALOG1_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QGroupBox>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>

QT_BEGIN_NAMESPACE

class Ui_Dialog1
{
public:
    QGridLayout *gridLayout_7;
    QGridLayout *gridLayout_6;
    QGroupBox *groupBox;
    QGridLayout *gridLayout;
    QLineEdit *Input;
    QLabel *label_2;
    QLineEdit *Out;
    QLabel *label;
    QGroupBox *groupBox_2;
    QGridLayout *gridLayout_2;
    QPushButton *getNewTree;
    QPushButton *Swap;
    QPushButton *Find;
    QComboBox *comboBox;
    QPushButton *Create;
    QPushButton *Output;
    QLineEdit *findnum;
    QSpacerItem *verticalSpacer;
    QGroupBox *groupBox_3;
    QGridLayout *gridLayout_5;
    QGridLayout *gridLayout_4;
    QLabel *label_3;
    QLineEdit *lineEdit;
    QLabel *label_4;
    QLineEdit *lineEdit_2;
    QGridLayout *gridLayout_3;
    QPushButton *get;
    QPushButton *apply;
    QPushButton *paint;

    void setupUi(QDialog *Dialog1)
    {
        if (Dialog1->objectName().isEmpty())
            Dialog1->setObjectName(QStringLiteral("Dialog1"));
        Dialog1->resize(542, 314);
        gridLayout_7 = new QGridLayout(Dialog1);
        gridLayout_7->setObjectName(QStringLiteral("gridLayout_7"));
        gridLayout_6 = new QGridLayout();
        gridLayout_6->setObjectName(QStringLiteral("gridLayout_6"));
        groupBox = new QGroupBox(Dialog1);
        groupBox->setObjectName(QStringLiteral("groupBox"));
        gridLayout = new QGridLayout(groupBox);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        Input = new QLineEdit(groupBox);
        Input->setObjectName(QStringLiteral("Input"));

        gridLayout->addWidget(Input, 1, 1, 1, 1);

        label_2 = new QLabel(groupBox);
        label_2->setObjectName(QStringLiteral("label_2"));

        gridLayout->addWidget(label_2, 2, 0, 1, 1);

        Out = new QLineEdit(groupBox);
        Out->setObjectName(QStringLiteral("Out"));

        gridLayout->addWidget(Out, 2, 1, 1, 1);

        label = new QLabel(groupBox);
        label->setObjectName(QStringLiteral("label"));

        gridLayout->addWidget(label, 1, 0, 1, 1);


        gridLayout_6->addWidget(groupBox, 0, 0, 1, 1);

        groupBox_2 = new QGroupBox(Dialog1);
        groupBox_2->setObjectName(QStringLiteral("groupBox_2"));
        gridLayout_2 = new QGridLayout(groupBox_2);
        gridLayout_2->setObjectName(QStringLiteral("gridLayout_2"));
        getNewTree = new QPushButton(groupBox_2);
        getNewTree->setObjectName(QStringLiteral("getNewTree"));

        gridLayout_2->addWidget(getNewTree, 3, 0, 1, 1);

        Swap = new QPushButton(groupBox_2);
        Swap->setObjectName(QStringLiteral("Swap"));

        gridLayout_2->addWidget(Swap, 2, 0, 1, 1);

        Find = new QPushButton(groupBox_2);
        Find->setObjectName(QStringLiteral("Find"));

        gridLayout_2->addWidget(Find, 1, 0, 1, 1);

        comboBox = new QComboBox(groupBox_2);
        comboBox->setObjectName(QStringLiteral("comboBox"));

        gridLayout_2->addWidget(comboBox, 0, 1, 1, 1);

        Create = new QPushButton(groupBox_2);
        Create->setObjectName(QStringLiteral("Create"));

        gridLayout_2->addWidget(Create, 0, 0, 1, 1);

        Output = new QPushButton(groupBox_2);
        Output->setObjectName(QStringLiteral("Output"));

        gridLayout_2->addWidget(Output, 2, 1, 1, 1);

        findnum = new QLineEdit(groupBox_2);
        findnum->setObjectName(QStringLiteral("findnum"));

        gridLayout_2->addWidget(findnum, 1, 1, 1, 1);


        gridLayout_6->addWidget(groupBox_2, 0, 1, 1, 1);


        gridLayout_7->addLayout(gridLayout_6, 0, 0, 1, 1);

        verticalSpacer = new QSpacerItem(20, 2, QSizePolicy::Minimum, QSizePolicy::Expanding);

        gridLayout_7->addItem(verticalSpacer, 1, 0, 1, 1);

        groupBox_3 = new QGroupBox(Dialog1);
        groupBox_3->setObjectName(QStringLiteral("groupBox_3"));
        gridLayout_5 = new QGridLayout(groupBox_3);
        gridLayout_5->setObjectName(QStringLiteral("gridLayout_5"));
        gridLayout_4 = new QGridLayout();
        gridLayout_4->setObjectName(QStringLiteral("gridLayout_4"));
        label_3 = new QLabel(groupBox_3);
        label_3->setObjectName(QStringLiteral("label_3"));

        gridLayout_4->addWidget(label_3, 0, 0, 1, 1);

        lineEdit = new QLineEdit(groupBox_3);
        lineEdit->setObjectName(QStringLiteral("lineEdit"));

        gridLayout_4->addWidget(lineEdit, 0, 1, 1, 1);

        label_4 = new QLabel(groupBox_3);
        label_4->setObjectName(QStringLiteral("label_4"));

        gridLayout_4->addWidget(label_4, 1, 0, 1, 1);

        lineEdit_2 = new QLineEdit(groupBox_3);
        lineEdit_2->setObjectName(QStringLiteral("lineEdit_2"));

        gridLayout_4->addWidget(lineEdit_2, 1, 1, 1, 1);


        gridLayout_5->addLayout(gridLayout_4, 0, 0, 1, 1);

        gridLayout_3 = new QGridLayout();
        gridLayout_3->setObjectName(QStringLiteral("gridLayout_3"));
        get = new QPushButton(groupBox_3);
        get->setObjectName(QStringLiteral("get"));

        gridLayout_3->addWidget(get, 0, 0, 1, 1);

        apply = new QPushButton(groupBox_3);
        apply->setObjectName(QStringLiteral("apply"));

        gridLayout_3->addWidget(apply, 0, 1, 1, 1);

        paint = new QPushButton(groupBox_3);
        paint->setObjectName(QStringLiteral("paint"));

        gridLayout_3->addWidget(paint, 1, 1, 1, 1);


        gridLayout_5->addLayout(gridLayout_3, 0, 1, 1, 1);


        gridLayout_7->addWidget(groupBox_3, 2, 0, 1, 1);


        retranslateUi(Dialog1);

        QMetaObject::connectSlotsByName(Dialog1);
    } // setupUi

    void retranslateUi(QDialog *Dialog1)
    {
        Dialog1->setWindowTitle(QApplication::translate("Dialog1", "Dialog", Q_NULLPTR));
        groupBox->setTitle(QApplication::translate("Dialog1", "\346\230\276\347\244\272\345\214\272", Q_NULLPTR));
        label_2->setText(QApplication::translate("Dialog1", "\350\276\223\345\207\272\357\274\232", Q_NULLPTR));
        label->setText(QApplication::translate("Dialog1", "\350\276\223\345\205\245\357\274\232", Q_NULLPTR));
        groupBox_2->setTitle(QApplication::translate("Dialog1", "\346\223\215\344\275\234\345\214\272", Q_NULLPTR));
        getNewTree->setText(QApplication::translate("Dialog1", ">>>", Q_NULLPTR));
        Swap->setText(QApplication::translate("Dialog1", "Swap", Q_NULLPTR));
        Find->setText(QApplication::translate("Dialog1", "Find", Q_NULLPTR));
        comboBox->clear();
        comboBox->insertItems(0, QStringList()
         << QApplication::translate("Dialog1", "\345\211\215\345\272\217\351\201\215\345\216\206", Q_NULLPTR)
         << QApplication::translate("Dialog1", "\344\270\255\345\272\217\351\201\215\345\216\206", Q_NULLPTR)
         << QApplication::translate("Dialog1", "\345\220\216\345\272\217\351\201\215\345\216\206", Q_NULLPTR)
         << QApplication::translate("Dialog1", "\345\261\202\346\254\241\351\201\215\345\216\206", Q_NULLPTR)
        );
        Create->setText(QApplication::translate("Dialog1", "Create", Q_NULLPTR));
        Output->setText(QApplication::translate("Dialog1", "Output", Q_NULLPTR));
        groupBox_3->setTitle(QApplication::translate("Dialog1", "\351\207\215\346\236\204\345\214\272", Q_NULLPTR));
        label_3->setText(QApplication::translate("Dialog1", "\345\211\215\345\272\217\346\225\260\345\210\227", Q_NULLPTR));
        label_4->setText(QApplication::translate("Dialog1", "\344\270\255\345\272\217\346\225\260\345\210\227", Q_NULLPTR));
        get->setText(QApplication::translate("Dialog1", "get", Q_NULLPTR));
        apply->setText(QApplication::translate("Dialog1", "apply", Q_NULLPTR));
        paint->setText(QApplication::translate("Dialog1", "paint", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class Dialog1: public Ui_Dialog1 {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_DIALOG1_H
