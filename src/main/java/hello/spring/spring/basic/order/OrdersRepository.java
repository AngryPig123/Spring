package hello.spring.spring.basic.order;

public interface OrdersRepository {

    void save(Orders orders);

    Orders findById(Long id);

    void deleteOrders(Long id);

}
