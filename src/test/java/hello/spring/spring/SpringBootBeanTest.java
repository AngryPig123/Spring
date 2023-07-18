package hello.spring.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.TextColor.BLUE;

@Slf4j
@SpringBootTest
public class SpringBootBeanTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    void 모든_스프링_웹_빈_조회() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);

        beansOfType.forEach((k, v) -> {
            log.info(getFormat(BLUE, "\nk = {}\nv = {}\n"), k, v);
        });

    }

}
