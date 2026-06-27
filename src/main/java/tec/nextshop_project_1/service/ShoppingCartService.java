// PAQUETES
package tec.nextshop_project_1.service;

//IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.config.AuxStoreSettings;
import tec.nextshop_project_1.data.Client;
import tec.nextshop_project_1.data.Cart;
import tec.nextshop_project_1.data.CartItem;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.repository.interfaces.IShoppingCartRepository;
import java.util.ArrayList;
import java.util.List;
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
    AuxStoreSettings auxStoreSettings;

    /**
     * Constructor de clase, inyección de dependencia:
     * @param shoppingRepository
     */
    public ShoppingCartService(
            IShoppingCartRepository shoppingRepository) {
        this.shoppingRepository = shoppingRepository;
        this.auxStoreSettings = new AuxStoreSettings();
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
    public boolean addItem(Client client, Product product, int quantity) {

        log.info("Agrega::producto {} al carrito del cliente {}",
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
            item.setQuantity(item.getQuantity() + quantity);
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

        log.info("Carrito::items{} items",cart.getItems().size());
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

    /**
     * Busca un producto específico dentro del carrito de un cliente.
     * @param clientId identificador del cliente.
     * @param productId identificador del producto.
     * @return Item encontrado o Optional vacío.
     */
    public Optional<CartItem> findCartItem(
            Long clientId,
            Long productId) {

        Optional<Cart> cart =
                findCartByClientId(clientId);

        if(cart.isEmpty()) {
            return Optional.empty();
        }

        return cart.get()
                .getItems()
                .stream()
                .filter(item ->
                        item.getProduct()
                                .getId()
                                .equals(productId))
                .findFirst();

    }

    /**
     * Obtiene la cantidad de un producto dentro del carrito.
     * @param clientId identificador del cliente.
     * @param productId identificador del producto.
     * @return Cantidad encontrada o 0.
     */
    public int getProductQuantity(
            Long clientId,
            Long productId) {

        Optional<CartItem> item =
                findCartItem(
                        clientId,
                        productId
                );

        return item
                .map(CartItem::getQuantity)
                .orElse(0);

    }

    /**
     * Incrementa en una unidad la cantidad de un producto
     * dentro del carrito de compras de un cliente.
     * @param client cliente propietario del carrito.
     * @param product producto a incrementar.
     * @return true si fue posible incrementar la cantidad.
     */
    public boolean increaseQuantity(
            Client client,
            Product product) {

        // Valida parámetros
        if(client == null || product == null) {

            log.warn("Cliente o producto nulo.");
            return false;

        }

        // Reutiliza la lógica existente para agregar una unidad.
        return addItem(
                client,
                product,
                1
        );

    }

    /**
     * Disminuye en una unidad la cantidad de un producto
     * dentro del carrito de compras.
     * Si la cantidad llega a cero, el producto se elimina
     * completamente del carrito.
     * @param client cliente propietario del carrito.
     * @param product producto cuya cantidad se disminuirá.
     * @return true si fue posible realizar la operación.
     */
    public boolean decreaseQuantity(
            Client client,
            Product product) {

        // Valida parámetros
        if(client == null || product == null) {

            log.warn("Cliente o producto nulo.");
            return false;

        }

        // Busca carrito del cliente
        Optional<Cart> cartOpt =
                findCartByClientId(
                        client.getId()
                );

        if(cartOpt.isEmpty()) {

            log.warn("Carrito no encontrado.");
            return false;

        }

        Cart cart =
                cartOpt.get();

        // Busca el producto dentro del carrito
        Optional<CartItem> itemOpt =
                cart.getItems()
                        .stream()
                        .filter(item ->
                                item.getProduct()
                                        .getId()
                                        .equals(product.getId()))
                        .findFirst();

        if(itemOpt.isEmpty()) {

            log.warn("Producto no encontrado en el carrito.");
            return false;

        }

        CartItem item =
                itemOpt.get();

        // Disminuye cantidad
        item.setQuantity(
                item.getQuantity() - 1
        );

        // Si la cantidad llegó a cero,
        // elimina el producto del carrito.
        if(item.getQuantity() <= 0) {

            cart.getItems().remove(item);

        }

        // Guarda carrito actualizado
        shoppingRepository.saveCart(cart);

        return true;

    }

    /**
     * Elimina completamente un producto del carrito
     * de compras de un cliente.
     * @param client cliente propietario del carrito.
     * @param productId identificador del producto.
     * @return true si fue posible eliminar el producto.
     */
    public boolean removeItem(
            Client client,
            Long productId) {

        // Busca carrito del cliente
        Optional<Cart> cartOpt =
                findCartByClientId(
                        client.getId()
                );

        if(cartOpt.isEmpty()) {

            log.warn("Carrito no encontrado.");
            return false;

        }

        Cart cart =
                cartOpt.get();

        // Elimina el producto del carrito
        boolean removed =
                cart.getItems()
                        .removeIf(item ->
                                item.getProduct()
                                        .getId()
                                        .equals(productId));

        if(!removed) {

            log.warn("Producto no encontrado en el carrito.");
            return false;

        }

        // Guarda carrito actualizado
        shoppingRepository.saveCart(cart);

        return true;

    }

    /**
     * Obtiene todos los productos (items) pertenecientes
     * al carrito de compras de un cliente.
     * @param clientId identificador del cliente.
     * @return Lista de productos del carrito.
     */
    public List<CartItem> getCartItems(
            Long clientId) {

        Optional<Cart> cart =
                findCartByClientId(
                        clientId
                );

        if(cart.isEmpty()) {

            log.warn("Carrito no encontrado para el cliente {}",
                    clientId);

            return new ArrayList<>();

        }

        return cart.get().getItems();

    }

    /**
     * Obtiene la cantidad total de productos
     * existentes en el carrito de un cliente.
     *
     * @param clientId identificador del cliente.
     * @return Cantidad total de unidades.
     */
    public int getCartItemsCount(
            Long clientId) {

        Optional<Cart> cart =
                findCartByClientId(
                        clientId
                );

        if(cart.isEmpty()) {

            return 0;

        }

        return cart.get()
                .getItems()
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

    }

    /**
     * @param clientId
     * @return Subtotal de productos del carrito.
     */
    public double calculateCartItemsSubTotal(Long clientId){

        double subtotal = 0;
        double calculatedLine = 0;

        Optional<Cart> cart =
                findCartByClientId(
                        clientId
                );

        if(cart.isEmpty()) {

            return 0;

        }

        for (CartItem cartItem : cart.get().getItems()) {
            calculatedLine = cartItem.getQuantity() *
                    cartItem.getProduct().getPrice();
            subtotal += calculatedLine;
        }

        return subtotal;

    }

    /**
     * Calcula total de impuesto de venta, de
     * todos los items en el carrito de compra.
     * @param clientId
     * @return Total de impuesto IVA (13%)
     */
    public double calculateCartItemsTax(Long clientId) {
        double taxAmount = calculateCartItemsSubTotal(clientId) * auxStoreSettings.getTax();
        return taxAmount;
    }

    /**
     *
     * @param clientId
     * @return
     */
    public double calculateCartItemsTotalDiscount(Long clientId){

        double totalDiscounts= 0;
        double calculatedLine = 0;

        Optional<Cart> cart =
                findCartByClientId(
                        clientId
                );

        if(cart.isEmpty()) {

            return 0;

        }

        for (CartItem cartItem : cart.get().getItems()) {

            if (cartItem.getProduct().getDiscountPercentage() > 0)
            {
                calculatedLine = cartItem.getQuantity() * cartItem.getProduct().getPrice() *
                        (cartItem.getProduct().getDiscountPercentage()/100);
                totalDiscounts += calculatedLine;

            }

        }

        return totalDiscounts;

    }

    /**
     * Devuelve costo de envío general
     * @return
     */
    public double getShippingCost(){
        return auxStoreSettings.getShippingCost();
    }

    public double calculateCartItemsTotal(Long clientId) {

        double tax = 0;
        double total= 0;
        double subtotal = 0;
        double discount = 0;
        double lineTotal = 0;

        Optional<Cart> cart =
                findCartByClientId(
                        clientId
                );

        if(cart.isEmpty()) {

            return 0;

        }

        for (CartItem cartItem : cart.get().getItems()) {

            Product item = cartItem.getProduct();
            // subtotal
            subtotal = item.getPrice() * cartItem.getQuantity();
            // descuento
            discount = subtotal * (item.getDiscountPercentage()/100);
            // impuesto
            tax = (subtotal - discount) * auxStoreSettings.getTax();
            // cáculo línea
            lineTotal = subtotal - discount + tax;
            // Acumula total por línea
            total += lineTotal;
        }

        return (total + auxStoreSettings.getShippingCost());

        }

}