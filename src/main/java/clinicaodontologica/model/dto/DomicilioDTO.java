package clinicaodontologica.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioDTO implements Serializable {

    @NotNull(message = "Debe ingresar el nombre de la calle")
    @Size(min = 3, message = "El nombre de la calle debe tener 3 caracteres como minimo")
    private String calle;

    @NotNull(message = "Ingrese un numero para su domicilio")
    @Positive(message = "el numero del domicilio debe ser mayor a cero")
    private int numero;

    @NotNull(message = "Debe ingresar el nombre de la localidad")
    @Size(min = 3, message = "El nombre de la localidad debe tener 3 caracteres como minimo")
    private String localidad;

    @NotNull(message = "Debe ingresar el nombre de la provincia")
    @Size(min = 3, message = "El nombre de la provincia debe tener 3 caracteres como minimo")
    private String provincia;

}
