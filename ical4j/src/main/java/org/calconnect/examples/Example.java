package org.calconnect.examples;

/**
 * User: mike Date: 2/18/16 Time: 22:35
 */
public interface Example {
  /**
   * @return a short keyword to identify
   */
  String getKey();

  /**
   * @return more descriptive title
   */
  String getTitle();

  /**
   * @return the example calendar object
   */
  void runExample();
}
