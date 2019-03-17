#pragma once

#include<iostream>
#include<cstdio>

#define COMMAND_LINE_PARSER_H

using namespace std;

typedef struct cmd_struct {

	int* elem_list;
	int size_elem_list;
	char cmd;
	struct cmd_struct *next;
}cmd_list;

class COMMAND_LINE_PARSER
{
	cmd_list * result_head;
public:
	COMMAND_LINE_PARSER(int argc, char *argv[]);
	cmd_list* get_list();
	~COMMAND_LINE_PARSER();
};
