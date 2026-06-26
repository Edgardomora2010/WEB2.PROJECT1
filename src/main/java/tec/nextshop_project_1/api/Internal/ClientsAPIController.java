// PAQUETES
package tec.nextshop_project_1.api.Internal;

// IMPORTACIÓN DE LIBRERÍAS
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.service.ClientService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/internal/clients")
public class ClientsAPIController {

    // OBJETOS Y VARIABLES GLOBALES
    private final ClientService clientService;

    /**
     * Constructor de clase con inserción de dependencia.
     * @param clientService
     */
    public ClientsAPIController(ClientService clientService) {
        this.clientService = clientService;
    }

    /**
     * Obtiene un usuario mediante su identificador.
     * @param clientId identificador del usuario.
     * @param session sesión actual.
     * @return Información del usuario.
     * Si no hay una sesión autenticada, devuelve respuesta de no autorización
     */
    @GetMapping("/{clientId}")
    public ResponseEntity<Client> getClientById(
            @PathVariable Long clientId,
            HttpSession session) {

        if(session.getAttribute("client") == null) {

            return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED
            ).build();

        }

        Optional<Client> client =
                clientService.findById(
                        clientId
                );

        return client
                .map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.notFound().build());

    }

    /**
     * Actualiza información de cliente mediante la API REST interna.
     * @param clientId identificador del usuario.
     * @param client información actualizada.
     * @param session sesión actual.
     * @return Un HTTP 200 si la actualización fue exitosa, 401 si no existe
     * una sesión autenticada, 404 si el usuario no existe.
     */
    @PutMapping("/{clientId}")
    public ResponseEntity<Void> updateClient(
            @PathVariable Long clientId,
            @RequestBody Client client,
            HttpSession session) {

        if(session.getAttribute("client") == null) {

            return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED
            ).build();

        }

        boolean updated =
                clientService.updateClient(
                        clientId,
                        client.getPassword(),
                        client.getPhoneNumber(),
                        client.getAddress(),
                        client.isActive()
                );

        if(!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();

    }

    /**
     * Cambia el estado (activo/inactivo) de un usuario mediante
     * la API REST interna.
     * @param clientId identificador del usuario.
     * @param session sesión actual.
     * @return Código HTTP 200 si fue posible realizar la operación, 401 si no existe
     * una sesión autenticada, 404 si el usuario no existe.
     */
    @PatchMapping("/{clientId}/status")
    public ResponseEntity<Void> switchClientStatus(
            @PathVariable Long clientId,
            HttpSession session) {

        if(session.getAttribute("client") == null) {

            return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED
            ).build();

        }

        boolean updated = clientService.switchClientStatus(clientId);

        if(!updated) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();

    }

    /**
     * Registra un nuevo usuario mediante la API REST interna.
     * @param client información del usuario.
     * @param session sesión actual.
     * @return Código HTTP 201 si fue posible registrar el usuario, 401 si no existe
     * una sesión autenticada, 409 si el correo electrónico ya existe.
     */
    @PostMapping
    public ResponseEntity<Void> createClient(
            @RequestBody Client client,
            HttpSession session) {

        if(session.getAttribute("client") == null) {

            return ResponseEntity.status(
                    HttpStatus.UNAUTHORIZED
            ).build();

        }

        boolean created = clientService.addClient(
                        client,
                        client.getRole()
                );

        if(!created) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
