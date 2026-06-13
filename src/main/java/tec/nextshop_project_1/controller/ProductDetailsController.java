// PAQUETES
package tec.nextshop_project_1.controller;

//IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.service.CategoryService;
import tec.nextshop_project_1.service.InventoryService;
import tec.nextshop_project_1.service.ProductService;
import java.util.Optional;

/**
 * Controlador encargado de gestionar la carga, visualización y
 * comportamiento de la página ProductDetails.html.
 */
@Slf4j
@Controller
public class ProductDetailsController {

    // OBJETOS/VARIABLES GLOBALES
    private final CategoryService categoryService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    /**
     * Constructor con inyección de dependencias:
     * @param productService
     * @param inventoryService
     */
    public ProductDetailsController(CategoryService categoryService, ProductService productService,
                                    InventoryService inventoryService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.inventoryService = inventoryService;

    }

    @GetMapping("/products/{id}")
    public String showProductDetails(@PathVariable Long id,
                                     Model model) {

        // Obtiene producto mediante el id de producto
        Optional<Product> product =
                productService.findByProductId(id);

        // si el producto tiene datos
        if (product.isEmpty()) {
            return "redirect:/";
        }

        // 1. Carga categorías de productos en selector de búsqueda
        // a la vista
        log.info("Carga categorías de productos al selector");
        model.addAttribute(
                "product_categories",
                categoryService.getAllCategories());

        // 2. Envía datos de producto a vista
        log.info("Carga datos de producto seleccionado");
        model.addAttribute(
                "product",
                product.get()
        );

        // 3. Envía datos de stock de productos en inventario a vista
        log.info("Carga cantidad de stock de producto");
        model.addAttribute(
                "productStock",
                inventoryService.getProductStock(id)
        );

        // Retorna página para vistalizar detalle completo de producto seleccionado
        // ProductDetails.html
        return "pages/ProductDetails";

    }

}