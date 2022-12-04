package clinicaodontologica.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    public DomicilioDTO() {
    }

    public DomicilioDTO(String calle, int numero, String localidad, String provincia) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "DomicilioDTO{" +
                "calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
