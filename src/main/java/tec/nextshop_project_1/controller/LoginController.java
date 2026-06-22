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
    public String showLoginPage(
            @RequestParam(required = false) String error,
            Model model) {

        log.info("Carga::página:[Login.html]");

        // Si el parámetro recibido de error, es por logueo requerido (que puede provenir)
        // de algún intento de cargar productos al carrito sin sesión de usuario
        if ("login_required".equals(error)) {

            // Devuelve mensaje en vista (fragment:errorSite), indicándose necesidad de logueo para
            // agregar productos al carrito
            model.addAttribute(
                    "errorMessage",
                    "Debe autenticarse para utilizar el carrito."
            );

        }

        // Retorna página de logueo.
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

        log.info("Intento::autenticación de cliente.");

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

                log.warn("Error::en usuario o contraseña.");

                // Se informa detalle a la vista
                model.addAttribute(
                        "errorMessage",
                        "El correo electrónico no se encuentra registrado."
                );

                // No redirecciona ya que no se autenticó usuario
                return "pages/login";

            // Si la contraseña de usuario no existe o es inválida
            case INVALID_PASSWORD:

                log.warn("Error::en usuario o contraseña.");

                // Se informa detalle a la vista
                model.addAttribute(
                        "errorMessage",
                        "La contraseña es incorrecta."
                );

                model.addAttribute(
                        "clientEmail",
                        clientEmail
                );

                // misma página de login
                return "pages/login";

            // Si usuario está desactivado
            case INACTIVE_ACCOUNT:
                log.warn("Error::cuenta inactiva.");

                // Se informa detalle a la vista
                model.addAttribute(
                        "errorMessage",
                        "La cuenta actual se encuentra desactivada."
                );

                model.addAttribute(
                        "clientEmail",
                        clientEmail
                );

                // misma página de login
                return "pages/login";


            // Si el usuario y contraseña si existen, y fue posible
            // autenticarlos
            case SUCCESS:

                // Se solicita al servicio, usuario autenticado
                Optional<Client> client =
                        clientService.findByClientEmail(clientEmail);

                if(client.isEmpty()) {
                    log.info("Error en la autenticación.");
                    return "pages/login";
                }

                log.info("Autenticación completada correctamente.");

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