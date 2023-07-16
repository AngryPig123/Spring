package hello.spring.spring.basic;

import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;
import hello.spring.spring.basic.order.OrdersService;
import hello.spring.spring.basic.policy.DiscountPolicy;
import hello.spring.spring.basic.policy.FixDiscountPolicyImpl;
import hello.spring.spring.basic.policy.RateDiscountPolicyImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static hello.spring.spring.basic.CommonUtil.getFormat;
import static hello.spring.spring.basic.DefaultSetting.*;
import static hello.spring.spring.basic.TextColor.BLUE;

@Slf4j
@Transactional
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

        Random random = new Random();

        //  랜덤 멤버 셋팅.
        int randomMember = random.nextInt(5);
        Member member = initMemberInfo.get(randomMember);
        memberService.join(member);

        //  랜덤 주문 셋팅
        int randomOrder = random.nextInt(5);
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

    private static ApplicationContext discountTest;

    @Test
    void test() {

        discountTest =
                new AnnotationConfigApplicationContext(
                        AutoAppConfig.class,
                        DiscountService.class
                );

        DiscountService discountService = discountTest.getBean(DiscountService.class);


        DiscountPolicy fixDiscountPolicy = discountService.discount("fixDiscountPolicy");
        DiscountPolicy beanFindFixDiscountPolicy = discountTest.getBean(FixDiscountPolicyImpl.class);
        Assertions.assertEquals(fixDiscountPolicy, beanFindFixDiscountPolicy);

        DiscountPolicy rateDiscountPolicy = discountService.discount("rateDiscountPolicy");
        DiscountPolicy beanFindRateDiscountPolicy = discountTest.getBean(RateDiscountPolicyImpl.class);
        Assertions.assertEquals(rateDiscountPolicy, beanFindRateDiscountPolicy);

    }

    public static class DiscountService {

        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println(policyMap);
            System.out.println(policies);
        }

        public DiscountPolicy discount(String discountPolicy) {
            return policyMap.get(discountPolicy);
        }

    }

}
