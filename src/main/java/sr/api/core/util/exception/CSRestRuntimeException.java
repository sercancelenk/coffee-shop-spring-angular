package sr.api.core.util.exception;

/**
 * Created by byzas on 27/08/15.
 */
public class CSRestRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer errCode;
    private String exceptionMessage;
    private String displayMessage;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public CSRestRuntimeException(Integer errCode) {
        this.errCode = errCode;
    }

    public CSRestRuntimeException(Integer errCode, String exceptionMessage) {
        this.errCode = errCode;
        this.exceptionMessage = exceptionMessage;
    }


    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }
}