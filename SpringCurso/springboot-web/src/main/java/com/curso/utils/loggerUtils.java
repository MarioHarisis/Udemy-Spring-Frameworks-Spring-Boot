package com.curso.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class loggerUtils {

    private static Logger logger = LoggerFactory.getLogger(loggerUtils.class);

    public static Logger getLogger() {
        return logger;
    }

}
