#include "main_window.h"
#include"connect_GUI_server.h"
#include <QtWidgets/QApplication>


void message_handler(QtMsgType type, const QMessageLogContext &, const QString & msg) {
	config::log_write(msg.toStdString());
	connect_GUI::object->w->ui.text_log->append(msg);
}
int main(int argc, char *argv[])
{
	qInstallMessageHandler(message_handler);
	QApplication a(argc, argv);
	connect_GUI::connect_GUI con;
	connect_GUI::object = &con;
	
	con.w->show();
	return a.exec();
}
