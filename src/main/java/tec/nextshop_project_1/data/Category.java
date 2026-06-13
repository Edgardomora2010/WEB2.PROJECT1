// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase para representar la información de las categorías de productos
 * de la aplicación.
 */
@Data
@AllArgsConstructor
public class Category {

    // OBJETOS/VARIABLES
    private Long id;
    private String name;
    private String description;
    private boolean active;

}
