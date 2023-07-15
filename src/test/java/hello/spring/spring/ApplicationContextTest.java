package hello.spring.spring;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.member.MemberRepository;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.member.MemberServiceImpl;
import hello.spring.spring.basic.member.MemoryMemberRepository;
import hello.spring.spring.basic.order.OrderServiceImpl;
import hello.spring.spring.basic.order.OrdersRepository;
import hello.spring.spring.basic.order.OrdersRepositoryImpl;
import hello.spring.spring.basic.order.OrdersService;
import hello.spring.spring.basic.policy.DiscountPolicy;
import hello.spring.spring.basic.policy.FixDiscountPolicyImpl;
import hello.spring.spring.basic.policy.RateDiscountPolicyImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.TextColor.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationContextTest {

    private static AnnotationConfigApplicationContext applicationContext;
    private static AnnotationConfigApplicationContext noUniqueTestApplicationContext;


    @BeforeAll
    static void beforeAllSetting() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        noUniqueTestApplicationContext = new AnnotationConfigApplicationContext(TestAppConFig.class);
    }

    @Test
    @Order(1)
    void 모든_bean_출력() { //  전체 bean 목록.
        for (String name : applicationContext.getBeanDefinitionNames()) {
            Object bean = applicationContext.getBean(name);
            log.info(getFormat(BLUE, "\nname = {}\nbean = {}\n"), name, bean);
        }
    }

    @Test
    @Order(2)
    void APPLICATION_bean_출력() {
        for (String name : applicationContext.getBeanDefinitionNames()) {

            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(name);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = applicationContext.getBean(name);
                log.info(getFormat(YELLOW, "\nname = {}\nbean = {}\n"), name, bean);
            }    //  내가 등록한 bean 목록!

            if (beanDefinition.getRole() == BeanDefinition.ROLE_INFRASTRUCTURE) {
                Object bean = applicationContext.getBean(name);
                log.info(getFormat(GRAY, "\nname = {}\nbean = {}\n"), name, bean);
            }    //  스프링 내부에서 등록한 bean 목록!

        }
    }

    @Test
    @Order(3)
    void bean_이름으로() {
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Assertions.assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @Order(4)
    void bean_타입으로() {
        String bean = "memberService";
        MemberService memberService = applicationContext.getBean(MemberService.class);
        log.info(getFormat(BLUE, "\nname = {}\nbean = {}\n"), bean, memberService);
        Assertions.assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @Order(4)
    void 구체_타입으로() {
        String bean = "memberService";
        MemberService memberService = applicationContext.getBean(MemberServiceImpl.class);
        log.info(getFormat(BLUE, "\nname = {}\nbean = {}\n"), bean, memberService);
        Assertions.assertInstanceOf(MemberServiceImpl.class, memberService);
    }

    @Test
    @Order(5)
    void 조회_실패() {
        Assertions
                .assertThrows(
                        NoSuchBeanDefinitionException.class,
                        () -> applicationContext.getBean("FailTest")
                );
    }

    @Test
    @Order(6)
    void 타입_조회_중복_확인() {
        Assertions
                .assertThrows(
                        NoUniqueBeanDefinitionException.class,
                        () -> noUniqueTestApplicationContext.getBean(DiscountPolicy.class)
                );
    }

    @Test
    @Order(7)
    void bean_이름으로_조회() {
        DiscountPolicy fixDiscount = noUniqueTestApplicationContext.getBean("fixDiscount", DiscountPolicy.class);
        Assertions.assertInstanceOf(DiscountPolicy.class, fixDiscount);
        DiscountPolicy rateDiscount = noUniqueTestApplicationContext.getBean("rateDiscount", DiscountPolicy.class);
        Assertions.assertInstanceOf(DiscountPolicy.class, rateDiscount);
    }

    @Test
    @Order(8)
    void 특정_타입_모두_조회() {
        Map<String, DiscountPolicy> beansOfType = noUniqueTestApplicationContext.getBeansOfType(DiscountPolicy.class);
        beansOfType.forEach((k, v) -> {
            log.info(getFormat(BLUE, "\nkey = {}\ntype = {}\n"), k, v);
            Assertions.assertInstanceOf(DiscountPolicy.class, v);
        });
    }

    @Test
    @Order(9)
    void object_타입으로_조회() {
        Map<String, Object> beansOfType = noUniqueTestApplicationContext.getBeansOfType(Object.class);
        beansOfType.forEach((k, v) -> {
            log.info(getFormat(BLUE, "\nkey = {}\ntype = {}\n"), k, v);
            Assertions.assertInstanceOf(Object.class, v);
        });
    }

    @Test
    @Order(10)
    void bean_메타_정보확인() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(name);
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                log.info(getFormat(BLUE, "beanDefinition = {}"), beanDefinition);
            }
        }
    }

    @Configuration
    public static class TestAppConFig {

        @Bean
        public MemberService memberService() {
            return new MemberServiceImpl(memberRepository());
        }

        @Bean
        public OrdersService ordersService() {
            return new OrderServiceImpl(orderRepository(), rateDiscount());
        }

        @Bean
        public MemberRepository memberRepository() {
            return new MemoryMemberRepository();
        }

        @Bean("rateDiscount")
        public DiscountPolicy rateDiscount() {
            return new RateDiscountPolicyImpl(memberService());
        }

        @Bean("fixDiscount")
        public DiscountPolicy fixDiscount() {
            return new FixDiscountPolicyImpl(memberService());
        }

        @Bean
        public OrdersRepository orderRepository() {
            return new OrdersRepositoryImpl();
        }

    }

}










