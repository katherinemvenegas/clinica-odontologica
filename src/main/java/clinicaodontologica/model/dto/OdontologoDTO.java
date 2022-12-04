package clinicaodontologica.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class OdontologoDTO implements Serializable {
    @NotBlank
    @Size(min=6, message = "La matricula debe tener 6 caracteres como minimo")
    private String matricula;

    @NotBlank
    @Size(min=3, message = "El nombre debe tener 3 caracteres como minimo")
    private String nombre;
    @NotBlank
    @Size(min=2, message = "El apellido debe tener 2 caracteres como minimo")
    private String apellido;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public OdontologoDTO() {
    }

    public OdontologoDTO(String matricula, String nombre, String apellido) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "OdontologoDTO{" +
                "matricula='" + matricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
