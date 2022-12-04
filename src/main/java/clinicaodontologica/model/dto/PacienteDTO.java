package clinicaodontologica.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

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

    public PacienteDTO() {
    }

    public PacienteDTO(String apellido, String nombre, String email, Long dni, LocalDate fechaDeIngreso, DomicilioDTO domicilioDTO) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.fechaDeIngreso = fechaDeIngreso;
        this.domicilio = domicilioDTO;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public LocalDate getFechaDeIngreso() {
        return fechaDeIngreso;
    }

    public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
        this.fechaDeIngreso = fechaDeIngreso;
    }

    public DomicilioDTO getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(DomicilioDTO domicilio) {
        this.domicilio = domicilio;
    }

    @Override
    public String toString() {
        return "PacienteDTO{" +
                "apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", dni=" + dni +
                ", fechaDeIngreso=" + fechaDeIngreso +
                ", domicilio=" + domicilio +
                '}';
    }
}
