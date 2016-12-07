package cz.muni.fi.pa165.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Jakub Fiser
 * @date 04/12/2016
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found.")
public class ResourceNotFound extends RuntimeException {
}