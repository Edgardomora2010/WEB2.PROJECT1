// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERÍAS
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Inventory;
import tec.nextshop_project_1.repository.interfaces.IInventoryRepository;
import tec.nextshop_project_1.repository.interfaces.IProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio simulado para la gestión de inventario.
 * Mantiene la información en memoria para el uso de la aplicación
 * hasta que, en una segunda fase, se implemente una base de datos
 * real en MySQL.
 */
@Repository
@Profile("in-memory")
public class InventoryRepository implements IInventoryRepository {

    // OBJETOS Y VARIABLES GLOBALES
    private final List<Inventory> inventoryList = new ArrayList<>();
    private final IProductRepository productRepository;

    /**
     * Constructor con inyección de dependencia:
     * ProductRepository.
     * @param productRepository
     */
    public InventoryRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Precarga inventario de producto en memoria para uso de aplicación.
     */
    @PostConstruct
    public void init() {

        /*
            id
            product
            quantity
            minimumStock
         */

        this.inventoryList.add(new Inventory(1L, productRepository.findByProductId(1L).orElseThrow(), 35, 10));
        this.inventoryList.add(new Inventory(2L, productRepository.findByProductId(2L).orElseThrow(), 18, 5));
        this.inventoryList.add(new Inventory(3L, productRepository.findByProductId(3L).orElseThrow(), 42, 10));
        this.inventoryList.add(new Inventory(4L, productRepository.findByProductId(4L).orElseThrow(), 15, 5));
        this.inventoryList.add(new Inventory(5L, productRepository.findByProductId(5L).orElseThrow(), 8, 2));
        this.inventoryList.add(new Inventory(6L, productRepository.findByProductId(6L).orElseThrow(), 10, 3));
        this.inventoryList.add(new Inventory(7L, productRepository.findByProductId(7L).orElseThrow(), 30, 10));
        this.inventoryList.add(new Inventory(8L, productRepository.findByProductId(8L).orElseThrow(), 22, 5));
        this.inventoryList.add(new Inventory(9L, productRepository.findByProductId(9L).orElseThrow(), 5, 2));
        this.inventoryList.add(new Inventory(10L, productRepository.findByProductId(10L).orElseThrow(), 12, 3));
        this.inventoryList.add(new Inventory(11L, productRepository.findByProductId(11L).orElseThrow(), 4, 2));
        this.inventoryList.add(new Inventory(12L, productRepository.findByProductId(12L).orElseThrow(), 7, 2));
        this.inventoryList.add(new Inventory(13L, productRepository.findByProductId(13L).orElseThrow(), 20, 5));
        this.inventoryList.add(new Inventory(14L, productRepository.findByProductId(14L).orElseThrow(), 18, 5));
        this.inventoryList.add(new Inventory(15L, productRepository.findByProductId(15L).orElseThrow(), 3, 1));
        this.inventoryList.add(new Inventory(16L, productRepository.findByProductId(16L).orElseThrow(), 2, 1));

        this.inventoryList.add(new Inventory(17L, productRepository.findByProductId(17L).orElseThrow(), 15, 5));
        this.inventoryList.add(new Inventory(18L, productRepository.findByProductId(18L).orElseThrow(), 24, 5));
        this.inventoryList.add(new Inventory(19L, productRepository.findByProductId(19L).orElseThrow(), 18, 5));
        this.inventoryList.add(new Inventory(20L, productRepository.findByProductId(20L).orElseThrow(), 25, 10));
        this.inventoryList.add(new Inventory(21L, productRepository.findByProductId(21L).orElseThrow(), 16, 5));
        this.inventoryList.add(new Inventory(22L, productRepository.findByProductId(22L).orElseThrow(), 7, 2));
        this.inventoryList.add(new Inventory(23L, productRepository.findByProductId(23L).orElseThrow(), 3, 1));
        this.inventoryList.add(new Inventory(24L, productRepository.findByProductId(24L).orElseThrow(), 6, 2));
        this.inventoryList.add(new Inventory(25L, productRepository.findByProductId(25L).orElseThrow(), 11, 3));
        this.inventoryList.add(new Inventory(26L, productRepository.findByProductId(26L).orElseThrow(), 9, 3));
        this.inventoryList.add(new Inventory(27L, productRepository.findByProductId(27L).orElseThrow(), 2, 1));
        this.inventoryList.add(new Inventory(28L, productRepository.findByProductId(28L).orElseThrow(), 14, 5));
        this.inventoryList.add(new Inventory(29L, productRepository.findByProductId(29L).orElseThrow(), 5, 2));
        this.inventoryList.add(new Inventory(30L, productRepository.findByProductId(30L).orElseThrow(), 8, 2));

        this.inventoryList.add(new Inventory(31L, productRepository.findByProductId(31L).orElseThrow(), 18, 5));
        this.inventoryList.add(new Inventory(32L, productRepository.findByProductId(32L).orElseThrow(), 35, 10));
        this.inventoryList.add(new Inventory(33L, productRepository.findByProductId(33L).orElseThrow(), 28, 10));
        this.inventoryList.add(new Inventory(34L, productRepository.findByProductId(34L).orElseThrow(), 10, 3));
        this.inventoryList.add(new Inventory(35L, productRepository.findByProductId(35L).orElseThrow(), 6, 2));
        this.inventoryList.add(new Inventory(36L, productRepository.findByProductId(36L).orElseThrow(), 4, 2));
        this.inventoryList.add(new Inventory(37L, productRepository.findByProductId(37L).orElseThrow(), 22, 5));
        this.inventoryList.add(new Inventory(38L, productRepository.findByProductId(38L).orElseThrow(), 2, 1));
        this.inventoryList.add(new Inventory(39L, productRepository.findByProductId(39L).orElseThrow(), 12, 3));
        this.inventoryList.add(new Inventory(40L, productRepository.findByProductId(40L).orElseThrow(), 5, 2));

        this.inventoryList.add(new Inventory(41L, productRepository.findByProductId(41L).orElseThrow(), 3, 1));

        this.inventoryList.add(new Inventory(42L, productRepository.findByProductId(42L).orElseThrow(), 14, 5));
        this.inventoryList.add(new Inventory(43L, productRepository.findByProductId(43L).orElseThrow(), 12, 5));
        this.inventoryList.add(new Inventory(44L, productRepository.findByProductId(44L).orElseThrow(), 10, 3));
        this.inventoryList.add(new Inventory(45L, productRepository.findByProductId(45L).orElseThrow(), 9, 3));
        this.inventoryList.add(new Inventory(46L, productRepository.findByProductId(46L).orElseThrow(), 8, 2));
        this.inventoryList.add(new Inventory(47L, productRepository.findByProductId(47L).orElseThrow(), 11, 3));
        this.inventoryList.add(new Inventory(48L, productRepository.findByProductId(48L).orElseThrow(), 7, 2));
        this.inventoryList.add(new Inventory(49L, productRepository.findByProductId(49L).orElseThrow(), 4, 1));
        this.inventoryList.add(new Inventory(50L, productRepository.findByProductId(50L).orElseThrow(), 13, 5));
        this.inventoryList.add(new Inventory(51L, productRepository.findByProductId(51L).orElseThrow(), 6, 2));

        this.inventoryList.add(new Inventory(52L, productRepository.findByProductId(52L).orElseThrow(), 20, 5));
        this.inventoryList.add(new Inventory(53L, productRepository.findByProductId(53L).orElseThrow(), 15, 5));
        this.inventoryList.add(new Inventory(54L, productRepository.findByProductId(54L).orElseThrow(), 18, 5));
        this.inventoryList.add(new Inventory(55L, productRepository.findByProductId(55L).orElseThrow(), 12, 5));
        this.inventoryList.add(new Inventory(56L, productRepository.findByProductId(56L).orElseThrow(), 30, 10));
        this.inventoryList.add(new Inventory(57L, productRepository.findByProductId(57L).orElseThrow(), 8, 2));
        this.inventoryList.add(new Inventory(58L, productRepository.findByProductId(58L).orElseThrow(), 5, 2));
        this.inventoryList.add(new Inventory(59L, productRepository.findByProductId(59L).orElseThrow(), 16, 5));
        this.inventoryList.add(new Inventory(60L, productRepository.findByProductId(60L).orElseThrow(), 9, 3));
        this.inventoryList.add(new Inventory(61L, productRepository.findByProductId(61L).orElseThrow(), 6, 2));
        this.inventoryList.add(new Inventory(62L, productRepository.findByProductId(62L).orElseThrow(), 4, 1));

        this.inventoryList.add(new Inventory(63L, productRepository.findByProductId(63L).orElseThrow(), 17, 5));
        this.inventoryList.add(new Inventory(64L, productRepository.findByProductId(64L).orElseThrow(), 14, 5));
        this.inventoryList.add(new Inventory(65L, productRepository.findByProductId(65L).orElseThrow(), 15, 5));
        this.inventoryList.add(new Inventory(66L, productRepository.findByProductId(66L).orElseThrow(), 10, 3));
        this.inventoryList.add(new Inventory(67L, productRepository.findByProductId(67L).orElseThrow(), 24, 8));
        this.inventoryList.add(new Inventory(68L, productRepository.findByProductId(68L).orElseThrow(), 7, 2));
        this.inventoryList.add(new Inventory(69L, productRepository.findByProductId(69L).orElseThrow(), 4, 1));
        this.inventoryList.add(new Inventory(70L, productRepository.findByProductId(70L).orElseThrow(), 12, 5));
        this.inventoryList.add(new Inventory(71L, productRepository.findByProductId(71L).orElseThrow(), 8, 2));
        this.inventoryList.add(new Inventory(72L, productRepository.findByProductId(72L).orElseThrow(), 5, 2));
        this.inventoryList.add(new Inventory(73L, productRepository.findByProductId(73L).orElseThrow(), 3, 1));

        this.inventoryList.add(new Inventory(74L, productRepository.findByProductId(74L).orElseThrow(), 18, 5));
        this.inventoryList.add(new Inventory(75L, productRepository.findByProductId(75L).orElseThrow(), 25, 10));
        this.inventoryList.add(new Inventory(76L, productRepository.findByProductId(76L).orElseThrow(), 14, 5));
        this.inventoryList.add(new Inventory(77L, productRepository.findByProductId(77L).orElseThrow(), 20, 5));
        this.inventoryList.add(new Inventory(78L, productRepository.findByProductId(78L).orElseThrow(), 22, 5));
        this.inventoryList.add(new Inventory(79L, productRepository.findByProductId(79L).orElseThrow(), 12, 3));
        this.inventoryList.add(new Inventory(80L, productRepository.findByProductId(80L).orElseThrow(), 6, 2));
        this.inventoryList.add(new Inventory(81L, productRepository.findByProductId(81L).orElseThrow(), 4, 1));
        this.inventoryList.add(new Inventory(82L, productRepository.findByProductId(82L).orElseThrow(), 11, 3));
        this.inventoryList.add(new Inventory(83L, productRepository.findByProductId(83L).orElseThrow(), 7, 2));
        this.inventoryList.add(new Inventory(84L, productRepository.findByProductId(84L).orElseThrow(), 3, 1));
        this.inventoryList.add(new Inventory(85L, productRepository.findByProductId(85L).orElseThrow(), 2, 1));

        this.inventoryList.add(new Inventory(86L, productRepository.findByProductId(86L).orElseThrow(), 40, 10));
        this.inventoryList.add(new Inventory(87L, productRepository.findByProductId(87L).orElseThrow(), 80, 20));
        this.inventoryList.add(new Inventory(88L, productRepository.findByProductId(88L).orElseThrow(), 75, 20));
        this.inventoryList.add(new Inventory(89L, productRepository.findByProductId(89L).orElseThrow(), 35, 10));
        this.inventoryList.add(new Inventory(90L, productRepository.findByProductId(90L).orElseThrow(), 28, 10));
        this.inventoryList.add(new Inventory(91L, productRepository.findByProductId(91L).orElseThrow(), 50, 15));
        this.inventoryList.add(new Inventory(92L, productRepository.findByProductId(92L).orElseThrow(), 25, 10));
        this.inventoryList.add(new Inventory(93L, productRepository.findByProductId(93L).orElseThrow(), 30, 10));
        this.inventoryList.add(new Inventory(94L, productRepository.findByProductId(94L).orElseThrow(), 60, 15));
        this.inventoryList.add(new Inventory(95L, productRepository.findByProductId(95L).orElseThrow(), 22, 5));
        this.inventoryList.add(new Inventory(96L, productRepository.findByProductId(96L).orElseThrow(), 10, 3));
        this.inventoryList.add(new Inventory(97L, productRepository.findByProductId(97L).orElseThrow(), 8, 2));
        this.inventoryList.add(new Inventory(98L, productRepository.findByProductId(98L).orElseThrow(), 45, 10));
        this.inventoryList.add(new Inventory(99L, productRepository.findByProductId(99L).orElseThrow(), 6, 2));

    }

