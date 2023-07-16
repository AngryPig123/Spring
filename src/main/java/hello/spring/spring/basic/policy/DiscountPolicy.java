package hello.spring.spring.basic.policy;

import hello.spring.spring.basic.order.Orders;

public interface DiscountPolicy {

    int discount(Orders orders);


}
