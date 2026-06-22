// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACION DE LIBRERIAS
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Cart;
import tec.nextshop_project_1.data.Client;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para clientes de la aplicación
 * Implementaciones (in-memory, JPA, etc.).
 */
@Repository
public interface IClientRepository {

    Optional<Client> findById(Long clientId);

    /**
     * Encuentra un cliente por su correo electrónico
     * @param clientMail el correo electrónico a buscar
     * @return Retorna un Optional que contiene el usuario encontrado;
     * en caso contrario, un Optional vacío.
     */
    Optional<Client> findByClientEmail(String clientMail);

    /**
     * Agrega un cliente a la aplicación
     * @param client
     */
    void addClient(Client client);

    /**
     * @return Una lista con todos los clientes de la aplicación
     */
    List<Client> getAllClients();

    /**
     * Actualiza la información editable de un cliente.
     * @param client cliente con información actualizada.
     */
    void updateClient(Client client);

    /**
     * Cambia el estado de un usuario.
     * Si está activo lo desactiva.
     * Si está inactivo lo activa.
     * @param clientId identificador del usuario.
     * @return true si fue posible modificar el estado.
     */
    boolean switchClientStatus(Long clientId);

    /**
     * Realiza una búsqueda de clientes según el criterio indicado.
     * @param searchType tipo de búsqueda (Id, nombre, correo).
     * @param searchValue valor a buscar.
     * @return Lista de clientes encontrados.
     */
    List<Client> searchClients(
            String searchType,
            String searchValue
    );

}

