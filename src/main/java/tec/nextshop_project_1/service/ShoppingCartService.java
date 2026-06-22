// PAQUETES
package tec.nextshop_project_1.service;

//IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.data.Cart;
import tec.nextshop_project_1.data.CartItem;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.repository.interfaces.IShoppingCartRepository;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión del carrito de compras.
 */
@Slf4j
@Service
public class ShoppingCartService {

    // OBJETOS/VARIABLES GLOBALES
    private final IShoppingCartRepository shoppingRepository;

    /**
     * Constructor de clase, inyección de dependencia:
     * @param shoppingRepository
     */
    public ShoppingCartService(
            IShoppingCartRepository shoppingRepository) {

        this.shoppingRepository = shoppingRepository;

    }

    /**
     * Agrega un producto al carrito de un cliente.
     * Si el producto ya existe en el carrito,
     * incrementa su cantidad.
     * @param client
     * @param product
     * @param quantity
     * @return true si la operación fue exitosa
     */
    public boolean addItem(
            Client client,
            Product product,
            int quantity
    ) {

        log.info("Agregar producto {} al carrito del cliente {}",
                product.getName(),
                client.getId()
        );

        // Valida que el producto no sea nulo y la cantidad superior a 0
        if(product == null || quantity <= 0) {

            log.warn("Producto o cantidad inválida");
            return false;

        }

        // Buscar carrito existente
        Optional<Cart> cartOpt =
                shoppingRepository.findCartByClientId(
                        client.getId()
                );

        Cart cart;

        // Si no existe carrito, crearlo
        if(cartOpt.isEmpty()) {

            cart = new Cart();
            cart.setId(System.currentTimeMillis());
            cart.setClient(client);
            cart.setItems(new ArrayList<>());

        }

        else {

            cart = cartOpt.get();

        }

        // Buscar producto existente en carrito
        Optional<CartItem> existingItem =
                cart.getItems()
                        .stream()
                        .filter(item ->
                                item.getProduct()
                                        .getId()
                                        .equals(product.getId()))
                        .findFirst();

        // Si el producto ya existe, aumenta cantidad
        if(existingItem.isPresent()) {

            CartItem item = existingItem.get();

            item.setQuantity(
                    item.getQuantity() + quantity
            );

            log.info("Cantidad actualizada para producto {}", product.getName());

        }

        // Si no existe, crea nuevo item
        else {

            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            cart.getItems().add(item);
            log.info("Nuevo item agregado al carrito");

        }

        log.info(
                "CARRITO TIENE {} ITEMS",
                cart.getItems().size()
        );

        // Guarda carrito actualizado
        shoppingRepository.saveCart(cart);

        return true;

    }

    /**
     * Busca el carrito asociado a un cliente.
     * @param clientId
     * @return Carrito encontrado o Optional vacío
     */
    public Optional<Cart> findCartByClientId(
            Long clientId) {

        if(clientId == null) {

            log.warn("Identificador de cliente nulo");
            return Optional.empty();

        }

        return shoppingRepository.findCartByClientId(
                clientId
        );

    }

}