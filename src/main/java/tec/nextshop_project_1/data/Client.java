//PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Clase para representar la información de los clientes dentro
 * de la aplicación.
 */
@Data
@AllArgsConstructor
public class Client {

    // OBJETOS/VARIABLES
    public enum Profile {
        CUSTOMER,
        ADMIN
    }

    private final Profile role;
    private final Long id;
    private final String name;
    private final String lastName;
    private final String email;
    private String password;
    private String phoneNumber;
    private String address;
    private boolean active;

}
