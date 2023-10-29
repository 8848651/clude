package com.base.xczx_plus_base.base.exception;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(XczxPlusException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public RestErrorResponse customException(XczxPlusException e){
        RestErrorResponse restErrorResponse = new RestErrorResponse(e.getErrMessage());
        return restErrorResponse;
   }


   @ExceptionHandler(Exception.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public RestErrorResponse exception(Exception e){
        RestErrorResponse restErrorResponse = new RestErrorResponse("执行过程异常");
        return restErrorResponse;
   }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse methodException(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError FieldError:fieldErrors) {
            list.add(FieldError.getDefaultMessage());
        }
        String errMessage = StringUtils.join(list, ",");
        RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
        return restErrorResponse;
    }


}
