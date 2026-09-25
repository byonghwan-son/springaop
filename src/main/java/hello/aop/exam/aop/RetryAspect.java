package hello.aop.exam.aop;

import hello.aop.exam.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class RetryAspect {
  @Around("@annotation(retry)")
  public Object doRetry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
    log.info("【Retry】 {} retry={}", joinPoint.getSignature(), retry);

    int maxRetry = retry.count();
    Exception exceptionHolder = new Exception();

    for(int retryCount = 1; retryCount <= maxRetry; retryCount++) {
      try {
        return joinPoint.proceed();
      } catch (Exception e) {
        log.info("【Retry】 try count ➔ {} / {}", retryCount, maxRetry);
        exceptionHolder = e;
      }
    }

    throw exceptionHolder;
  }
}
