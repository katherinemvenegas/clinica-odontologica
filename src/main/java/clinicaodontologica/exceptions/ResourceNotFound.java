package clinicaodontologica.exceptions;

import org.springframework.dao.DataAccessException;

public class ResourceNotFound extends DataAccessException {

    public ResourceNotFound(String message) {
        super(message);
    }
}
