#include "file_writer.h"



FILE_WRITER::FILE_WRITER(char* file_name)
{
	this->file_name = file_name;
	this->outfile = new ofstream;
	this->outfile->open(this->file_name, ios::trunc);
	this->is_first_elem = true;
}
void FILE_WRITER::writer(int num) {
	if (!this->is_first_elem) {
		 *outfile << "  ";

		*outfile << num;
	}else{
		this->is_first_elem = false;
		   *outfile << num;
	}


}
void FILE_WRITER::writer(char ch) {
	if (!this->is_first_elem) {
		 *outfile << "  ";
		*outfile << ch;
	}else{
		this->is_first_elem = false;
		   *outfile << ch;
	}
}

FILE_WRITER::~FILE_WRITER()
{
	this->outfile->close();
	delete this->outfile;
}
