package cz.muni.fi.pa165.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author mhajas
 */
@JsonIgnoreProperties({ "bottles", "persons" })
public abstract class StoreDTOMixin {
}
