package cz.muni.fi.pa165.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jakub Fiser
 * @date 15/12/2016
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Resource already exists.")
public class ResourceConflict extends RuntimeException {
}
