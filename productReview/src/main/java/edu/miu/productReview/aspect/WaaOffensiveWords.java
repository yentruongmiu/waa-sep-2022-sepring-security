package edu.miu.productReview.aspect;

import edu.miu.productReview.service.OffensiveWordService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
@RequiredArgsConstructor
public class WaaOffensiveWords {

    @Autowired
    private final HttpServletRequest request;

    @Autowired
    private final OffensiveWordService offensiveWordService;

    @Around("execution(* edu.miu.productReview.controller.*.*(..))")
    public Object filterOffensiveWordsOnReviews(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (request.getUserPrincipal() != null &&
                request.getMethod().equalsIgnoreCase(HttpMethod.POST.name()) &&
                proceedingJoinPoint.getArgs().length != 0) {
            Object param = proceedingJoinPoint.getArgs()[0];
            Field[] fields = param.getClass().getDeclaredFields();
            for (Field f:
                 fields) {
               if(f.getType() == String.class) {
                   f.setAccessible(true);
                   String value = (String) f.get(param);
                   offensiveWordService.maskOffensiveWords(value)
                           .ifPresent(v -> {
                               try {
                                   f.set(param,v);
                               } catch (IllegalAccessException e) {
                                   throw new RuntimeException(e);
                               }
                           });
               }

            }
        }
        return proceedingJoinPoint.proceed();
    }
}
