package sr.api.core.business.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sr.api.core.business.service.ILoggerService;

/**
 * Created by byzas on 16/01/16.
 */

@Service(value = "loggerService")
public class LoggerServiceImpl implements ILoggerService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void info(String s, Object... strings) {
        logger.info(s, strings);
    }

    @Override
    public void info(String s) {
        logger.info(s);
    }

    @Override
    public void error(String s, StackTraceElement stackTrace, Object... strings) {
        StringBuffer sb = new StringBuffer();
        sb.append("[" + stackTrace.getClassName());
        sb.append(" - ");
        sb.append(stackTrace.getMethodName() + "]");
        sb.append(" : ");
        sb.append(s);
        logger.error(sb.toString(), strings);
    }

    @Override
    public void error(String s, StackTraceElement stackTrace) {
        StringBuffer sb = new StringBuffer();
        if(stackTrace != null) {
            sb.append("[" + stackTrace.getClassName());
            sb.append(" - ");
            sb.append(stackTrace.getMethodName() + "]");
            sb.append(" : ");
        }
        sb.append(s);
        logger.error(sb.toString());
    }
}
