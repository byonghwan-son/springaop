package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * orderService : doLog(), doTransaction() 어드바이스 적용
 * orderRepository : doLog() 어드바이스 적용
 */
@Slf4j
@Aspect
public class AspectV6Advice {
  @Around("hello.aop.order.aop.Pointcuts.allService()")
  public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      // @Before
      log.info("【트랜잭션 시작】 ➔ {}", joinPoint.getSignature());
      Object result = joinPoint.proceed();
      // @AfterReturning
      log.info("【트랜잭션 커밋】 ➔ {}", joinPoint.getSignature());
      return result;
    } catch (Exception e) {
      // @AfterThrowing
      log.info("【트랜잭션 롤백】 ➔ {}", joinPoint.getSignature());
      throw e;
    } finally {
      // @After
      log.info("【리소스 릴리즈】 ➔ {}", joinPoint.getSignature());
    }
  }

  @Before("hello.aop.order.aop.Pointcuts.allService()")
  public void doBefore(JoinPoint joinPoint) throws Throwable {
    log.info("【Before】 ➔ {}", joinPoint.getSignature());
//    joinPoint.proceed();
  }

  // 메소드 시그니처가 일치해야 아래의 메소드가 호출된다.
  // Object result 를 String result로 변경하면 메소드 시그니처가 일치하지 않아서
  // 아래의 메소드가 호출되지 않는다.
  // Object는 파라미터를 모두 흡수함.
  @AfterReturning(value = "hello.aop.order.aop.Pointcuts.allService()",
                  returning = "result")
  public void doAfterReturning(JoinPoint joinPoint, Object result) {
    log.info("【AfterReturning】 ➔ {}", joinPoint.getSignature());
    log.info("【return】 ➔ {}", result);
  }

  @AfterThrowing(value = "hello.aop.order.aop.Pointcuts.allService()",
                 throwing = "ex")
  public void doAfterThrowing(JoinPoint joinPoint, Exception ex) {
    log.info("【AfterThrowing】 ➔ {}", joinPoint.getSignature());
    log.info("【exception】 ➔ {}", ex);
  }

  @After("hello.aop.order.aop.Pointcuts.allService()")
  public void doAfter(JoinPoint joinPoint) {
    log.info("【After】 ➔ {}", joinPoint.getSignature());
  }
}
