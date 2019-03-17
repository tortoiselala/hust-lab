#define FILE_WRITER_H

#include<string>
#include<iostream>
#include<fstream>



using namespace std;
class FILE_WRITER {
	bool is_first_elem;
	char* file_name;
	ofstream* outfile;

public:

	FILE_WRITER(char* file_name);
	void writer(int num);
	void writer(char ch);
	~FILE_WRITER();
};
