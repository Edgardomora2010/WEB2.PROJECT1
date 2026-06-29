package tec.nextshop_project_1.repository.jpa;

import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Order;
import tec.nextshop_project_1.repository.interfaces.IOrderRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaOrderRepositoryAdapter implements IOrderRepository {

    private final OrderJpaRepository repository;

    public JpaOrderRepositoryAdapter(OrderJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveOrder(Order order) {
        repository.save(order);
    }

    @Override
    public Optional<Order> findByOrderId(Long orderId) {
        if (orderId == null) {
            return Optional.empty();
        }
        return repository.findById(orderId);
    }

    @Override
    public List<Order> findOrdersByClientId(Long clientId) {
        if (clientId == null) {
            return List.of();
        }
        return repository.findByClientId(clientId);
    }

    @Override
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    @Override
    public void deleteOrder(Long orderId) {
        if (orderId != null) {
            repository.deleteById(orderId);
        }
    }
}
