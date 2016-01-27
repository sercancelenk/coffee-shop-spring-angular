package sr.api.web.util;

import sr.api.core.util.exception.CSRestRuntimeException;

/**
 * Created by byzas on 04/06/15.
 */
public class Response {
    private Object data;
    private long duration;
    private boolean valid = true;
    private ResponseError error;

    public Response() {
    }

    public Response(Object data) {
        this.data = data;
    }

    public void addError(Throwable exception) {

        ResponseError error = new ResponseError();
        if (exception instanceof CSRestRuntimeException) {
            error.setId(((CSRestRuntimeException) exception).getErrCode());
            error.setDescriptionText(((CSRestRuntimeException) exception).getExceptionMessage());

        } else {
            error.setId(1002);
            error.setDescriptionText("Generic Exception");
        }
        this.setError(error);
        valid = false;
    }

    public ResponseError getError() {
        return error;
    }

    public void setError(ResponseError error) {
        this.error = error;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}