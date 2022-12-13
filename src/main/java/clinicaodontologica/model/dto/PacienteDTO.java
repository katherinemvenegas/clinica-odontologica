package clinicaodontologica.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PacienteDTO implements Serializable {

    @NotNull(message = "Debe ingresar su apellido")
    @Size(min = 2, message = "El apellido debe tener 2 caracteres como minimo")
    private String apellido;

    @NotNull(message = "Debe ingresar su nombre")
    @Size(min = 3, message = "El nombre debe tener 3 caracteres como minimo")
    private String nombre;

    @NotNull(message = "Debe ingresar su email")
    @Email(regexp = ".+[@].+[\\.].+", message = "El email debe tener el siguiente formato: mail@mail.com")
    private String email;

    @NotNull(message = "debe ingresar un numero de DNI")
    @Min(value = 500000, message = "Ingrese un DNI valido, es decir, mayor a 500000")
    private Long dni;

    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fechaDeIngreso;

    @Valid
    private DomicilioDTO domicilio;

}
