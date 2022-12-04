package clinicaodontologica.exceptions;

import org.springframework.dao.InvalidDataAccessResourceUsageException;

public class ResourceNotFound extends InvalidDataAccessResourceUsageException {

    public ResourceNotFound(String message) {
        super(message);
    }
}
