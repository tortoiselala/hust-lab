#include"command_line_parser.h"
#define max_cmd_num 100


COMMAND_LINE_PARSER::COMMAND_LINE_PARSER(int args, char *argv[])
{
	result_head = (cmd_list*)malloc(sizeof(cmd_list));
	cmd_list* tail = result_head;

	for (int i = 1; i < args; i++) {
		if (argv[i][0] == '-' && argv[i][1] >= 'A' && argv[i][1] <= 'Z') {
			tail->next = (cmd_list*)malloc(sizeof(cmd_list));
			tail = tail->next;
			tail->cmd = argv[i][1];
			tail->elem_list = (int *)malloc(sizeof(int) * max_cmd_num);
			tail->size_elem_list = 0;
		}
		else {
			tail->elem_list[tail->size_elem_list++] = atoi(argv[i]);
		}
	}
	tail->next = NULL;
	tail = result_head->next;
	printf("[info] 输入的命令行参数为：\n");
	while (tail != NULL) {
		printf("%c ", tail->cmd);
		for (int i = 0; i < tail->size_elem_list; i++) {
			printf("%d ", tail->elem_list[i]);
		}
	   //	printf("\n");
		tail = tail->next;
	}
	printf("[info] 命令行参数解析完毕\n");
}
cmd_list * COMMAND_LINE_PARSER::get_list()
{
	return this->result_head;
}

COMMAND_LINE_PARSER::~COMMAND_LINE_PARSER()
{
	while (result_head != NULL) {
		cmd_list * tmp = result_head->next;
		free(result_head);
		result_head = tmp;
	}
}
