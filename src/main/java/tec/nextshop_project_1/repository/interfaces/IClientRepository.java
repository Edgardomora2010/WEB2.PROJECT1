// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACION DE LIBRERIAS
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Client;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para clientes de la aplicación
 * Implementaciones (in-memory, JPA, etc.).
 */
@Repository
public interface IClientRepository {

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

}

