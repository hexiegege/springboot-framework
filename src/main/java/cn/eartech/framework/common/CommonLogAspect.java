package cn.eartech.framework.common;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author shanfa
 * @Desc 日志AOP切面处理类
 * @date 2020/3/27
 * @Version 1.0
 */
@Slf4j
@Aspect
@Configuration
public class CommonLogAspect {

    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 以自定义 异常切点
     */
    @Pointcut("execution(public * cn.eartech.framework.*.*.*(..))")
    public void exceptionLog() {
    }

    /**
     * 定义请求日志切入点，其切入点表达式有多种匹配方式,这里是指定路径
     */
    @Pointcut("execution(public * cn.eartech.framework.controller.*.*(..))")
    public void webLog() {
    }


    /**
     * 在切点之前织入
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", new Gson().toJson(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    //方法的返回值注入给ret
    /**
     * 在切点之后织入
     *
     * @throws Throwable
     */
    @After("webLog()")
    public void doAfter() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);
    }

    /**
     *  返回数据之后
     * @param ret
     */
    @AfterReturning(returning = "ret", value = "webLog()")
    public void doAfterReturning(Object ret) {
        log.info("Response:" + ret);       // 响应的内容---方法的返回值responseEntity
    }





    /**
     * 抛出异常后 打印日志
     *
     * @param point
     * @throws IllegalAccessException
     */
    @AfterThrowing(value = "exceptionLog()", throwing = "e")
    public void afterThrowing(JoinPoint point, Exception e) throws IllegalAccessException {
        LoggerFactory.getLogger(point.getTarget().getClass()).error("程序运行抛出异常，文件名为[{}],方法名[{}],异常信息为[{}]", point.getTarget().getClass().getCanonicalName(),point.getSignature().getName(), e.getMessage());
    }
//    @AfterThrowing(pointcut = "exceptionLog()")
//    public void afterThrowing(JoinPoint point) throws IllegalAccessException {
//        LoggerFactory.getLogger(point.getTarget().getClass()).error("程序运行抛出异常，文件名为[{}],方法名[{}]", point.getTarget().getClass().getCanonicalName(),point.getSignature().getName());
//    }

}
