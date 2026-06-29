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
import tec.nextshop_project_1.data.Inventory;
import tec.nextshop_project_1.data.Order;
import tec.nextshop_project_1.service.OrderService;
import tec.nextshop_project_1.service.InventoryService;
import tec.nextshop_project_1.service.ShoppingCartService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Controlador encargado de gestionar el proceso de pago
 * y confirmación de pedidos.
 */
@Slf4j
@Controller
public class OrdersController {

    // OBJETOS/VARIABLES GLOBALES
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final InventoryService inventoryService;

    /**
     * Constructor de clase.
     */
    public OrdersController(
            ShoppingCartService shoppingCartService,
            OrderService orderService,InventoryService inventoryService) {

        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
    }

    /**
     * Carga la página de confirmación de pedido para previsualización.
     * @param session sesión del cliente.
     * @param model modelo para la vista.
     * @return página PayOrder.
     */
    @GetMapping("/PayOrder")
    public String createClientPreOrder(
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Cliente no autenticado.
        if(client == null) {

            return "redirect:/login";

        }

        if(client.getRole().name().equals("ADMIN")){
            return "redirect:/cart?alert=admin_user_not_allowed";
        }

        Optional<Cart> cart =
                shoppingCartService.findCartByClientId(
                        client.getId()
                );

        // Carrito inexistente o vacío.
        if(cart.isEmpty()
                || cart.get().getItems().isEmpty()) {

            return "redirect:/?alert=product_in_cart_required";

        }

        // se valida que la cantidad a salir del carrito para cada
        // producto sea menor que la cantidad del ese producto en
        // inventario (stock).

        // Esto podría suceder en un entorno real si se deja mucho
        // tiempo productos en el carrito, y no se realiza la compra
        // rápido, tal que otros usuario de la aplicación puede realizar
        // compras de los mismos productos, antes que la del usuario
        // con productos en carrito.
        List<Inventory> rejectedProducts =
                inventoryService.validateInventoryDecrease(
                        cart.get()
                );


        // si se retornó una lista de productos de inventario no vacía
        // significa que hay productos en carrito cuyas cantidad supera
        // la que existen en existencias, por lo tanto se debe notificar
        // a la página que existen productos en el carrito, que no pueden
        // venderse porque no hay existencias.
        if (!rejectedProducts.isEmpty()) {

            // Por tiempo no se implementa una página que devuelva los
            // productos sin stock, sin embargo se deja la prevista
            /*
            model.addAttribute(
                    "rejectedProducts",
                    rejectedProducts
            );*/

            // De momento solo se implemente mensaje en página del carrito
            // indicándose a cliente la falta de stock de productos que imposibilita
            // la compra.
            return "redirect:/cart?alert=inventory_not_available";

        }

        // Si la cantidad de productos en inventario, cubre lo demandado por la pre
        // orden o (productos en carrito), se arma construye la preorden.
        Order preOrder = orderService.createPreOrder(
                        client,
                        cart.get()
                );

        // Envía preorden a la vista.
        model.addAttribute(
                "order",
                preOrder
        );

        return "pages/PayOrder";

    }

    @PostMapping("/checkout/confirm")
    public String createClientOrder(
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("cardType") String cardType,
            @RequestParam("cardNumber") String cardNumber,
            HttpSession session,
            Model model){


        Client client =
                (Client) session.getAttribute("client");

        // Cliente no autenticado.
        if(client == null) {
            return "redirect:/login";
        }

        // Solo clientes pueden comprar.
        if(client.getRole().name().equals("ADMIN")){
            return "redirect:/cart?alert=admin_user_not_allowed";
        }

        Optional<Cart> cart =
                shoppingCartService.findCartByClientId(
                        client.getId()
                );

        // Carrito inexistente o vacío.
        if(cart.isEmpty()
                || cart.get().getItems().isEmpty()) {

            return "redirect:/?alert=product_in_cart_required";
        }

        // Primera validación de inventario.
        List<Inventory> rejectedProducts =
                inventoryService.validateInventoryDecrease(
                        cart.get()
                );

        if (!rejectedProducts.isEmpty()) {

            return "redirect:/cart?alert=inventory_not_available";

        }

        // Reconstruye la preorden.
        Order preOrder =
                orderService.createPreOrder(
                        client,
                        cart.get()
                );

        // Segunda validación utilizando la preorden.
        rejectedProducts =
                inventoryService.validateInventoryDecrease(
                        preOrder
                );

        if (!rejectedProducts.isEmpty()) {
            return "redirect:/cart?alert=inventory_not_available";
        }

        // Se setea últimos datos de confirmación de compra a la orden que
        // se obtienen del DOM
        preOrder.setPaymentMethod(Order.PaymentMethod.valueOf(paymentMethod));
        preOrder.setCardType(cardType);
        preOrder.setCardNumber(cardNumber);
        preOrder.setPaymentDate(LocalDateTime.now());
        preOrder.setDeliveredDate(LocalDateTime.now().plusDays(15)

        );

        orderService.createClientOrder(preOrder);

        Optional<Order> wasOrderAdded =
                orderService.findByOrderId(preOrder.getId());

        if (wasOrderAdded.isEmpty()){
            return "redirect:/?error=order_fail_to_be_created";
        }

        return "redirect:/?success=order_successfully_created";

    }

}