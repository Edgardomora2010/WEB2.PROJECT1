// PAQUETES
package tec.nextshop_project_1.repository.in_memory;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.stereotype.Repository;
import tec.nextshop_project_1.data.Cart;
import tec.nextshop_project_1.data.CartItem;
import tec.nextshop_project_1.repository.interfaces.IShoppingCartRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Repositorio simulado para la gestión de carritos de compra.
 * Mantiene la información en memoria para uso de la aplicación
 * hasta que, en una segunda fase, se implemente persistencia
 * mediante base de datos.
 */
@Repository
public class ShoppingCartRepository implements IShoppingCartRepository {

    // OBJETOS Y VARIABLES GLOBALES

    /**
     * Estructura de almacenamiento en memoria.
     * Relaciona un cliente con su carrito de compras.
     */
    private final Map<Long, Cart> carts;

    /**
     * Constructor de clase.
     */
    public ShoppingCartRepository() {
        this.carts = new HashMap<>();
    }

    @Override
    public void addItem(CartItem cartItem) {

    }

    /**
     * Obtiene el carrito asociado a un cliente.
     * @param clientId Identificador de cliente
     * @return Carrito encontrado (si existe)
     */
    @Override
    public Optional<Cart> findCartByClientId(Long clientId) {

        return Optional.ofNullable(
                carts.get(clientId)
        );

    }

    /**
     * Guarda o actualiza un carrito en memoria.
     * @param cart Carrito a almacenar
     */
    @Override
    public void saveCart(Cart cart) {

        carts.put(
                cart.getClient().getId(),
                cart
        );

    }

    /**
     * Elimina el carrito asociado a un cliente.
     * @param clientId Identificador de cliente
     */
    @Override
    public void deleteCart(Long clientId) {

        carts.remove(clientId);

    }

}
