package cz.muni.fi.pa165.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mhajas
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Resource is not valid.")
public class ResourceNotValid extends RuntimeException {
}
