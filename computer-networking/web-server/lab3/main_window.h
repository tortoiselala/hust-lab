
#ifndef MAIN_WINDOW_H
#define MAIN_WINDOW_H

#include <QObject>
#include<QMessageBox>
#include <QtWidgets/QMainWindow>
#include "ui_main_window.h"
#include"server.h"
#include"IP_checker.h"
using namespace std;

class main_window : public QMainWindow
{
	Q_OBJECT

public:
	main_window(QWidget *parent = Q_NULLPTR);

//private:
	Ui::main_windowClass ui;


};
#endif //! MAIN_WINDOW_H