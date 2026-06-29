package tec.nextshop_project_1.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import tec.nextshop_project_1.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByNameIgnoreCase(String name);

    Optional<Product> findBySkuIgnoreCase(String sku);

    List<Product> findByCategory_NameIgnoreCase(String categoryName);

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategory_NameIgnoreCaseAndNameContainingIgnoreCase(String categoryName, String name);
}
