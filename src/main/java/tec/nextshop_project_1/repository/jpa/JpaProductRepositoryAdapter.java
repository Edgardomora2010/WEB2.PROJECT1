package tec.nextshop_project_1.repository.jpa;

import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.repository.interfaces.IProductRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class JpaProductRepositoryAdapter implements IProductRepository {

    private final ProductJpaRepository repository;

    public JpaProductRepositoryAdapter(ProductJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Product> findByProductName(String productName) {
        if (productName == null || productName.isBlank()) {
            return Optional.empty();
        }
        return repository.findByNameIgnoreCase(productName.trim());
    }

    @Override
    public Optional<Product> findByProductId(Long productId) {
        if (productId == null) {
            return Optional.empty();
        }
        return repository.findById(productId);
    }

    @Override
    public List<Product> findByCategory(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return List.of();
        }
        return repository.findByCategory_NameIgnoreCase(categoryName.trim());
    }

    @Override
    public List<Product> findByNameContaining(String productName) {
        if (productName == null || productName.isBlank()) {
            return List.of();
        }
        return repository.findByNameContainingIgnoreCase(productName.trim());
    }

    @Override
    public List<Product> findByCategoryAndNameContaining(String categoryName, String productName) {
        if (categoryName == null || categoryName.isBlank()
                || productName == null || productName.isBlank()) {
            return List.of();
        }
        return repository.findByCategory_NameIgnoreCaseAndNameContainingIgnoreCase(
                categoryName.trim(),
                productName.trim()
        );
    }

    @Override
    public void addProduct(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public void updateProduct(Product product) {
        repository.save(product);
    }

    @Override
    public boolean switchProductStatus(Long productId) {
        Optional<Product> product = findByProductId(productId);
        if (product.isEmpty()) {
            return false;
        }
        product.get().setActive(!product.get().isActive());
        repository.save(product.get());
        return true;
    }

    @Override
    public List<Product> searchProducts(String searchType, String searchValue) {
        if (searchType == null || searchType.isBlank() || searchValue == null || searchValue.isBlank()) {
            return getAllProducts();
        }

        String value = searchValue.trim();
        return switch (searchType.toLowerCase()) {
            case "id" -> {
                try {
                    yield findByProductId(Long.parseLong(value)).map(List::of).orElse(List.of());
                } catch (NumberFormatException ex) {
                    yield List.of();
                }
            }
            case "sku" -> findBySku(value).map(List::of).orElse(List.of());
            case "name", "nombre" -> findByNameContaining(value);
            case "category", "categoria", "categoría" -> findByCategory(value);
            case "featured", "destacado" -> getAllProducts().stream()
                    .filter(Product::isFeatured)
                    .toList();
            case "discount", "descuento" -> getAllProducts().stream()
                    .filter(product -> product.getDiscountPercentage() > 0)
                    .toList();
            default -> getAllProducts();
        };
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        if (sku == null || sku.isBlank()) {
            return Optional.empty();
        }
        return repository.findBySkuIgnoreCase(sku.trim());
    }
}
