// PAQUETES
package tec.nextshop_project_1.controller;

// IMPORTACIÓN DE LIBRERÍAS
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.service.CategoryService;

/**
 * Controlador encargado de gestionar las operaciones de mantenimiento
 * de categorías de la aplicación.
 */
@Slf4j
@Controller
public class AdminCategoriesController {

    // OBJETOS/VARIABLES GLOBALES
    private final CategoryService categoryService;

    /**
     * Constructor con inyección de dependencia.
     * @param categoryService servicio de categorías.
     */
    public AdminCategoriesController(
            CategoryService categoryService) {

        this.categoryService =
                categoryService;

    }

    /**
     * Carga la página principal de mantenimiento
     * de categorías.
     *
     * @param error mensaje de error opcional.
     * @param session sesión actual.
     * @param model modelo de datos.
     * @return Plantilla AdminCategories.html
     */
    @GetMapping("/AdminCategories")
    public String showAdminCategoriesPage(
            @RequestParam(required = false)
            String error,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(client == null) {

            return "redirect:/login";

        }

        // Usuario sin permisos administrativos
        if(client.getRole() != Client.Profile.ADMIN) {

            return "redirect:/";

        }

        // Mensaje de error
        if(error != null) {

            model.addAttribute(
                    "errorMessage",
                    error
            );

        }

        // Recarga la lista de categorías
        log.info(
                "Recarga::categorías registradas."
        );

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        // Formulario en modo creación
        model.addAttribute(
                "isNew",
                true
        );

        return "managment/AdminCategories";

    }

    /**
     * Guarda una nueva categoría de productos.
     * @param categoryName
     * @param description
     * @param session
     * @param model
     * @return Redirección al mantenimiento de categorías.
     */
    @PostMapping("/AdminCategories/Save")
    public String saveCategory(
            @RequestParam String categoryName,
            @RequestParam String description,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(client == null) {

            return "redirect:/login";

        }

        // Usuario sin permisos administrativos
        if(client.getRole() != Client.Profile.ADMIN) {

            return "redirect:/";

        }

        // Validación de nombre
        if(categoryName == null ||
                categoryName.trim().isEmpty()) {

            model.addAttribute(
                    "errorMessage",
                    "Debe indicar el nombre de la categoría."
            );

            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            model.addAttribute(
                    "isNew",
                    true
            );

            return "managment/AdminCategories";

        }

        Category category =
                new Category(
                        null,
                        categoryName,
                        description,
                        true
                );

        boolean wasAdded =
                categoryService.addCategory(
                        category
                );

        // Categoría duplicada
        if(!wasAdded) {

            log.warn(
                    "No fue posible agregar la categoría:{}",
                    categoryName
            );

            model.addAttribute(
                    "errorMessage",
                    "La categoría ya existe."
            );

            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            model.addAttribute(
                    "isNew",
                    true
            );

            return "managment/AdminCategories";

        }

        log.info(
                "Categoría agregada:{}",
                categoryName
        );

        return "redirect:/AdminCategories";

    }

/**
 * Carga la categoría seleccionada en el formulario para su edición.
 *
 * @param categoryId identificador de la categoría.
 * @param session sesión actual.
 * @param model modelo de datos.
 * @return Plantilla AdminCategories.html
 */
    @PostMapping("/AdminCategories/Edit")
    public String showEditCategoryPage(
            @RequestParam Long categoryId,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(client == null) {

            return "redirect:/login";

        }

        // Usuario sin permisos administrativos
        if(client.getRole() != Client.Profile.ADMIN) {

            return "redirect:/";

        }

        Category category =
                categoryService.getAllCategories()
                        .stream()
                        .filter(currentCategory ->
                                currentCategory.getId().equals(categoryId))
                        .findFirst()
                        .orElse(null);

        // Categoría inexistente
        if(category == null) {

            model.addAttribute(
                    "errorMessage",
                    "La categoría seleccionada no existe."
            );

            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            model.addAttribute(
                    "isNew",
                    true
            );

            return "managment/AdminCategories";

        }

        model.addAttribute(
                "category",
                category
        );

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        model.addAttribute(
                "isNew",
                false
        );

        log.info(
                "Carga::categoría para edición:{}",
                category.getName()
        );

        return "managment/AdminCategories";

    }

/**
 * Actualiza la información de una categoría.
 *
 * @param categoryId identificador de la categoría.
 * @param categoryName nombre de la categoría.
 * @param description descripción.
 * @param session sesión actual.
 * @param model modelo de datos.
 * @return Redirección al mantenimiento de categorías.
 */
    @PostMapping("/AdminCategories/Update")
    public String updateCategory(
            @RequestParam Long categoryId,
            @RequestParam String categoryName,
            @RequestParam String description,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(client == null) {

            return "redirect:/login";

        }

        // Usuario sin permisos administrativos
        if(client.getRole() != Client.Profile.ADMIN) {

            return "redirect:/";

        }

        boolean updated =
                categoryService.updateCategory(
                        categoryId,
                        categoryName,
                        description
                );

        if(!updated) {

            model.addAttribute(
                    "errorMessage",
                    "No fue posible actualizar la categoría."
            );

            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            model.addAttribute(
                    "isNew",
                    true
            );

            return "managment/AdminCategories";

        }

        log.info(
                "Categoría actualizada:{}",
                categoryName
        );

        return "redirect:/AdminCategories";

    }

/**
 * Activa o desactiva una categoría de productos.
 *
 * @param categoryId identificador de la categoría.
 * @param session sesión actual.
 * @param model modelo de datos.
 * @return Redirección al mantenimiento de categorías.
 */
    @PostMapping("/AdminCategories/SwitchStatus")
    public String switchCategoryStatus(
            @RequestParam Long categoryId,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(client == null) {

            return "redirect:/login";

        }

        // Usuario sin permisos administrativos
        if(client.getRole() != Client.Profile.ADMIN) {

            return "redirect:/";

        }

        boolean wasUpdated =
                categoryService.switchCategoryStatus(
                        categoryId
                );

        // No fue posible cambiar el estado.
        if(!wasUpdated) {

            log.warn(
                    "No fue posible cambiar el estado de la categoría:{}",
                    categoryId
            );

            model.addAttribute(
                    "errorMessage",
                    "No es posible desactivar una categoría que posee productos asociados."
            );

            model.addAttribute(
                    "categories",
                    categoryService.getAllCategories()
            );

            model.addAttribute(
                    "isNew",
                    true
            );

            return "managment/AdminCategories";

        }

        log.info(
                "Cambio de estado::categoría:{}",
                categoryId
        );

        return "redirect:/AdminCategories";

    }

}

