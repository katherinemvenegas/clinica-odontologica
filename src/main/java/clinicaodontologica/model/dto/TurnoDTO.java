package clinicaodontologica.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TurnoDTO implements Serializable {

    @NotNull(message = "Ingrese un numero de paciente")
    @Positive(message = "el numero del paciente debe ser mayor a cero")
    private Long idPaciente;
    @NotNull(message = "Ingrese un numero de odontologo")
    @Positive(message = "el numero del odontologo debe ser mayor a cero")
    private Long idOdontologo;
    private String pacienteApellido;
    private String odontologoApellido;

    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "Debe ingresar una fecha para el turno")
    private LocalDate fecha;

    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @JsonSerialize(using = LocalTimeSerializer.class)
    @NotNull(message = "Debe ingresar una hora para el turno")
    private LocalTime hora;
}
