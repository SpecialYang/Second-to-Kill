package com.specialyang.secondstokill.exception;

import com.specialyang.secondstokill.entity.CodeMsg;
import com.specialyang.secondstokill.entity.Response;
import com.sun.tools.javac.main.Main;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Fan Yang in 2018/12/6 11:57 AM.
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Response<String> exceptionHandler(HttpServletRequest request, Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            return Response.error(ge.getCm());
        }
        else if (e instanceof BindException) {
            e.printStackTrace();
            BindException be = (BindException) e;
            List<ObjectError> errors = be.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Response.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            e.printStackTrace();
            return Response.error(CodeMsg.SERVER_ERROR);
        }
    }
}
