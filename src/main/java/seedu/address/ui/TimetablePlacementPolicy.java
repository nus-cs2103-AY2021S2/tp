package seedu.address.ui;

import seedu.address.model.schedule.Schedulable;
import seedu.address.model.schedule.SchedulableUtil;
import seedu.address.model.schedule.SimplePeriod;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

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

    private static final int SECONDS_IN_A_MINUTE = 60;
    private static final int SECONDS_IN_AN_HOUR = 3600;
    private static final long SECONDS_IN_DAY = 86400;

    public static final double TIMETABLE_DISPLAY_SIZE = 5760;


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
        this.startDateTime = startDate.atTime(startHour, startMinute);
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
        return !(endTimeOfSchedulable.compareTo(startDateTime) <= 0
                || startTimeOfSchedulable.compareTo(endDateTime) >= 0);
    }

    /**
     * Gets the column to put a schedulable in.
     * The schedulable must lie within
     * Note the schedulable must lie within a day from 7am - 7pm, else it will just slot into the
     * first available day of the schedulable. You can call the method breakIntoUnits first to get
     * the single day slots before calling this method on each schedule.
     *
     * @param schedulable
     * @return
     */

    public TimetableView.Column getColumnPlacement(Schedulable schedulable) {
        LocalDateTime dateTimeToSchedule = schedulable.getStartLocalDateTime();
        int daysBetween = (int) Duration.between(startDateTime, dateTimeToSchedule).toDays();
        switch(daysBetween) {
        case 0:
            return TimetableView.Column.ONE;
        case 1:
            return TimetableView.Column.TWO;
        case 2:
            return TimetableView.Column.THREE;
        case 3:
            return TimetableView.Column.FOUR;
        case 4:
            return TimetableView.Column.FIVE;
        case 5:
            return TimetableView.Column.SIX;
        case 6:
            return TimetableView.Column.SEVEN;
        default:
            assert false; // Cannot end up here.

        }
        return null;

    }

    /**
     * Gets the number of seconds so far in a day, starting from 00:00
     * @param localDateTime
     * @return
     */

    public static int getSecondsInDay(LocalDateTime localDateTime) {
        return localDateTime.getHour() * SECONDS_IN_AN_HOUR + localDateTime.getMinute() * SECONDS_IN_A_MINUTE + localDateTime.getSecond();
    }

    public double getVerticalPosition(Schedulable schedulable) {
        LocalDateTime startingDateTime = schedulable.getStartLocalDateTime();
        int minutesSoFar = getSecondsInDay(applyOffset(startingDateTime));
        double ratio = (double) minutesSoFar / SECONDS_IN_DAY;
        return ratio * TIMETABLE_DISPLAY_SIZE;
    }

    /**
     * Returns the size in pixels of the timetable cell to represent the Schedulable.
     * We assume the schedulable must start and end on the same day.
     * @param schedulable
     * @return
     */
    public double getLengthOfSlot(Schedulable schedulable) {
        LocalDateTime offSetStartDate = applyOffset(schedulable.getStartLocalDateTime());
        LocalDateTime offSetEndDate = applyOffset(schedulable.getTerminateLocalDateTime());
        long startSecondsSoFar = getSecondsInDay(offSetStartDate);
        long endSecondsSoFar = getSecondsInDay(offSetEndDate);
        assert endSecondsSoFar >= startSecondsSoFar;
        double ratio = (double)(endSecondsSoFar - startSecondsSoFar) / SECONDS_IN_DAY;
        return TIMETABLE_DISPLAY_SIZE * ratio;

    }

    /**
     * Breaks the Schedulable object into continuous units that lie within each day. For example, if a
     * Schedulable object spans across several columns, it will be broken down into individual parts
     * to schedule in each column. Furthermore, if the schedulable units
     * outside outside the time range, it will be filtered out.
     * @param schedulable
     * @return
     */

    public Stream<Schedulable> breakIntoDayUnits(Schedulable schedulable) {

        assert test(schedulable);
        Schedulable offSetSchedule = SchedulableUtil.applyNegativeOffset(schedulable, startHour, startMinute);
        List<Schedulable> splittedSchedulables = SchedulableUtil.splitSchedulableByDay(offSetSchedule);
        return splittedSchedulables
                .stream()
                .map(s -> SchedulableUtil.applyPositiveOffset(s, startHour, startMinute))
                .filter(this :: test);

    }

    /**
     * apply offset start hour and start minutes so each day period starts from 00:00 and ends at LocalTime.max the
     * next day.
     * @param localDateTime
     * @return
     */
    public LocalDateTime applyOffset(LocalDateTime localDateTime) {
        return localDateTime.minusHours(startHour).minusMinutes(startMinute);
    }

    /**
     * remove the offset on a date Time which has previously been offset by amount startHour and startminute
     * in @code{applyOffset}
     *
     * @param offSetDateTime
     * @return
     */
    public LocalDateTime removeOffset(LocalDateTime offSetDateTime) {
        return offSetDateTime.plusHours(startHour).plusMinutes(startMinute);
    }

    /**
     * Gets the local date time of the end of the day, right before 00:00.
     * @return
     */
    public LocalDateTime getEndOfTheDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(LocalTime.MAX);
    }

    /**
     * Gets the start of the day at 00:00.
     * @param localDateTime
     * @return
     */
    public LocalDateTime getStartOfTheDay(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atTime(0,0);
    }



}
