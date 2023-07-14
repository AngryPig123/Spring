package hello.spring.spring;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.PureAppConfig;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.OrdersRepository;
import hello.spring.spring.basic.order.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.annotation.Order;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.TextColor.*;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SingletonContainerTest {

    private static PureAppConfig pureAppConfig;
    private static AnnotationConfigApplicationContext applicationContext;

    @BeforeAll
    static void beforeAll() {
        pureAppConfig = new PureAppConfig();
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Test
    @Order(1)
    void 싱글톤_적용_전() {
        MemberService memberService1 = pureAppConfig.memberService();
        MemberService memberService2 = pureAppConfig.memberService();

        log.info(getFormat(BLUE, "\nmemberService1 = {}\n"), memberService1);
        log.info(getFormat(BLUE, "\nmemberService2 = {}\n"), memberService2);

        Assertions.assertNotSame(memberService1, memberService2);
    }

    @Test
    @Order(2)
    void 싱글톤_적용_후() {
        MemberService memberService1 = pureAppConfig.memberServiceToSingleton();
        MemberService memberService2 = pureAppConfig.memberServiceToSingleton();

        log.info(getFormat(BLUE, "\nmemberService1 = {}\n"), memberService1);
        log.info(getFormat(BLUE, "\nmemberService2 = {}\n"), memberService2);

        Assertions.assertSame(memberService1, memberService2);
    }

    @Test
    @Order(3)
    void 스프링_싱글톤_확인() {
        MemberService memberService1 = applicationContext.getBean(MemberService.class);
        MemberService memberService2 = applicationContext.getBean(MemberService.class);

        log.info(getFormat(BLUE, "\nmemberService1 = {}\n"), memberService1);
        log.info(getFormat(BLUE, "\nmemberService2 = {}\n"), memberService2);

        Assertions.assertSame(memberService1, memberService2);
    }

    @Test
    @Order(3)
    void 스프링_싱글톤_테스트() {
        OrdersService ordersService1 = applicationContext.getBean("ordersService", OrdersService.class);
        OrdersService ordersService2 = applicationContext.getBean("ordersService", OrdersService.class);
        OrdersRepository ordersService = applicationContext.getBean("orderRepository", OrdersRepository.class);

        log.info(getFormat(BLUE, "\nordersService1.getOrdersRepository() = {}\n"), ordersService1.getOrdersRepository());
        log.info(getFormat(EMERALD, "\nordersService2.getOrdersRepository() = {}\n"), ordersService2.getOrdersRepository());
        log.info(getFormat(YELLOW, "\nordersService = {}\n"), ordersService);

        Assertions.assertSame(ordersService1.getOrdersRepository(), ordersService2.getOrdersRepository());
        Assertions.assertSame(ordersService, ordersService1.getOrdersRepository());
        Assertions.assertSame(ordersService2.getOrdersRepository(), ordersService);
    }


    @Test
    @Order(4)
    void Configuration_테스트() {
        AppConfig bean = applicationContext.getBean(AppConfig.class);
        log.info(getFormat(BLUE, "\nbean = {}"), bean);
        log.info(getFormat(BLUE, "\nclass type = {}"), bean.getClass());

        AppConfig appConfig = new AppConfig();
        log.info(getFormat(BLUE, "\nbean = {}"), appConfig);
        log.info(getFormat(BLUE, "\nclass type = {}"), appConfig.getClass());

    }

}
