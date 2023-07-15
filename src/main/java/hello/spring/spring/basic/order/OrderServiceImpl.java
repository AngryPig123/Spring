package hello.spring.spring.basic.order;

import hello.spring.spring.basic.policy.DiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(
            OrdersRepository ordersRepository,
            @Qualifier("rateDiscountPolicy") DiscountPolicy discountPolicy
    ) {
        this.ordersRepository = ordersRepository;
        this.discountPolicy = discountPolicy;
    }

    public OrdersRepository getOrdersRepository() {
        return ordersRepository;
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
