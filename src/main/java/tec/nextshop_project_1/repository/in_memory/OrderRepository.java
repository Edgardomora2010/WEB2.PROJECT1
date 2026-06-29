// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Order;
import tec.nextshop_project_1.repository.interfaces.IOrderRepository;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Repositorio simulado para la gestión de órdenes de compra.
 * Mantiene la información únicamente en memoria.
 */
@Repository
@Profile("in-memory")
public class OrderRepository implements IOrderRepository {

    /**
     * Estructura de almacenamiento en memoria.
     * Relaciona el id de la orden con la orden.
     */
    private final Map<Long, Order> orders;

    /**
     * Constructor.
     */
    public OrderRepository() {

        this.orders = new HashMap<>();

    }

    /**
     * Guarda o actualiza una orden.
     */
    @Override
    public void saveOrder(Order order) {

        orders.put(
                order.getId(),
                order
        );

    }

    /**
     * Busca una orden por id.
     */
    @Override
    public Optional<Order> findByOrderId(Long orderId) {
        return Optional.ofNullable(
                orders.get(orderId)
        );

    }

    /**
     * Obtiene todas las órdenes de un cliente.
     */
    @Override
    public List<Order> findOrdersByClientId(Long clientId) {

        return orders.values()
                .stream()
                .filter(order ->
                        order.getClientId().equals(clientId))
                .collect(Collectors.toList());

    }

    /**
     * Obtiene todas las órdenes.
     */
    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(
                orders.values()
        );
    }

    /**
     * Elimina una orden.
     */
    @Override
    public void deleteOrder(Long orderId) {
        orders.remove(orderId);
    }

}
