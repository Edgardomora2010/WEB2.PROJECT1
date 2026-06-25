// PAQUETES
package tec.nextshop_project_1.controller;

// IMPORTACIÓN DE LIBRERÍAS
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import tec.nextshop_project_1.service.ClientService;
import tec.nextshop_project_1.data.Client;

/**
 * Controlador encargado de gestionar las solicitudes relacionadas con el
 * manejo de la cuenta de usuario de la aplicación.
 */
@Slf4j
@Controller
public class MyAccountController {

    private final ClientService clientService;

    /**
     * Constructor con inyección de dependencia:
     * @param clientService
     */
    public MyAccountController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * @return Plantilla: MyAccount.html
     */
    @GetMapping("/MyAccount")
    public String showAccountPage(
            HttpSession session,
            Model model) {

        Client client =
                (Client) session.getAttribute("client");

        if(client == null) {
            log.warn("Intento::acceso a cuenta sin autenticación."
            );

            return "redirect:/login?error=login_required";
        }

        /*
        if (client.getRole().name()!="CUSTOMER"){
            log.warn("Error:el usuario no está autorizado para accesar esta página.");

            model.addAttribute(
                    "errorMessage",
                    "Usuario no autorizado para accesar página"
            );

            return "pages/home";
        }*/

        log.info("Carga::página:[MyAccount.html]");
        return "pages/MyAccount";
    }

    /**
     * @return: Plantilla Home.html
     */
    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        Client client =
                (Client) session.getAttribute("client");

        if(client == null) {
            return "redirect:/login";
        }

        log.info("Cierre de sesión");
        session.invalidate();
        return "redirect:/";

    }

    /**
     * Carga la página para que el cliente edite su perfil.
     * @param session sesión actual.
     * @param model modelo de datos.
     * @return Plantilla EditClient.html
     */
    @GetMapping("/MyAccount/Edit")
    public String showEditMyAccountPage(
            HttpSession session,
            Model model) {

        Client currentClient =
                (Client) session.getAttribute("client");

        // Usuario no autenticado
        if(currentClient == null) {
            return "redirect:/login";
        }

        // Solo clientes pueden editar su perfil.
        if(currentClient.getRole() != Client.Profile.CUSTOMER) {
            return "redirect:/?error=customer_only";
        }

        model.addAttribute(
                "client",
                currentClient
        );

        model.addAttribute(
                "isNew",
                false
        );

        log.info(
                "Carga::edición de perfil de cliente."
        );

        return "managment/EditClient";

    }

}
