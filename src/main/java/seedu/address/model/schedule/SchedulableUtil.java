package seedu.address.model.schedule;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to manipulate schedulables, like shifting a schedulable by a certain offset,
 * or finding overlap between schedulable objects.
 */
public class SchedulableUtil {

    /**
     * Splits a schedulable into multiple schedulables on different days. For example if a schedulable is scheduled
     * on friday 2pm  to Sunday 2pm, it should split into schedulables
     * [fri 2pm- fri 11.59.99999pm, sat 12am - sat 11.59.5999999 pm , sunday 12am - 2pm]
     * @param schedulable
     * @return a mutable list containing the schedulables.
     */

    public static List<Schedulable> splitSchedulableByDay(Schedulable schedulable) {

        LocalDateTime startDateTime = schedulable.getStartLocalDateTime();
        LocalDateTime endDateTime = schedulable.getTerminateLocalDateTime();

        ArrayList<Schedulable> listOfSchedulableUnits = new ArrayList<>();

        //same day interval.
        if (startDateTime.toLocalDate().isEqual(endDateTime.toLocalDate())) {
            listOfSchedulableUnits.add(schedulable);
            return listOfSchedulableUnits;
        }

        String name = schedulable.getNameString();

        //Check if startTime == to 23:59.59999

        if (startDateTime.toLocalTime() != LocalTime.MAX) {
            Schedulable firstPeriod = new SimplePeriod(name, startDateTime, getEndOfTheDay(startDateTime));
            listOfSchedulableUnits.add(firstPeriod);
        }

        //Check if case !endTime == 00:00
        if (!endDateTime.isEqual(getStartOfTheDay(endDateTime))) {
            Schedulable lastPeriod = new SimplePeriod(name, getStartOfTheDay(endDateTime), endDateTime);
            listOfSchedulableUnits.add(lastPeriod);
        }

        startDateTime = getStartOfTheNextDay(startDateTime);
        endDateTime = getStartOfTheDay(endDateTime);

        //iterate through each day slot in between
        while(endDateTime.isAfter(startDateTime)) {
            Schedulable toAdd = new SimplePeriod(name, startDateTime, getEndOfTheDay(startDateTime));
            listOfSchedulableUnits.add(toAdd);
            startDateTime = getStartOfTheNextDay(startDateTime);
        }

        return listOfSchedulableUnits;
    }



    /**
     * Applies a positive offset to a Schedulable object by adding a positive number of hours and minutes to its
     * start and end times.
     * @param schedulable
     * @param hourOffset
     * @param minuteOffset
     * @return
     */

    public static Schedulable applyPositiveOffset(Schedulable schedulable, int hourOffset, int minuteOffset) {

        return new SimplePeriod(schedulable.getNameString(),
                applyPositiveOffset(schedulable.getStartLocalDateTime(), hourOffset, minuteOffset),
                applyPositiveOffset(schedulable.getTerminateLocalDateTime(), hourOffset, minuteOffset));
    }

    /**
     * Applies a negative offset to a Schedulable object by subtracting a positive number of hours and minutes from its
     * start and end times.
     * @param schedulable
     * @param hourOffset
     * @param minuteOffset
     * @return
     */

    public static Schedulable applyNegativeOffset(Schedulable schedulable, int hourOffset, int minuteOffset) {
        return new SimplePeriod(
                schedulable.getNameString(),
                applyNegativeOffset(schedulable.getStartLocalDateTime(), hourOffset, minuteOffset),
                 applyNegativeOffset(schedulable.getTerminateLocalDateTime(), hourOffset, minuteOffset));
    }

    /**
     * Offsets a local date by a negative number of hours and minutes;
     * @return
     */
    public static LocalDateTime applyNegativeOffset(LocalDateTime localDateTime, int hourOffset, int minuteOffset) {
        return localDateTime.minusHours(hourOffset).minusMinutes(minuteOffset);
    }

    /**
     * Offsets a datetime by a positive number of hours and minutes.
     * in @code{applyOffset}
     *
     * @param offSetDateTime
     * @return
     */
    public static LocalDateTime applyPositiveOffset(LocalDateTime offSetDateTime, int hourOffset, int minuteOffset) {
        return offSetDateTime.plusHours(hourOffset).plusMinutes(minuteOffset);
    }



    /**
     * Gets the local date time of the end of the day, right before 00:00.
     * @return
     */
    public static LocalDateTime getEndOfTheDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(LocalTime.MAX);
    }

    /**
     * Gets the start of the day at 00:00.
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getStartOfTheDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(0,0);
    }

    /**
     * Get the start of the next day of this localDateTime.
     */
    public static LocalDateTime getStartOfTheNextDay(LocalDateTime localDateTime) {
        return getStartOfTheDay(localDateTime).plusDays(1);
    }


}
