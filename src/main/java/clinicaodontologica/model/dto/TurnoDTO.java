package clinicaodontologica.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class TurnoDTO implements Serializable {

    private Long idPaciente, idOdontologo;
    private String pacienteApellido;
    private String odontologoApellido;

    @DateTimeFormat
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate fecha;

    public TurnoDTO() {
    }

    public TurnoDTO(Long idPaciente, Long idOdontologo, LocalDate fecha) {
        this.idPaciente = idPaciente;
        this.idOdontologo = idOdontologo;
        this.fecha = fecha;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdOdontologo() {
        return idOdontologo;
    }

    public void setIdOdontologo(Long idOdontologo) {
        this.idOdontologo = idOdontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPacienteApellido() {
        return pacienteApellido;
    }

    public void setPacienteApellido(String pacienteApellido) {
        this.pacienteApellido = pacienteApellido;
    }

    public String getOdontologoApellido() {
        return odontologoApellido;
    }

    public void setOdontologoApellido(String odontologoApellido) {
        this.odontologoApellido = odontologoApellido;
    }

    @Override
    public String toString() {
        return "TurnoDTO{" +
                "id paciente=" + idPaciente +
                ", id odontologo=" + idOdontologo +
                ", fecha=" + fecha +
                '}';
    }
}
