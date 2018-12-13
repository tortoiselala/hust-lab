#ifndef LAB_3_H
#define LAB_3_H

void run_lab3();
void *readbuf(int shmid, int shareBufferId, char *shareBufferAddress);
void *writebuf(int shmid, int shareBufferId, char *shareBufferAddress);

#endif//!LAB_3_H