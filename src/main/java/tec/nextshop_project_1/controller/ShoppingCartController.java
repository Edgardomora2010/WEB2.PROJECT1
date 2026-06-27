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
import tec.nextshop_project_1.data.Cart;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.service.InventoryService;
import tec.nextshop_project_1.service.ProductService;
import tec.nextshop_project_1.service.ShoppingCartService;
import java.util.Optional;

/**
 * Controlador encargado de gestionar la carga, visualización
 * y comportamiento de la página ShoppingCart.html.
 */
@Slf4j
@Controller
public class ShoppingCartController {

    // OBJETOS/VARIABLES GLOBALES
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final InventoryService inventoryService;

    /**
     * Constructor con inyección de dependencias:
     * @param shoppingCartService
     * @param productService
     * @param inventoryService
     */
    public ShoppingCartController(
            ShoppingCartService shoppingCartService,
            ProductService productService,
            InventoryService inventoryService) {

        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.inventoryService = inventoryService;

    }

    /**
     * Carga la página ShoppingCart.html.
     * Permite visualizar el contenido del carrito de compras
     * del cliente autenticado.
     * @param session
     * @param model
     * @return Plantilla:ShoppingCart.html
     */
    @GetMapping("/cart")
    public String showShoppingCart(
            HttpSession session,
            Model model) {

        // 1. Carga de página de carrito de compra
        log.info("Carga::página de registro de productos a carrito:[ShoppingCart.html]");

        // Obtiene cliente autenticado en sesión
        Client client = (Client) session.getAttribute("client");

        // Si no existe cliente autenticado
        if (client == null) {

            log.warn("Error:intento de acceso al carrito sin autenticación");

            // Se informa detalle a la vista
            model.addAttribute(
                    "errorMessage",
                    "Debe autenticarse para agregar productos."
            );

            return "redirect:/login?error=login_required";

        }

        log.info("Página::[ShoppingCart] preparada para cliente en sesión.");

        // Obtiene carrito asociado al cliente
        Optional<Cart> cart =
                shoppingCartService.findCartByClientId(
                        client.getId()
                );

        // Si el carrito está vacío o no tiene items.
        if(cart.isEmpty() || cart.get().getItems().isEmpty()) {

            // Se informa detalle a la vista
            model.addAttribute(
                    "errorMessage",
                    "Para accesar el carrito primero deben agregar productos."
            );

            return "pages/home";

        }

        // Carga items del carrito al modelo
        if (cart.isPresent()) {

            model.addAttribute(
                    "cartItems",
                    cart.get().getItems()
            );

        }

        // Carga stock de productos al modelo
        model.addAttribute(
                "productStock",
                inventoryService.getProductStockMap()
        );

        // Carga carrito al modelo
        model.addAttribute(
                "shoppingCart",
                cart.orElse(null)
        );

        // Cantidad de items de pedido
        model.addAttribute(
                "totalItems",
                shoppingCartService.getCartItemsCount(client.getId())
        );

        // Subtotal
        model.addAttribute(
                "subTotal",
                shoppingCartService.calculateCartItemsSubTotal(client.getId())
        );

        // Descuentos
        model.addAttribute(
                "discounts",
                shoppingCartService.calculateCartItemsTotalDiscount(client.getId())
        );

        // Impuestos
        model.addAttribute(
                "taxAmount",
                shoppingCartService.calculateCartItemsTax(client.getId())
        );

        // Costo de envío
        model.addAttribute(
                "shippingCost",
                shoppingCartService.getShippingCost()
        );

        // total
        model.addAttribute(
                "totalAmount",
                shoppingCartService.calculateCartItemsTotal(client.getId())
        );


        return "pages/ShoppingCart";
    }

