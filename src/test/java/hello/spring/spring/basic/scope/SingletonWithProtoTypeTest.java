package hello.spring.spring.basic.scope;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class SingletonWithProtoTypeTest {

    //    private static AnnotationConfigApplicationContext singletonContext;
    private static AnnotationConfigApplicationContext prototypeContext;
    private static AnnotationConfigApplicationContext clientContext;

    @BeforeAll
    static void beforeAll() {
        clientContext = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeTest.class);
        prototypeContext = new AnnotationConfigApplicationContext(PrototypeTest.class);
    }

    @Test
    void 프로토_타입_조회() {

        PrototypeTest bean1 = prototypeContext.getBean(PrototypeTest.class);
        bean1.addCount();
        bean1.addCount();
        bean1.addCount();
        Assertions.assertEquals(bean1.getCount(), 3);

        PrototypeTest bean2 = prototypeContext.getBean(PrototypeTest.class);
        Assertions.assertNotEquals(bean1.getCount(), bean2.getCount());

    }

    @Test
    void 싱글톤_클라이언트_프로토_타입_주입_테스트() {

        ClientBean clientBean1 = clientContext.getBean(ClientBean.class);
        clientBean1.logic();
        Assertions.assertEquals(clientBean1.getCount(), 1);

        ClientBean clientBean2 = clientContext.getBean(ClientBean.class);
        clientBean2.logic();
        Assertions.assertEquals(clientBean2.getCount(), 2);

    }

    @Scope("prototype")
    public static class PrototypeTest {

        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            log.info("count = {}", this);
        }

        @PreDestroy
        public void destroy() {
            log.info("destroy");
        }

    }

    @Scope("singleton")
    @RequiredArgsConstructor
    public static class ClientBean {

        private final PrototypeTest prototypeTest;

        public void logic() {
            prototypeTest.addCount();
        }

        public int getCount() {
            return prototypeTest.getCount();
        }

    }

}
