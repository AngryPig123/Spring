package hello.spring.spring;

import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.member.MemberServiceImpl;
import hello.spring.spring.basic.order.OrderServiceImpl;
import hello.spring.spring.basic.order.Orders;
import hello.spring.spring.basic.order.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.Random;

import static hello.spring.spring.basic.DefaultSetting.*;

@Slf4j
@TestMethodOrder(MethodOrderer.Random.class)
public class OrdersTest {

    private final MemberService memberService = new MemberServiceImpl();
    private final OrdersService ordersService = new OrderServiceImpl();

    private Member member;
    private Orders orders;

    @BeforeEach
    void before() {
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
    void 주문_저장() {
        ordersService.join(orders);
        Orders findOrders = ordersService.findOrders(orders.getId());
        log.info("\nfindOrders = {}\nfindMember = {}\n\n", findOrders, member);
        Assertions.assertEquals(orders, findOrders);
    }

}
