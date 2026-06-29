package tec.nextshop_project_1.repository.jpa;

import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.repository.interfaces.IOrderRepository;

@Repository
public class JpaOrderRepositoryAdapter implements IOrderRepository {

    private final OrderJpaRepository repository;

    public JpaOrderRepositoryAdapter(OrderJpaRepository repository) {
        this.repository = repository;
    }
}
