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
import java.time.LocalDate;

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
        log.info("Carga::página de registro de clientes:[Register.html]");
        return "pages/register";
    }

    /**
     * Constructor con inyección de dependencia:
     * @param clientService
     */
    public RegisterController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Orden del constructor en data:Client
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
                                      LocalDate.now(),
                                true);

        log.info("Intento::registro de usuario:");
        boolean wasClientAdded = clientService.addClient(newClient, Client.Profile.CUSTOMER);

        if (!wasClientAdded) {

            // Si ya existe usuario en sistema
            log.warn("Error: el usuario ya existe.");

            model.addAttribute(
                    "errorMessage",
                    "Ya existe un usuario con el correo: " + clientEmail);
            return "pages/register";

        }

        // Si no existe usuario en sistema
        if (wasClientAdded) {
            log.info("Se agregó exitosamente el cliente: {}", clientEmail);
        }

        return "redirect:/";

    }

}
