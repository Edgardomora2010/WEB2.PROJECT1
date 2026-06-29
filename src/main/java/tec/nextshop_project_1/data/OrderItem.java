// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACIÓN DE LIBRERÍAS
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase para representar una línea de una orden de compra.
 * Cada objeto almacena una fotografía del producto vendido
 * al momento de realizar la compra.
 */
@Data
@RequiredArgsConstructor
public class OrderItem {

    // Datos históricos del producto
    private Long productId;
    private String sku;
    private String productName;
    // Datos de la venta
    private int quantity;
    private double unitPrice;
    // Totales de la línea
    private double subTotal;
    private double discountAmount;
    private double taxAmount;
    private double totalAmount;

}
