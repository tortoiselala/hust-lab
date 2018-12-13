#include "../include/lab4.h"
#include<iostream>
#include<cstring>
#include<sys/types.h>
#include<sys/stat.h>
#include<dirent.h>//for DIR
#include<unistd.h>
#include<pwd.h>//for getpwuid



/*
目录打印样式
+dictionary name
  -file name 1
  -file name 2
  +sub dictionary name
    -file name 1
    -file name 2
  -file name 3

depth计算打印的空格数量，每层目录，空格加2
*/

void ls::ls_l(std::string path, int depth){
    std::string pathBackup(path);
    struct dirent *entry = NULL;
    struct stat fileInfo;
    struct dirent* dirst = NULL;
    DIR *dirp = opendir(path.c_str());
    if(dirp == NULL){
        std::cout << "[error] open dir: " << path << " false" << std::endl;
        exit(-1);
    }
    for(dirst = readdir(dirp); dirst; dirst = readdir(dirp)){
        if(std::string(dirst->d_name) == "." || (std::string(dirst->d_name) == "..")){
            continue;
        }
        lstat((path + "/" + dirst->d_name).c_str(), &fileInfo);
        if(S_ISDIR(fileInfo.st_mode)){
            printDir(*dirst, depth);
            ls_l(path + "/" + dirst->d_name, depth + 1);
        }else{
            printFile(*dirst, fileInfo, depth);
        }
    }
}

void ls::printDir(const struct dirent &dirst, int depth){
     char buffer[100];
    memset(buffer, '\0', 100);
    memset(buffer, ' ', (depth +1)* 2);
    std::string result(buffer);
    result += "+";
    result += dirst.d_name;
    std::cout << result << std::endl;
}
void ls::printFile(const struct dirent & dirst, const struct stat &fileInfo, int depth){
    
    char buffer[100];
    memset(buffer, '\0', 100);
    memset(buffer, ' ', (depth +1)* 2);
    std::string result(buffer);
    result += "-";
    result = result + getMod(fileInfo) + getUser(fileInfo) + getInfoAndSizeAndTime(fileInfo, dirst);
    std::cout << result << std::endl;
}
std::string ls::getMod(const struct stat & fileInfo){
    mode_t mode = fileInfo.st_mode;
    std::string formatedLine;
    return formatedLine 
        + (S_ISDIR(mode)?"d":"-")
        + ((mode&S_IRWXU)&S_IRUSR?"r":"-")
        + ((mode&S_IRWXU)&S_IWUSR?"w":"-")
        + ((mode&S_IRWXU)&S_IXUSR?"x":"-")
        + ((mode&S_IRWXG)&S_IRGRP?"r":"-")
        + ((mode&S_IRWXG)&S_IWGRP?"w":"-")
        + ((mode&S_IRWXG)&S_IXGRP?"x":"-")
        + ((mode&S_IRWXO)&S_IROTH?"r":"-")
        + ((mode&S_IRWXO)&S_IWOTH?"w":"-")
        + ((mode&S_IRWXO)&S_IXOTH?"x":"-");
}
std::string ls::getUser(const struct stat & fileInfo){
    return std::string(getpwuid(fileInfo.st_uid)->pw_name);
}
std::string ls::getInfoAndSizeAndTime(const struct stat & fileInfo, const struct dirent & dirst){
    
    char info[1000];
    char ctm[10];
    char formatCtm[10];
    memset(info, '\0', 100);
    struct tm * ltm;
    ltm = localtime(&fileInfo.st_mtime);
    sprintf(info, "%10d%10d", fileInfo.st_ino, fileInfo.st_size);
    std::string result(info);
    strftime(ctm, 9, "%b", ltm);
    sprintf(formatCtm, "%5s", ctm);
    result += formatCtm;
    strftime(ctm, 9, "%d", ltm);
    sprintf(formatCtm, "%3s", ctm);
    result += formatCtm;
    strftime(ctm, 9, "%Y", ltm);
    sprintf(formatCtm, "%5s", ctm);
    result += formatCtm;
    result += "   ";
    result += dirst.d_name;
    return result;
}

void run_lab4(){
    std::cout << "[input] enter a path please:" << std::endl;
    std::string path;
    std::cin >> path;
    ls l(path);
    l.ls_l();
    return;
}