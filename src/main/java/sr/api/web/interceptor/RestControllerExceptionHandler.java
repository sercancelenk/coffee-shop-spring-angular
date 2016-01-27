package sr.api.web.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sr.api.core.util.exception.CSRestRuntimeException;
import sr.api.core.business.service.ILoggerService;
import sr.api.web.util.Response;

/**
 * Created by byzas on 16/01/16.
 */

@ControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {

    @Autowired
    ILoggerService loggerService;

    @ExceptionHandler(value = CSRestRuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    Response exception(CSRestRuntimeException exception, WebRequest request){
        Response response = new Response();
        response.addError(exception);
        response.setValid(false);


        loggerService.error(exception.getExceptionMessage(), null);

        return response;
    }

}


