package tec.nextshop_project_1.data;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    private Long id;
    private String sku;
    private String name;
    @Column(length = 1000)
    private String description;
    private double price;
    private String imagePath;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "product_properties", joinColumns = @JoinColumn(name = "product_id"))
    private List<Property> properties;
    private boolean featured;
    private double discountPercentage = 0;
    private LocalDateTime createdAt;
    private boolean active;

    public Product(Long id, String sku, String name, String description, double price, String imagePath, Category category, List<Property> properties, boolean active) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
        this.properties = properties == null ? new ArrayList<>() : properties;
        this.featured = false;
        this.discountPercentage = 0;
        this.createdAt = LocalDateTime.now();
        this.active = active;
    }

    public Product(Long id, String sku, String name, String description, double price, String imagePath, Category category, List<Property> properties, boolean featured, double discountPercentage, LocalDateTime createdAt, boolean active) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imagePath = imagePath;
        this.category = category;
        this.properties = properties == null ? new ArrayList<>() : properties;
        this.featured = featured;
        this.discountPercentage = discountPercentage;
        this.createdAt = createdAt;
        this.active = active;
    }
}
