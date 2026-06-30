package tec.nextshop_project_1.repository.jpa;

import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Inventory;
import tec.nextshop_project_1.repository.interfaces.IInventoryRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaInventoryRepositoryAdapter implements IInventoryRepository {

    private final InventoryJpaRepository repository;

    public JpaInventoryRepositoryAdapter(InventoryJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Inventory> findByProductId(Long productId) {
        if (productId == null) {
            return Optional.empty();
        }
        return repository.findByProduct_Id(productId);
    }

    @Override
    public List<Inventory> getAllInventoryItems() {
        return repository.findAll();
    }

    @Override
    public boolean addInventoryItem(Inventory inventory) {
        if (inventory == null || inventory.getProduct() == null) {
            return false;
        }
        if (repository.existsByProduct_Id(inventory.getProduct().getId())) {
            return false;
        }
        repository.save(inventory);
        return true;
    }

    @Override
    public boolean updateInventory(Long productId, int quantity, int minimumStock) {
        Optional<Inventory> inventory = findByProductId(productId);

        if (inventory.isEmpty()) {
            return false;
        }

        Inventory currentInventory = inventory.get();
        currentInventory.setQuantity(quantity);
        currentInventory.setMinimumStock(minimumStock);
        repository.save(currentInventory);

        return true;
    }
}
