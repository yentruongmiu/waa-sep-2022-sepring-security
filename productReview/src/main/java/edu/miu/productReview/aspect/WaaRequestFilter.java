package edu.miu.productReview.aspect;

import edu.miu.productReview.service.OffensiveWordService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;

@Aspect
@Component
@RequiredArgsConstructor
@Order(Integer.MIN_VALUE)
public class WaaRequestFilter {


    @Autowired
    private final HttpServletRequest request;

    @Autowired
    private final OffensiveWordService offensiveWordService;

    @Around("execution(* edu.miu.productReview.controller.*.*(..))")
    public Object filterOffensiveWordsOnReviews(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (request.getUserPrincipal() != null &&
                request.getMethod().equalsIgnoreCase(HttpMethod.POST.name()) &&
                proceedingJoinPoint.getArgs().length != 0) {
            Object[] params = proceedingJoinPoint.getArgs();
            for (Object param : params) {
                Field[] fields = param.getClass().getDeclaredFields();
                for (Field f :
                        fields) {
                    if (f.getType() == String.class) {
                        f.setAccessible(true);
                        String value = (String) f.get(param);
                        boolean isValid = offensiveWordService.validateOffensiveWords(value);
                        if (!isValid) {
                            return new ResponseEntity<>("Max Bad Words Requests Limit has been Reached. You need wait for 15 minutes.", HttpStatus.BAD_REQUEST);
                        }
                    }

                }
            }
        }
        return proceedingJoinPoint.proceed();
    }
}
