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
    register(new ParseIcs());
  }
  
  private static void register(final Example eg) {
    examples.put(eg.getKey(), eg);
  }

  public static void main(String[] args) {
    if ((args == null) ||
            (args.length == 0)) {
      /* List the examples */
  
      Utils.pline("Parameters are none for this list or one or");
      Utils.pline("more of the following keywords:");
      
      for (final String s: examples.keySet()) {
        Utils.pline(s + "\t" + examples.get(s).getTitle());
      }
      
      return;
    }

    for (final String s: args) {
      final Example eg = examples.get(s);
      
      if (eg == null) {
        Utils.pline("No example with key " + s);
        continue;
      }
      
      Utils.pline("Example " + s);
      Utils.pline("");
      
      eg.runExample();
    }

  }
}
