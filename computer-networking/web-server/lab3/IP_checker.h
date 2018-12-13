//#pragma once



#ifndef IP_CHECKER
#define IP_CHECKER
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#include <winsock2.h>
#include <ws2tcpip.h>
#include <iphlpapi.h>
#include <stdio.h>
#include<string>
using namespace std;

#pragma comment(lib, "iphlpapi.lib")
#pragma comment(lib, "ws2_32.lib")

#define MALLOC(x) HeapAlloc(GetProcessHeap(), 0, (x))
#define FREE(x) HeapFree(GetProcessHeap(), 0, (x))

/* Note: could also use malloc() and free() */

typedef struct IP_checker_result {
	string * result;
	int num;
}checker_result;

class IP_checker {
private:
	int i;

	/* Variables used by GetIpAddrTable */
	PMIB_IPADDRTABLE pIPAddrTable;
	DWORD dwSize = 0;
	DWORD dwRetVal = 0;
	IN_ADDR IPAddr;

	/* Variables used to return error message */
	LPVOID lpMsgBuf;
	
public:
	IP_checker(void);
	~IP_checker(void);

	virtual checker_result* get_IP_list();
	virtual void get();
};
#endif // !IP_CHECKER
