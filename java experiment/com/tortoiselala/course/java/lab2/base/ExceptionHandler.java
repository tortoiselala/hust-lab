package com.tortoiselala.course.java.lab2.base;

import java.util.logging.Logger;

public class ExceptionHandler {

    private static Logger log = Logger.getLogger("log");

    public static void handler(Exception e){
        log.severe(e.getMessage());
        e.printStackTrace();
    }
    private ExceptionHandler(){};
}
