package seedu.address.ui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import seedu.address.model.schedule.Schedulable;
import seedu.address.model.schedule.SchedulableUtil;

import static java.util.Objects.requireNonNull;

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

    public static final int SECONDS_IN_A_MINUTE = 60;
    public static final int SECONDS_IN_AN_HOUR = 3600;
    public static final long SECONDS_IN_DAY = 86400;

    public static final double TIMETABLE_DISPLAY_SIZE = 5760;
    public static final int NUMBER_OF_COLUMNS = 7;

    private final int startHour;
    private final int startMinute;
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;


    /**
     * Takes in a startDate that it should be used as a reference point. Initializes the start and end times
     * represented bu this timetable policy.
     * @param startDate
     * @param startHour
     * @param startMinute
     */

    public TimetablePlacementPolicy(LocalDate startDate, int startHour, int startMinute) {
        requireNonNull(startDate);
        assert startHour <= 23 && startHour >= 0;
        assert startMinute >= 0 && startMinute <= 59;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.startDateTime = startDate.atTime(startHour, startMinute);
        this.endDateTime = startDateTime.plusDays(NUMBER_OF_COLUMNS);
    }

    /**
     * Default start hour and start minute initialised to 7 and 0 respectively
     * See {@link #TimetablePlacementPolicy(LocalDate,int,int)}
     *
     * @param startDate
     */

    public TimetablePlacementPolicy(LocalDate startDate) {
        this(startDate,7, 0);
    }


    /**
     * Tests if a Schedulable lies within the range of the timetable range. Note that to be considered within range,
     * it must overlap with the open interval (timetableStartDate, timetableEndDate).
     *
     * @param schedulable
     * @return
     */
    public boolean isWithinRange(Schedulable schedulable) {
        LocalDateTime startTimeOfSchedulable = schedulable.getStartLocalDateTime();
        LocalDateTime endTimeOfSchedulable = schedulable.getTerminateLocalDateTime();
        return !(endTimeOfSchedulable.compareTo(startDateTime) <= 0 || startTimeOfSchedulable.compareTo(endDateTime) >= 0);
    }

    /**
     * Gets the column to put a schedulable in.
     * The schedulable start date must lie within the timetable Range.
     * Note the schedulable must lie within a day from 7am - 7pm, else it will just slot into the
     * day corresponding to the schedulable's start date.
     * You can call the method {@link #breakIntoDayUnits(Schedulable)} first to get
     * the single day slots before calling this method on each schedule.
     *
     * @param schedulable
     * @return
     */

    public TimetableView.Column getColumnPlacement(Schedulable schedulable) {
        LocalDateTime dateTimeToSchedule = schedulable.getStartLocalDateTime();
        int daysBetween = (int) Duration.between(startDateTime, dateTimeToSchedule).toDays();
        switch (daysBetween) {
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
     * Gets the number of seconds so far in a day, starting from 00:00.
     *
     * @param localDateTime
     * @return
     */

    public static int getSecondsInDay(LocalDateTime localDateTime) {
        return localDateTime.getHour() * SECONDS_IN_AN_HOUR + localDateTime.getMinute() * SECONDS_IN_A_MINUTE + localDateTime.getSecond();
    }

    /**
     * Gets the y-coordinate position of a timetable slot to be placed within the column
     * with the coordinate of value 0 corresponding to the tip of the column. ( the y value
     * measures the vertical distance from the topleft corner of the slot to the top of the
     * timetable). Note the schedulabe should fit within one day inside the timetable, and
     * not overlap across multiple days. See {@link #breakIntoDayUnits(Schedulable)} on how
     * to split schedulable into days that fit in the timetable.
     *
     * @param schedulable
     * @return
     */

    public double getVerticalPosition(Schedulable schedulable) {
        LocalDateTime startingDateTime = schedulable.getStartLocalDateTime();
        int minutesSoFar = getSecondsInDay(applyOffset(startingDateTime));
        double ratio = (double) minutesSoFar / SECONDS_IN_DAY;
        return ratio * TIMETABLE_DISPLAY_SIZE;
    }

    /**
     * Returns the length in pixels of the timetable cell to represent the Schedulable.
     * We assume the schedulable must start and end on the same day in the timetable.
     *
     * @param schedulable
     * @return
     */
    public double getLengthOfSlot(Schedulable schedulable) {
        LocalDateTime offSetStartDate = applyOffset(schedulable.getStartLocalDateTime());
        LocalDateTime offSetEndDate = applyOffset(schedulable.getTerminateLocalDateTime());
        long startSecondsSoFar = getSecondsInDay(offSetStartDate);
        long endSecondsSoFar = getSecondsInDay(offSetEndDate);
        assert endSecondsSoFar >= startSecondsSoFar;
        double ratio = (double) (endSecondsSoFar - startSecondsSoFar) / SECONDS_IN_DAY;
        return TIMETABLE_DISPLAY_SIZE * ratio;

    }

    /**
     * Splits a schedulable that overlaps across several days into parts which lie within one day. A "day" is treated
     * as a timeframe from  time (startHour:startMinute) to the next day (startHour:startMinute). For example in the
     * case when startHour = 7, startMinute = 0, the day starts from 7 am to 7 am the next day.
     * If a Schedulable object overlaps across several days, for example a schedulable
     * that goes from 7 am to 5 pm the next day will be split into two schedulables, one from 7am to 6.599999 am, and
     * one from the next day 7am to 5pm. Then all the schedulables which lie outside outside the time range of this
     * timetable ( which spans 7 days), will be filtered out. The method retursn the resulting stream of broken down
     * schedulables.
     *
     * @param schedulable to split into schedulables that span across a day
     * @return the stream of schedulables split by day.
     */
    public Stream<Schedulable> breakIntoDayUnits(Schedulable schedulable) {
        Schedulable offSetSchedule = SchedulableUtil.applyNegativeOffset(schedulable, startHour, startMinute);
        List<Schedulable> splittedSchedulables = SchedulableUtil.splitSchedulableByDay(offSetSchedule);
        return splittedSchedulables.stream().map(s -> SchedulableUtil.applyPositiveOffset(s, startHour, startMinute)).filter(this::isWithinRange);
    }

    /**
     * apply negative offset by start hour and start minutes so each day period starts from 00:00  and ends at
     * LocalTime.max the next day.
     *
     * @param localDateTime
     * @return
     */
    public LocalDateTime applyOffset(LocalDateTime localDateTime) {
        return localDateTime.minusHours(startHour).minusMinutes(startMinute);
    }


}
