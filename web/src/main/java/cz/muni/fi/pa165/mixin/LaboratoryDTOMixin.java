package cz.muni.fi.pa165.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author mhajas
 */
@JsonIgnoreProperties({ "bottlesToCheck", "persons" })
public abstract class LaboratoryDTOMixin {
}
