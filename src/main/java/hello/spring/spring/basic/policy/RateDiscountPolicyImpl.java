package hello.spring.spring.basic.policy;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;

public class RateDiscountPolicyImpl implements DiscountPolicy {

    private final MemberService memberService = new AppConfig().memberService();
    private final double discountRateAmount = 0.1D;

    @Override
    public int discount(Orders orders) {
        Long memberId = orders.getMemberId();
        Member member = memberService.findMember(memberId);

        if (member.getGrade() == Grade.VIP) {
            return (int) (orders.getItemPrice() * discountRateAmount);
        }

        return 0;
    }

}
