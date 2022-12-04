package clinicaodontologica.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionConfig {

    private static final Logger logger = Logger.getLogger(ExceptionConfig.class);

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> idNotFound(ResourceNotFound ex) {
        logger.error(ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> invalidFormatInput(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(error -> (error.getDefaultMessage())).collect(Collectors.toList());
        logger.error(errors);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
