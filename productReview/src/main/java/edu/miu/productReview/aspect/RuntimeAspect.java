package edu.miu.productReview.aspect;

import edu.miu.productReview.domain.ActivityLog;
import edu.miu.productReview.exception.AopIsAwesomeHeaderException;
import edu.miu.productReview.service.ActivityLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
public class RuntimeAspect {
    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private HttpServletRequest request;
    @Pointcut("@annotation(edu.miu.productReview.aspect.ExecutionTime)")
    public void runtimeLog(){}

    @Around("runtimeLog()")
    public Object calculateRunTimeLog(ProceedingJoinPoint proceedingJoinPoint ) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - start;

        ActivityLog activityLog = ActivityLog.builder()
                .duration(duration)
                .date(LocalDateTime.now())
                .operation(proceedingJoinPoint.getSignature().getName())
                .build();
        activityLogService.save(activityLog);

        return result;
    }

    @Around("within(edu.miu.productReview.controller.*)")
    public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        if(request.getMethod().equalsIgnoreCase("POST")) {
//            var header = request.getHeader("AOP-IS-AWESOME");
//            if (header != null) {
//                return proceedingJoinPoint.proceed();
//            } else {
//                throw new AopIsAwesomeHeaderException("Header not found AOP-IS-AWESOME");
//            }
//        } else {
//            return proceedingJoinPoint.proceed();
//        }
        System.out.println("Check header");
        return proceedingJoinPoint.proceed();
    }
}
