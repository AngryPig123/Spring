package hello.spring.spring.basic;

import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;
import hello.spring.spring.basic.order.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Random;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.DefaultSetting.*;
import static hello.spring.spring.basic.TextColor.BLUE;

@Slf4j
class AutoAppConfigTest {

    private static AnnotationConfigApplicationContext applicationContext;

    @BeforeAll
    static void beforeAll() {
        applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    }

    @Test
    void 컴포넌트_스캔_확인() {

        for (String name : applicationContext.getBeanDefinitionNames()) {

            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(name);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = applicationContext.getBean(name);
                log.info(getFormat(BLUE, "\nname = {} \nbean = {}\n"), name, bean);
            }

        }

    }

    @Test
    void object_테스트() {
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);
        beansOfType.forEach((k, v) -> {
            log.info(getFormat(BLUE, "\nname = {}\nbean = {}\n"), k, v);
        });
    }

    @Test
    void 작동_테스트() {

        //  bean 가져오기.
        MemberService memberService = applicationContext.getBean("memberServiceImpl", MemberService.class);
        OrdersService ordersService = applicationContext.getBean("orderServiceImpl", OrdersService.class);

        //  랜덤 멤버 셋팅.
        int randomMember = new Random().nextInt(5);
        Member member = initMemberInfo.get(randomMember);
        memberService.join(member);

        //  랜덤 주문 셋팅
        int randomOrder = new Random().nextInt(5);
        String menuName = menuNameList.get(randomOrder);
        Integer price = menuInfoMap.get(menuName);
        Orders orders = new Orders(member.getId(), menuName, price);
        ordersService.join(orders);

        log.info("orders = {}", orders);

        Member findMember = memberService.findMember(member.getId());
        Orders findOrder = ordersService.findOrders(orders.getId());

        log.info(getFormat(BLUE, "\nfind member = {}\n"), findMember);
        log.info(getFormat(BLUE, "\nfind order = {}\n"), findOrder);

    }

}