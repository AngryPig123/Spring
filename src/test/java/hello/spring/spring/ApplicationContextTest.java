package hello.spring.spring;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.TextColor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.TextColor.GRAY;
import static hello.spring.spring.basic.TextColor.RESET;

@Slf4j
public class ApplicationContextTest {

    private static ApplicationContext applicationContext;

    @BeforeAll
    static void beforeAllSetting() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    void 멤버_서비스_BEAN_검색() {

        for (String name : applicationContext.getBeanDefinitionNames()) {
            log.info(getFormat(GRAY, "name = {}"), name);
        }

    }

}










