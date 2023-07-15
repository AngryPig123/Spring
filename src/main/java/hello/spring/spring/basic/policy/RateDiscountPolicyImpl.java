package hello.spring.spring.basic.policy;

import hello.spring.spring.basic.member.Grade;
import hello.spring.spring.basic.member.Member;
import hello.spring.spring.basic.member.MemberService;
import hello.spring.spring.basic.order.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rateDiscountPolicy")
public class RateDiscountPolicyImpl implements DiscountPolicy {

    private final MemberService memberService;

    @Autowired
    public RateDiscountPolicyImpl(MemberService memberService) {
        this.memberService = memberService;
    }

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
