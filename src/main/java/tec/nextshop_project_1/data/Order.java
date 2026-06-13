// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Clase para representar la información de las órdenes de compra
 * de la aplicación.
 */
@Data
@RequiredArgsConstructor
public class Order {

    // OBJETOS/VARIABLES
    private Long id;
    private Client client;
    private List<OrderItem> items;
    private LocalDateTime orderDate;
    private String status;
    private double totalAmount;

}
