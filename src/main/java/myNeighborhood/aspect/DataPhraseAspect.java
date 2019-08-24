package myNeighborhood.aspect;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import myNeighborhood.model.entity.CrawlingData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author doyoung hwang on 2019-08-24
 */

// Aspect = pointcut + advice

@Aspect
@Configuration
public class DataPhraseAspect {

  private static final String CRAWLING_DATA_LIST = "crawlingDataList";

  // TODO 1-7 myNeighborhood.repository 이하 save (..) 을 대상으로 지정한다.
  // NOTE : execution([접근제한자] 리턴타입 [패키지~클래스.]이름(파라미터| "..", ...)
  @Pointcut("execution(public * myNeighborhood.repository..save(..))")
  public void mapper() {
  }

  // TODO 1-8 다양한 advisor 가 있지만 우리는 args 를 변경할 것이므로 둘러싸는걸 사용해야 한다.
  // NOTE : Advisor의 종류 - @Before @After @AfterReturning @Around @AfterThrowing
  @Around("mapper()")
  public Object modifyDataPhrase(ProceedingJoinPoint joinPoint) throws Throwable {

    Object[] args = joinPoint.getArgs();

    for (Object arg : args) {
      Field crawlingDataField = Arrays.stream(arg.getClass().getDeclaredFields())
          .filter(field -> field.getName().equals(CRAWLING_DATA_LIST))
          .findFirst().orElse(null);

      // NOTE java reflection
      crawlingDataField.setAccessible(true);
      List<CrawlingData> crawlingDataList = (List<CrawlingData>) crawlingDataField.get(arg);

      // TODO 1-9 데이터를 변경하고 저장
      crawlingDataList.forEach(d -> d.setData(d.getCrawlingType().createBy(d.getData())));
      crawlingDataField.set(arg, crawlingDataList);
    }

    // TODO 1-10 기존 메소드 시작
    return joinPoint.proceed(args);
  }
}
