// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Clase para representar la información del carrito de compras (items) dentro
 * de la aplicación.
 */
@Data
@RequiredArgsConstructor
public class CartItem {

    // OBJETOS/VARIABLES
    private Product product;
    private int quantity;

}

