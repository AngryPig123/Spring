package hello.spring.spring.basic.policy;

import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("fixDiscountPolicy")
public class FixDiscountPolicyImpl implements DiscountPolicy {

    private final MemberService memberService;

    @Autowired
    public FixDiscountPolicyImpl(MemberService memberService) {
        this.memberService = memberService;
    }

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
