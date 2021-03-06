// SocketServerLevel1.cpp: 定义控制台应用程序的入口点。
#define _CRT_SECURE_NO_WARNINGS
#include"SocketServerLevel1.h"

int main(int argc, char* argv[])
{	
	string ip = string("127.0.0.1");
	int port = 8080;
	string virtualPath = string("F:/index");
	
	for (int i = 1; i < argc; i = i+2) {
		if (!strcmp(argv[i], "-c")|| !strcmp(argv[i], "check")) {
			IPAdapterAddressChecker ipChecker = IPAdapterAddressChecker();
			ipChecker.get();
			exit(-1);
		}
		else if(!strcmp(argv[i],"-p") || !strcmp(argv[i], "port")){
			port = atoi(argv[i + 1]);
		}
		else if (!strcmp(argv[i], "-t") || !strcmp(argv[i], "path")) {
			virtualPath = string(argv[i + 1]);
		}
		else if (!strcmp(argv[i], "-h") || !strcmp(argv[i], "host")) {
			ip = string(argv[i + 1]);
		}
		else {
			print_cmd_usage();
			exit(-1);
		}
	}
	print_running_usage();
	//server * ser = new server(ip, port, virtualPath);
	server * ser = new server(ip,port, virtualPath);
	while (true) {
		char buf[1024];
		printf("[enter ] enter command:");
		scanf("%s", buf);
		if (!strcmp(buf, "start")) {
			if (ser->start_pause == true) {
				printf("[error ] server is running, please pause first!\n");
			}
			else {
				ser->start();
				}
		}
		else if (!strcmp(buf, "exit")) {
			exit(0);
		}
		else if (!strcmp(buf, "stop")) {
			if (ser->start_pause == false) {
				printf("[error ] server is not running, please start first!\n");
			}
			else {
				ser->start();
				printf("[info ] Service has paused successfully !\n");
			}
		}else if (!strcmp(buf, "-c") || !strcmp(buf, "check")) {
			IPAdapterAddressChecker ipChecker = IPAdapterAddressChecker();
			ipChecker.get();
		}
		else if (!strcmp(buf, "-p") || !strcmp(buf, "port")) {
			int port = 0;
			printf("[info  ] please enter port num:");
			scanf("%d",&port);
			if (port > 0) {
				ser->port = port;
				printf("[info  ] port has been set: %d\n", ser->port);
			}
			else {
				printf("[error ] wrong port value: %d. try again!\n", port);
			}
		}
		else if (!strcmp(buf, "-t") || !strcmp(buf, "path")) {
			char path_buf[1024];
			printf("[info  ] please enter path:");
			scanf("%s", path_buf);
			ser->setVirtualPath(string(path_buf));
			printf("[info  ] path has been set: %s!\n", ser->virtualPath.c_str());
		}
		else if (!strcmp(buf, "-h") || !strcmp(buf, "host")) {
			char ip_buf[1024];
			printf("[info  ] please enter ip:");
			scanf("%s", ip_buf);
			ser->ip = string(ip_buf);
			printf("[info  ] ip has been set: %s!\n", ser->ip.c_str());
		}
		else {
			print_running_usage();
		}
	}
	system("pause");
}