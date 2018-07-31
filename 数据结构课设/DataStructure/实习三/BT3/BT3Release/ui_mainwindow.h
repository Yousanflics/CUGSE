/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.8.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QProgressBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QPushButton *pushButton_Compress;
    QPushButton *pushButton_Decompress;
    QTextBrowser *textBrowser;
    QProgressBar *progressBar;
    QWidget *widget;
    QGridLayout *gridLayout;
    QVBoxLayout *verticalLayout;
    QPushButton *pushButton_OpenFile;
    QCheckBox *checkBox;
    QPushButton *pushButton_Save;
    QVBoxLayout *verticalLayout_2;
    QLabel *label_FilePath;
    QLineEdit *lineEdit_PassWord;
    QLineEdit *lineEdit;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(452, 386);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        pushButton_Compress = new QPushButton(centralWidget);
        pushButton_Compress->setObjectName(QStringLiteral("pushButton_Compress"));
        pushButton_Compress->setGeometry(QRect(190, 100, 121, 23));
        pushButton_Decompress = new QPushButton(centralWidget);
        pushButton_Decompress->setObjectName(QStringLiteral("pushButton_Decompress"));
        pushButton_Decompress->setGeometry(QRect(320, 100, 121, 23));
        textBrowser = new QTextBrowser(centralWidget);
        textBrowser->setObjectName(QStringLiteral("textBrowser"));
        textBrowser->setGeometry(QRect(10, 160, 431, 192));
        progressBar = new QProgressBar(centralWidget);
        progressBar->setObjectName(QStringLiteral("progressBar"));
        progressBar->setGeometry(QRect(10, 130, 431, 23));
        progressBar->setValue(24);
        widget = new QWidget(centralWidget);
        widget->setObjectName(QStringLiteral("widget"));
        widget->setGeometry(QRect(10, 10, 431, 78));
        gridLayout = new QGridLayout(widget);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        gridLayout->setContentsMargins(0, 0, 0, 0);
        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        pushButton_OpenFile = new QPushButton(widget);
        pushButton_OpenFile->setObjectName(QStringLiteral("pushButton_OpenFile"));

        verticalLayout->addWidget(pushButton_OpenFile);

        checkBox = new QCheckBox(widget);
        checkBox->setObjectName(QStringLiteral("checkBox"));

        verticalLayout->addWidget(checkBox);

        pushButton_Save = new QPushButton(widget);
        pushButton_Save->setObjectName(QStringLiteral("pushButton_Save"));

        verticalLayout->addWidget(pushButton_Save);


        gridLayout->addLayout(verticalLayout, 0, 0, 1, 1);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        label_FilePath = new QLabel(widget);
        label_FilePath->setObjectName(QStringLiteral("label_FilePath"));

        verticalLayout_2->addWidget(label_FilePath);

        lineEdit_PassWord = new QLineEdit(widget);
        lineEdit_PassWord->setObjectName(QStringLiteral("lineEdit_PassWord"));
        lineEdit_PassWord->setEchoMode(QLineEdit::Password);

        verticalLayout_2->addWidget(lineEdit_PassWord);

        lineEdit = new QLineEdit(widget);
        lineEdit->setObjectName(QStringLiteral("lineEdit"));

        verticalLayout_2->addWidget(lineEdit);


        gridLayout->addLayout(verticalLayout_2, 0, 1, 1, 1);

        MainWindow->setCentralWidget(centralWidget);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", Q_NULLPTR));
        pushButton_Compress->setText(QApplication::translate("MainWindow", "Huffman Compress", Q_NULLPTR));
        pushButton_Decompress->setText(QApplication::translate("MainWindow", "Huffman Decompress", Q_NULLPTR));
        pushButton_OpenFile->setText(QApplication::translate("MainWindow", "OpenFile", Q_NULLPTR));
        checkBox->setText(QApplication::translate("MainWindow", "PassWord", Q_NULLPTR));
        pushButton_Save->setText(QApplication::translate("MainWindow", "Save as", Q_NULLPTR));
        label_FilePath->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
