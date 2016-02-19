/* ********************************************************************
    Appropriate copyright notice
*/
package org.calconnect.examples;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Duration;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

import java.util.GregorianCalendar;

/**
 * User: mike Date: 2/18/16 Time: 14:16
 */
public class SimpleEvent implements Example {
  @Override
  public String getKey() {
    return "simple-event";
  }

  @Override
  public String getTitle() {
    return "Simple 1 hour non-recurring event with no attendees";
  }

  @Override
  public Calendar getExample() {
    try {
      final TimeZone tz = Utils.getTimezone("America/New_York");
      
      /* Make up a start date 10 days from now.
         Ensure on an hour boundary
       */
      java.util.Calendar startDate = new GregorianCalendar();
      startDate.setTimeZone(tz);
      startDate.add(java.util.Calendar.DAY_OF_MONTH, 10);
      startDate.set(java.util.Calendar.MINUTE, 0);
      startDate.set(java.util.Calendar.SECOND, 0);

      // Create the event
      String eventName = "Simple event";
      DateTime start = new DateTime(startDate.getTime());
      start.setTimeZone(tz);
      
      VEvent event = new VEvent(start, eventName);

      PropertyList props = event.getProperties();

      /* One hour */
      Duration dur = new Duration(null, "PT1H");
      props.add(dur);

      /* Need a uid */
      props.add(Utils.generateUid());
      
      /* Add the location */
      props.add(new Location("Mo's bar - back room"));

      /* And the description - Note that ical4j handles any
         wrapping of long lines and with the 
         escaping of special characters. 
       */
      props.add(new Description(
              "We'll continue with the unfinished business from last time,\n" +
                      "in particular:\n" +
                      "Can names start wih a number?\n" +
                      "What if they are all numeric?\n" +
                      "Reuse of names - is it valid\n" +
                      "I remind the attendees we have spent 3 " +
                      "months on these subjects. We need " +
                      "closure!!!"));
      
      /* Create a calendar object */
      Calendar cal = new Calendar();
      PropertyList calProps = cal.getProperties();
      
      calProps.add(
              new ProdId("-//ABC Corporation/NONSGML iCal4j 1.0//EN"));
      calProps.add(CalScale.GREGORIAN);
      calProps.add(Version.VERSION_2_0);

      // Add the event and print
      cal.getComponents().add(event);
      
      return cal;
    } catch (Throwable t) {
      t.printStackTrace();
      return null;
    }
  }
}