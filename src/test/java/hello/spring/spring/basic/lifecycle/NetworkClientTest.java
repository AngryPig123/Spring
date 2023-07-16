package hello.spring.spring.basic.lifecycle;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
class NetworkClientTest {

    private static AnnotationConfigApplicationContext applicationContext;

    @BeforeAll
    static void beforeAll() {
        applicationContext = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
    }

    @Test
    void 생명_주기_테스트() {
        NetworkClient bean1 = applicationContext.getBean(NetworkClient.class);
        NetworkClient bean2 = applicationContext.getBean(NetworkClient.class);
        Assertions.assertSame(bean1, bean2);
        applicationContext.close();
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("www.google.com");
            return networkClient;
        }
    }

}