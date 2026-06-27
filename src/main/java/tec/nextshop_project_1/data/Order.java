// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase para representar la información de las órdenes de compra
 * de la aplicación.
 */
@Data
@RequiredArgsConstructor
public class Order {

    /**
     * Métodos de pago soportados por la aplicación.
     */
    public enum PaymentMethod {
        CASH,
        CREDIT_CARD,
        DEBIT_CARD,
        SINPE,
        BANK_TRANSFER
    }

    /**
     * Estados posibles de una orden de compra.
     */
    public enum OrderStatus {
        PENDING,
        PAID,
        PREPARING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    // OBJETOS/VARIABLES
    private Long id;
    private Client client;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private String shippingAddress;
    private double subTotal;
    private double discountAmount;
    private double taxAmount;
    private double shippingAmount;
    private double totalAmount;

}