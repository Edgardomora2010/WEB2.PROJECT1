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
    private ProductService productService;

    /**
     * // Constructor con inyección de dependencia: ICategoryRepository
     * @param categoryRepository
     */
    public CategoryService(ICategoryRepository categoryRepository,
                           ProductService productService) {
        this.categoryRepository = categoryRepository;
        this.productService = productService;
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
    public boolean addCategory(Category category) {

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

        categoryRepository.addCategory(newCategory);
        return true;

    }

    /**
     * Actualiza la información de una categoría.
     *
     * @param categoryId identificador de la categoría.
     * @param categoryName nombre de la categoría.
     * @param description descripción.
     * @return true si fue posible actualizar.
     */
    public boolean updateCategory(
            Long categoryId,
            String categoryName,
            String description) {

        Optional<Category> currentCategory =
                categoryRepository.getAllCategories()
                        .stream()
                        .filter(category ->
                                category.getId().equals(categoryId))
                        .findFirst();

        if(currentCategory.isEmpty()) {

            return false;

        }

        Category updatedCategory =
                new Category(
                        categoryId,
                        categoryName.trim(),
                        description.trim(),
                        currentCategory.get().isActive()
                );

        categoryRepository.updateCategory(
                updatedCategory
        );

        return true;

    }
    
    /**
     * * Cambia el estado activo/inactivo de una categoría.
     * No permite desactivar categorías que tengan productos
     * asociados.
     * @param categoryId identificador de la categoría.
     * @return true si fue posible modificar el estado.
     */
    public boolean switchCategoryStatus(
            Long categoryId) {

        Optional<Category> category =
                categoryRepository.getAllCategories()
                        .stream()
                        .filter(currentCategory ->
                                currentCategory.getId().equals(categoryId))
                        .findFirst();

        // Categoría inexistente.
        if(category.isEmpty()) {

            return false;

        }

        // No permite desactivar una categoría
        // con productos asociados.
        if(category.get().isActive()) {
            
            if(productService.hasProductsInCategory(
                    categoryId)) {

                return false;

            }

        }

        return categoryRepository.switchCategoryStatus(
                categoryId
        );

    }
    
}
