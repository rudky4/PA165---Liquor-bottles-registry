package cz.muni.fi.pa165.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Martin SUmera
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="The role is not set")
public class RoleIsNotSet extends RuntimeException {
}
