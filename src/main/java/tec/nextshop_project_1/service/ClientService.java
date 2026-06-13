// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERIAS
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.repository.interfaces.IClientRepository;
import java.util.List;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión de clientes.
 */
@Service
public class ClientService {

    // OBJETOS/VARIABLES GLOBALES
    private final IClientRepository clientRepository;

    public enum LoginResult {
        SUCCESS,
        USER_NOT_FOUND,
        INVALID_PASSWORD
    }

    /**
     * Constructor de clase con inyección de dependencia:
     * @param clientRepository
     */
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Busca un cliente mediante su correo electrónico.
     * @param clientEmail correo electrónico a buscar.
     * @return Retorna un Optional que contiene el cliente encontrado;
     *         en caso contrario, un Optional vacío.
     */
    public Optional<Client> findByClientEmail(String clientEmail) {
        if (clientEmail == null || clientEmail.trim().isEmpty()) {
            return Optional.empty();
        }
        return clientRepository.findByClientEmail(clientEmail.trim());
    }

    /**
     * @return Lista de todos los clientes
     */
    public List<Client> getAllClients() {

        return clientRepository.getAllClients();

    }

    public LoginResult authenticate(
            String clientEmail,
            String clientPassword) {

        Optional<Client> client =
                findByClientEmail(clientEmail);

        if (client.isEmpty()) {
            return LoginResult.USER_NOT_FOUND;
        }

        if (!client.get().getPassword().equals(clientPassword)) {
            return LoginResult.INVALID_PASSWORD;
        }

        return LoginResult.SUCCESS;
    }

    /**
     * @param client
     * Agrega cliente a la aplicación
     */
    public boolean addClient(Client client) {

        // Busca el id de cliente más alto y genera el siguiente consecutivo.
        long nextId = 1;
        for (Client currentClient : clientRepository.getAllClients()) {
            if (currentClient.getId() >= nextId) {
                nextId = currentClient.getId() + 1;
            }
        }

        Optional<Client> clientEmail = findByClientEmail(client.getEmail()) ;

        if (clientEmail.isPresent()){
            return false;
        }

        // Orden del constructor en Client
        /*
           Profile,
           ID,
           Nombre,
           Apellido
           correo electrónico
           contraseña
           Teléfono
           Dirección
           estado
         */

        Client newClient = new Client(Client.Profile.CUSTOMER,
                                      nextId,
                                      client.getName().trim(),
                                      client.getLastName().trim(),
                                      client.getEmail().trim(),
                                      client.getPassword(),
                                      client.getPhoneNumber(),
                                      client.getAddress(),
                                true);

        clientRepository.addClient(newClient);
        return true;

    }



}
