package com.study.pattern.facadePattern;


import org.apache.log4j.Logger;

/*
*
* 外观模式
*
* */
public class facadeMain {
    public static final Logger LOGGER = Logger.getLogger(facadeMain.class);
    public static void main(String[] args)
    {
        Computer computer = new Computer();
        computer.start();
        LOGGER.info("=================");
        computer.shutDown();
    }
}
