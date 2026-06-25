// PAQUETES
package tec.nextshop_project_1.service;

// IMPORTACIÓN DE LIBRERÍAS
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tec.nextshop_project_1.data.Category;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.data.Property;
import tec.nextshop_project_1.repository.interfaces.IProductRepository;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Clase perteneciente a la capa de servicio, encargada de la lógica de negocio
 * para la gestión de productos.
 */
@Slf4j
@Service
public class ProductService {

    // OBJETOS/VARIABLES GLOBALES
    private final IProductRepository productRepository;

    /**
     * Constructor de clase, inyección de dependencia: ProductRepository
     * @param productRepository
     */
    public ProductService(IProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    /**
     * @param productName
     * @return Un producto buscado mediante el nombre de producto
     */
    public Optional<Product> findByProductName(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            return Optional.empty();
        }
        return productRepository.findByProductName(productName.trim());
    }

    /**
     * @param productId
     * @return Un producto mediante el id del producto
     */
    public Optional<Product> findByProductId(long productId) {
        if (productId <= 0) {
            return Optional.empty();
        }
        return productRepository.findByProductId(productId);
    }

    /**
     * @return Una lista de todos los productos de la aplicación
     */
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    /**
     * @return Una lista de productos destacados de la aplicación
     */
    public List<Product> getFeaturedProducts() {

        return productRepository.getAllProducts()
                .stream()
                .filter(Product::isFeatured)
                .toList();
    }

    /**
     * @return Una lista de productos en oferta de la aplicación
     */
    public List<Product> getProductsOnSale() {

        return productRepository.getAllProducts()
                .stream()
                .filter(product ->
                        product.getDiscountPercentage() > 0)
                .toList();
    }

    /**
     * @return Una lista de productos más recientes
     */
    public List<Product> getNewestProducts() {

        return productRepository.getAllProducts()
                .stream()
                .sorted(
                        Comparator.comparing(Product::getCreatedAt)
                                .reversed()
                )
                .limit(10)
                .toList();
    }

    /**
     *
     * @param categoryName
     * @param productName
     * @return
     */
    public List<Product> getProductsBySearchSelection(
            String categoryName,
            String productName) {

        log.info(
                "Busqueda -> Categoria [{}] Nombre [{}]",
                categoryName,
                productName
        );

        // Normaliza el texto de búsqueda para evitar errores por
        // espacios en blanco o diferencias entre mayúsculas y minúsculas.
        String searchText =
                productName == null ? "" : productName.trim().toLowerCase();

        return productRepository.getAllProducts()
                .stream()

                // Filtro categoría
                // Si la categoría seleccionada es "Todas",
                // no se aplica filtro de categoría.
                //
                // En caso contrario, únicamente se mantienen los productos
                // cuya categoría coincide con la seleccionada.
                .filter(product ->

                        "Todas".equals(categoryName)

                                ||

                                product.getCategory()
                                        .getName()
                                        .equalsIgnoreCase(categoryName)
                )

                // Filtro nombre
                // Si no se indicó texto de búsqueda,
                // no se aplica filtro por nombre.
                //
                // En caso contrario, se conservan únicamente los productos
                // cuyo nombre contiene el texto ingresado por el usuario.
                .filter(product ->

                        searchText.isEmpty()

                                ||

                                product.getName()
                                        .toLowerCase()
                                        .contains(searchText)
                )

                .toList();
    }

