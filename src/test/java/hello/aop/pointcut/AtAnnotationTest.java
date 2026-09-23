package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Slf4j
@SpringBootTest
@Import(AtAnnotationTest.AtAnnotationAspect.class)
public class AtAnnotationTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemberServiceImpl memberServiceImpl;

  @Test
  void success() {
    log.info("memberService → {}", memberService.getClass());
    memberService.hello("hello");
    memberServiceImpl.internal("test");
  }

  @Slf4j
  @Aspect
  static class AtAnnotationAspect {

    @Around("@annotation(hello.aop.member.annotation.MethodAop)")
    public Object doAtAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
      log.info("【@Around】 {}", joinPoint.getSignature());
      return joinPoint.proceed();
    }
  }
}
