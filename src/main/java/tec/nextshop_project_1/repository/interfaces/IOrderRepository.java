// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACIÓN DE LIBRERÍAS
import tec.nextshop_project_1.data.Order;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para órdenes de compra.
 */
public interface IOrderRepository {

    /**
     * Guarda una nueva orden o actualiza una existente.
     * @param order
     */
    void saveOrder(Order order);

    /**
     * Busca una orden mediante su identificador.
     * @param orderId
     * @return
     */
    Optional<Order> findByOrderId(Long orderId);

    /**
     * Obtiene todas las órdenes de un cliente.
     * @param clientId
     * @return
     */
    List<Order> findOrdersByClientId(Long clientId);

    /**
     * Obtiene todas las órdenes registradas.
     * @return
     */
    List<Order> getAllOrders();

    /**
     * Elimina una orden.
     * @param orderId
     */
    void deleteOrder(Long orderId);

}
