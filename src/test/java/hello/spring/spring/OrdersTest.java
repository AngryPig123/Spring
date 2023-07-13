package hello.spring.spring;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;
import hello.spring.spring.basic.order.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Random;

import static hello.spring.spring.basic.DefaultSetting.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@Slf4j
@TestMethodOrder(MethodOrderer.Random.class)
public class OrdersTest {

    private MemberService memberService;
    private OrdersService ordersService;

    private Member member;
    private Orders orders;

    @BeforeEach
    void before() {

        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        ordersService = appConfig.ordersService();

        int randomMember = new Random().nextInt(5);
        member = initMemberInfo.get(randomMember);
        memberService.join(member);
        int randomMenu = new Random().nextInt(5);
        String menuName = menuNameList.get(randomMenu);
        Integer price = menuInfoMap.get(menuName);
        orders = new Orders(member.getId(), menuName, price);

    }

    @AfterEach
    void after() {
        memberService.deleteMember(member.getId());
        ordersService.deleteOrders(orders.getId());
    }

    @RepeatedTest(10)
    void 주문_저장_VIP() {
        assumeTrue(member.getGrade() == Grade.VIP);
        ordersService.join(orders);
        Orders findOrders = ordersService.findOrders(orders.getId());
        log.info("\nfindOrders = {}\nfindMember = {}\n\n", findOrders, member);
        Assertions.assertEquals(orders, findOrders);

        int discount = (int) (orders.getItemPrice() * 0.1D);
        Assertions.assertEquals(orders.getDiscountPrice(), discount);

    }

    @RepeatedTest(10)
    void 주문_저장_BASIC() {
        assumeTrue(member.getGrade() == Grade.BASIC);
        ordersService.join(orders);
        Orders findOrders = ordersService.findOrders(orders.getId());
        log.info("\nfindOrders = {}\nfindMember = {}\n\n", findOrders, member);
        Assertions.assertEquals(orders, findOrders);
        Assertions.assertEquals(orders.getDiscountPrice(), 0);
    }

}
