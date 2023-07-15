package hello.spring.spring.basic.order;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrdersRepositoryImpl implements OrdersRepository {

    private final Map<Long, Orders> store = new HashMap<>();
    private static Long seq = 1L;

    @Override
    public void save(Orders orders) {
        store.put(seq, orders);
        orders.setId(seq);
        seq++;
    }

    @Override
    public Orders findById(Long id) {
        return store.get(id);
    }

    @Override
    public void deleteOrders(Long id) {
        store.remove(id);
    }

}
