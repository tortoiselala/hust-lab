#include "queue_ex_6.h"


QUEUE::QUEUE(int m) :s2(m), STACK(m) {}

QUEUE::QUEUE(const QUEUE & s) : s2(s.s2), STACK(s.s2.size()) {}

QUEUE::operator int() const
{
   //	cout << "[info] breakpoint in class:QUEUE function: operator int message:pos of queue: " << STACK::operator int() + (int)(this->s2) ;
	return STACK::operator int() + (int)(this->s2);
}

QUEUE & QUEUE::operator<<(int e)
{

	if (STACK::operator int() >=  this->size() && (int)(this->s2) == 0) {
	   //	cout << "[info] breakpoint in class:QUEUE function: operator << message: stack 1 status: full ,transfer to stack 2";

		while (STACK::operator int() > 0) {
			int tmp = 0;
			STACK:: operator >> (tmp);
			this->s2 << tmp;
		}
	}
	if (STACK::operator int() >=  this->size()) {
	   //	cout << "[error] breakpoint in class:QUEUE function: operator << message: queue full ";
		this->error_flag = true;
		return *this;
	}
	STACK:: operator << (e);
   //	cout << "[info] breakpoint in class:QUEUE function: operator << message: push " << e;
	return *this;
}

QUEUE & QUEUE::operator>>(int & e)
{
	if ((int)(this->s2) > 0) {
		this->s2 >> e;
	}
	else if (STACK::operator int() >= 1) {
		while (STACK::operator int() > 1) {
			int tmp = 0;
			STACK::operator>> (tmp);
			this->s2 << tmp;
		}
		STACK::operator >> (e);
		while ((int)(this->s2) > 0) {
			int tmp = 0;
			this->s2 >> tmp;
			STACK::operator << (tmp);
		}
	}
	else {
	   //	cout << "[error] breakpoint in class:QUEUE function: operator >> message: empty queue ";
		this->error_flag = true;
	}
   //	cout << "[info] breakpoint in class:QUEUE function: operator >> message: pop " << e;
	return *this;
}

int QUEUE::operator[](int x) 
{
	if (x >= (int)(this->s2)) {
		x -= (int)(this->s2);
	}
	else {
	   //	cout << "[info] breakpoint in class:QUEUE function: operator[] message:get elem in stack 2" << endl;
		return (this->s2)[x];
	}
	if (x + 1 >(STACK::operator int())) {
	   //	cout << "[error] breakpoint in class:QUEUE function: operator[] message:error index" << endl;
		STACK::error_flag = true;
		return 0;
	}
	return STACK::operator [](x);
}

QUEUE & QUEUE::operator=(const QUEUE & s)
{
	this->s2 = s.s2;
	STACK::operator=(s);
	return *this;
}

void QUEUE::print() const
{
	//cout << "[info] breakpoint in class:QUEUE function: print message:开始打印队列元素" << endl;
	for (int i = (int)(this->s2) - 1; i >= 0; --i) {
		this->file_writer->writer((this->s2)[i]);

	}
	for (int i = 0; i < STACK::operator int(); ++i) {
		this->file_writer->writer(STACK::operator[](i));
	}
}

QUEUE::~QUEUE()
{
	this->file_writer->~FILE_WRITER();
	this->STACK::~STACK();
}

void run_queue_ex_6(int argc, char * argv[])
{
char file_name[] = "U201614515_4.txt";
	if(argc >1){

	COMMAND_LINE_PARSER * command_line_parser = new COMMAND_LINE_PARSER(argc, argv);
	cmd_list * head = command_line_parser->get_list()->next;
	QUEUE * queue = new QUEUE(10);
	while (head != NULL) {
		switch (head->cmd) {
		case 'S':
			queue = new QUEUE(head->elem_list[0]);
			queue->file_writer = new FILE_WRITER(file_name);
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			queue->file_writer->writer(head->elem_list[0]);
			printf("%d  ", head->elem_list[0]);
			break;
		case 'I':
			for (int i = 0; i < head->size_elem_list; i++) {
				(*queue) << head->elem_list[i];
			}
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			if (queue->error_flag == true) {
				queue->file_writer->writer('E');
				printf("%c  ", 'E');
				return;
			}
			queue->print();
			break;
		case 'O':
			int e;
			printf("option O point hit\n");
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			printf("option O point hit par: size = %d\n", head->size_elem_list);
			if (head->size_elem_list == 0 || head->elem_list[0] > int(*queue)) {
				queue->error_flag = true;
			}
			else {

				for (int i = 0; i < head->elem_list[0]; i++) {
					(*queue) >> e;
				}
			}
			if (queue->error_flag == true) {
				queue->file_writer->writer('E');
				printf("%c ", 'E');
				return;
			}

			queue->print();
			break;
		case 'C':
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			queue->print();
			break;
		case 'A':
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			queue->print();
			break;
		case 'N':
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			queue->file_writer->writer(int(*queue));
			//_num(howMany(&stack_main));
			break;
		case 'G':
			queue->file_writer->writer(head->cmd);
			printf("%c  ", head->cmd);
			if (head->elem_list[0] >= int(*queue)) {
				queue->file_writer->writer('E');
				printf("%c  ", 'E');
				return;
			}
			else {
				queue->file_writer->writer((*queue)[head->elem_list[0]]);
			}
			break;
		default:
			break;
		}
		head = head->next;
	}
	}else{
        while (true)
		{
			cout << "input the number of man and women:" << endl;
			int M, F;
			cin >> M >> F;
			if (M == F)
			{
				cout << "input error! can not input equal number!" << endl;
				continue;
			}
			if (!isPrime(M) || !isPrime(F))
			{
				cout << "input error! must input prime num!" << endl;
				continue;
			}
			cout << "input location of the man and the woman:" << endl;
			int manLocation, womanLocation;
			cin >> manLocation >> womanLocation;
			QUEUE man(M), woman(F);//男士队伍，女士队伍
			man.file_writer = new FILE_WRITER(file_name);
			woman.file_writer =new FILE_WRITER(file_name);
			int i;
			/*初始化队伍*/
			for (i = 1; i <= M; i++)
				man << i;
			for (int i = 1; i <= F; i++)
				woman << i;
			int res = 1;
			/*模拟跳舞*/
			while (1)
			{
				int m, w;
				man >> m; woman >> w;
				if (m == manLocation && w == womanLocation) break;
				man << m; woman << w;
				res++;
				//if (res > 10000) { cout << "no answer" << endl; break; }
			}
			cout << "在第" << res << "支舞曲该男士可和该女士一起跳舞" << endl;
			system("pause");
		}

	}
	system("pause");
}
