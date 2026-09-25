package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * JDK 동적 프록시는 대상 객체인 'MemberServiceImpl'을 구현한 인터페이스로만 캐스팅 가능하다.
 * CGLIB 프록시는 구현 클래스와 부모 클래스로 캐스팅 가능하다.
 */
@Slf4j
public class ProxyCastingTest {

  @Test
  void jdkProxy() {
    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(false);  // JDK 동적 프록시

    // 프록시를 인터페이스로 캐스팅 성공
    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

    log.info("proxy class={}", memberServiceProxy.getClass());

    // 캐스팅 실패 (ClassCastException)
    assertThatThrownBy(() -> {
      var castingMemberService = (MemberServiceImpl) memberServiceProxy;
    }).isInstanceOf(ClassCastException.class);
  }

  @Test
  void cglibProxy() {
    MemberServiceImpl target = new MemberServiceImpl();
    ProxyFactory proxyFactory = new ProxyFactory(target);
    proxyFactory.setProxyTargetClass(true);  // CGLIB 프록시

    // 프록시를 인터페이스로 캐스팅 성공
    MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

    log.info("proxy class={}", memberServiceProxy.getClass());

    // CGLIB 프록시를 구현 클래스로 캐스팅 가능
    var castingMemberService = (MemberServiceImpl) memberServiceProxy;
  }
}
