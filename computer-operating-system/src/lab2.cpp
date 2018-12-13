#include"../include/lab2.h"

#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/sem.h>
#include<iostream>
#include<unistd.h>
#include<pthread.h>
int semid;

void run_lab2(){
    key_t semkey = 100;
    semid = semget(semkey, 1 ,IPC_CREAT | 0666);
    if(semid < 0){
        std::cout << "somthing wrong while semget" << std::endl;
        exit(0);
    }
    union semun arg;
    arg.val = 1;
    if(semctl(semid, 0, SETVAL, arg)<0){
        std::cout << "somthing wrong while set the semval" << std::endl;
        exit(0);
    }

    pthread_t p1, p2;
    int num = 0;
    pthread_create(&p1, NULL, subp1, &num);
    pthread_create(&p2, NULL, subp2, &num);
    pthread_join(p1, NULL);
    pthread_join(p2, NULL);

    if(semctl(semid,  IPC_RMID, 0)<0){
        std::cout << "can't delete sem, somthing wrong"<< std::endl;
        exit(0);
    }
    return;
}

void P(int semid, int index){
    struct sembuf sem;
    sem.sem_num = index;
    sem.sem_op = -1;
    sem.sem_flg = 0;
    semop(semid, &sem, 1);
    return;
}

void V(int semid, int index){
    struct sembuf sem;
    sem.sem_num = index;
    sem.sem_op = 1;
    sem.sem_flg = 0;
    semop(semid, &sem, 1);
    return;
}

void *subp1(void *arg){
    for(int i = 1;i<=100;++i){
         P(semid, 0);
        (*((int *)(arg))) += i;

       
        V(semid, 0);
        sleep(1);
    }
}

void *subp2(void *arg){
    for(int i = 1;i<=100;++i){
        P(semid, 0);
        std::cout << (*((int *)(arg))) << std::endl;
        
        V(semid, 0);
        sleep(1);
    }
}
