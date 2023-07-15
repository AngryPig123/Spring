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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration  //  Application 의 설정 정보.
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrdersService ordersService() {
        return new OrderServiceImpl(orderRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicyImpl(memberService());
//        return new FixDiscountPolicyImpl();
    }

    @Bean
    public OrdersRepository orderRepository() {
        return new OrdersRepositoryImpl();
    }

}
