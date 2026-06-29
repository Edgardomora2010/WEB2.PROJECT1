package tec.nextshop_project_1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.nextshop_project_1.data.Client;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmailIgnoreCase(String email);
}
