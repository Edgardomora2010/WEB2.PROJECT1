// PAQUETES
package tec.nextshop_project_1.controller;

// IMPORTACIÓN DE LIBRERÍAS
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.service.CategoryService;
import org.springframework.ui.Model;
import tec.nextshop_project_1.service.InventoryService;
import tec.nextshop_project_1.service.ProductService;
import tec.nextshop_project_1.service.ShoppingCartService;

import javax.management.remote.rmi.RMIConnection;

/**
 * Controlador principal encargado de gestionar la carga, visualización
 * y comportamiento de la página principal: Home.
 */
@Slf4j
@Controller
public class HomeController  {

    // OBJETOS/VARIABLES GLOBALES
    private final CategoryService categoryService;
    private final ProductService productService;
    private final InventoryService inventoryService;
    private final ShoppingCartService shoppingCartService;

    /**
     * Constructor con inyección de dependencias:
     * @param categoryService
     * @param productService
     * @param inventoryService
     */
    public HomeController(CategoryService categoryService, ProductService productService, InventoryService inventoryService, ShoppingCartService shoppingCartService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.inventoryService = inventoryService;
        this.shoppingCartService = shoppingCartService;
    }

    /**
     * Carga la página principal de la aplicación.
     * Envía al modelo las categorías y listas de productos requeridas por Home.
     * @param model Modelo utilizado para enviar información a la vista.
     * @return Nombre de la plantilla: Home.html.
     */
    @GetMapping("/")
    public String showHome(@RequestParam(required = false) String alert,
                           Model model,HttpSession session) {

        Client client =
                (Client) session.getAttribute("client");

        // 1.
        log.info("Carga::página principal:[Home.html]");

        log.info("Carga::categorías de producto para:[selector de búsqueda]");
        // 2. Carga categorías de productos en selector de búsqueda.
        model.addAttribute(
                "product_categories",
                      categoryService.getAllCategories());

        // 3. Carga de productos destacados a section de productos destacados
        log.info("Carga:: en <section> [productos destacados]");
        model.addAttribute(
                "featuredProducts",
                productService.getFeaturedProducts());

        // 4. Carga de productos en oferta a section de productos en oferta
        log.info("Carga:: en <section> [productos en oferta]");
        model.addAttribute(
                "saleProducts",
                productService.getProductsOnSale());

        // 5. Carga de productos agregados recientemente a section de productos
        // recientes
        log.info("Carga:: en <section> [productos recientes]");
        model.addAttribute(
                "newProducts",
                productService.getNewestProducts());

        // 6. Carga de cantidades disponibles en stock para los productos
        // mostrados en las diferentes <sections> de la página.
        log.info("Carga::[cantidad en stock] de todos los productos en:[tarjetas de productos]");
        model.addAttribute(
                "productStock",
                inventoryService.getProductStockMap()
        );

        //Items en carrito
        if (client != null) {
            model.addAttribute(
                    "cartItemsCount",
                    shoppingCartService.getCartItemsCount(client.getId())
            );
        } else {
            model.addAttribute("cartItemsCount", 0);
        }

        // Se informa detalle a la vista
        if ("product_in_cart_required".equals(alert)) {
            model.addAttribute(
                    "errorMessage",
                    "Para acceder al carrito primero debe agregar al menos un producto."
            );
        }

        // Devuelve plantilla (Home.html), habiéndose cargado componentes la de página
        return "pages/home";

    }

    /** Procesa la búsqueda de productos realizada por el usuario.
     * @param categoryName
     * @param productName
     * @param model
     * @return Plantilla Home con secciones de productos por defecto
     * (destacados, en oferta, recientemente agregados) + los de búsqueda
     * personalizada por el cliente
     * */
    @PostMapping("/search-products")
    // Recibe parámetros de búsqueda categoría seleccionada en el selector
    // de categorías + y nombre de un producto buscado
    public String searchProducts(
            @RequestParam("category_name") String categoryName,
            @RequestParam("product_name") String productName,
            Model model) {

        // 1. Selector de categorías
        log.info("Recarga::categorías de productos en objeto:[selector de categorías]");
        model.addAttribute(
                "product_categories",
                categoryService.getAllCategories()
        );

        // Mantiene criterio de búsqueda seleccionado
        model.addAttribute(
                "selectedCategory",
                categoryName
        );

        // Mantiene texto buscado
        model.addAttribute(
                "searchedProductName",
                productName
        );

        // 2. Carga resultados de búsqueda según la categoría seleccionada
        // y el nombre de producto ingresado por el cliente, en el <section>
        // para búsquedas de productos realizadas por el cliente
        log.info("Búsqueda:: de productos realizada por: Categoría: {}, Nombre: {}",
                categoryName,
                productName
        );

        model.addAttribute(
                "searchResults",
                productService.getProductsBySearchSelection(
                        categoryName,
                        productName
                )
        );

        var results =
                productService.getProductsBySearchSelection(
                        categoryName,
                        productName
                );

        model.addAttribute(
                "searchResults",
                results
        );

        model.addAttribute(
                "resultCount",
                results.size()
        );


        // 3. Destacados
        log.info("Carga:: en <section> [productos destacados]");
        model.addAttribute(
                "featuredProducts",
                productService.getFeaturedProducts()
        );

        // 4. Ofertas
        log.info("Carga:: en <section> [productos en oferta]");
        model.addAttribute(
                "saleProducts",
                productService.getProductsOnSale()
        );

        // 5. Recientes
        log.info("Carga:: en <section> [productos recientes]");
        model.addAttribute(
                "newProducts",
                productService.getNewestProducts()
        );

        // 6. Carga de cantidades disponibles en stock para los productos
        // mostrados en las diferentes <sections> de la página.
        log.info("Recarga:[cantidad en stock] en todas:[tarjetas de productos]");
        model.addAttribute(
                "productStock",
                inventoryService.getProductStockMap()
        );

        // Retorna página Home.html, recargada mostrando los <sections> de
        // búsqueda de producto realizada por el cliente, + las secciones
        // por defecto de productos: destacados, en oferta y recientes.
        return "pages/home";

    }

}