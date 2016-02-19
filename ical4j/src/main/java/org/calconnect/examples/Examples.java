/* ********************************************************************
    Appropriate copyright notice
*/
package org.calconnect.examples;

import java.util.HashMap;
import java.util.Map;

/**
 * User: mike Date: 2/18/16 Time: 22:54
 */
public class Examples {
  private static Map<String, Example> examples =
          new HashMap<>();

  static {
    register(new SimpleEvent());
    register(new SimpleMeeting());
  }
  
  private static void register(final Example eg) {
    examples.put(eg.getKey(), eg);
  }

  public static void main(String[] args) {
    if ((args == null) ||
            (args.length == 0)) {
      /* List the examples */
  
      pln("Parameters are none for this list or one or");
      pln("more of the following keywords:");
      
      for (final String s: examples.keySet()) {
        pln(s + "\t" + examples.get(s).getTitle());
      }
      
      return;
    }

    for (final String s: args) {
      final Example eg = examples.get(s);
      
      if (eg == null) {
        pln("No example with key " + s);
        continue;
      }
      
      pln("Example " + s);
      pln("");
      
      /* Use the ical4j CalendarOutputter class to fold the output lines
         to a maximum length.
       */
      pln(Utils.calToString(eg.getExample()));
    }
  }
  
  private static void pln(String msg) {
    System.out.println(msg);
  }
}
