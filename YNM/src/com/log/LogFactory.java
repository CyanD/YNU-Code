package com.log;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogFactory {

    public static Logger getInstance(Class c) {
        Logger log = Logger.getLogger(c);
        String filePath = "";
        if (!filePath.endsWith("/")) {//sysInfo.FILE_SEPARATOR
            filePath = filePath + "/";
        }
        PropertyConfigurator.configure(filePath + "conf" + "//" + "log4j.properties");
        return log;
    }
}