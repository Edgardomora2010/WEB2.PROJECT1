// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERIAS
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.repository.interfaces.IClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                        Client.Profile.CUSTOMER,
                        1L,
                "Edgardo",
                "Mora",
                "edgardomm@gmail.com",
                "nextshop123+",
                "84360264",
                "La Aurora, Heredia",
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
                true));

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


}
