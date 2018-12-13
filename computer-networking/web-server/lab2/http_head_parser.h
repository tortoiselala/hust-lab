#pragma once

using namespace std;

#ifndef HTTP_HEAD_PARSER
#define HTTP_HEAD_PARSER
#include<iostream>
#include<string>
#include<vector>
#include<map>
namespace http_head_parser {
	enum KEY {
		GET = 0, Content_Type, Host, Connection, Cache_Control, Upgrade_Insecure_Requests,
		User_Agent, DNT, Accept, Accept_Encoding, Accept_Language
	};
	static const KEY key;
	static const string keyName[] = { "GET", "Content-Type","Host","Connection", "Cache-Control","Upgrade-Insecure-Requests","User-Agent","DNT", "Accept", "Accept-Encoding", "Accept-Language" };
	static const string error = string("error");

	class http_head_parser {

	private:

		string message;
		map<string, string> splitMessageList;

		virtual void spiltMessage();
		virtual string trim(const std::string& str, const std::string& whitespace = " \t");
	public:


		http_head_parser(string message);
		~http_head_parser();

		virtual string getMessage(int key);

	};
}
#endif // !HTTP_HEAD_PARSER