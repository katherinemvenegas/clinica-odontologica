package clinicaodontologica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OdontologoDTO implements Serializable {

    @NotNull(message = "Debe ingresar su matricula")
    @Size(min = 6, message = "La matricula debe tener 6 caracteres como minimo")
    private String matricula;

    @NotNull(message = "Debe ingresar su nombre")
    @Size(min = 3, message = "El nombre debe tener 3 caracteres como minimo")
    private String nombre;

    @NotNull(message = "Debe ingresar su apellido")
    @Size(min = 2, message = "El apellido debe tener 2 caracteres como minimo")
    private String apellido;

}
