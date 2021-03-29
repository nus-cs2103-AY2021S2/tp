package seedu.address.ui;

import seedu.address.model.scheduler.Schedulable;
import seedu.address.model.scheduler.SimplePeriod;
import seedu.address.model.scheduler.Timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * In charge of putting meetings into the @code{TimetableView} given a certain meeting,
 * provides method to check if it can be placed in the timetable or
 * not.
 * Provides method to calculate how far down the column it should be placed,
 * and in which column for a timetable.
 *
 * The policy will have seven days. with the first day starting on a specified start date.
 * The start time (HH:mm) of the day is fixed to be 7:00 am. A day will end at 7:00 am on the next day.
 *
 * To check if it is valid to be scheduled, it just needs to overlap with the time range of this timetable.
 * To overlap it must intersect a positive length interval (i.e it cannot just overlap at the endpoints)
 *
 * The part which is overlapping between days will be broken up.
 *
 *
 */
public class TimetablePlacementPolicy {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private static int startHour = 7;
    private static int startMinute = 0;

    /**
     * Takes in a startDate that it should be used as a reference point. Initializes the start and end times
     * represented bu this timetable policy.
     * @param startDate
     */

    public TimetablePlacementPolicy(LocalDate startDate) {
        this.startDateTime = startDate.atTime(startHour, startMinute)
        this.endDateTime = startDateTime.plusDays(TimetableView.NUMBER_OF_COLUMNS);
    }

    /**
     * Tests if a Schedulable lies within the range of the timetable range.
     * @param schedulable
     * @return
     */
    public boolean test(Schedulable schedulable) {
        LocalDateTime startTimeOfSchedulable = schedulable.getStartLocalDateTime();
        LocalDateTime endTimeOfSchedulable = schedulable.getTerminateLocalDateTime();
        return endTimeOfSchedulable.compareTo(startDateTime) <= 0
                || startTimeOfSchedulable.compareTo(endDateTime) >= 0;
    }

    /**
     * Gets the column to put a schedulable in if it lies within a day from 7am - 7pm.
     * @param schedulable
     * @return
     */


    public TimetableView.Column getColumnPlacement(Schedulable schedulable) {
    }

    public double getVerticalPosition(Schedulable schedulable) {
    }

    /**
     * Breaks the Schedulable object into continuous units that lie within each day. For example, if a
     * Schedulable object spans across several columns, it will be broken down into individual parts
     * to schedule in each column. Furthermore, if the schedulable units
     * outside outside the time range, it will be filtered out.
     * @param schedulable
     * @return
     */

    public List<Schedulable> breakIntoUnits(Schedulable schedulable) {
        LocalDateTime startDateTime = schedulable.getStartLocalDateTime();
        LocalDateTime endDateTime = schedulable.getTerminateLocalDateTime();

        //apply offset
        LocalDateTime offSetStartTime = applyOffset(startDateTime);
        LocalDateTime offSetEndTime = applyOffset(endDateTime);

        //same day
        if (offSetStartTime.toLocalDate().isEqual( startDateTime.toLocalDate()) {
            return List.of(schedulable);
        }
        //case when returns more than one element.
        ArrayList<Schedulable> listOfSchedulableUnits = new ArrayList<>();
        String name = schedulable.getNameString();

        Schedulable firstPeriod = new SimplePeriod(name,
                removeOffset(offSetStartTime),
                removeOffset(getStartOfNextDay(offSetStartTime)));
        listOfSchedulableUnits.add(firstPeriod);

        if (!offSetEndTime.isEqual(getStartOfTheDay(offSetEndTime))) {
            Schedulable lastPeriod = new SimplePeriod(name,
                    removeOffset(getStartOfTheDay(offSetEndTime)),
                    removeOffset(offSetEndTime));
            listOfSchedulableUnits.add(lastPeriod);
        }

        offSetStartTime = getStartOfNextDay(offSetStartTime);
        offSetEndTime = getStartOfTheDay(offSetEndTime);

        //iterate through each day slot in between
        while(offSetEndTime.isAfter(offSetStartTime)) {
            Schedulable toAdd = new SimplePeriod(name,
                    removeOffset(offSetStartTime),
                    removeOffset(getStartOfNextDay(offSetStartTime)));
            listOfSchedulableUnits.add(toAdd);
        }

    }

    /**
     * apply offset so each day period starts from 00:00 and ends at 00:00 the next day.
     * @param localDateTime
     * @return
     */
    public LocalDateTime applyOffset(LocalDateTime localDateTime) {
        return localDateTime.minusHours(startHour).minusMinutes(startMinute);
    }
    public LocalDateTime removeOffset(LocalDateTime localDateTime) {
        return localDateTime.plusHours(startHour).plusMinutes(startMinute);
    }

    /**
     * Gets the local date time of the (official) start of the next day, with time at 00:00
     * given the date today.
     * @return
     */
    public LocalDateTime getStartOfNextDay(LocalDateTime localDateTime) {
        localDateTime.toLocalDate().plusDays(1).atTime(0,0);
    }

    /**
     * Gets the start of the day at 00:00.
     * @param localDateTime
     * @return
     */
    public LocalDateTime getStartOfTheDay(LocalDateTime localDateTime) {
        localDateTime.toLocalDate().atTime(0,0);
    }



}
