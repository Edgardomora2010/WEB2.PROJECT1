// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACION DE LIBRERIAS
import tec.nextshop_project_1.data.Category;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz de repositorio para categorías  de productos
 * Implementaciones (in-memory, JPA, JDBC, etc.).
 */
public interface ICategoryRepository {

    /**
     * @param categoryName
     * @return Categoría mediante el nombre de categoría
     */
    Optional<Category> findByCategoryName(String categoryName);

    /**
     * @param category
     * Agrega una categoría de producto a la aplicación
     */
    void addProductCategory(Category category);

    /**
     * @return Una lista con todas las categorías de productos.
     */
    List<Category> getAllCategories();

}
