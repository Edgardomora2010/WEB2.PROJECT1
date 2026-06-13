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
import java.util.Optional;

/**
 * Controlador encargado de gestionar las solicitudes relacionadas con el
 * inicio de sesión de los clientes dentro de la aplicación.
 */
@Slf4j
@Controller
public class LoginController {

    // OBJETOS/VARIABLES GLOBALES
    private final ClientService clientService;

    /**
     * Constructor con inyección de dependencia:
     * @param clientService
     */
    public LoginController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * @return Plantilla de Login.html
     */
    @GetMapping("/login")
    public String showLoginPage() {
        log.info("Carga de página Login.html");
        return "pages/login";
    }

    /**
     * Procesa la autenticación de clientes en la aplicación.
     * Valida credenciales y registra la sesión del usuario autorizado.
     * @param clientEmail
     * @param clientPassword
     * @param session
     * @param model
     * @return
     */
    @PostMapping("/login")
    // Recibe del Dom, parámetros email y contraseña del cliente
    public String login(
            @RequestParam("client_email") String clientEmail,
            @RequestParam("client_password") String clientPassword,
            HttpSession session,
            Model model) {

        log.info(
                "Intento de Logueo para cliente: {}",
                clientEmail
        );

        // Recibe resultado del proceso de autenticación realizado por
        // la clase de servicio de clientes: clientService.
        ClientService.LoginResult loginResult =
                clientService.authenticate(
                        clientEmail,
                        clientPassword
                );

        switch (loginResult) {

            // Si el cliente (correo) no existe
            case USER_NOT_FOUND:

                log.warn(
                        "Correo no encontrado: {}",
                        clientEmail
                );

                // Se informa detalle a la vista
                model.addAttribute(
                        "errorMessage",
                        "El correo electrónico no se encuentra registrado."
                );

                // No redirecciona ya que no se autenticó usuario
                return "pages/login";

            // Si la contraseña de usuario no existe o es inválida
            case INVALID_PASSWORD:


                log.warn(
                        "Contraseña incorrecta para: {}",
                        clientEmail
                );

                // Se informa detalle a la vista
                model.addAttribute(
                        "errorMessage",
                        "La contraseña es incorrecta."
                );

                // misma página de login
                return "pages/login";

            // Si el usuario y contraseña si existen, y fue posible
            // autenticarlos
            case SUCCESS:

                // Se solicita al servicio, usuario autenticado
                Optional<Client> client =
                        clientService.findByClientEmail(clientEmail);

                log.info(
                        "Cliente autorizado correctamente: {}",
                        clientEmail
                );

                // Se registra en la sesión usuario autenticado
                session.setAttribute(
                        "client",
                        client.get()
                );

                // La aplicación redirecciona al Home.html, pero con usuario
                // ya autenticado
                return "redirect:/";

            default:

                // Cualquier validación o situación que no sea la de éxito,
                // en autenticación, devuelve a plantilla de Login
                return "pages/login";

        }

    }

}