#include"../include/lab3.h"
#include"../include/lab2.h"

#include<iostream>
#include<sys/types.h>
#include<sys/shm.h>
#include<sys/ipc.h>
#include<sys/sem.h>
#include<unistd.h>
#include<sys/wait.h>
#include<cstring>

void run_lab3(){
    //创建共享缓冲区
    int shareBufferid = shmget(100, 200, IPC_CREAT | 0666);
    if(shareBufferid < 0){
        std::cout << "currupt while getting buffer id" << std::endl;
        exit(0);
    }

    char *buffer = (char *)shmat(shareBufferid, NULL, SHM_R | SHM_W);

    //创建信号灯
    //0号信号灯用于标识缓冲区是否为空
    //1号信号灯用于标识是否有数据
    key_t semkey= 100;
    int semid = semget(semkey, 2, IPC_CREAT | 0666);
    if(semid < 0){
        std::cout << "currupt while getting semid" << std::endl;
        exit(0);
    }
    //信号灯赋初值
    union semun arg;
    arg.val = 1;
    if(semctl(semid, 0, SETVAL, arg)<0){
        std::cout << "somthing wrong while set the semval" << std::endl;
        exit(0);
    }
    arg.val = 0;
    if(semctl(semid, 1, SETVAL, arg)<0){
        std::cout << "somthing wrong while set the semval" << std::endl;
        exit(0);
    }
    pid_t pid_1;
    pid_t pid_2;
    //创建进程1
    if ((pid_1 = fork()) == 0) {
		writebuf(semid, shareBufferid, buffer);
        exit(0);
	}
    //创建进程2
    if ((pid_2 = fork()) == 0) {
		readbuf(semid, shareBufferid, buffer);
        exit(0);
	}
    //等待进程结束
    int status;
	waitpid(pid_1, &status, 0);
	waitpid(pid_2, &status, 0);
    //删除信号灯
    if(semctl(semid, IPC_RMID, 0)<0){
        std::cout << "can't delete sem, somthing wrong"<< std::endl;
        exit(0);
    }
    //删除共享内存组
    if(semctl(shareBufferid, IPC_RMID, 0)<0){
        std::cout << "can't delete share buffer, somthing wrong"<< std::endl;
        exit(0);
    }
    return;
}

void *readbuf(int shmid, int shareBufferId, char *shareBufferAddress){
    char buffer[200];
    for(int i = 1;i < 100; ++i){
        
        P(shmid, 0);
        std::cout << "[write] please input some text(less than 200 byte):";
        memset(buffer, '\0', 200);
        std::cin >> buffer;
        strncpy(shareBufferAddress, buffer, 200);
        V(shmid, 1);
        sleep(1);
    }
}

void *writebuf(int shmid, int shareBufferId, char *shareBufferAddress){
    char buffer[200];
    for(int i = 1;i < 100; ++i){
        P(shmid, 1);
       
        memset(buffer, '\0', 200);
        strncpy(buffer, shareBufferAddress, 200);
        std::cout<<"[read] " << buffer << std::endl;
        V(shmid, 0);
        sleep(1);
    }
}
