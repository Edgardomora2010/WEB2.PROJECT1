// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACIÓN DE LIBRERIAS
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Product;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para productos de la aplicación.
 * Implementaciones (in-memory, JPA, JDBC, etc.).
 */
@Repository
public interface IProductRepository {

    /**
     * @param productName
     * @return Un producto mediante la búsqueda por nombre de producto
     */
    Optional<Product> findByProductName(String productName);

    /**
     * @param productId
     * @return Un producto mediante la búsqueda por id de producto
     */
    Optional<Product> findByProductId(Long productId);

    /**
     * @param categoryName
     * @return Lista de productos pertenecientes a una categoría
     */
    List<Product> findByCategory(String categoryName);

    /**
     * @param productName
     * @return Lista de productos cuyo nombre contiene el texto indicado
     */
    List<Product> findByNameContaining(String productName);

    /**
     * @param categoryName
     * @param productName
     * @return Lista de productos pertenecientes a una categoría y cuyo
     * nombre contiene el texto indicado
     */
    List<Product> findByCategoryAndNameContaining(
            String categoryName,
            String productName
    );

    /**
     * Agrega un producto a la aplicación
     * @param product
     */
    void addProduct(Product product);

    /**
     * @return Una lista con todos los productos de la aplicación
     */
    List<Product> getAllProducts();

}