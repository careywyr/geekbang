package cn.leafw.datasource.config;

import cn.leafw.datasource.annotations.Slave;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面处理
 *
 * @author <a href="mailto:wyr95626@95626.cn">CareyWYR</a>
 * @date 2022/6/16
 */
@Aspect
@Component
@Order(1)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Slf4j
public class DynamicDataSourceAspect {
    @Around("execution(* cn.leafw.datasource.service.*.*(..))")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature signature = (MethodSignature)jp.getSignature();
        log.info("方法名： {}", signature.getMethod().getName());
        Slave slave = signature.getMethod().getAnnotation(Slave.class);
        if (null == slave) {
            log.info("master");
            DynamicDataSourceContextHolder.setDataSourceKey("master");
        }else {
            log.info("slave");
            DynamicDataSourceContextHolder.setDataSourceKey("slave");
        }
        return jp.proceed();
    }
}

