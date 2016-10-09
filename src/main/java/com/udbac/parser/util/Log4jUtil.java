package com.udbac.parser.util;

import org.apache.log4j.Logger;


public class Log4jUtil {
    Logger logger = Logger.getLogger(Log4jUtil.class);

    public void sqlError(String sql) {
        logger.error(sql);
    }

}
