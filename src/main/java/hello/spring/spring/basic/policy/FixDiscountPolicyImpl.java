package hello.spring.spring.basic.policy;

import hello.spring.spring.basic.AppConfig;
import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;

public class FixDiscountPolicyImpl implements DiscountPolicy {

    private final MemberService memberService = new AppConfig().memberService();
    private final int discountFixAmount = 1000;

    @Override
    public int discount(Orders orders) {
        Long memberId = orders.getMemberId();
        Member member = memberService.findMember(memberId);

        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        }

        return 0;
    }
}
