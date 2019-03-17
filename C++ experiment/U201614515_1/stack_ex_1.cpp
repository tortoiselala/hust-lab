#include"stack_ex_1.h"

bool cmd_check_result = false;
char  file_path[] = "U201614515_1.txt";
FILE_WRITER * file_writer = new FILE_WRITER(file_path);
void run_stack(int argc, char *argv[]) {
	
	
	COMMAND_LINE_PARSER * parser = new COMMAND_LINE_PARSER(argc, argv);
	cmd_list * cmd_list_prase_result = parser->get_list();
	cmd_list * tail = cmd_list_prase_result->next;
	STACK stack_main;//��ջ
	while (tail != NULL) {

		switch (tail->cmd) {
		case 'S':
			initSTACK(&stack_main, tail->elem_list[0]);
			file_writer->writer(tail->cmd);
			//file_writer(file_path, tail->cmd);
			file_writer->writer(tail->elem_list[0]);
			//file_writer_num(tail->elem_list[0]);
			break;
		case 'I':
			for (int i = 0; i < tail->size_elem_list; i++) {
				push(&stack_main, tail->elem_list[i]);
			}
			file_writer->writer(tail->cmd);
			if (cmd_check_result == true) {
				file_writer->writer('E');
				return;
			}
			print(&stack_main);
			break;
		case 'O':
			int e;
			file_writer->writer(tail->cmd);
			for (int i = 0; i < tail->elem_list[0]; i++) {
				pop(&stack_main, e);
			}

			if (cmd_check_result == true) {
				file_writer->writer('E');
				return;
			}

			print(&stack_main);
			break;
		case 'C':
			assign(&stack_main, stack_main);

			file_writer->writer(tail->cmd);
			print(&stack_main);
			break;
		case 'A':
			assign(&stack_main, stack_main);
			file_writer->writer(tail->cmd);
			print(&stack_main);
			break;
		case 'N':
			file_writer->writer(tail->cmd);
			file_writer->writer(howMany(&stack_main));
			break;
		case 'G':
			file_writer->writer(tail->cmd);
			if (tail->elem_list[0] >= howMany(&stack_main)) {
				file_writer->writer('E');
				return;
			}
			else {
				file_writer->writer(getelem(&stack_main, tail->elem_list[0]));
				//file_writer_num(getelem(&stack_main, tail->elem_list[0]));
			}
			break;
		default:
			break;
		}
		tail = tail->next;
	}
	delete file_writer;
}

void initSTACK(STACK *const p, int m) {//��ʼ��pָ���ջ�����m��Ԫ��
	if (m <= 0) {
		printf("[error] ���ܳ�ʼ���ռ��СΪ%d��ջ!\n", m);
		return;
	}
	p->elems = (int *)malloc(sizeof(int) * m);
	p->max = m;
	p->pos = 0;
}
void initSTACK(STACK *const p, const STACK &s) {//��ջs��ʼ��pָ���ջ
	if (s.max == 0) {
		printf("[error]  ջsΪ��,���ʼ����\n");
		return;
	}
	initSTACK(p, s.max);
	for (int i = 0; i < s.pos; i++) {
		*(p->elems + i) = *(s.elems + i);
		++(p->pos);
	}
}
int  size(const STACK *const p) {//����pָ���ջ�����Ԫ�ظ���max
	return p->max;
}
int  howMany(const STACK *const p) {//����pָ���ջ��ʵ��Ԫ�ظ���pos
	return p->pos;
}
int  getelem(const STACK *const p, int x) {//ȡ�±�x����ջԪ��
	if (x < 0) {
		printf("[error] ���ܻ�ȡλ��%d����ջԪ��!\n", x);
		return 0;
	}
	return (p->elems)[x];
}

STACK *const push(STACK *const p, int e) {//��e��ջ��������p
	if (howMany(p) == size(p)) {
		printf("[error] ջ������\n");
		cmd_check_result = true;
		return p;
	}
	if (size(p) == 0) {
		printf("[error] ջ��СΪ0��\n");
		cmd_check_result = true;
		return p;
	}
	p->elems[p->pos++] = e;
	return p;
}

STACK *const pop(STACK *const p, int &e) {//��ջ��e��������p
	if (howMany(p) == 0) {
		printf("[erroe] ջΪ�գ�\n");
		cmd_check_result = true;
		return p;
	}
	e = (p->elems)[p->pos--];
	return p;
}

STACK *const assign(STACK*const p, const STACK&s) {//��s��pָ��ջ,������p
	p->elems = s.elems;
	p->max = s.max;
	p->pos = s.pos;
	return p;

}
void print(const STACK*const p) {			//��ӡpָ���ջ
	printf("[elem in stack]  [");
	for (int i = 0; i < p->pos; i++) {
		printf("%d", p->elems[i]);
		if (i != p->pos - 1) {
			printf(", ");
		}
	}
	printf("]\n");
	for (int i = 0; i < p->pos; i++) {
		file_writer->writer(p->elems[i]);
		//file_writer_num(p->elems[i]);
	}
	return;
}
void destroySTACK(STACK*const p) {		//����pָ���ջ
	free(p->elems);
	p->max = 0;
	p->pos = 0;
}