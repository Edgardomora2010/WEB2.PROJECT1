// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase para representar la información de los productos
 * pertenecientes a una orden de compra.
 */
@Data
@RequiredArgsConstructor
public class OrderItem {

    // OBJETOS/VARIABLES
    private Product product;
    private int quantity;
    private double unitPrice;
    private double subTotal;

}
