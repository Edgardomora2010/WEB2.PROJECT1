// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

/**
 * Clase para representar la información del carrito de compras dentro
 * de la aplicación.
 */
@Data
@RequiredArgsConstructor
public class Cart {

    // OBJETOS/VARIABLES
    private Long id;
    private Client client;
    private List<CartItem> items;

}
