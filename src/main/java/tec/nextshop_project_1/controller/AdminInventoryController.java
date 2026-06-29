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
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.data.Inventory;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.service.InventoryService;
import tec.nextshop_project_1.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Controlador encargado del mantenimiento administrativo
 * del inventario de productos.
 */
@Slf4j
@Controller
public class AdminInventoryController {

    // OBJETOS / VARIABLES GLOBALES
    private final InventoryService inventoryService;
    private final ProductService productService;

    /**
     * Constructor.
     * @param inventoryService
     * @param productService
     */
    public AdminInventoryController(
            InventoryService inventoryService,
            ProductService productService) {

        this.inventoryService = inventoryService;
        this.productService = productService;

    }

    /**
     * Carga la página principal del mantenimiento de inventario.
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/AdminInventory")
    public String showInventoryPage(
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado.
        if(client == null){
            return "redirect:/login";
        }

        // Solo administradores.
        if(client.getRole() != Client.Profile.ADMIN){
            return "redirect:/?error=admin_only";
        }

        model.addAttribute(
                "inventoryItems",
                inventoryService.getAllInventoryItems()
        );

        return "managment/AdminInventory";

    }

    /**
     * Realiza búsquedas sobre el inventario utilizando
     * los criterios de búsqueda de productos.
     * @param searchType
     * @param searchValue
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/AdminInventory/Search")
    public String searchInventory(
            @RequestParam("searchType") String searchType,
            @RequestParam("searchValue") String searchValue,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        if(client == null){
            return "redirect:/login";
        }

        if(client.getRole() != Client.Profile.ADMIN){
            return "redirect:/?error=admin_only";
        }

        List<Product> products =
                productService.searchProducts(
                        searchType,
                        searchValue
                );

        List<Inventory> inventoryItems =
                new ArrayList<>();

        for(Product product : products){

            inventoryService.findByProductId(
                    product.getId()
            ).ifPresent(inventoryItems::add);

        }

        model.addAttribute(
                "inventoryItems",
                inventoryItems
        );

        return "managment/AdminInventory";

    }

    /**
     * Carga un registro de inventario para su edición.
     * @param productId Identificador del producto.
     * @param session sesión actual.
     * @param model modelo para la vista.
     * @return Plantilla AdminInventory.html
     */
    @PostMapping("/AdminInventory/Edit")
    public String editInventory(
            @RequestParam("productId") Long productId,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado.
        if (client == null) {
            return "redirect:/login";
        }

        // Solo administradores.
        if (client.getRole() != Client.Profile.ADMIN) {
            return "redirect:/?error=admin_only";
        }

        // Recarga la tabla
        model.addAttribute(
                "inventoryItems",
                inventoryService.getAllInventoryItems()
        );

        Optional<Inventory> inventory =
                inventoryService.findByProductId(productId);

        if (inventory.isEmpty()) {

            model.addAttribute(
                    "errorMessage",
                    "No se encontró el registro de inventario."
            );

            return "managment/AdminInventory";

        }

        model.addAttribute(
                "inventory",
                inventory.get()
        );

        return "managment/AdminInventory";

    }

    @PostMapping("/AdminInventory/Update")
    public String updateInventory(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") int quantity,
            @RequestParam("minimumStock") int minimumStock,
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado.
        if (client == null) {
            return "redirect:/login";
        }

        // Solo administradores.
        if (client.getRole() != Client.Profile.ADMIN) {
            return "redirect:/?error=admin_only";
        }

        boolean updated =
                inventoryService.updateInventory(
                        productId,
                        quantity,
                        minimumStock
                );

        if (!updated) {

            model.addAttribute(
                    "errorMessage",
                    "No fue posible actualizar el inventario."
            );

        } else {

            model.addAttribute(
                    "successMessage",
                    "Inventario actualizado correctamente."
            );

        }

        model.addAttribute(
                "inventoryItems",
                inventoryService.getAllInventoryItems()
        );

        return "managment/AdminInventory";

    }

}