package tec.nextshop_project_1.data;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    public enum Profile {
        CUSTOMER,
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    private Profile role;
    @Id
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private LocalDate registrationDate;
    private boolean active;
}
