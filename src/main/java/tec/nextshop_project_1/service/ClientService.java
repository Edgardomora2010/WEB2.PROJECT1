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
        INVALID_PASSWORD,
        INACTIVE_ACCOUNT
    }

    /**
     * Constructor de clase con inyección de dependencia:
     * @param clientRepository
     */
    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * Busca un cliente mediante su identificador.
     * @param clientId identificador de cliente.
     * @return Cliente encontrado o optional vacío.
     */
    public Optional<Client> findById(Long clientId) {

        if(clientId == null) {
            return Optional.empty();
        }

        return clientRepository.findById(clientId);

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

        if (client.get().getPassword().equals(clientPassword))
        {
            if(!client.get().isActive()) {
                return LoginResult.INACTIVE_ACCOUNT;
            }
            else {
                return LoginResult.SUCCESS;
            }
        }

        return LoginResult.SUCCESS;

    }

    /**
     * Agrega un cliente a la aplicación.
     * @param client información del cliente.
     * @param role perfil que se asignará al usuario.
     * @return true si fue posible agregar el usuario;
     *         false si el correo ya existe.
     */
    public boolean addClient(Client client, Client.Profile role) {

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

        Client newClient = new Client(role,
                                      nextId,
                                      client.getName().trim(),
                                      client.getLastName().trim(),
                                      client.getEmail().trim(),
                                      client.getPassword(),
                                      client.getPhoneNumber(),
                                      client.getAddress(),
                                      client.getRegistrationDate(),
                                true);

        clientRepository.addClient(newClient);
        return true;

    }

    /**
     * Actualiza la información editable de un usuario.
     * @param clientId identificador del usuario.
     * @param password contraseña.
     * @param phoneNumber teléfono.
     * @param address dirección.
     * @param active estado del usuario.
     * @return true si fue posible actualizar.
     */
    public boolean updateClient(
            Long clientId,
            String password,
            String phoneNumber,
            String address,
            boolean active) {

        Optional<Client> client =
                findById(clientId);

        if(client.isEmpty()) {
            return false;
        }

        Client updatedClient =
                new Client(
                        client.get().getRole(),
                        client.get().getId(),
                        client.get().getName(),
                        client.get().getLastName(),
                        client.get().getEmail(),
                        password,
                        phoneNumber,
                        address,
                        client.get().getRegistrationDate(),
                        active
                );

        clientRepository.updateClient(
                updatedClient
        );

        return true;

    }

    /**
     * Cambia el estado activo/inactivo de un usuario.
     * @param clientId identificador del usuario.
     * @return true si fue posible realizar la operación.
     */
    public boolean switchClientStatus(Long clientId) {

        return clientRepository.switchClientStatus(
                clientId
        );

    }

    /**
     * Busca clientes según criterio indicado.
     * @param searchType tipo de búsqueda.
     * @param searchValue valor a buscar.
     * @return Lista de clientes encontrados.
     */
    public List<Client> searchClients(
            String searchType,
            String searchValue) {

        return clientRepository.searchClients(
                searchType,
                searchValue
        );

    }

    /**
     * Registra un nuevo administrador en la aplicación.
     * @param name nombre.
     * @param lastName apellidos.
     * @param email correo electrónico.
     * @param password contraseña.
     * @param phoneNumber teléfono.
     * @param address dirección.
     * @return true si fue posible registrarlo.
     */
    public boolean createAdmin(
            String name,
            String lastName,
            String email,
            String password,
            String phoneNumber,
            String address) {

        Client newClient =
                new Client(
                        Client.Profile.ADMIN,
                        null,
                        name,
                        lastName,
                        email,
                        password,
                        phoneNumber,
                        address,
                        java.time.LocalDate.now(),
                        true
                );

        return addClient(
                newClient,
                Client.Profile.ADMIN
        );

    }

}
