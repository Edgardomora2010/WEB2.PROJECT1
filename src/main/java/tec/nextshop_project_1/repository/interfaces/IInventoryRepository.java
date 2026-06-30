// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Inventory;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para inventario de productos
 * Implementaciones (in-memory, JPA, JDBC, etc.).
 */
@Repository
public interface IInventoryRepository {
    Optional<Inventory> findByProductId(Long productId);

    /**
     * @return Todos los items de inventario
     */
    List<Inventory> getAllInventoryItems();

    /**
     * Agrega un nuevo registro de inventario.
     * @param inventory
     * @return true si se agregó correctamente, false si ya existe.
     */
    boolean addInventoryItem(Inventory inventory);

    /**
     * Actualiza la cantidad disponible y el stock mínimo de un producto
     * en inventario.
     * @param productId id del producto a modificar.
     * @param quantity nueva cantidad (mayor o menor al actual)
     * @param minimumStock nuevo stock mínimo (mayor o menor al actual)
     * @return true si se actualizó correctamente.
     */
    boolean updateInventory(
            Long productId,
            int quantity,
            int minimumStock
    );

}
