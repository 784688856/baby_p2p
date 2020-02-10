package com.xkkj.common.handler;

import com.xkkj.common.constants.ResultCodeEnum;
import com.xkkj.common.exception.YanXueException;
import com.xkkj.common.util.ExceptionUtil;
import com.xkkj.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: GlobalExceptionHandler 统一异常处理器
 * @author: xuhao
 * @time: 2020/1/6 10:31
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        log.error(ExceptionUtil.getMessage(e));
        return Result.error();
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    @ResponseBody
    public Result error(BadSqlGrammarException e){
        log.error(ExceptionUtil.getMessage(e));
        return Result.setResultBody(ResultCodeEnum.BAD_SQL_GRAMMAR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    //JsonParseException
    public Result error(HttpMessageNotReadableException e){
        log.error(ExceptionUtil.getMessage(e));
        return Result.setResultBody(ResultCodeEnum.JSON_PARSE_ERROR);
    }
    @ExceptionHandler(YanXueException.class)
    @ResponseBody
    public Result error(YanXueException e){
        log.error(ExceptionUtil.getMessage(e));
        return Result.error().message(e.getMessage()).code(e.getCode());
    }

}
