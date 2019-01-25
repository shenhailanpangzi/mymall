package org.linlinjava.litemall.core.config;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 如果系统内部产生了异常而开发者没有catch，那么异常的内容会发送到前端。
 * 这里通过提供全局异常处理器，来处理所有开发者没有处理的异常，
 * 返回 “系统内部错误”之类的信息给前端从而达到保护系统的效果。
 */
//在spring 3.2中，新增了@ControllerAdvice 注解，可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute，并应用到所有@RequestMapping中。
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object badArgumentHandler(IllegalArgumentException e) {
        e.printStackTrace();
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Object badArgumentHandler(MethodArgumentTypeMismatchException e) {
        e.printStackTrace();
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public Object badArgumentHandler(MissingServletRequestParameterException e) {
        e.printStackTrace();
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public Object badArgumentHandler(HttpMessageNotReadableException e) {
        e.printStackTrace();
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public Object badArgumentHandler(ValidationException e) {
        e.printStackTrace();
        if (e instanceof ConstraintViolationException) {
            ConstraintViolationException exs = (ConstraintViolationException) e;
            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                String message = ((PathImpl) item.getPropertyPath()).getLeafNode().getName() + item.getMessage();
                return ResponseUtil.fail(402, message);
            }
        }
        return ResponseUtil.badArgumentValue();
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object seriousHandler(Exception e) {
        e.printStackTrace();
        return ResponseUtil.serious();
    }
}
