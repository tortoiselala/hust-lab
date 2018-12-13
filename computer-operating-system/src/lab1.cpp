#include "../include/lab1.h"

#include<signal.h>

#include<unistd.h>
#include<iostream>
#include<cstring>
#include<sys/types.h>
#include<sys/wait.h>

pid_t pid_1;
pid_t pid_2;
int pipefd[2];

void run_lab1()
{
	pipe(pipefd);//create pip, pipefd[0] for read and pipefdp[0] for write
	signal(SIGINT, recvSigInt);
    signal(SIGALRM, recvSigInt);
    alarm(10);

	if ((pid_1 = fork()) == 0) {
		child1();
	}
	if ((pid_2 = fork()) == 0) {
		child2();
	}
	int status;
	waitpid(pid_1, &status, 0);
	waitpid(pid_2, &status, 0);

	close(pipefd[0]);
	close(pipefd[1]);
	printf("Parent Process is Killed!");
	return;
}

int child1(){
    signal(SIGINT, SIG_IGN);//屏蔽终止信号
    signal(SIGUSR1, killChild);
    int count = 0;
    char str[30];
    close(pipefd[0]);
    while(true){
        sprintf(str,"I send you %d times.", count++);
        write(pipefd[1], str,20);
        sleep(1);
    }
}
int child2(){
    signal(SIGINT,SIG_IGN);//屏蔽终止信号
    signal(SIGUSR2, killChild);
    char str[30];
     close(pipefd[1]);
    while(true){
		memset(str, '\0' ,30 );
        read(pipefd[0], str, 20);
		std::cout << str << std::endl;
    }
}

void killChild(int sig){
    if(sig == SIGUSR1){
		std::cout << "Child Process l is Killed by Parent!" << std::endl;
    }else if(sig == SIGUSR2){
		std::cout << "Child Process 2 is Killed by Parent!" << std::endl;
    }
    close(pipefd[0]);
    close(pipefd[1]);

    exit(0);
}

void recvSigInt(int signum) {
	kill(pid_1, SIGUSR1);
	kill(pid_2, SIGUSR2);
}
