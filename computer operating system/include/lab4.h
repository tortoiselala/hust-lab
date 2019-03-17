#ifndef LAB_4_H
#define LAB_4_H

#include<sys/stat.h>
#include<string>


class ls{
    std::string path;

public:
    ls(std::string p){
        this->path = p;
    }
    ~ls(){};

    void ls_l(){
        ls_l(this->path, 0);
    }
    void ls_l(std::string path,int depth);
private:
    //打印目录相关信息
    void printDir(const struct dirent &dirst, int depth);
    //打印文件相应信息
    void printFile(const struct dirent & dirst, const struct stat &sb, int depth);
    //生成权限描述符
    std::string getMod(const struct stat & fileInfo);
    std::string getUser(const struct stat & fileInfo);
    std::string getInfoAndSizeAndTime(const struct stat & fileInfo, const struct dirent & dirst);
};

void run_lab4();
#endif//!LAB_4_H