package hello.spring.spring.basic.policy;

import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.member.MemberServiceImpl;
import hello.spring.spring.basic.order.Orders;

public class DiscountPolicyImpl implements DiscountPolicy {

    private final MemberService memberService = new MemberServiceImpl();
    private final int discountFixAmount = 1000;
    private final double discountAmount = 0.1D;

    @Override
    public int discount(Orders orders) {
        Long memberId = orders.getMemberId();
        Member member = memberService.findMember(memberId);

        if (member.getGrade() == Grade.VIP) {
            return (int) (orders.getItemPrice() * discountAmount);
        }

        return 0;
    }
}
