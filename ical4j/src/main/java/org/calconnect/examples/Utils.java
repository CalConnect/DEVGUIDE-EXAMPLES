/* ********************************************************************
    Appropriate copyright notice
*/
package org.calconnect.examples;

import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;

/** Some simple utility routines.
 * 
 * User: mike Date: 2/18/16 Time: 14:39
 */
public class Utils {
  private static TimeZoneRegistry timezones;
  
  static {
    timezones = TimeZoneRegistryFactory.getInstance()
                                       .createRegistry();
  }

  public static TimeZone getTimezone(final String id) {
    return timezones.getTimeZone(id);
  }
  
  /** Create a UID using the ical4j generator
   * 
   * @return Uid property
   * @throws Exception
   */
  public static Uid generateUid() throws Exception {
    UidGenerator ug = new UidGenerator("uidGen");
    return ug.generateUid();
  }
}
