package myNeighborhood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author doyoung hwang on 2019-08-04
 */

@SpringBootApplication
@EnableAspectJAutoProxy // TODO 1-4 해당 application은 AOP를 사용하겠다 선언
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }
}
