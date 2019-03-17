/********************************************************************************
** Form generated from reading UI file 'main_window.ui'
**
** Created by: Qt User Interface Compiler version 5.11.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAIN_WINDOW_H
#define UI_MAIN_WINDOW_H

#include <QtCore/QLocale>
#include <QtCore/QVariant>
#include <QtGui/QIcon>
#include <QtWidgets/QApplication>
#include <QtWidgets/QComboBox>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QTextBrowser>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_main_windowClass
{
public:
    QWidget *centralWidget;
    QTabWidget *tab_widget;
    QWidget *tab_log;
    QTextBrowser *text_log;
    QWidget *tab_setting;
    QLabel *label_main_path;
    QPushButton *button_main_path;
    QLabel *label_port;
    QLabel *label_max_connection;
    QLabel *label_timeout;
    QLabel *labe_tip_max_connection;
    QLabel *label_log_file_path;
    QLabel *label_log_lines;
    QLineEdit *text_main_path;
    QLineEdit *text_port;
    QLineEdit *text_max_connection;
    QLineEdit *text_timeout;
    QLineEdit *text_log_max_lines;
    QLineEdit *text_log_path_2;
    QLabel *label_server_ip;
    QComboBox *combobox_server_ip;
    QLabel *label_address_family;
    QComboBox *combobox_address_family;
    QLabel *label_socket_stream;
    QComboBox *combobox_socket_stream;
    QLabel *label_ipproto;
    QComboBox *combobox_ipproto;
    QLabel *labe_tip_timeout;
    QPushButton *button_start_pause;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *main_windowClass)
    {
        if (main_windowClass->objectName().isEmpty())
            main_windowClass->setObjectName(QStringLiteral("main_windowClass"));
        main_windowClass->resize(554, 480);
        main_windowClass->setMinimumSize(QSize(554, 480));
        main_windowClass->setMaximumSize(QSize(554, 480));
        centralWidget = new QWidget(main_windowClass);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        tab_widget = new QTabWidget(centralWidget);
        tab_widget->setObjectName(QStringLiteral("tab_widget"));
        tab_widget->setGeometry(QRect(0, 0, 551, 421));
        tab_widget->setLocale(QLocale(QLocale::Chinese, QLocale::China));
        tab_log = new QWidget();
        tab_log->setObjectName(QStringLiteral("tab_log"));
        text_log = new QTextBrowser(tab_log);
        text_log->setObjectName(QStringLiteral("text_log"));
        text_log->setGeometry(QRect(0, 0, 551, 370));
        QIcon icon;
        icon.addFile(QStringLiteral(":/icon/disable_log.png"), QSize(), QIcon::Normal, QIcon::Off);
        icon.addFile(QStringLiteral(":/icon/disable_log.png"), QSize(), QIcon::Normal, QIcon::On);
        tab_widget->addTab(tab_log, icon, QString());
        tab_setting = new QWidget();
        tab_setting->setObjectName(QStringLiteral("tab_setting"));
        label_main_path = new QLabel(tab_setting);
        label_main_path->setObjectName(QStringLiteral("label_main_path"));
        label_main_path->setGeometry(QRect(70, 30, 61, 24));
        button_main_path = new QPushButton(tab_setting);
        button_main_path->setObjectName(QStringLiteral("button_main_path"));
        button_main_path->setGeometry(QRect(350, 30, 31, 24));
        QIcon icon1;
        icon1.addFile(QStringLiteral(":/icon/file.png"), QSize(), QIcon::Normal, QIcon::Off);
        button_main_path->setIcon(icon1);
        button_main_path->setFlat(true);
        label_port = new QLabel(tab_setting);
        label_port->setObjectName(QStringLiteral("label_port"));
        label_port->setGeometry(QRect(70, 60, 71, 24));
        label_max_connection = new QLabel(tab_setting);
        label_max_connection->setObjectName(QStringLiteral("label_max_connection"));
        label_max_connection->setGeometry(QRect(40, 110, 111, 24));
        label_timeout = new QLabel(tab_setting);
        label_timeout->setObjectName(QStringLiteral("label_timeout"));
        label_timeout->setGeometry(QRect(60, 150, 81, 24));
        labe_tip_max_connection = new QLabel(tab_setting);
        labe_tip_max_connection->setObjectName(QStringLiteral("labe_tip_max_connection"));
        labe_tip_max_connection->setGeometry(QRect(200, 110, 81, 24));
        label_log_file_path = new QLabel(tab_setting);
        label_log_file_path->setObjectName(QStringLiteral("label_log_file_path"));
        label_log_file_path->setGeometry(QRect(20, 240, 101, 24));
        label_log_lines = new QLabel(tab_setting);
        label_log_lines->setObjectName(QStringLiteral("label_log_lines"));
        label_log_lines->setGeometry(QRect(0, 190, 131, 24));
        text_main_path = new QLineEdit(tab_setting);
        text_main_path->setObjectName(QStringLiteral("text_main_path"));
        text_main_path->setGeometry(QRect(140, 30, 201, 24));
        text_port = new QLineEdit(tab_setting);
        text_port->setObjectName(QStringLiteral("text_port"));
        text_port->setGeometry(QRect(140, 60, 51, 24));
        text_max_connection = new QLineEdit(tab_setting);
        text_max_connection->setObjectName(QStringLiteral("text_max_connection"));
        text_max_connection->setGeometry(QRect(140, 110, 51, 24));
        text_timeout = new QLineEdit(tab_setting);
        text_timeout->setObjectName(QStringLiteral("text_timeout"));
        text_timeout->setGeometry(QRect(140, 150, 51, 24));
        text_log_max_lines = new QLineEdit(tab_setting);
        text_log_max_lines->setObjectName(QStringLiteral("text_log_max_lines"));
        text_log_max_lines->setGeometry(QRect(140, 190, 51, 24));
        text_log_path_2 = new QLineEdit(tab_setting);
        text_log_path_2->setObjectName(QStringLiteral("text_log_path_2"));
        text_log_path_2->setGeometry(QRect(140, 240, 201, 24));
        label_server_ip = new QLabel(tab_setting);
        label_server_ip->setObjectName(QStringLiteral("label_server_ip"));
        label_server_ip->setGeometry(QRect(60, 270, 61, 24));
        combobox_server_ip = new QComboBox(tab_setting);
        combobox_server_ip->setObjectName(QStringLiteral("combobox_server_ip"));
        combobox_server_ip->setGeometry(QRect(140, 270, 151, 24));
        label_address_family = new QLabel(tab_setting);
        label_address_family->setObjectName(QStringLiteral("label_address_family"));
        label_address_family->setGeometry(QRect(70, 300, 51, 24));
        combobox_address_family = new QComboBox(tab_setting);
        combobox_address_family->setObjectName(QStringLiteral("combobox_address_family"));
        combobox_address_family->setGeometry(QRect(140, 300, 151, 24));
        label_socket_stream = new QLabel(tab_setting);
        label_socket_stream->setObjectName(QStringLiteral("label_socket_stream"));
        label_socket_stream->setGeometry(QRect(10, 330, 101, 24));
        combobox_socket_stream = new QComboBox(tab_setting);
        combobox_socket_stream->setObjectName(QStringLiteral("combobox_socket_stream"));
        combobox_socket_stream->setGeometry(QRect(140, 330, 151, 24));
        label_ipproto = new QLabel(tab_setting);
        label_ipproto->setObjectName(QStringLiteral("label_ipproto"));
        label_ipproto->setGeometry(QRect(70, 360, 41, 24));
        combobox_ipproto = new QComboBox(tab_setting);
        combobox_ipproto->setObjectName(QStringLiteral("combobox_ipproto"));
        combobox_ipproto->setGeometry(QRect(140, 360, 151, 24));
        labe_tip_timeout = new QLabel(tab_setting);
        labe_tip_timeout->setObjectName(QStringLiteral("labe_tip_timeout"));
        labe_tip_timeout->setGeometry(QRect(200, 150, 81, 24));
        QIcon icon2;
        icon2.addFile(QStringLiteral(":/icon/disable_setting.png"), QSize(), QIcon::Normal, QIcon::Off);
        icon2.addFile(QStringLiteral(":/icon/normal_setting.png"), QSize(), QIcon::Normal, QIcon::On);
        tab_widget->addTab(tab_setting, icon2, QString());
        button_start_pause = new QPushButton(centralWidget);
        button_start_pause->setObjectName(QStringLiteral("button_start_pause"));
        button_start_pause->setGeometry(QRect(520, 420, 31, 21));
        QIcon icon3;
        icon3.addFile(QStringLiteral(":/icon/disable_running.png"), QSize(), QIcon::Normal, QIcon::Off);
        icon3.addFile(QStringLiteral(":/icon/normal_running.png"), QSize(), QIcon::Normal, QIcon::On);
        icon3.addFile(QStringLiteral(":/icon/disable_running.png"), QSize(), QIcon::Disabled, QIcon::Off);
        icon3.addFile(QStringLiteral(":/icon/normal_running.png"), QSize(), QIcon::Disabled, QIcon::On);
        button_start_pause->setIcon(icon3);
        button_start_pause->setFlat(true);
        main_windowClass->setCentralWidget(centralWidget);
        mainToolBar = new QToolBar(main_windowClass);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        main_windowClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(main_windowClass);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        main_windowClass->setStatusBar(statusBar);

        retranslateUi(main_windowClass);

        tab_widget->setCurrentIndex(0);


        QMetaObject::connectSlotsByName(main_windowClass);
    } // setupUi

    void retranslateUi(QMainWindow *main_windowClass)
    {
        main_windowClass->setWindowTitle(QApplication::translate("main_windowClass", "Server", nullptr));
#ifndef QT_NO_TOOLTIP
        tab_widget->setToolTip(QString());
#endif // QT_NO_TOOLTIP
#ifndef QT_NO_STATUSTIP
        tab_widget->setStatusTip(QString());
#endif // QT_NO_STATUSTIP
#ifndef QT_NO_STATUSTIP
        tab_log->setStatusTip(QApplication::translate("main_windowClass", "Log Console", nullptr));
#endif // QT_NO_STATUSTIP
        tab_widget->setTabText(tab_widget->indexOf(tab_log), QString());
        tab_widget->setTabToolTip(tab_widget->indexOf(tab_log), QApplication::translate("main_windowClass", "Log Console", nullptr));
        tab_widget->setTabWhatsThis(tab_widget->indexOf(tab_log), QApplication::translate("main_windowClass", "Log Console", nullptr));
#ifndef QT_NO_STATUSTIP
        tab_setting->setStatusTip(QApplication::translate("main_windowClass", "Setting", nullptr));
#endif // QT_NO_STATUSTIP
        label_main_path->setText(QApplication::translate("main_windowClass", "\344\270\273\347\233\256\345\275\225\357\274\232", nullptr));
        button_main_path->setText(QString());
        label_port->setText(QApplication::translate("main_windowClass", "\347\253\257\345\217\243\345\217\267\357\274\232", nullptr));
        label_max_connection->setText(QApplication::translate("main_windowClass", "\346\234\200\345\244\247\350\277\236\346\216\245\346\225\260\357\274\232", nullptr));
        label_timeout->setText(QApplication::translate("main_windowClass", "\350\266\205\346\227\266\346\227\266\351\227\264\357\274\232", nullptr));
        labe_tip_max_connection->setText(QApplication::translate("main_windowClass", "\357\274\2100\344\270\272\344\270\215\351\231\220\345\210\266\357\274\211", nullptr));
        label_log_file_path->setText(QApplication::translate("main_windowClass", "\346\227\245\345\277\227\346\226\207\344\273\266\344\275\215\347\275\256\357\274\232", nullptr));
        label_log_lines->setText(QApplication::translate("main_windowClass", "\347\252\227\345\217\243\346\234\200\345\244\247\346\230\276\347\244\272\350\241\214\346\225\260\357\274\232", nullptr));
        label_server_ip->setText(QApplication::translate("main_windowClass", "\346\234\215\345\212\241\345\231\250IP\357\274\232", nullptr));
        label_address_family->setText(QApplication::translate("main_windowClass", "\345\234\260\345\235\200\346\227\217\357\274\232", nullptr));
        label_socket_stream->setText(QApplication::translate("main_windowClass", "\346\225\260\346\215\256\344\274\240\351\200\201\347\261\273\345\236\213\357\274\232", nullptr));
        label_ipproto->setText(QApplication::translate("main_windowClass", "\345\215\217\350\256\256\357\274\232", nullptr));
        labe_tip_timeout->setText(QApplication::translate("main_windowClass", "(1-1000ms)", nullptr));
        tab_widget->setTabText(tab_widget->indexOf(tab_setting), QString());
        tab_widget->setTabToolTip(tab_widget->indexOf(tab_setting), QApplication::translate("main_windowClass", "Settings", nullptr));
        tab_widget->setTabWhatsThis(tab_widget->indexOf(tab_setting), QApplication::translate("main_windowClass", "Settings", nullptr));
#ifndef QT_NO_TOOLTIP
        button_start_pause->setToolTip(QApplication::translate("main_windowClass", "\350\277\220\350\241\214/\345\201\234\346\255\242", nullptr));
#endif // QT_NO_TOOLTIP
#ifndef QT_NO_STATUSTIP
        button_start_pause->setStatusTip(QApplication::translate("main_windowClass", "\350\277\220\350\241\214/\345\201\234\346\255\242", nullptr));
#endif // QT_NO_STATUSTIP
        button_start_pause->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class main_windowClass: public Ui_main_windowClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAIN_WINDOW_H
