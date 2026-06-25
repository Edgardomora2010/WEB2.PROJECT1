// PAQUETES
package tec.nextshop_project_1.data;

// IMPORTACION DE LIBRERÍAS
import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para representar la información de los productos de venta
 * de la aplicación.
 */
@Data
public class Product {

    // OBJETOS/VARIABLES
    private Long id;
    private String sku;
    private String name;
    private String description;
    private double price;
    private String imagePath;
    private Category category;
    private List<Property> properties;
    private boolean featured;
    private double discountPercentage;
    private LocalDateTime createdAt;
    private boolean active;

    // CONSTRUCTORES SOBRECARGADOS DE CLASE

    // Constructor mínimo
    public Product(Long id,String sku,String name, String description, double price, String imagePath, Category category, List<Property> properties, boolean active) {

        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;

        // Corrección:
        // Si no se recibe lista de propiedades, se crea una lista vacía para evitar
        // errores por referencias nulas.
        if(properties == null) {

            this.properties =
                    new ArrayList<>();
        }
        else {

            this.properties =
                    properties;
        }

        this.featured = false;
        this.discountPercentage = 0;
        this.createdAt = LocalDateTime.now();
        this.active = active;
    }

    // Constructor completo
    public Product(Long id,String sku,String name,String description,double price,String imagePath,Category category, List<Property> properties,boolean featured,double discountPercentage,LocalDateTime createdAt,boolean active) {

        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;

        // Corrección:
        // Si no se recibe lista de propiedades, se crea una lista vacía para evitar
        // errores por referencias nulas.
        if(properties == null) {

            this.properties =
                    new ArrayList<>();
        }
        else {

            this.properties =
                    properties;
        }

        this.featured = featured;
        this.discountPercentage = discountPercentage;
        this.createdAt = createdAt;
        this.active = active;

    }

}
