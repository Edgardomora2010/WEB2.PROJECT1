// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase para representar la información de inventario de productos dentro
 * de la aplicación.
 */
@Data
@AllArgsConstructor
public class Inventory {

    // OBJETOS/VARIABLES
    private Long id;
    private Product product;
    private int quantity;
    private int minimumStock;

}
