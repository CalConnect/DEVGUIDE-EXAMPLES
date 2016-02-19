/* ********************************************************************
    Appropriate copyright notice
*/
package org.calconnect.examples;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.UidGenerator;

import java.io.StringWriter;

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

  /** Converts to string with lines folded 
   * 
   * @param cal to be output
   */
  public static String calToString(final Calendar cal) {
    CalendarOutputter co = new CalendarOutputter(false, 73);
    
    final StringWriter sw = new StringWriter();
    
    try {
      co.output(cal, sw);
    } catch (final Throwable t) {
      t.printStackTrace();
      return "Failed";
    }
    
    return sw.toString();
  }
}
