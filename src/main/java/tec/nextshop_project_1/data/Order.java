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
    private String orderNumber;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;
    private LocalDateTime deliveredDate;
    private OrderStatus status = OrderStatus.PENDING;
    private PaymentMethod paymentMethod;
    // Datos del cliente
    private Long clientId;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String shippingAddress;
    // Productos
    private List<OrderItem> orderItems;
    // Totales
    private double subTotal;
    private double discountAmount;
    private double taxAmount;
    private double shippingAmount;
    private double totalAmount;
    // Simulación tipo de pago
    // Observaciones: Esto debería modelarse en otra entidad sin embargo por
    // motivos de tiempo y que es un proyecto académico se aprovecha la oportunidad
    // de modelarlo en esta entidad.
    private String cardType;
    private String cardNumber;

}