#ifndef LAB_2_H
#define LAB_2_H

void run_lab2();
void P(int semid, int index);
void V(int semid, int index);
void *subp1(void *arg);
void *subp2(void *arg);

union semun {
               int              val;    /* Value for SETVAL */
               struct semid_ds *buf;    /* Buffer for IPC_STAT, IPC_SET */
               unsigned short  *array;  /* Array for GETALL, SETALL */
               struct seminfo  *__buf;  /* Buffer for IPC_INFO
                                           (Linux-specific) */
};

#define debug(message) std::cout<<message<<std::endl;

#endif //!LAB_2_H