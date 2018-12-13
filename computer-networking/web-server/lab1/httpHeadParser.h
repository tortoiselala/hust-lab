#pragma once

using namespace std;

#ifndef HTTP_HEAD_PARSER
#define HTTP_HEAD_PARSER
#include<iostream>
#include<string>
#include<vector>
#include<map>

class httpHeadParser {
	

private:
	
	string message;
	map<string, string> splitMessageList;
	virtual string trim(const std::string& str,
		const std::string& whitespace = " \t");
	virtual void spiltMessage();
public:
	enum KEY {
		GET = 0,
		Content_Type,
		Host, Connection, Cache_Control,
		Upgrade_Insecure_Requests, User_Agent,
		DNT, Accept, Accept_Encoding, Accept_Language
	};
	static const KEY key;
	static const string keyName[];
	static const string error;
	httpHeadParser(string message);
	~httpHeadParser();

	virtual string getMessage(string field);

};
#endif // !HTTP_HEAD_PARSER