/**
 *
 */
package tools.daogen.config.roules;

import groovy.transform.ToString;
import groovy.util.slurpersupport.GPathResult;

/**
 *
 *
 */
@ToString(includeNames = true, includeFields = true)
public interface Configuration {

	abstract void load(GPathResult xml);
}
