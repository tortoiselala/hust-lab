#include"config.h"
#include<qdebug.h>
const std::string config::config::value_address_family_AF_INET =  std::string("AF_INET");
const std::string config::config::value_address_family_AF_INET6 = std::string("AF_INET6");

const std::string config::config::value_socket_stream_SOCKET_DGRAM = std::string("SOCKET_DGRAM");
const std::string config::config::value_socket_stream_SOCKET_STREAM = std::string("SOCKET_STREAM");

const std::string config::config::value_IPPROTO_AUTO = std::string("IPPROTO_AUTO");                        //IPPROTO_AUTO
const std::string config::config::value_IPPROTO_TCP = std::string("IPPROTO_TCP");                           //IPPROTO_TCP
const std::string config::config::value_IPPROTO_UDP = std::string("IPPROTO_UDP");                           //IPPROTO_UDP

const std::string config::config::config_name = std::string("web-server.ini");
const std::string config::config::key_ip = std::string("ip");
const std::string config::config::key_port = std::string("port");
const std::string config::config::key_address_family = std::string("address_family");
const std::string config::config::key_path = std::string("path");
const std::string config::config::key_max_connection = std::string("max_connection");
const std::string config::config::key_socket_stream = std::string("socket_connection");
const std::string config::config::key_ipproto = std::string("ipproto");

const std::string config::config::log_name = std::string("web-server.log");

std::string config::config::get(std::string file, std::string key, std::string default_value)
{
    std::ifstream in(file.c_str(), std::ios::in);
    std::map<std::string, std::string> *file_content = load(file);
    std::map<std::string, std::string>::iterator it_find = file_content->find(key);

    if (it_find == file_content->end()) {
        file_content->clear();
		in.close();
        return default_value;
    }
    file_content->clear();
	in.close();
    return it_find->second;
}

void config::config::set(std::string file, std::string key, std::string value)
{
    
    std::map<std::string, std::string> *file_content = load(file);
	std::ofstream out(file.c_str(), std::ios::out);
    (*file_content)[key] = value;
    std::map<std::string, std::string>::iterator it_write = file_content->begin();
    while (it_write != file_content->end()) {
        out << it_write->first << "=" << it_write->second << std::endl;
        ++it_write;
    }
    file_content->clear();
	out.close();
    return;
}

std::map<std::string, std::string> * config::config::load(std::string file)
{
    std::map<std::string, std::string> *result = new std::map<std::string, std::string>;
    std::ifstream in(file.c_str());
    if (!in.is_open()) {
		fstream out_file;
		out_file.open(file.c_str(), ios::out);
		out_file.close();
		lock.lock();
		char buf[200];
		sprintf(buf, "%s %s is not exists, create %s...",get_local_time().c_str(), file.c_str(), file.c_str() );
		qDebug() << buf;
		lock.unlock();
	}
	else {
		while (!in.eof()) {
			char buf[256];
			memset(buf, '\0', 200);
			in.getline(buf, 256);
			int buf_length = strlen(buf);
			std::string buf_str = std::string(buf);
			for (int i = 0; i < buf_length; ++i) {
				if (buf[i] == '=') {
					std::string buf_key = buf_str.substr(0, i);
					std::string buf_value = buf_str.substr(i + 1, buf_length - i - 1);
					result->insert(std::pair<std::string, std::string>(buf_key, buf_value));
				}
			}
		}
		in.close();
	}
  
    return result;

}

void config::log_write(std::string message)
{
	std::ofstream out(config::log_name.c_str(), std::ios::app);
	out << message<<endl;
	out.close();
}

std::string config::no_newline(std::string str)
{
	str[strlen(str.c_str()) - 1] = '\0';
	return str;
}

std::string config::get_local_time()
{
	time_t nowtime = time(NULL);
	struct tm* local = localtime(&nowtime);
	char buf[100];
	sprintf(buf, "[ %s ]", no_newline(asctime(local)).c_str());
	return std::string(buf);
}
