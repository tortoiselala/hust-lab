#include "main_window.h"


main_window::main_window(QWidget *parent)
	: QMainWindow(parent)
{
	ui.setupUi(this);
	//设置主目录路径显示框不可编辑
	//ui.text_main_path->setFocusPolicy(Qt::NoFocus);
	ui.text_port->setValidator(new QIntValidator(0, 65535, this));
	ui.text_max_connection->setValidator(new QIntValidator(0, 10000, this));
	ui.text_timeout->setValidator(new QIntValidator(0, 1000, this));
	IP_checker checker = IP_checker();
	checker_result* result = checker.get_IP_list();
	for (int i = 0; i < result->num; ++i) {
		ui.combobox_server_ip->addItem(QString::fromStdString(result->result[i]));
	}

	

	ui.combobox_address_family->addItem(QString::fromStdString(config::config::value_address_family_AF_INET));
	ui.combobox_address_family->addItem(QString::fromStdString(config::config::value_address_family_AF_INET6));

	ui.combobox_socket_stream->addItem(QString::fromStdString(config::config::value_socket_stream_SOCKET_STREAM));
	ui.combobox_socket_stream->addItem(QString::fromStdString(config::config::value_socket_stream_SOCKET_DGRAM));

	ui.combobox_ipproto->addItem(QString::fromStdString(config::config::value_IPPROTO_AUTO));
	ui.combobox_ipproto->addItem(QString::fromStdString(config::config::value_IPPROTO_TCP));
	ui.combobox_ipproto->addItem(QString::fromStdString(config::config::value_IPPROTO_UDP));
}
