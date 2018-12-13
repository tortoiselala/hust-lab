//#pragma once

#ifndef CONFIG
#define CONFIG
#include<string>
#include<mutex>
#include<fstream>
#include<iostream>
#include<map>
#include"main_window.h"
namespace config {
	class config
	{
	public:
		static const std::string value_address_family_AF_INET;                // "AF_INET"
		static const std::string value_address_family_AF_INET6;               //"AF_INET6"

		static const std::string value_socket_stream_SOCKET_DGRAM;            //"SOCKET_DGRAM"
		static const std::string value_socket_stream_SOCKET_STREAM;           //"SOCKET_STREAM"

		static const std::string value_IPPROTO_AUTO;                          //IPPROTO_AUTO
		static const std::string value_IPPROTO_TCP;                           //IPPROTO_TCP
		static const std::string value_IPPROTO_UDP;                           //IPPROTO_UDP

		static const std::string key_ip;
		static const std::string key_port;
		static const std::string key_address_family;
		static const std::string key_path;
		static const std::string key_max_connection;
		static const std::string key_socket_stream;
		static const std::string key_ipproto;
		static const std::string config_name;
		static const std::string log_name;
		static std::string get(std::string file, std::string key, std::string default_value);
		static void set(std::string file, std::string key, std::string value);

	private:
		config();
		static std::map<std::string, std::string> * load(std::string file);
	};
	void log_write(std::string message);
	std::string no_newline(std::string str);
	std::string get_local_time();

	class lock_qDebug {
		std::mutex lock_var;
	public:
		void lock() {
			this->lock_var.lock();
		}
		void unlock() {
			this->lock_var.unlock();
		}
	};

	static lock_qDebug lock;
}
#endif // CONFIG_H_INCLUDED
