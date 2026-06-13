// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERIAS
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.repository.interfaces.ICategoryRepository;
import java.util.List;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión de categorías de productos.
 */
@Service
public class CategoryService {

    // OBJETOS/VARIABLES GLOBALES
    private final ICategoryRepository categoryRepository;

    /**
     * // Constructor con inyección de dependencia: ICategoryRepository
     * @param categoryRepository
     */
    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * @param categoryName
     * @return Categoría mediante el nombre de categoría
     */
    public Optional<Category> findByCategoryName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return Optional.empty();
        }
        return categoryRepository.findByCategoryName(categoryName.trim());
    }

    /**
     * @return Lista de todas las categorías de productos
     */
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    /**
     * @param category
     * Agrega una categoría de productos a la aplicación
     */
    public boolean addProductCategory(Category category) {

        // Busca el id de categoría más alto y genera el siguiente consecutivo.
        long nextId = 1;
        for (Category currentCategory : categoryRepository.getAllCategories()) {
            if (currentCategory.getId() >= nextId) {
                nextId = currentCategory.getId() + 1;
            }
        }

        Optional<Category> categoryName = findByCategoryName(category.getName()) ;

        if (categoryName.isPresent()){
            return false;
        }

        // Orden del constructor en Category
        /*
             id,
             name,
             description,
             active;
         */

        Category newCategory = new Category(
                nextId,
                category.getName().trim(),
                category.getDescription().trim(),
                true);

        categoryRepository.addProductCategory(newCategory);
        return true;

    }

}
