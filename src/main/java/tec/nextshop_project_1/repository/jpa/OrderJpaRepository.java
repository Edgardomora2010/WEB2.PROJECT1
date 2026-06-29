package tec.nextshop_project_1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.nextshop_project_1.data.Order;

import java.util.List;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {

    List<Order> findByClientId(Long clientId);
}
