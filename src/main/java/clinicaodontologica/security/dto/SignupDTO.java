package clinicaodontologica.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDTO {

    @NotNull(message = "Debe ingresar su email")
    @Email(regexp = ".+[@].+[\\.].+", message = "El email debe tener el siguiente formato: mail@mail.com")
    private String username;

    @NotNull(message = "Debe ingresar su contraseña")
    @Size(min = 6, message = "La contraseña debe tener 6 caracteres como minimo")
    private String password;

    private RolDTO rol;
}
