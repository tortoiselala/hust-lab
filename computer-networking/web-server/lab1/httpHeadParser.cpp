#include "httpHeadParser.h"
#pragma once

const string httpHeadParser::keyName[] =
	{ "GET", "Content-Type","Host","Connection", "Cache-Control",
		"Upgrade-Insecure-Requests",  "User-Agent",
		"DNT", "Accept", "Accept-Encoding", "Accept-Language" };
const string httpHeadParser::error = string("error");

httpHeadParser::httpHeadParser(string message)
{
	this->message = message;
	this->spiltMessage();
}

httpHeadParser::~httpHeadParser()
{
	this->splitMessageList.clear();
}

void httpHeadParser::spiltMessage()
{
	for (int i = 0, pre = 0; i < this->message.length(); ++i) {
		if (this->message[i] == '\r' && this->message[i + 1] == '\n') {
			string tmp = this->message.substr(pre, i - pre);
			size_t fingKeyGet = tmp.find("GET");
			size_t findKeyHttp = tmp.find("HTTP");
			if (fingKeyGet != std::string::npos && findKeyHttp != std::string::npos) {
				this->splitMessageList.insert(pair<string, string>("GET", this->trim(tmp.substr(fingKeyGet + 3, findKeyHttp - fingKeyGet - 3))));
				this->splitMessageList.insert(pair<string, string>("HTTP", this->trim(tmp.substr(findKeyHttp + 4, tmp.length() - findKeyHttp - 4))));
			}
			else {
				size_t findColonResult = tmp.find(':');
				if (findColonResult != std::string::npos) {
					this->splitMessageList.insert(pair<string, string>(this->trim(tmp.substr(0, findColonResult)), this->trim(tmp.substr(findColonResult + 1, tmp.length()))));
				}
			}

			pre = i + 2;
		}
	}
}

string httpHeadParser::getMessage(string field)
{
	map<string, string>::iterator it = this->splitMessageList.find(field);
	if (it != this->splitMessageList.end()) {
		return this->splitMessageList.at(field);
	}
	return this->error;
	
}

string httpHeadParser::trim(const std::string & str, const std::string & whitespace)
{
	const auto strBegin = str.find_first_not_of(whitespace);
	if (strBegin == std::string::npos)
		return ""; // no content

	const auto strEnd = str.find_last_not_of(whitespace);
	const auto strRange = strEnd - strBegin + 1;

	return str.substr(strBegin, strRange);
}

