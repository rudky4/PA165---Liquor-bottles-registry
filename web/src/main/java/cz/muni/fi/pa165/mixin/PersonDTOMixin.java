package cz.muni.fi.pa165.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author mhajas
 */
@JsonIgnoreProperties({ "password" })
public abstract class PersonDTOMixin {
}
