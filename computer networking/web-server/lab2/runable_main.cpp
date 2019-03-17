#pragma once

#include"runable_main.h"

int main(int argc, char * argv[]) {

	string ip = string("127.0.0.1");
	int port = 8080;
	u_short address_family = server::server::IPv4;
	string virtual_path = string("F:/index");
	int max_connetcion = 10;
	int socket_stream = server::server::socket_stream;
	int ipproto = server::server::ipproto_auto;

	if (argc <= 1) {
		print_usage(ip,
			port,
			address_family,
			virtual_path,
			max_connetcion,
			socket_stream,
			ipproto);

	}
	for (int i = 1; i < argc; i = i + 2) {
		if (!strcmp(argv[i], "-c") || !strcmp(argv[i], "check")) {
			IPAdapterAddressChecker ipChecker = IPAdapterAddressChecker();
			ipChecker.get();
			exit(-1);
		}
		else if (!strcmp(argv[i], "-p") || !strcmp(argv[i], "port")) {
			port = atoi(argv[i + 1]);
		}
		else if (!strcmp(argv[i], "-t") || !strcmp(argv[i], "path")) {
			virtual_path = string(argv[i + 1]);
		}
		else if (!strcmp(argv[i], "-h") || !strcmp(argv[i], "host")) {
			ip = string(argv[i + 1]);
		}
		else if (!strcmp(argv[i], "-f") || !strcmp(argv[i], "address_family")) {
			address_family = string(argv[i + 1]) == "AF_INET" ? AF_INET : string(argv[i + 1]) == "AF_INET6" ? AF_INET6 : -1;
			if (address_family == -1) {
				printf("wrong address family value: %s\n", argv[i + 1]);
				exit(-1);
			}
		}
		else if (!strcmp(argv[i], "-n") || !strcmp(argv[i], "max_connection")) {
			max_connetcion = atoi(argv[i + 1]);
			if (max_connetcion <= 0) {
				printf("wrong max connection value: %s\n", argv[i + 1]);
				exit(-1);
			}
		}
		else if (!strcmp(argv[i], "-s") || !strcmp(argv[i], "socket_stream")) {
			socket_stream = string(argv[i + 1]) == "SOCKET_DGRAM " ? server::server::socket_dgram : string(argv[i + 1]) == "SOCKET_STREAM" ? server::server::socket_stream : -1;
			if (socket_stream <= 0) {

				printf("wrong socket stream value: %s\n", argv[i + 1]);
				exit(-1);
			}
		}
		else if (!strcmp(argv[i], "-i") || !strcmp(argv[i], "ipproto")) {
			ipproto =
				string(argv[i + 1]) == "AUTO" ? server::server::ipproto_auto :
				string(argv[i + 1]) == "IPPROTO_TCP" ? server::server::ipproto_tcp :
				string(argv[i + 1]) == "IPPROTO_UDP" ? server::server::ipproto_udp : -1;
			if (socket_stream == -1) {

				printf("wrong ipproto value: %s\n", argv[i + 1]);
				exit(-1);
			}
		}
		else if (!strcmp(argv[i], "-u") || !strcmp(argv[i], "usage")) {
			print_usage(ip,
				port,
				address_family,
				virtual_path,
				max_connetcion,
				socket_stream,
				ipproto);
			exit(-1);
		}
		else {
			print_usage(ip,
				port,
				address_family,
				virtual_path,
				max_connetcion,
				socket_stream,
				ipproto);
			exit(-1);
		}
	}
	server::server * test_val = new server::server();
	test_val->set_ip(ip);
	test_val->set_port(port);
	test_val->set_address_family(address_family);
	test_val->set_virtual_path(virtual_path);
	test_val->set_max_connection(max_connetcion);
	test_val->set_socket_type(socket_stream);
	test_val->set_socket_protocol(ipproto);
	while (true) {
		char buf[1024];
		printf("[enter ] enter command:");
		scanf("%s", buf);
		if (!strcmp(buf, "start")) {
			try {
				test_val->init_sock();
				thread stop_thread(server::_stop, test_val);
				//stop_thread.detach();
				test_val->set_listener();
				stop_thread.join();
			}
			catch (string error) {
				printf("%s", error.c_str());
			}
		}
		else if (!strcmp(buf, "exit")) {
			exit(0);
		}
		else if (!strcmp(buf, "-c") || !strcmp(buf, "check")) {
			IPAdapterAddressChecker ipChecker = IPAdapterAddressChecker();
			ipChecker.get();
		}
		else if (!strcmp(buf, "-p") || !strcmp(buf, "port")) {
			int port = 0;
			printf("[info  ] please enter port num:");
			scanf("%d", &port);
			if (port > 0) {
				test_val->set_port(port);
				printf("[info  ] port has been set: %d\n", test_val->get_port());
			}
			else {
				printf("[error ] wrong port value: %d. try again!\n", port);
			}
		}
		else if (!strcmp(buf, "-t") || !strcmp(buf, "path")) {
			char path_buf[1024];
			printf("[info  ] please enter path:");
			scanf("%s", path_buf);
			test_val->set_virtual_path(string(path_buf));
			printf("[info  ] path has been set: %s!\n", test_val->get_virtual_path().c_str());
		}
		else if (!strcmp(buf, "-h") || !strcmp(buf, "host")) {
			char ip_buf[1024];
			printf("[info  ] please enter ip:");
			scanf("%s", ip_buf);
			test_val->set_ip(string(ip_buf));
			printf("[info  ] ip has been set: %s!\n", test_val->get_ip().c_str());
		}
		else {
			print_usage(ip,
				port,
				address_family,
				virtual_path,
				max_connetcion,
				socket_stream,
				ipproto);
		}

	}
	
	system("pause");
	return 0;
}

void print_usage(
	string default_ip,
	int default_port,
	u_short default_address_family,
	string default_virtual_path,
	int default_max_connetcion,
	int default_socket_stream,
	int default_ipproto)
{
	printf("Usage: \n");
	printf("     -h  host                  the ip you want other to visit web site, if you\n");
	printf("                               don't know, use -c or check to list the ip you computer support\n");
	printf("     -c  check                 list all ip you can use for host ip\n");
	printf("     -t  path                  set path your website's root path\n");
	printf("     -f  address_family        set address family, value can be AF_INET or AF_INET6\n");
	printf("     -n  max_connection        set the number allowed to be connected\n");
	printf("     -s  socket_stream         set socket stream type, value can be SOCKET_DGRAM or SOCKET_STREAM\n");
	printf("     -i  ipproto               set ip protoal type value can be:AUTO¡¢IPPROTO_TCP or IPPROTO_UDP\n");
	printf("     -u  usage\n");
	printf("     if anyone paramater you don't set it will be default\n");
	printf("          ip   :%s\n", default_ip.c_str());
	printf("          port :%d\n", default_port);
	printf("          path :%s\n", default_address_family == AF_INET ? "AF_INET" : "AF_INET6");
	printf("          path :%s\n", default_virtual_path.c_str());
	printf("          path :%d\n", default_max_connetcion);
	printf("          path :%s\n", default_socket_stream == server::server::socket_dgram ? "SOCKET_DGRAM" : "SOCKET_STREAM");
	printf("          path :%s\n", default_ipproto == 0 ? "AUTO" : default_ipproto == server::server::ipproto_tcp ? "IPPROTO_TCP" : "IPPROTO_UDP");

	printf("        enjoy your self\n");
}