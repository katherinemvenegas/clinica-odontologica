package clinicaodontologica.security.persistence.entities;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "rol_id")
    private Rol rol;
}
