package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

  private final ObjectProvider<CallServiceV2> callServiceProvider;

  public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
    this.callServiceProvider = callServiceProvider;
  }

//  private final ApplicationContext applicationContext;

//  public CallServiceV2(ApplicationContext applicationContext) {
//    this.applicationContext = applicationContext;
//  }

  public void external() {
    log.info("call external");
//    CallServiceV2 callServiceV2 = applicationContext.getBean(CallServiceV2.class);
    CallServiceV2 callServiceV2 = callServiceProvider.getObject();
    callServiceV2.internal(); // 내부 메서드 호출 (this.internal()); // proxy.internal() 이 아님
  }

  public void internal() {
    log.info("call internal");
  }

}
