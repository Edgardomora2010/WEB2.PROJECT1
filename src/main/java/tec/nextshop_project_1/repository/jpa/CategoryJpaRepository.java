package tec.nextshop_project_1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.nextshop_project_1.data.Category;

import java.util.Optional;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNameIgnoreCase(String name);
}
