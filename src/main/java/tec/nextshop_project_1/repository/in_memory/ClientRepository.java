// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERIAS
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.repository.interfaces.IClientRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repositorio simulado para la gestión de clientes.
 * Mantiene la información en memoria para el uso de la aplicación
 * hasta que, en una segunda fase, se implemente una base de datos
 * real en MySQL.
 */
@Repository
public class ClientRepository implements IClientRepository {

    // OBJETOS/VARIABLES GLOBALES
    private final List<Client> clientsList = new ArrayList<>();

    /**
     * Crea clientes por defecto para simular información poblada en una base de
     * datos, y los agrega a la lista de usuario.
     */
    @PostConstruct
    public void init() {

        this.clientsList.add(

                new Client(
                        Client.Profile.ADMIN,
                        1L,
                "Edgardo",
                "Mora",
                "edgardomm@gmail.com",
                "nextshop123+",
                "84360264",
                "La Aurora, Heredia",
                        LocalDate.of(2026, 1, 15),
                true));

        this.clientsList.add(

                new Client(
                Client.Profile.CUSTOMER,
                2L,
                "Oscar",
                "Marin",
                "oscarmz@gmail.com",
                "nextshop123+",
                "60593251",
                "San josé",
                        LocalDate.of(2026, 1, 30),
                true));

        this.clientsList.add(

                new Client(
                Client.Profile.CUSTOMER,
                3L,
                "Brian",
                "Smith",
                "brian@gmail.com",
                "nextshop123+",
                "60604545",
                "Limón",
                LocalDate.of(2026, 1, 10),
                false));

    }

    /**
     * Busca un cliente mediante su id de cliente
     * @param clientId
     * @return Un opcional que contiene el cliente encontrado;
     * en caso contrario un opcional vacío.
     */
    @Override
    public Optional<Client> findById(Long clientId) {

        return clientsList.stream()
                .filter(client ->
                        client.getId().equals(clientId))
                .findFirst();

    }

    /**
     * Busca un cliente mediante su correo electrónico.
     * @param clientMail correo electrónico a buscar.
     * @return Un Optional que contiene el cliente encontrado;en caso contrario,
     * un Optional vacío.
     */
    @Override
    public Optional<Client> findByClientEmail(String clientMail) {
        if (clientMail == null || clientMail.trim().isEmpty()) {
            return Optional.empty();
        }

        return clientsList.stream()
                .filter(client -> client.getEmail().equalsIgnoreCase(clientMail.trim()))
                .findFirst();
    }

    /**
     * @param client
     * Agrega cliente a la lista de clientes de la aplicación
     */
    @Override
    public void addClient(Client client) {
        clientsList.add(client);
    }

    /**
     * @return Lista de clientes de la aplicación
     */
    public List<Client> getAllClients(){
        return clientsList;
    }

    /**
     * Actualiza la información editable de un cliente.
     * @param client cliente con información actualizada.
     */
    @Override
    public void updateClient(Client client) {

        Optional<Client> currentClient =
                findById(client.getId());

        if(currentClient.isEmpty()) {
            return;
        }

        currentClient.get().setPassword(
                client.getPassword()
        );

        currentClient.get().setPhoneNumber(
                client.getPhoneNumber()
        );

        currentClient.get().setAddress(
                client.getAddress()
        );

        currentClient.get().setActive(
                client.isActive()
        );

    }

    /**
     * Cambia el estado activo/inactivo de un usuario.
     * @param clientId identificador del usuario.
     * @return true si fue posible modificar el estado.
     */
    @Override
    public boolean switchClientStatus(Long clientId) {

        Optional<Client> client =
                findById(clientId);

        if(client.isEmpty()) {
            return false;
        }

        client.get().setActive(
                !client.get().isActive()
        );

        return true;

    }

    /**
     * Busca clientes según criterio indicado.
     * @param searchType tipo de búsqueda.
     * @param searchValue valor a buscar.
     * @return Lista de clientes encontrados.
     */
    @Override
    public List<Client> searchClients(
            String searchType,
            String searchValue) {

        if(searchValue == null ||
                searchValue.trim().isEmpty()) {

            return getAllClients();

        }

        String value =
                searchValue.trim().toLowerCase();

        switch(searchType) {

            case "ID":

                return clientsList.stream()
                        .filter(client ->
                                String.valueOf(client.getId())
                                        .contains(value))
                        .collect(Collectors.toList());

            case "EMAIL":

                return clientsList.stream()
                        .filter(client ->
                                client.getEmail()
                                        .toLowerCase()
                                        .contains(value))
                        .collect(Collectors.toList());

            case "NAME":

                return clientsList.stream()
                        .filter(client ->
                                client.getName()
                                        .toLowerCase()
                                        .contains(value))
                        .collect(Collectors.toList());

            default:

                return getAllClients();

        }

    }


}
