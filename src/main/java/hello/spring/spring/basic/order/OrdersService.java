package hello.spring.spring.basic.order;

public interface OrdersService {

    void join(Orders orders);

    Orders findOrders(Long ordersId);

    void deleteOrders(Long ordersId);

    OrdersRepository getOrdersRepository();


}
