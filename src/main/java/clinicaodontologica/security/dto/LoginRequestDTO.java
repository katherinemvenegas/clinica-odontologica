package clinicaodontologica.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "Debe ingresar su email")
    @Email(regexp = ".+[@].+[\\.].+", message = "El email debe tener el siguiente formato: mail@mail.com")
    private String username;

    @NotNull(message = "Debe ingresar su contraseña")
    private String password;
}
