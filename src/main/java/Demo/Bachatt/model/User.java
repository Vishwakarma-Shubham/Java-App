package Demo.Bachatt.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private int uid;

    @NotNull
    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @NotNull
    @Email
    @Column(name = "email", nullable = false, length = 25, unique = true)
    private String email;

    @NotNull
    @Column(name = "phone", nullable = false)
    private long phone;

    @NotNull
    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "phoneVerified", nullable = false)
    private boolean phoneVerified = false;

    @Column(name = "emailVerified", nullable = false)
    private boolean emailVerified = false;

    @NotNull
    @Column(name = "password", nullable = false, length = 255)
    private String password;
}
