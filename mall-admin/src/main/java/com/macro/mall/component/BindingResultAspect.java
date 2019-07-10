package com.macro.mall.component;

import com.macro.mall.common.api.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.concurrent.TimeUnit;

/**
 * HibernateValidator错误结果处理切面
 * Created by macro on 2018/4/26.
 */
@Aspect
@Component
@Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.macro.mall.controller.*.*(..))")
    public void BindingResult() {
    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if(fieldError!=null){
                        return CommonResult.validateFailed(fieldError.getDefaultMessage());
                    }else{
                        return CommonResult.validateFailed();
                    }
                }
            }
        }
        Object result = joinPoint.proceed();
//        if(joinPoint.getSignature().getName().startsWith("cache")) {
//            CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
//            result = ResponseEntity.ok().cacheControl(cacheControl).body(result);
//        } else {
//            result = ResponseEntity.ok().body(result);
//        }
        return result;
    }

    @AfterReturning(returning = "result", pointcut = "BindingResult()")
    public void doAfterReturning(Object result){
//        CommonResult responseMessage = (CommonResult) result;

    }
}
