package hello.aop;

import hello.aop.exam.aop.TraceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopApplication {

  public static void main(String[] args) {
    SpringApplication.run(AopApplication.class, args);
  }

}
