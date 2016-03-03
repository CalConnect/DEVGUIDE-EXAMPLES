/* Example libical code to create Simple Event
 *
 * To compile: cc -o SimpleEvent -lical SimpleEvent.c
 */

#include <libical/ical.h>

#include <stdio.h>


int main(int argc, char *argv[])
{
    icalcomponent *cal, *event;
    icalproperty *prop;
    const char *my_product, *uid, *tzid, *desc;
    struct icaltimetype dtstart;
    struct icaldurationtype duration;

    /* Create VCALENDAR component */
    cal = icalcomponent_new(ICAL_VCALENDAR_COMPONENT);

    /* Create VERSION property and add to calendar.
       In this case, we create the new property and assign it to a variable
       (perhaps we need to access it later).
       We then pass this variable to the 'add' function.
    */
    prop = icalproperty_new_version("2.0");
    icalcomponent_add_property(cal, prop);

    /* Create PRODID property and add it to calendar.
       In this case, we don't plan on needing the property later
       so we can nest the 'new' function within the 'add' function.
     */
    my_product = "-//ABC Corporation/NONSGML My Product//EN";
    icalcomponent_add_property(cal, icalproperty_new_prodid(my_product));

    /* Create a uid for our event.
       NOTE: This should probably be created by a third-party library
       to guarantee uniqueness (e.g. OSSP UUID).
    */
    uid = "ff808181-1fd7389e-011f-d7389ef9-00000003";

    /* Create start time for our meeting */
    dtstart = icaltime_null_time();
    dtstart.year = 2016;
    dtstart.month = 4;
    dtstart.day = 20;
    dtstart.hour = 12;
    tzid = "America/New_York";

    /* Create duration for our meeting (1hr = 3600s) */
    duration = icaldurationtype_from_int(3600);

    /* Create VEVENT component.
       In this case, we use the varargs variants of creating a component
       and property which allows us to construct the component all in one go.
    */
    event =
        icalcomponent_vanew(ICAL_VEVENT_COMPONENT,
                            icalproperty_new_summary("Lunchtime meeting"),
                            icalproperty_new_uid(uid),
                            icalproperty_vanew_dtstart(dtstart,
                                                       icalparameter_new_tzid(tzid),
                                                       0),
                            icalproperty_new_duration(duration),
                            0);

    /* Create LOCATION property and add it to the event */
    icalcomponent_add_property(event,
                               icalproperty_new_location("Mo's bar - back room"));

    /* Create DESCRIPTION property and add it to the event */
    desc =
        "We'll continue with the unfinished business from last time,\n"
        "in particular:\n"
        "Can names start wih a number?\n"
        "What if they are all numeric?\n"
        "Reuse of names - is it valid\n"
        "I remind the attendees we have spent 3 "
        "months on these subjects. We need "
        "closure!!!";
    icalcomponent_add_property(event, icalproperty_new_description(desc));

    /* Add our event to the calendar */
    icalcomponent_add_component(cal, event);

    /* Create a string from our calendar and send it to stdout */
    printf("%s", icalcomponent_as_ical_string(cal));
}
