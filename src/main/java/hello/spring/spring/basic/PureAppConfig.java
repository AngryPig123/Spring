package hello.spring.spring.basic;

import hello.spring.spring.basic.member.MemberRepository;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.member.MemberServiceImpl;
import hello.spring.spring.basic.member.MemoryMemberRepository;
import hello.spring.spring.basic.order.OrderServiceImpl;
import hello.spring.spring.basic.order.OrdersRepository;
import hello.spring.spring.basic.order.OrdersRepositoryImpl;
import hello.spring.spring.basic.order.OrdersService;
import hello.spring.spring.basic.policy.DiscountPolicy;
import hello.spring.spring.basic.policy.RateDiscountPolicyImpl;
import hello.spring.spring.basic.singleton5.MemberServiceToSingleton;


public class PureAppConfig {


    public MemberService memberServiceToSingleton() {
        return MemberServiceToSingleton.getInstance();
    }

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }


    public OrdersService ordersService() {
        return new OrderServiceImpl(orderRepository(), discountPolicy());
    }


    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicyImpl();
//        return new FixDiscountPolicyImpl();
    }


    public OrdersRepository orderRepository() {
        return new OrdersRepositoryImpl();
    }

}
