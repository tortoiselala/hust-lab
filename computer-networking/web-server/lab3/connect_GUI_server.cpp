#include "connect_GUI_server.h"
connect_GUI::connect_GUI::connect_GUI()
{
	IP_checker checker = IP_checker();
	checker_result* result = checker.get_IP_list();

	string ip = string(result->result[0]);
	int port = 8080;
	u_short address_family = server::server::IPv4;
	string virtual_path = string("F:/index");
	int max_connetcion = 10;
	int socket_stream = server::server::socket_stream;
	int ipproto = server::server::ipproto_auto;

	this->s = new server::server();
	this->w = new main_window();
	this->s->server_start_pause = false;
	this->s->set_ip(ip);
	this->s->set_port(port);
	this->s->set_address_family(address_family);
	this->s->set_virtual_path(virtual_path);
	this->s->set_max_connection(max_connetcion);
	this->s->set_socket_type(socket_stream);
	this->s->set_socket_protocol(ipproto);

	this->w->ui.text_main_path->setText(QString::fromStdString(virtual_path));
	char buf[100];
	_itoa(port, buf, 10);
	this->w->ui.text_port->setText(QString::fromStdString(buf));
	_itoa(max_connetcion, buf, 10);
	this->w->ui.text_max_connection->setText(QString::fromStdString(buf));
	_itoa(100, buf, 10);
	this->w->ui.text_log_max_lines->setText(QString::fromStdString(buf));
	_itoa(50, buf, 10);
	this->w->ui.text_timeout->setText(QString::fromStdString(buf));

	this->w->ui.text_log_path_2->setText(QString::fromStdString(config::config::log_name));
	//slot for main path button
	QObject::connect(this->w->ui.button_main_path, &QPushButton::released, [this]() {
		QString  main_path = QFileDialog::getExistingDirectory(this->w, tr("Select main path"), tr((const char *)((this->w->ui.text_main_path->text())).toLocal8Bit()));
		this->w->ui.text_main_path->setText(main_path);
		this->s->set_virtual_path(string((const char *)main_path.toLocal8Bit()));
		char buf[100];
		sprintf(buf, "%s main path changed to %s.", config::get_local_time().c_str(), (const char *)main_path.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
		config::config::set(config::config::config_name, config::config::key_path, string((const char *)main_path.toLocal8Bit()));
	});
	//slot for port line text
	QObject::connect(this->w->ui.text_port, &QLineEdit::editingFinished, [this]() {
		QString port_str = this->w->ui.text_port->text();
		this->s->set_port(atoi((const char *)port_str.toLocal8Bit()));
		char buf[100];
		sprintf(buf, "%s port changed to %s.", config::get_local_time().c_str(), (const char *)port_str.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
		config::config::set(config::config::config_name, config::config::key_port, string((const char *)port_str.toLocal8Bit()));
	});
	//slot for start pause button
	QObject::connect(this->w->ui.button_start_pause, &QPushButton::released,[this]() {
			this->s->launch_pause();    
	});
	//solt for max connection line text
	QObject::connect(this->w->ui.text_max_connection, &QLineEdit::editingFinished, [this]() {
		QString max_connection_str = this->w->ui.text_max_connection->text();
		this->s->set_max_connection(atoi((const char *)max_connection_str.toLocal8Bit()));
		char buf[100];
		sprintf(buf, "%s max connection nums changed to %s.", config::get_local_time().c_str(), (const char *)max_connection_str.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
		config::config::set(config::config::config_name, config::config::key_max_connection, string((const char *)max_connection_str.toLocal8Bit()));
	});
	//slot for ip combobox
	QObject::connect(this->w->ui.combobox_server_ip, &QComboBox::currentTextChanged, [this]() {
		QString ip_str = this->w->ui.combobox_server_ip->currentText();
		this->s->set_ip((const char *)ip_str.toLocal8Bit());
		char buf[100];
		sprintf(buf, "%s server ip changed to %s.", config::get_local_time().c_str(), (const char *)ip_str.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();
		config::config::set(config::config::config_name, config::config::key_ip, string((const char *)ip_str.toLocal8Bit()));
	});
	//slot for address family combobox
	QObject::connect(this->w->ui.combobox_address_family, &QComboBox::currentTextChanged, [this]() {
		QString address_family_qstr = this->w->ui.combobox_address_family->currentText();
		string address_family_str = string((const char *)address_family_qstr.toLocal8Bit());
		u_short address_family = address_family_str == config::config::value_address_family_AF_INET ? server::server::IPv4 : server::server::IPv6;
		this->s->set_address_family(address_family);

		char buf[100];
		sprintf(buf, "%s server ip changed to %s.", config::get_local_time().c_str(), (const char *)address_family_qstr.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();

		config::config::set(config::config::config_name, config::config::key_address_family, address_family_str);
	});
	//slot for socket stream type
	QObject::connect(this->w->ui.combobox_socket_stream, &QComboBox::currentTextChanged, [this]() {
		QString socket_stream_qstr = this->w->ui.combobox_socket_stream->currentText();
		string socket_stream_str = string((const char *)socket_stream_qstr.toLocal8Bit());
		int socket_stream = server::server::socket_stream;
		if (socket_stream_str == config::config::value_socket_stream_SOCKET_DGRAM) {
			socket_stream = server::server::socket_dgram;
		}
		this->s->set_socket_type(socket_stream);

		char buf[100];
		sprintf(buf, "%s server ip changed to %s.", config::get_local_time().c_str(), (const char *)socket_stream_qstr.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();

		config::config::set(config::config::config_name, config::config::key_socket_stream, socket_stream_str);
	});
	//slot for socket proto
	QObject::connect(this->w->ui.combobox_ipproto, &QComboBox::currentTextChanged, [this]() {
		QString ipproto_qstr = this->w->ui.combobox_ipproto->currentText();
		string ipproto_str = string((const char *)ipproto_qstr.toLocal8Bit());
		int ipproto = server::server::ipproto_auto;
		if (ipproto_str == config::config::value_IPPROTO_TCP) {
			ipproto = server::server::ipproto_tcp;
		}
		else if (ipproto_str == config::config::value_IPPROTO_UDP) {
			ipproto = server::server::ipproto_udp;
		}
		this->s->set_socket_protocol(ipproto);

		char buf[100];
		sprintf(buf, "%s server ip changed to %s.", config::get_local_time().c_str(), (const char *)ipproto_qstr.toLocal8Bit());
		config::lock.lock();
		qDebug() << buf;
		config::lock.unlock();

		config::config::set(config::config::config_name, config::config::key_ipproto, ipproto_str);
	});

}

