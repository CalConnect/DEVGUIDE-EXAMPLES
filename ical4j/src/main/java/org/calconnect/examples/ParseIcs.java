/* ********************************************************************
    Appropriate copyright notice
*/
package org.calconnect.examples;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.UnfoldingReader;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;

import java.io.FileReader;

/**
 * User: mike Date: 2/18/16 Time: 14:16
 */
public class ParseIcs implements Example {
  @Override
  public String getKey() {
    return "parse-ics";
  }

  @Override
  public String getTitle() {
    return "Parse an ics file";
  }

  final static String ics = "ical4j/src/main/resources/example.ics";
  
  @Override
  public void runExample() {
    try {
      CalendarBuilder builder = new CalendarBuilder();

      final UnfoldingReader ufrdr =
              new UnfoldingReader(new FileReader(ics),
                                  true);

      Calendar calendar = builder.build(ufrdr);

      for (final Object o : calendar.getComponents()) {
        Component component = (Component)o;

        System.out.println("Component: " + component.getName());

        for (final Object o1 : component.getProperties()) {
          Property property = (Property)o1;
          System.out.println(
                  property.getName() + ": " + property.getValue());
        }
      }
    } catch (Throwable t) {
      t.printStackTrace();
    }
  }
}