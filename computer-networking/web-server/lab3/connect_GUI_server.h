#ifndef CONNECT_GUI_SERVER_H
#define CONNECT_GUI_SERVER_H

using namespace std;
#include<QObject>
#include<QDebug>
#include<QFileDialog>
#include"server.h"
#include"config.h"
#include"main_window.h"
namespace connect_GUI {
	class connect_GUI :QObject {
	public:
		main_window * w;
		server::server *s;

		connect_GUI();

	};
	static connect_GUI * object;
}

#endif // !CONNECT_GUI_SERVER_H
