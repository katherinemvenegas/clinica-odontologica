package clinicaodontologica.utils;

import clinicaodontologica.security.dto.LoginRequestDTO;
import clinicaodontologica.security.dto.RolDTO;
import clinicaodontologica.security.dto.SignupDTO;
import clinicaodontologica.security.persistence.entities.Rol;
import clinicaodontologica.security.persistence.entities.RolName;
import clinicaodontologica.security.persistence.entities.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

public class UsuarioFactory {

    public static SignupDTO createSignUpDTO() {
        return new SignupDTO("maria@mail.com", "abc123", new RolDTO("ADMIN"));
    }

    public static Usuario getUser() {

        return new Usuario(1L, "maria@mail.com", "abc123", new Rol(1L, RolName.ADMIN));
    }

    public static LoginRequestDTO createRequest() {
        return new LoginRequestDTO("maria@mail.com", "abc123");
    }

    public static UserDetails getUserDetails() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(getUser().getRol().getRolName().name()));
        return new User(getUser().getUsername(), getUser().getPassword(), authorities);
    }
}
