package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * 정해진 실행 시간 초과할 때 로그 남기기
 * 오류중에 특정 오류에서만 로그 남기기 등
 */
@Slf4j
@Aspect
public class TraceAspect {
  // 【Bean】
  @Before("@annotation(hello.aop.exam.annotation.Trace)")
  public void doTrace(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    log.info("【Trace】 {} args = {}", joinPoint.getSignature(), args);
  }
}