    /**
     *
     * @param productId
     * @return Item de inventario mediante id de producto
     */
    @Override
    public Optional<Inventory> findByProductId(Long productId) {

        if (productId == null || productId <= 0){
            return Optional.empty();
        }

        return inventoryList.stream()
                .filter(inventoryItem -> inventoryItem.getProduct().getId().equals(productId))
                .findFirst();
    }

    /**
     * Agrega un nuevo registro de inventario.
     * @param inventory
     * @return true si se agregó correctamente, false si ya existe.
     */
    public boolean addInventoryItem(Inventory inventory) {

        long nextId = 1;
        for (Inventory currentInventory : inventoryList) {
            if (currentInventory.getId() >= nextId) {
                nextId = currentInventory.getId() + 1;
            }
        }

        Optional<Inventory> inventoryItem =
                findByProductId(
                        inventory.getProduct().getId()
                );

        if (inventoryItem.isPresent()) {
            return false;
        }

        Inventory newInventory = new Inventory(
                nextId,
                inventory.getProduct(),
                inventory.getQuantity(),
                inventory.getMinimumStock()
        );

        inventoryList.add(newInventory);

        return true;

    }

    /**
     * Actualiza la cantidad disponible y el stock mínimo de un producto
     * en inventario.
     * @param productId id del producto a modificar.
     * @param quantity nueva cantidad (mayor o menor al actual)
     * @param minimumStock nuevo stock mínimo (mayor o menor al actual)
     * @return
     */
    @Override
    public boolean updateInventory(
            Long productId,
            int quantity,
            int minimumStock) {

        Optional<Inventory> inventory =
                findByProductId(productId);

        if (inventory.isEmpty()) {
            return false;
        }

        inventory.get().setQuantity(quantity);
        inventory.get().setMinimumStock(minimumStock);

        return true;

    }

    /**
     * @return Lista completa de registros de inventario.
     */
    @Override
    public List<Inventory> getAllInventoryItems() {
        return inventoryList;
    }

}
