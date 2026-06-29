// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.*;
import tec.nextshop_project_1.repository.interfaces.IInventoryRepository;

import java.util.*;

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

    /**
     *
     * @param cart Carrito de productos de cliente.
     * @return Una lista de productos en el carrito, cuya stock en inventario es
     * inferior a lo pretende rebajar el carrito del inventario.
     */
    public List<Inventory> validateInventoryDecrease(Cart cart){

        List<Inventory> rejectedProducts = new ArrayList<>();

        // Recorre los productos del carrito.
        for (CartItem cartItem : cart.getItems()) {

            Optional<Inventory> optInventory =
                    findByProductId(
                            cartItem.getProduct().getId()
                    );

            if (optInventory.isPresent()) {

                Inventory inventory =
                        optInventory.get();

                if (inventory.getQuantity() < cartItem.getQuantity()) {

                    rejectedProducts.add(
                            inventory
                    );

                }

            }

        }

        return rejectedProducts;

    }

    /**
     * Método sobrecargado que devuelve, Una lista de productos en el carrito, cuyo
     * stock en inventario es inferior a lo pretende rebajar el carrito del inventario.
     * @param preOrder Recibe una objeto Order en vez de Cart
     * @return
     */
    public List<Inventory> validateInventoryDecrease(Order preOrder){

        List<Inventory> rejectedProducts = new ArrayList<>();

        // Recorre los productos del carrito.
        for (OrderItem orderItem : preOrder.getOrderItems()) {

            Optional<Inventory> optInventory =
                    findByProductId(
                            orderItem.getProductId()
                    );

            if (optInventory.isPresent()) {

                Inventory inventory =
                        optInventory.get();

                if (inventory.getQuantity() < orderItem.getQuantity()) {

                    rejectedProducts.add(
                            inventory
                    );

                }

            }

        }

        return rejectedProducts;

    }

    /**
     * Actualiza la información de inventario de un producto.
     * @param productId
     * @param quantity
     * @param minimumStock
     * @return true si la actualización fue exitosa.
     */
    public boolean updateInventory(
            Long productId,
            int quantity,
            int minimumStock) {

        if (productId == null || productId <= 0) {
            return false;
        }

        if (quantity < 0 || minimumStock < 0) {
            return false;
        }

        return inventoryRepository.updateInventory(
                productId,
                quantity,
                minimumStock
        );

    }

    /**
     * Rebaja del inventario las cantidades correspondientes a los productos
     * vendidos en una orden.
     * @param order Orden confirmada.
     * @return true si fue posible rebajar todo el inventario.
     */
    public boolean decreaseInventory(Order order) {

        // Recorre los productos vendidos
        for (OrderItem orderItem : order.getOrderItems()) {

            Optional<Inventory> optInventory =
                    findByProductId(
                            orderItem.getProductId()
                    );

            // Producto inexistente
            if(optInventory.isEmpty()) {
                return false;
            }

            Inventory inventory =
                    optInventory.get();

            if(inventory.getQuantity()
                    < orderItem.getQuantity()) {

                return false;

            }

            // se rebajan existencias
            inventory.setQuantity(inventory.getQuantity()
                    - orderItem.getQuantity());

            // Guardar cambios en el repositorio
            //inventoryRepository.saveInventory(inventory);

        }

        return true;

    }

}








