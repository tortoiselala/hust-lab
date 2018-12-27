#pragma once

using namespace std;

#ifndef IP_ADAPTER_ADDRESSES_CHECKER
#define IP_ADAPTER_ADDRESSES_CHECKER
#define _WINSOCK_DEPRECATED_NO_WARNINGS
#include <winsock2.h> // for IN_ADDR, 
#include <ws2tcpip.h>
#include <iphlpapi.h> //for PMIB_IPADDRTABLE, GetIpAddrTable
#include <stdio.h>

#pragma comment(lib, "iphlpapi.lib")
#pragma comment(lib, "ws2_32.lib")

#define MALLOC(x) HeapAlloc(GetProcessHeap(), 0, (x))
#define FREE(x) HeapFree(GetProcessHeap(), 0, (x))

/* Note: could also use malloc() and free() */

class IPAdapterAddressChecker {
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
	IPAdapterAddressChecker();
	~IPAdapterAddressChecker(void);
	virtual void get();
};
#endif // !IP_ADAPTER_ADDRESSES_CHECKER
