package com.tortoiselala.course.java.lab2.view;

import com.tortoiselala.course.java.lab2.base.ExceptionHandler;
import com.tortoiselala.course.java.lab2.base.User;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    private LoginFrame loginFrame;
    private DoctorFrame doctorFrame;

    private ExecutorService executorService;
    public static User user;
    public static final CountDownLatch count = new CountDownLatch(1);

    public Main() {
        this.loginFrame = new LoginFrame();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void start() {
        runLoginTask();
        try {
            Main.count.await();
            if (Main.user.isPermission()) {
                if (Main.user.isDocterOrPatient()) {
                    this.doctorFrame = new DoctorFrame();
                    this.executorService.submit(this.doctorFrame);
                }
            }
        } catch (InterruptedException e) {
            ExceptionHandler.handler(e);
        }
        this.executorService.shutdown();
    }

    private void runLoginTask() {
        this.executorService.submit(loginFrame);

    }
}