    /**
     * Procesa la solicitud para agregar un producto
     * al carrito de compras del cliente.
     * @param productId
     * @param session
     * @return Redirección a carrito o Login
     */
    @PostMapping("/cart/addItem")
    public String addProductToCart(
            @RequestParam("product_id") Long productId,
            HttpSession session,Model model) {

        // Obtiene cliente autenticado desde la sesión
        Client client = (Client) session.getAttribute("client");

        // Si no existe cliente autenticado, redirecciona a Login
        if (client == null) {

            log.warn("Intento de agregar producto al carrito sin autenticación");
            // Se informa detalle a la vista
            model.addAttribute(
                    "errorMessage",
                    "Debe autenticarse para agregar productos."
            );

            return "redirect:/login?error=login_required";

        }

        log.info("Agregar::producto:{}",
                productId);

        // Busca producto solicitado
        Optional<Product> product =
                productService.findByProductId(productId);

        // Valida existencia de producto
        if (product.isEmpty()) {

            log.warn("Producto no encontrado: {}",
                    productId
            );

            return "redirect:/home";
        }

        // Consulta stock disponible
        int productStock = inventoryService.getProductStock(productId);
        // Valida stock
        if (productStock <= 0) {

            log.warn("Producto sin stock disponible:{}",
                    productId
            );

            return "redirect:/products/" + productId;
        }

        // Agrega una unidad al carrito
        boolean added =
                shoppingCartService.addItem(
                        client,
                        product.get(),
                        1
                );

        // Valida resultado
        if (!added) {

            log.warn("No fue posible agregar producto al carrito.");
            return "redirect:/products/" + productId;
        }

        log.info("Producto::agregado al carrito correctamente");
        return "redirect:/cart";

    }

    @PostMapping("/cart/removeItem")
    public String removeProductFromCart(
            @RequestParam("product_id") Long productId,
            HttpSession session,Model model) {

        // Obtiene cliente autenticado desde la sesión
        Client client = (Client) session.getAttribute("client");

        // Si no existe cliente autenticado, redirecciona a Login
        if (client == null) {

            log.warn("Intento de agregar producto al carrito sin autenticación");
            // Se informa detalle a la vista
            model.addAttribute(
                    "errorMessage",
                    "Debe autenticarse para agregar productos."
            );

            return "redirect:/login?error=login_required";

        }

        log.info("Agregar::producto:{}",
                productId);

        // Busca producto solicitado
        Optional<Product> product =
                productService.findByProductId(productId);

        // Valida existencia de producto
        if (product.isEmpty()) {

            log.warn("Producto no encontrado: {}",
                    productId
            );

            return "redirect:/home";
        }

        shoppingCartService.removeItem(
                client,
                productId
        );

        return "redirect:/cart";
    }

    @PostMapping("/ShoppingCart/UpdateQuantity")
    public String updateQuantity(
            @RequestParam("product_id") Long productId,
            @RequestParam String operation,
            HttpSession session, Model model) {

        // Obtiene cliente autenticado desde la sesión
        Client client = (Client) session.getAttribute("client");

        // Si no existe cliente autenticado, redirecciona a Login
        if (client == null) {

            log.warn("Intento de agregar producto al carrito sin autenticación");
            // Se informa detalle a la vista
            model.addAttribute(
                    "errorMessage",
                    "Debe autenticarse para agregar productos."
            );

            return "redirect:/login?error=login_required";
        }

        // Busca producto solicitado
        Optional<Product> product =
                productService.findByProductId(productId);

        // Valida existencia de producto
        if (product.isEmpty()) {

            log.warn("Producto no encontrado: {}",
                    productId
            );

            return "redirect:/home";
        }

        if(operation.equals("ADD")) {

            int itemQuantity = shoppingCartService.
                    getProductQuantity(client.getId(),productId);

            int itemTotalStock = inventoryService.
                    getProductStock(productId);

            if(itemQuantity<itemTotalStock) {

                shoppingCartService.increaseQuantity(
                        client,
                        product.get());
            }

        }
        else if(operation.equals("SUBTRACT")) {

            shoppingCartService.decreaseQuantity(
                    client,
                    product.get()
            );

        }

        return "redirect:/cart";
    }

}