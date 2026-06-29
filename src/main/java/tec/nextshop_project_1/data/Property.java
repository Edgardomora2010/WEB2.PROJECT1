package tec.nextshop_project_1.data;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Property {

    @Column(name = "property_name")
    private String name;
    @Column(name = "property_value")
    private String value;
}
