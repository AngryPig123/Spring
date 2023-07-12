package hello.spring.spring.basic.order;

import hello.spring.spring.basic.policy.DiscountPolicy;
import hello.spring.spring.basic.policy.DiscountPolicyImpl;

public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository = new OrdersRepositoryImpl();
    private final DiscountPolicy discountPolicy = new DiscountPolicyImpl();

    @Override
    public void join(Orders orders) {
        int discount = discountPolicy.discount(orders);
        orders.setDiscountPrice(discount);
        orders.setTotalPrice(orders.getItemPrice() - discount);
        ordersRepository.save(orders);
    }

    @Override
    public Orders findOrders(Long ordersId) {
        return ordersRepository.findById(ordersId);
    }

    @Override
    public void deleteOrders(Long ordersId) {
        ordersRepository.deleteOrders(ordersId);
    }

}
