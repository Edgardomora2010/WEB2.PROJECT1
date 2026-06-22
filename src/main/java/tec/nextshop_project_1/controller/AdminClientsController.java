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
import tec.nextshop_project_1.service.ClientService;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones de mantenimiento
 * de usuarios de la aplicación.
 */
@Slf4j
@Controller
public class AdminClientsController {

    // OBJETOS/VARIABLES GLOBALES
    private final ClientService clientService;

    /**
     * Constructor con inyección de dependencia.
     * @param clientService
     */
    public AdminClientsController(
            ClientService clientService) {
        this.clientService = clientService;

    }

    /**
     * Carga la página principal de mantenimiento de usuarios.
     * @param session
     * @param model
     * @return Plantilla AdminClients.html
     */
    @GetMapping("/AdminClients")
    public String showAdminClientsPage(
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

        List<Client> clients =
                clientService.getAllClients();

        model.addAttribute(
                "clients",
                clients
        );

        log.info(
                "Carga::mantenimiento de usuarios."
        );

        return "managment/AdminClients";

    }

    /**
     * Realiza la búsqueda de usuarios según el criterio seleccionado
     * por el administrador.
     *
     * @param searchType tipo de búsqueda.
     * @param searchValue valor a buscar.
     * @param session sesión actual.
     * @param model modelo de datos para la vista.
     * @return Plantilla AdminClients.html
     */
    @PostMapping("/AdminClients/Search")
    public String searchClients(
            @RequestParam("searchType") String searchType,
            @RequestParam("searchValue") String searchValue,
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

        List<Client> clients =
                clientService.searchClients(
                        searchType,
                        searchValue
                );

        model.addAttribute(
                "clients",
                clients
        );

        log.info(
                "Búsqueda de usuarios realizada. Tipo: {} Valor: {}",
                searchType,
                searchValue
        );

        return "managment/AdminClients";

    }

    /**
     * Desactiva un usuario del sistema.
     *
     * La eliminación física de usuarios no se realiza.
     * El registro permanece almacenado pero marcado como inactivo.
     *
     * @param clientId identificador del usuario.
     * @param session sesión actual.
     * @return Página de mantenimiento de usuarios.
     */
    @PostMapping("/AdminClients/switchStatus")
    public String switchClientStatus(
            @RequestParam("clientId") Long clientId,
            HttpSession session) {

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

        // No permitir que un administrador
        // modifique su propio estado.

        if(client.getId().equals(clientId)) {

            log.warn(
                    "El usuario actual, no puede desactivarse así mismo."
            );

            return "redirect:/AdminClients";

        }

        boolean wasDeactivated =
                clientService.switchClientStatus(clientId);

        if(wasDeactivated) {

            log.info(
                    "Usuario desactivado correctamente. Id:{}",
                    clientId
            );

        } else {

            log.warn(
                    "No fue posible desactivar el usuario. Id:{}",
                    clientId
            );

        }

        return "redirect:/AdminClients";

    }

    /**
     * Carga la página para registrar un nuevo administrador.
     * @param session sesión actual.
     * @param model modelo de datos.
     * @return Plantilla EditClient.html
     */
    @GetMapping("/AdminClients/Create")
    public String showCreateClientPage(
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

        model.addAttribute(
                "isNew",
                true
        );

        log.info(
                "Carga::formulario para creación de administrador."
        );

        return "managment/EditClient";

    }

    /**
     * Carga la información de un usuario para edición.
     * @param clientId identificador del usuario.
     * @param session sesión actual.
     * @param model modelo de datos.
     * @return Plantilla EditClient.html
     */
    @PostMapping("/AdminClients/Edit")
    public String showEditClientPage(
            @RequestParam("clientId") Long clientId,
            HttpSession session,
            Model model) {

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

        var client =
                clientService.findById(clientId);

        if(client.isEmpty()) {

            log.warn(
                    "No fue posible localizar usuario. Id:{}",
                    clientId
            );

            return "redirect:/AdminClients";
        }

        model.addAttribute(
                "client",
                client.get()
        );

        model.addAttribute(
                "isNew",
                false
        );

        log.info(
                "Carga::edición de usuario. Id:{}",
                clientId
        );

        return "managment/EditClient";

    }

    /**
     * Registra un nuevo administrador.
     *
     * @param name nombre.
     * @param lastName apellidos.
     * @param email correo.
     * @param password contraseña.
     * @param phoneNumber teléfono.
     * @param address dirección.
     * @param session sesión actual.
     * @param model modelo de datos.
     * @return Página correspondiente.
     */
    @PostMapping("/AdminClients/Save")
    public String saveClient(
            @RequestParam("name") String name,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
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

        boolean wasCreated =
                clientService.createAdmin(
                        name,
                        lastName,
                        email,
                        password,
                        phoneNumber,
                        address
                );

        if(!wasCreated) {

            model.addAttribute(
                    "errorMessage",
                    "Ya existe un usuario con ese correo electrónico."
            );

            model.addAttribute(
                    "isNew",
                    true
            );

            return "managment/EditClient";
        }

        log.info(
                "Administrador agregado correctamente. Correo:{}",
                email
        );

        return "redirect:/AdminClients";

    }

    /**
     * Actualiza la información editable de un usuario.
     * @param clientId identificador.
     * @param password contraseña.
     * @param phoneNumber teléfono.
     * @param address dirección.
     * @param active estado.
     * @param session sesión actual.
     * @return Página correspondiente.
     */
    @PostMapping("/AdminClients/Update")
    public String updateClient(
            @RequestParam("clientId") Long clientId,
            @RequestParam("password") String password,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            @RequestParam("active") boolean active,
            HttpSession session) {

        Client currentClient =
                (Client) session.getAttribute("client");

        if(currentClient == null) {
            return "redirect:/login";
        }

        if(currentClient.getRole() != Client.Profile.ADMIN) {
            return "redirect:/";
        }

        boolean wasUpdated =
                clientService.updateClient(
                        clientId,
                        password,
                        phoneNumber,
                        address,
                        active
                );

        if(!wasUpdated) {

            log.warn(
                    "No fue posible actualizar usuario. Id:{}",
                    clientId
            );

        }

        log.info(
                "Usuario actualizado correctamente. Id:{}",
                clientId
        );

        return "redirect:/AdminClients";

    }

}