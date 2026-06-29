// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.config.AuxStoreSettings;
import tec.nextshop_project_1.data.*;
import tec.nextshop_project_1.repository.interfaces.IOrderRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión de pedidos de productos.
 */
@Slf4j
@Service
public class OrderService {

    // OBJETOS/VARIABLES GLOBALES
    private final IOrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final AuxStoreSettings auxStoreSet;
    private final InventoryService inventoryService;

    /**
     * Constructor de clase con inyección de dependencias.
     * @param oderRepository
     */
    public OrderService(IOrderRepository oderRepository, ShoppingCartService shoppingCartService, AuxStoreSettings auxStoreSet, InventoryService inventoryService) {
        this.orderRepository = oderRepository;
        this.shoppingCartService = shoppingCartService;
        this.auxStoreSet = auxStoreSet;
        this.inventoryService = inventoryService;
    }

    /**
     * Encontrar order mediante id de orden.
     * @param orderId
     * @return Una orden de un cliente.
     */
    public Optional<Order> findByOrderId(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    /**
     * Obtiene todas las órdenes realizadas por un cliente.
     * @param clientId identificador del cliente.
     * @return Lista de órdenes del cliente.
     */
    public List<Order> findOrdersByClientId(Long clientId){
        return orderRepository.findOrdersByClientId(
                clientId
        );
    }

    /**
     * Construye una preorden a partir del cliente y el carrito de compras.
     * La preorden permite visualizar el resumen de la compra antes de
     * confirmar el pedido definitivo.
     * @param client Cliente que realiza la compra.
     * @param cart Carrito de compras del cliente.
     * @return Preorden generada.
     */
    public Order createPreOrder(Client client, Cart cart){

        long nextOrderId = 1;
        Order preOrder = new Order();
        // Lista de items de preorden
        List<OrderItem> preOrderItems = new ArrayList<>();

        if(orderRepository.getAllOrders().size() > 0){
            for(Order currentOrder :
                    orderRepository.getAllOrders())
            {
                if(currentOrder.getId() >= nextOrderId) {
                    nextOrderId = currentOrder.getId() + 1;
                }
            }
        }

        // Datos básicos de orden de pedido, y cliente
        preOrder.setId(nextOrderId);
        preOrder.setOrderNumber("PNX-" + String.format("%010d", nextOrderId));
        preOrder.setOrderDate(LocalDateTime.now());
        preOrder.setStatus(Order.OrderStatus.PENDING);
        preOrder.setClientId(client.getId());
        preOrder.setClientName(cart.getClient().getName() + cart.getClient().getLastName());
        preOrder.setClientEmail(cart.getClient().getEmail());
        preOrder.setClientPhone(cart.getClient().getPhoneNumber());
        preOrder.setShippingAddress(cart.getClient().getAddress());

        // Items de carritos, covertidos a items de pedido
        for (CartItem cartItem : cart.getItems()){

            OrderItem orderItem = new OrderItem();
            Product prodItem = cartItem.getProduct();
            orderItem.setProductId(prodItem.getId());
            orderItem.setSku(prodItem.getSku());
            orderItem.setProductName(prodItem.getName());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setUnitPrice(prodItem.getPrice());
            double subtotal = prodItem.getPrice() * cartItem.getQuantity();
            orderItem.setSubTotal(subtotal);
            double discountAmount = (prodItem.getDiscountPercentage()/100);
            orderItem.setDiscountAmount(subtotal * discountAmount);
            double taxAmount = (subtotal - discountAmount) * auxStoreSet.getTax();
            orderItem.setTaxAmount(taxAmount);
            double total = (subtotal - discountAmount) + taxAmount;
            orderItem.setTotalAmount(total);

            preOrderItems.add(orderItem);

        }

        // se agrega lista de items de pre orden
        preOrder.setOrderItems(preOrderItems);

        // Resumen de preorden
        long clientId = client.getId();
        preOrder.setSubTotal(shoppingCartService.calculateCartItemsSubTotal(clientId));
        preOrder.setDiscountAmount(shoppingCartService.calculateCartItemsTotalDiscount(clientId));
        preOrder.setTaxAmount(shoppingCartService.calculateCartItemsTax(clientId));
        preOrder.setTotalAmount(shoppingCartService.calculateCartItemsTotal(clientId));
        preOrder.setShippingAmount(shoppingCartService.getShippingCost());
        // devuelve preorden
        return preOrder;

    }

    public Order createClientOrder(Order preOrder) {

         // Cambia estado a PAID
         // Rebaja inventario
         // Guarda orden
         // Elimina carrito
        Order newOrder = preOrder;
        newOrder.setStatus(Order.OrderStatus.PAID);
        inventoryService.decreaseInventory(newOrder);
        orderRepository.saveOrder(newOrder);
        shoppingCartService.deleteCart(newOrder.getClientId());

        return newOrder;
    }


}
