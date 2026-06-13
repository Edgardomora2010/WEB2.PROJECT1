// PAQUETES
package tec.nextshop_project_1.controller;

// IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.service.ClientService;

/**
 * Controlador encargado de gestionar las solicitudes relacionadas con el
 * registro de clientes dentro de la aplicación.
 */
@Slf4j
@Controller
public class RegisterController {

    // OBJETOS/VARIABLES GLOBALES
    private final ClientService clientService;

    /**
     * @return Plantilla Register.html
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        log.info("Carga de página de registro de cliente");
        return "pages/register";
    }

    /**
     * Constructor con inyección de dependencia:
     * @param clientService
     */
    public RegisterController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Orden del constructor en Client
        /*
           Profile,
           ID,
           Nombre,
           Apellido
           correo electrónico
           contraseña
           Teléfono
           Dirección
           estado
         */

    /**
     * Procesa el registro de nuevos clientes en la aplicación.
     * Valida información y registra el cliente si el correo no existe.
     * @param clientName
     * @param clientLastName
     * @param clientEmail
     * @param clientPassword
     * @param clientPhoneNumber
     * @param clientAdress
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestParam("name") String clientName,
                           @RequestParam("last_name") String clientLastName,
                           @RequestParam("email") String clientEmail,
                           @RequestParam("password") String clientPassword,
                           @RequestParam("phone_number") String clientPhoneNumber,
                           @RequestParam("address") String clientAdress,
                           HttpSession session,
                           Model model) {

        Client newClient = new Client(Client.Profile.CUSTOMER,
                                      null,
                                      clientName,
                                      clientLastName,
                                      clientEmail,
                                      clientPassword,
                                      clientPhoneNumber,
                                      clientAdress,
                                true);

        log.info("Intento de registro para el usuario: {}", clientEmail);

        boolean wasClientAdded = clientService.addClient(newClient);

        if (!wasClientAdded) {

            // Si ya existe usuario en sistema
            log.warn("Ya existe un usuario con el correo: {}", clientEmail);

            model.addAttribute(
                    "errorMessage",
                    "Ya existe un usuario con el correo: " + clientEmail);
            return "pages/login";

        }

        // Si no existe usuario en sistema
        if (wasClientAdded) {
            log.info("Se agregó exitosamente el cliente: {}", clientEmail);
        }

        return "redirect:/";

    }

}
