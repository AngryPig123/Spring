package hello.spring.spring.basic.order;

import hello.spring.spring.basic.policy.DiscountPolicy;

public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(
            OrdersRepository ordersRepository,
            DiscountPolicy discountPolicy
    ) {
        this.ordersRepository = ordersRepository;
        this.discountPolicy = discountPolicy;
    }

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
