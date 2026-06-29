package tec.nextshop_project_1.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.data.Inventory;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.repository.interfaces.ICategoryRepository;
import tec.nextshop_project_1.repository.interfaces.IClientRepository;
import tec.nextshop_project_1.repository.interfaces.IInventoryRepository;
import tec.nextshop_project_1.repository.interfaces.IProductRepository;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ICategoryRepository categoryRepository;
    private final IClientRepository clientRepository;
    private final IProductRepository productRepository;
    private final IInventoryRepository inventoryRepository;

    public DataInitializer(
            ICategoryRepository categoryRepository,
            IClientRepository clientRepository,
            IProductRepository productRepository,
            IInventoryRepository inventoryRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void run(String... args) {
        seedCategories();
        seedClients();
        seedProducts();
        seedInventory();
    }

    private void seedCategories() {
        if (!categoryRepository.getAllCategories().isEmpty()) {
            return;
        }

        tec.nextshop_project_1.repository.in_memory.CategoryRepository seedRepository =
                new tec.nextshop_project_1.repository.in_memory.CategoryRepository();
        seedRepository.init();

        seedRepository.getAllCategories().forEach(categoryRepository::addCategory);
    }

    private void seedClients() {
        if (!clientRepository.getAllClients().isEmpty()) {
            return;
        }

        tec.nextshop_project_1.repository.in_memory.ClientRepository seedRepository =
                new tec.nextshop_project_1.repository.in_memory.ClientRepository();
        seedRepository.init();

        seedRepository.getAllClients().forEach(clientRepository::addClient);
    }

    private void seedProducts() {
        if (!productRepository.getAllProducts().isEmpty()) {
            return;
        }

        tec.nextshop_project_1.repository.in_memory.CategoryRepository seedCategoryRepository =
                new tec.nextshop_project_1.repository.in_memory.CategoryRepository();
        seedCategoryRepository.init();

        tec.nextshop_project_1.repository.in_memory.ProductRepository seedProductRepository =
                new tec.nextshop_project_1.repository.in_memory.ProductRepository(seedCategoryRepository);
        seedProductRepository.init();

        Map<Long, Category> persistedCategories = categoryRepository.getAllCategories()
                .stream()
                .collect(Collectors.toMap(Category::getId, category -> category));

        for (Product product : seedProductRepository.getAllProducts()) {
            Category persistedCategory = persistedCategories.get(product.getCategory().getId());
            product.setCategory(persistedCategory);
            productRepository.addProduct(product);
        }
    }

    private void seedInventory() {
        if (!inventoryRepository.getAllInventoryItems().isEmpty()) {
            return;
        }

        tec.nextshop_project_1.repository.in_memory.InventoryRepository seedRepository =
                new tec.nextshop_project_1.repository.in_memory.InventoryRepository(productRepository);
        seedRepository.init();

        for (Inventory inventory : seedRepository.getAllInventoryItems()) {
            inventoryRepository.addInventoryItem(inventory);
        }
    }
}
