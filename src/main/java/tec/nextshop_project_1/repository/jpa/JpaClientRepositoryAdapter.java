package tec.nextshop_project_1.repository.jpa;

import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.repository.interfaces.IClientRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaClientRepositoryAdapter implements IClientRepository {

    private final ClientJpaRepository repository;

    public JpaClientRepositoryAdapter(ClientJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Client> findById(Long clientId) {
        if (clientId == null) {
            return Optional.empty();
        }
        return repository.findById(clientId);
    }

    @Override
    public Optional<Client> findByClientEmail(String clientMail) {
        if (clientMail == null || clientMail.isBlank()) {
            return Optional.empty();
        }
        return repository.findByEmailIgnoreCase(clientMail.trim());
    }

    @Override
    public void addClient(Client client) {
        repository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @Override
    public void updateClient(Client client) {
        repository.save(client);
    }

    @Override
    public boolean switchClientStatus(Long clientId) {
        Optional<Client> client = findById(clientId);
        if (client.isEmpty()) {
            return false;
        }
        client.get().setActive(!client.get().isActive());
        repository.save(client.get());
        return true;
    }

    @Override
    public List<Client> searchClients(String searchType, String searchValue) {
        if (searchType == null || searchType.isBlank() || searchValue == null || searchValue.isBlank()) {
            return getAllClients();
        }

        String value = searchValue.trim().toLowerCase();
        return switch (searchType.toLowerCase()) {
            case "id" -> {
                try {
                    yield findById(Long.parseLong(value)).map(List::of).orElse(List.of());
                } catch (NumberFormatException ex) {
                    yield List.of();
                }
            }
            case "email", "correo" -> findByClientEmail(value).map(List::of).orElse(List.of());
            case "name", "nombre" -> getAllClients().stream()
                    .filter(client -> client.getName() != null
                            && client.getName().toLowerCase().contains(value))
                    .toList();
            default -> getAllClients();
        };
    }
}
