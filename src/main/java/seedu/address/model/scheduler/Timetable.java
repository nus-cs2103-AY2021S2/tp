package seedu.address.model.scheduler;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;

/**
 * A weekly timetable class, starting from Monday to Sunday, containing half hour slots that can be booked.
 * Internals should not be modified except for booking purposes. The startOfTheWeek date is the date of the first day
 * of the week.
 *
 */

public class Timetable {
    public static final String MESSAGE_DAY_NOT_WITHIN_TIMETABLE = "the date is not within this week's timetable.";
    public static final int NUMBER_OF_DAYS = 7;

    private final DaySchedule[] weeklySchedule = new DaySchedule[NUMBER_OF_DAYS];
    private final LocalDate startOfTheWeek;

    /**
     * Constructor takes in the startDate of the timetable.
     * It is required that the startDate must start on a Monday.
     */

    public Timetable(LocalDate startOfTheWeek) {
        this.startOfTheWeek = startOfTheWeek;
        for ( int i = 0; i < NUMBER_OF_DAYS; i++) {
            LocalDate currentDay = startOfTheWeek.plusDays(i);
            weeklySchedule[i] = new DaySchedule(currentDay);
        }
    }

    @Override

    public String toString() {
        String returnString = "";
        for (int i = 0; i < NUMBER_OF_DAYS; i++ ) {
            returnString += weeklySchedule[i] + "\n";
        }
        return returnString;
    }

    /**
     * get the Day schedule corresponding to a localDate;
     * @param localDate
     * @return
     * @throws TimetableAccessException if the date is not within the timetable.
     */

    private DaySchedule getDaySchedule(LocalDate localDate) throws TimetableAccessException {
        if (!isDateWithinTimetable(localDate)) {
            throw new TimetableAccessException(MESSAGE_DAY_NOT_WITHIN_TIMETABLE);
        }
        int index = (int) DAYS.between(startOfTheWeek, localDate);
        return weeklySchedule[index];
    }


    /**
     * checks if a given date is part of the timetable or not.
     * @param localDate date within the timetable
     * @return
     */
    public boolean isDateWithinTimetable(LocalDate localDate) {
        requireNonNull(localDate);
        return !(localDate.isBefore(startOfTheWeek) || localDate.isAfter(startOfTheWeek.plusDays(NUMBER_OF_DAYS)));
    }

    /**
     * Books a slot in the timetable at a specified localDate, with interval range starTime and endTime;
     * @param localDate
     * @param startTime
     * @param endTime
     */
    public void bookTimeRange(LocalDate localDate, LocalTime startTime, LocalTime endTime)
        throws BookingException, TimetableAccessException {
        getDaySchedule(localDate).bookTimeRange(startTime, endTime);
    }

    /**
     * Frees the slots containing the time range in the timetable at a specified location, with interval range
     * startTime and endTime.
     */

    public void freeTimeRange(LocalDate localDate, LocalTime startTime, LocalTime endTime) throws TimetableAccessException {
        getDaySchedule(localDate).freeTimeRange(startTime, endTime);
    }

    public static void main(String args[]) {
        Timetable t = new Timetable(LocalDate.now());
        try {
            t.bookTimeRange(LocalDate.now().plusDays(5),
                    LocalTime.of(16, 0), LocalTime.of(18, 30));
        } catch(Exception e) {
            System.out.println("NO");
        }
        System.out.println(t);
    }

}

