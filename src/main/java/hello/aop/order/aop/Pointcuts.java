package hello.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * 포인트 컷 공통영역을 외부에 선언하기
 */
public class Pointcuts {
  @Pointcut("execution(* hello.aop.order..*(..))")
  public void allOrder(){} // pointcut signature

  // 클래스 이름 패턴이 *Service
  @Pointcut("execution(* *..*Service.*(..))")
  public void allService(){} // pointcut signature

  // allOrder && allService
  @Pointcut("allOrder() && allService()")
  public void orderAndService(){}
}