    /**
     * Valida y registra un nuevo producto en la aplicación.
     * @param product información del producto.
     * @return true si el producto fue agregado correctamente.
     */
    public boolean addProduct(Product product) {

        // Busca el id de producto más alto y genera el siguiente consecutivo.
        long nextId = 1;

        for(Product currentProduct :
                productRepository.getAllProducts()) {

            if(currentProduct.getId() >= nextId) {
                nextId = currentProduct.getId() + 1;
            }

        }

        // Valida que el SKU no exista previamente.
        Optional<Product> existingSku =
                findBySku(
                        product.getSku()
                );

        if(existingSku.isPresent()) {
            return false;
        }

        // Orden del constructor en Product

        /*
        id
        sku
        name
        description
        price
        imagePath
        category
        List<Property> properties
        active
         */

        // Constructor minimo anterior
        /*
        Product newProduct =
                new Product(
                        nextId,
                        product.getSku().trim(),
                        product.getName().trim(),
                        product.getDescription().trim(),
                        product.getPrice(),
                        product.getImagePath(),
                        product.getCategory(),
                        product.getProperties(),
                        true
                );

         */

        // Corrección a constructor completo de clase: Product
        // Orden del constructor en Product

        /*
        id
        sku
        name
        description
        price
        imagePath
        category
        List<Property> properties
        featured
        discountPercentage
        createdAt
        active
        */

        Product newProduct =
                new Product(
                        nextId,
                        product.getSku().trim(),
                        product.getName().trim(),
                        product.getDescription().trim(),
                        product.getPrice(),
                        product.getImagePath(),
                        product.getCategory(),
                        product.getProperties(),
                        product.isFeatured(),
                        product.getDiscountPercentage(),
                        java.time.LocalDateTime.now(),
                        product.isActive()
                );

        // Corrección:
        // Valida que no se pierda el path de la image genérica al crear nuevos
        // productos o actualizar existentes, cuando carezcan de ruta de imagen
        if (newProduct.getImagePath().isEmpty()){
            newProduct.setImagePath("/images/products/productIcon.png");
        }

        productRepository.addProduct(
                newProduct
        );

        return true;

    }

    /**
     * Actualiza la información editable de un producto.
     * @param productId identificador del producto.
     * @param sku sku.
     * @param name nombre.
     * @param description descripción.
     * @param price precio
     * @param imagePath ruta imagen.
     * @param category categoría.
     * @param properties propiedades.
     * @param featured destacado.
     * @param discountPercentage descuento.
     * @param active estado.
     * @return true si fue posible actualizar.
     */
    public boolean updateProduct(
            Long productId,
            String sku,
            String name,
            String description,
            double price,
            String imagePath,
            Category category,
            List<Property> properties,
            boolean featured,
            double discountPercentage,
            boolean active) {

        Optional<Product> product =
                findByProductId(productId);

        if(product.isEmpty()) {
            return false;
        }

        // Corrección:
        // Valida que no se pierda el path de la image genérica al crear nuevos
        // productos o actualizar existentes, cuando carezcan de ruta de imagen
        if (imagePath.isEmpty()) {
            imagePath = "/images/products/productIcon.png";
        }

        Product updatedProduct =
                new Product(
                        product.get().getId(),
                        sku,
                        name,
                        description,
                        price,
                        imagePath,
                        category,
                        properties,
                        featured,
                        discountPercentage,
                        product.get().getCreatedAt(),
                        active
                );

        productRepository.updateProduct(
                updatedProduct
        );

        return true;

    }

    /**
     * Cambia el estado activo/inactivo de un producto.
     * @param productId identificador del producto.
     * @return true si fue posible modificar el estado.
     */
    public boolean switchProductStatus(Long productId) {

        return productRepository.switchProductStatus(
                productId
        );

    }

    /**
     * Busca productos según criterio indicado.
     * @param searchType tipo de búsqueda.
     * @param searchValue valor a buscar.
     * @return Lista de productos encontrados.
     */
    public List<Product> searchProducts(
            String searchType,
            String searchValue) {

        return productRepository.searchProducts(
                searchType,
                searchValue
        );

    }

    /**
     * Busca un producto mediante su SKU.
     * @param sku código SKU del producto.
     * @return Producto encontrado o Optional vacío.
     */
    public Optional<Product> findBySku(String sku) {

        if(sku == null ||
                sku.trim().isEmpty()) {

            return Optional.empty();

        }

        return productRepository.findBySku(
                sku.trim()
        );

    }

}