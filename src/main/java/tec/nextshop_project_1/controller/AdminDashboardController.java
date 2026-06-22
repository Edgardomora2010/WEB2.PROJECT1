// PAQUETES
package tec.nextshop_project_1.controller;

// IMPORTACIÓN DE LIBRERÍAS
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import tec.nextshop_project_1.data.Client;

/**
 * Controlador principal encargado de gestionar la carga, visualización
 * y comportamiento de la página AdminDashboard, para la gestión
 * administrativa y de mantenimientos de la aplicación
 * (registro de categorías, productos, inventario, etc).
 */
@Slf4j
@Controller
public class AdminDashboardController {

    /**
     * @return Plantilla AdminDashboard.html
     */
    @GetMapping("/AdminDashboard")
    public String showAdminDashboardPage(HttpSession session) {

        Client client =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if (client == null) {
            return "redirect:/login";
        }

        // Usuario autenticado pero no administrador
        if (client.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        log.info("Carga de página principal para operaciones administrativas de la aplicación.");

        return "managment/AdminDashboard";
    }

}
