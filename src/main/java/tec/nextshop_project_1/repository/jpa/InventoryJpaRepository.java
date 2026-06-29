package tec.nextshop_project_1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.nextshop_project_1.data.Inventory;

import java.util.Optional;

public interface InventoryJpaRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProduct_Id(Long productId);

    boolean existsByProduct_Id(Long productId);
}
