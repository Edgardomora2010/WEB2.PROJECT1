// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase para representar la información de las propiedades de un producto
 * de la aplicación.
 */
@Data
@AllArgsConstructor
public class Property {

    // OBJETOS/VARIABLES
    private String name;
    private String value;

}
