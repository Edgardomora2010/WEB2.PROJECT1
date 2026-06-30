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
import tec.nextshop_project_1.data.Property;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.service.CategoryService;
import tec.nextshop_project_1.service.ProductService;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones de mantenimiento
 * de productos de la aplicación.
 */
@Slf4j
@Controller
public class AdminProductsController {

    // OBJETOS/VARIABLES GLOBALES
    private final ProductService productService;
    private final CategoryService categoryService;

    /**
     * Constructor con inyección de dependencia.
     * @param productService
     * @param categoryService
     */
    public AdminProductsController(
            ProductService productService,
            CategoryService categoryService) {

        this.productService = productService;
        this.categoryService = categoryService;

    }

    @GetMapping("/AdminProducts")
    public String showAdminProductsPage(
            @RequestParam(required = false) String error,
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

        List<Product> products =
                productService.getAllProducts();

        model.addAttribute(
                "products",
                products
        );

        // Mensajes de error
        if("duplicated_sku".equals(error)) {

            model.addAttribute(
                    "errorMessage",
                    "El SKU indicado ya existe."
            );

        }

        log.info(
                "Carga::mantenimiento de productos."
        );

        return "managment/AdminProducts";

    }

    @PostMapping("/AdminProducts/Search")
    public String searchProducts(
            @RequestParam("searchType") String searchType,
            @RequestParam("searchValue") String searchValue,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        if(client == null) {
            return "redirect:/login";
        }

        if(client.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        List<Product> products =
                productService.searchProducts(
                        searchType,
                        searchValue
                );

        model.addAttribute(
                "products",
                products
        );

        log.info(
                "Búsqueda de productos realizada. Tipo:{} Valor:{}",
                searchType,
                searchValue
        );

        return "managment/AdminProducts";

    }

    @PostMapping("/AdminProducts/SwitchStatus")
    public String switchProductStatus(
            @RequestParam("productId") Long productId,
            HttpSession session) {

        Client client =
                (Client) session.getAttribute("client");

        if(client == null) {
            return "redirect:/login";
        }

        if(client.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        boolean wasUpdated =
                productService.switchProductStatus(
                        productId
                );

        if(wasUpdated) {

            log.info(
                    "Estado de producto modificado. Id:{}",
                    productId
            );

        }
        else {

            log.warn(
                    "No fue posible modificar producto. Id:{}",
                    productId
            );

        }

        return "redirect:/AdminProducts";

    }

    @PostMapping("/AdminProducts/Edit")
    public String showEditProductPage(
            @RequestParam("productId") Long productId,
            HttpSession session,
            Model model) {

        Client currentClient =
                (Client) session.getAttribute("client");

        if(currentClient == null) {
            return "redirect:/login";
        }

        if(currentClient.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        var product =
                productService.findByProductId(
                        productId
                );

        if(product.isEmpty()) {

            log.warn(
                    "Producto no encontrado. Id:{}",
                    productId
            );

            return "redirect:/AdminProducts";
        }

        model.addAttribute(
                "product",
                product.get()
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
                "Propiedades de producto: {}",
                product.get().getProperties()
        );

        return "managment/EditProduct";

    }

    @GetMapping("/AdminProducts/Create")
    public String showCreateProductPage(
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        if(client == null) {
            return "redirect:/login";
        }

        if(client.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        model.addAttribute(
                "product",
                new Product());

        model.addAttribute(
                "isNew",
                true
        );

        log.info(
                "Carga::formulario para creación de producto."
        );

        return "managment/EditProduct";

    }

    /**
     * Registra o actualiza un producto.
     * @param productId identificador del producto.
     * @param sku código SKU.
     * @param name nombre del producto.
     * @param description descripción.
     * @param categoryName categoría seleccionada.
     * @param price precio del producto.
     * @param featured indicador de destacado.
     * @param discountPercentage porcentaje de descuento.
     * @param active estado del producto.
     * @param session sesión actual.
     * @return Página de mantenimiento de productos.
     */
    @PostMapping("/AdminProducts/Save")
    public String saveProduct(
            @RequestParam(required = false) Long productId,
            @RequestParam("sku") String sku,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String categoryName,
            @RequestParam("price") double price,
            @RequestParam("imagePath") String imagePath,
            @RequestParam("featured") boolean featured,
            @RequestParam("discountPercentage") double discountPercentage,
            @RequestParam("active") boolean active,
            @RequestParam(required = false) String propertyName1,
            @RequestParam(required = false) String propertyValue1,
            @RequestParam(required = false) String propertyName2,
            @RequestParam(required = false) String propertyValue2,
            @RequestParam(required = false) String propertyName3,
            @RequestParam(required = false) String propertyValue3,
            @RequestParam(required = false) String propertyName4,
            @RequestParam(required = false) String propertyValue4,
            @RequestParam(required = false) String propertyName5,
            @RequestParam(required = false) String propertyValue5,
            HttpSession session,Model model) {

        Client currentClient =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(currentClient == null) {
            return "redirect:/login";
        }

        // Usuario sin permisos administrativos
        if(currentClient.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        var category =
                categoryService.findByCategoryName(
                        categoryName
                );

        if(category.isEmpty()) {

            log.warn(
                    "Categoría no encontrada: {}",
                    categoryName
            );

            return "redirect:/AdminProducts";
        }

        // Construye lista de propiedades del producto.
        List<Property> properties =
                new ArrayList<>();

        if(propertyName1 != null &&
                !propertyName1.trim().isEmpty()) {

            properties.add(
                    new Property(
                            propertyName1.trim(),
                            propertyValue1 == null
                                    ? ""
                                    : propertyValue1.trim()
                    )
            );

        }

        if(propertyName2 != null &&
                !propertyName2.trim().isEmpty()) {

            properties.add(
                    new Property(
                            propertyName2.trim(),
                            propertyValue2 == null
                                    ? ""
                                    : propertyValue2.trim()
                    )
            );

        }

        if(propertyName3 != null &&
                !propertyName3.trim().isEmpty()) {

            properties.add(
                    new Property(
                            propertyName3.trim(),
                            propertyValue3 == null
                                    ? ""
                                    : propertyValue3.trim()
                    )
            );

        }

        if(propertyName4 != null &&
                !propertyName4.trim().isEmpty()) {

            properties.add(
                    new Property(
                            propertyName4.trim(),
                            propertyValue4 == null
                                    ? ""
                                    : propertyValue4.trim()
                    )
            );

        }

        if(propertyName5 != null &&
                !propertyName5.trim().isEmpty()) {

            properties.add(
                    new Property(
                            propertyName5.trim(),
                            propertyValue5 == null
                                    ? ""
                                    : propertyValue5.trim()
                    )
            );

        }

        // CREAR PRODUCTO

        if(productId == null) {

            // Constructor mínimo
    /*
    Product newProduct =
            new Product(
                    null,
                    sku,
                    name,
                    description,
                    price,
                    "",
                    category.get(),
                    properties,
                    active
            );

     */

            // Corrección:
            // Valida que no se pierda el path de la image genérica al crear nuevos
            // productos o actualizar existentes, cuando carezcan de ruta de imagen
            if(imagePath == null ||
                    imagePath.trim().isEmpty() ||
                    imagePath.equals("image")) {

                imagePath = "/images/products/productIcon.png";
            }

            // Constructor completo
            Product newProduct =
                    new Product(
                            null,
                            sku,
                            name,
                            description,
                            price,
                            imagePath,
                            category.get(),
                            properties,
                            featured,
                            discountPercentage,
                            java.time.LocalDateTime.now(),
                            active
                    );

            boolean wasCreated =
                    productService.addProduct(
                            newProduct
                    );

            if(wasCreated) {

                log.info(
                        "Producto creado correctamente. SKU:{}",
                        sku
                );

                return "redirect:/AdminProducts";

            }
            else {

                log.warn(
                        "No fue posible crear producto. SKU:{}",
                        sku
                );

                // Conserva la información digitada por el usuario.
                model.addAttribute(
                        "product",
                        newProduct
                );

                // Recarga categorías para el selector.
                model.addAttribute(
                        "categories",
                        categoryService.getAllCategories()
                );

                // Indica a la vista que se encuentra en modo creación.
                model.addAttribute(
                        "isNew",
                        true
                );

                // Mensaje de error para fragmento de mensajes.
                model.addAttribute(
                        "errorMessage",
                        "Error:No se pudo agregar producto,el SKU indicado ya existe."
                );

                return "managment/EditProduct";

            }

        }

        // ACTUALIZAR PRODUCTO

        else {

            // Corrección:
            // Valida que no se pierda el path de la image genérica al crear nuevos
            // productos o actualizar existentes, cuando carezcan de ruta de imagen
            if(imagePath == null ||
                    imagePath.trim().isEmpty() ||
                    imagePath.equals("image")) {

                imagePath =
                        "/images/products/productIcon.png";
            }

            boolean wasUpdated =
                    productService.updateProduct(
                            productId,
                            sku,
                            name,
                            description,
                            price,
                            imagePath,
                            category.get(),
                            properties,
                            featured,
                            discountPercentage,
                            active
                    );

            if(wasUpdated) {

                log.info(
                        "Producto actualizado correctamente. Id:{}",
                        productId
                );

            } else {

                log.warn(
                        "No fue posible actualizar producto. Id:{}",
                        productId
                );

                return "redirect:/AdminProducts?error=duplicated_sku";

            }

        }

        return "redirect:/AdminProducts";

    }

}
