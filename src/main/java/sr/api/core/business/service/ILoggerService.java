package sr.api.core.business.service;

/**
 * Created by byzas on 16/01/16.
 */
public interface ILoggerService {
    void info(String s, Object... strings);
    void info(String s);
    void error(String s,StackTraceElement stackTrace, Object... strings);
    void error(String s, StackTraceElement stackTrace);
}
