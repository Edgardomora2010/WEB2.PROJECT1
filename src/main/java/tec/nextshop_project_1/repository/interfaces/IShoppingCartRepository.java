// PAQUETES
package tec.nextshop_project_1.repository.interfaces;

// IMPORTACIÓN DE LIBRERÍAS
import tec.nextshop_project_1.data.Cart;
import tec.nextshop_project_1.data.CartItem;
import java.util.Optional;

/**
 * Interfaz de repositorio para shopping cart de la aplicación
 * Implementaciones (in-memory, JPA, etc.).
 */
public interface IShoppingCartRepository {

    /**
     * Agrega un item de producto al carrito de compra
     * @param cartItem
     */
    void addItem(CartItem cartItem);

    Optional<Cart> findCartByClientId(Long clientId);

    /**
     * Guarda o actualiza un carrito en memoria.
     * @param cart Carrito a almacenar
     */
    void saveCart(Cart cart);

    /**
     * Elimina el carrito asociado a un cliente.
     * @param clientId Identificador de cliente
     */
    void deleteCart(Long clientId);

}
