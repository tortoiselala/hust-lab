#include"stack_operator_overloading.h"


bool cmd_check = false;
STACK::STACK(int m):max(m), elems(new int[m]) {		//��ʼ��ջ������m��Ԫ��
	this->pos = 0;
}
STACK::STACK(const STACK&s):max(s.max), elems(s.elems) {			//��ջs������ʼ��ջ
	this->pos = s.pos;
}
int STACK::size() const {			//����ջ�����Ԫ�ظ���max
	return this->max;
}
STACK::operator int() const
{
	return this->pos;
}
int STACK::operator[] (int x) const {	//ȡ�±�x����ջԪ�أ���1��Ԫ��x=0
	if (x >= this->pos) {
		cout << "[error] ջ����" << this->pos << "��Ԫ�أ��޷�ȡ��λ��Ϊ" << x << "��Ԫ��";
		cmd_check = true;
		return 0;
	}
	return this->elems[x];
}
STACK& STACK::operator<<(int e) { 	//��e��ջ,������ջ
	if (this->pos >= this->size()) {
		cout << "[error] ջ����,��ջʧ��" << endl;
		cmd_check = true;
		return *this;
	}
	this->elems[this->pos++] = e;
	return *this;
}
STACK& STACK::operator>>(int &e) {	//��ջ��e,������ջ
	if (this->pos <= 0) {
		e = 0;
		return *this;
	}
	e = this->elems[--this->pos];
	return *this;
}
STACK& STACK::operator=(const STACK&s){ //��s��ջ,�����ر���ֵ��ջ
	delete[] this->elems;
	*((int **)&elems) = new int[s.max];
	*((int *)&(this->max))=s.max;
	for(int i = 0;i <s.pos;i++){
     	this->elems[i] = s.elems[i];
	}
	this->pos = s.pos;
	return *this;
}
void  STACK::print() const {			//��ӡջ
	cout << "[info] stack elme to string:[";
	for (int i = 0; i < this->pos; i++) {
		cout << this->elems[i];
		if (i != this->pos - 1) {
			cout << ", ";
		}
		else {
			cout << "]" << endl;
		}
	}
	for (int i = 0; i < this->pos; i++) {
		this->file_writer->writer(this->elems[i]);
	}
}
STACK::~STACK() {
	delete this->elems;
	this->pos = 0;
}

void run_stack_2(int argc, char* argv[]) {
	char file_name[] = "U201614515_3.txt";
	COMMAND_LINE_PARSER * command_line_parser = new COMMAND_LINE_PARSER(argc, argv);
	cmd_list * head = command_line_parser->get_list();
	cmd_list *tail = head->next;
	STACK* stack = new STACK(10);
	while (tail != NULL) {
		switch (tail->cmd) {
		case 'S':
			stack = new STACK(tail->elem_list[0]);
			stack->file_writer = new FILE_WRITER(file_name);
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			stack->file_writer->writer(tail->elem_list[0]);
			printf("%d  ", tail->elem_list[0]);
			break;
		case 'I':
			for (int i = 0; i < tail->size_elem_list; i++) {
				(*stack) << tail->elem_list[i];
			}
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			if (cmd_check == true) {
				stack->file_writer->writer('E');
				printf("%c  ", 'E');
				return;
			}
			stack->print();
			break;
		case 'O':
			int e;
			printf("option O point hit\n");
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			printf("option O point hit par: size = %d\n", tail->size_elem_list);
			
			if (tail->size_elem_list == 0 || tail->elem_list[0] > int(*stack)) {
				cmd_check = true;
			}
			else {
				
				for (int i = 0; i < tail->elem_list[0]; i++) {
					(*stack) >> e;
				}
			}
			if (cmd_check == true) {
				stack->file_writer->writer('E');
				printf("%c ", 'E');
				return;
			}

			stack->print();
			break;
		case 'C':
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			stack->print();
			break;
		case 'A':
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			stack->print();
			break;
		case 'N':
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			stack->file_writer->writer(int(*stack));
			//_num(howMany(&stack_main));
			break;
		case 'G':
			stack->file_writer->writer(tail->cmd);
			printf("%c  ", tail->cmd);
			if (tail->elem_list[0] >= int(*stack)) {
				stack->file_writer->writer('E');
				printf("%c  ", 'E');
				return;
			}
			else {
				stack->file_writer->writer((*stack)[tail->elem_list[0]]);
			}
			break;
		default:
			break;
		}
		tail = tail->next;
	}
	delete stack->file_writer;
	//system("pause");
}