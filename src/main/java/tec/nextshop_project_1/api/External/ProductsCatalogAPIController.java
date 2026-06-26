// PAQUETES
package tec.nextshop_project_1.api.External;

// IMPORTACIÓN DE LIBRERÍAS
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tec.nextshop_project_1.data.Product;
import tec.nextshop_project_1.service.ProductService;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsCatalogAPIController {

    // OBJETOS Y VARIABLES GLOBALES
    private final ProductService productService;

    /**
     * Constructor de clase con inserción de dependencia
     * @param productService
     */
    public ProductsCatalogAPIController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * @return Catálogo completo de producto expuesto al exterior para alimentar
     * otro sistemas y api, o microservicios
     */
    @GetMapping
    public Iterable<Product> getProductsCatalog() {
        return productService.getAllProducts();
    }

    /**
     * @param id
     * @return Un producto a partir de su id
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> userOpt = productService.findByProductId(id);
        return userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
