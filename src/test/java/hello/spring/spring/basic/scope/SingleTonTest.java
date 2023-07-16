package hello.spring.spring.basic.scope;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
class SingleTonTest {

    private static AnnotationConfigApplicationContext singletonContext;
    private static AnnotationConfigApplicationContext prototypeContext;

    @BeforeAll
    static void beforeAll() {
        singletonContext = new AnnotationConfigApplicationContext(SingleBean.class);
        prototypeContext = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
    }

    @Test
    void 싱글톤_타입_빈_조회() {
        SingleBean bean1 = singletonContext.getBean(SingleBean.class);
        SingleBean bean2 = singletonContext.getBean(SingleBean.class);
        Assertions.assertSame(bean1, bean2);
        singletonContext.close();
    }

    @Test
    void 프로토_타입_빈_조회() {
        ProtoTypeBean bean1 = prototypeContext.getBean(ProtoTypeBean.class);
        ProtoTypeBean bean2 = prototypeContext.getBean(ProtoTypeBean.class);
        Assertions.assertNotSame(bean1, bean2);
        prototypeContext.close();
    }

    @Scope("singleton")
    static class SingleBean {
        @PostConstruct
        public void init() {
            log.info("init()");
        }
        @PreDestroy
        public void destroy() {
            log.info("close()");
        }
    }

    @Scope("prototype")
    static class ProtoTypeBean {
        @PostConstruct
        public void init() {
            log.info("init()");
        }
        @PreDestroy
        public void destroy() {
            log.info("close()");
        }
    }

}
