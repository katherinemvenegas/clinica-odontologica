package clinicaodontologica.security.persistence.repository;

import clinicaodontologica.security.persistence.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username);

    @Query("FROM Usuario u WHERE u.username = :username")
    Usuario existByUsername(String username);
}
