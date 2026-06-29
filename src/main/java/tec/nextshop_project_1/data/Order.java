package tec.nextshop_project_1.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    public enum PaymentMethod {
        CASH,
        CREDIT_CARD,
        DEBIT_CARD,
        SINPE,
        BANK_TRANSFER
    }

    public enum OrderStatus {
        PENDING,
        PAID,
        PREPARING,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    @Id
    private Long id;
    private String orderNumber;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;
    private LocalDateTime deliveredDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Long clientId;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String shippingAddress;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;
    private double subTotal;
    private double discountAmount;
    private double taxAmount;
    private double shippingAmount;
    private double totalAmount;
    private String cardType;
    private String cardNumber;
}
