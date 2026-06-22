// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.Inventory;
import tec.nextshop_project_1.repository.interfaces.IInventoryRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión de inventario de productos.
 */
@Service
public class InventoryService {

    // OBJETOS/VARIABLES GLOBALES
    private final IInventoryRepository inventoryRepository;

    /**
     * Constructor de clase, inyección de dependencia:
     * IInventoryRepository.
     * @param inventoryRepository
     */
    public InventoryService(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * @return Lista completa de registros de inventario.
     */
    public List<Inventory> getAllInventoryItems() {
        return inventoryRepository.getAllInventoryItems();
    }

    /**
     * Busca un registro de inventario mediante el id del producto.
     * @param productId
     * @return Registro de inventario asociado al producto.
     */
    public Optional<Inventory> findByProductId(Long productId) {

        if (productId == null || productId <= 0) {
            return Optional.empty();
        }

        return inventoryRepository.findByProductId(productId);

    }

    /**
     * Obtiene la cantidad disponible de un producto.
     * @param productId
     * @return Cantidad disponible en inventario.
     */
    public int getProductStock(Long productId) {

        Optional<Inventory> inventory =
                findByProductId(productId);

        if (inventory.isEmpty()) {
            return 0;
        }

        return inventory.get().getQuantity();
    }

    /**
     * Obtiene una estructura con el stock disponible de los productos.
     * Relaciona el id de cada producto con su cantidad disponible en inventario.
     * @return Diccionario con los ids de productos y sus cantidades en stock.
     */
    public Map<Long, Integer> getProductStockMap() {

        Map<Long, Integer> productStock = new HashMap<>();
        for (Inventory inventory : getAllInventoryItems()) {

            productStock.put(
                    inventory.getProduct().getId(),
                    inventory.getQuantity()
            );
        }

        return productStock;

    }

}








