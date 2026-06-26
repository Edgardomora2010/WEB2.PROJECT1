// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERIAS
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.repository.interfaces.ICategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio simulado para la gestión de categorías.
 * Mantiene la información en memoria para el uso de la aplicación
 * hasta que, en una segunda fase, se implemente una base de datos
 * real en MySQL.
 */
@Repository
public class CategoryRepository implements ICategoryRepository {

    // OBJETOS/VARIABLES GLOBALES
    private final List<Category> categoryList = new ArrayList<>();

    /**
     * Crea categorías precargadas por defecto para simular información poblada
     * en una base de datos y los agrega a la lista de categorías de productos.
     */
    @PostConstruct
    public void init() {

        this.categoryList.add(new Category(1L,"Tecnología","Artículos de cómputo, dispositivos, celulares.",true));
        this.categoryList.add(new Category(2L,"Hogar","Artículos para uso doméstico, decoración y cocina.",true));
        this.categoryList.add(new Category(3L,"Deportes","Productos para deporte, actividad física y recreación.",true));
        this.categoryList.add(new Category(4L,"Libros","Material de lectura, educación y entretenimiento.",true));
        this.categoryList.add(new Category(5L,"Moda","Prendas de vestir, calzado y accesorios personales.",true));
        this.categoryList.add(new Category(6L,"Belleza","Artículos para el cuidado de la piel,cabello y cosmética.",true));
        this.categoryList.add(new Category(7L,"Juguetes","Artículos recreativos y educativos destinados al entretenimiento de niños.",true));
        this.categoryList.add(new Category(8L,"Otros","Productos de diversa naturaleza sin categoría específica",true));

    }

    /**
     * @param categoryName
     * @return Categoría de producto, mediante la búsqueda por medio del nombre de
     * la categoría
     */
    @Override
    public Optional<Category> findByCategoryName(String categoryName) {
        if (categoryName == null || categoryName.trim().isEmpty()) {
            return Optional.empty();
        }

        return categoryList.stream()
                .filter(categories -> categories.getName().equalsIgnoreCase(categoryName.trim()))
                .findFirst();
    }

    /**
     * @param category
     * Agrega una categoría de producto deseada en memoria, para uso del sistema.
     */
    @Override
    public void addCategory(Category category) {
        categoryList.add(category);
    }

    /**
     * @return Una lista de todas las categorías de producto en memoria, para uso
     * del sistema.
     */
    public List<Category> getAllCategories(){
        return categoryList;
    }

    /**
     * Actualiza una categoría existente en memoria.
     * @param category categoría actualizada.
     */
    @Override
    public void updateCategory(Category category) {

        for(int i = 0; i < categoryList.size(); i++) {

            if(categoryList.get(i).getId().equals(category.getId())) {

                categoryList.set(
                        i,
                        category
                );

                return;

            }

        }

    }

/**
 * Cambia el estado activo/inactivo de una categoría.
 *
 * @param categoryId identificador de la categoría.
 * @return true si fue posible modificar el estado.
 */
    @Override
    public boolean switchCategoryStatus(
            Long categoryId) {

        for(Category category : categoryList) {

            if(category.getId().equals(categoryId)) {

                category.setActive(
                        !category.isActive()
                );

                return true;

            }

        }

        return false;

    }

}
