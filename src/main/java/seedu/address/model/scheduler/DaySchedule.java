package seedu.address.model.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.util.Objects.requireNonNull;

/**
 * Represents a day in a timetable. Starting from 0000, ending at next day 0000, subdivided into intervals.
 */

public class DaySchedule {
    public static final int NUMBER_OF_PERIODS = 48;
    public static final String MESSAGE_BOOKING_FAILURE = "The timeslot %s is occupied. Please try another date.";
    private final DayOfWeek dayOfWeek;
    private final LocalDate localDate;
    private final HalfHourTimeSlot[] periodArray = new HalfHourTimeSlot[NUMBER_OF_PERIODS];

    /**
     * Creates the day schedule of a specified localDate and populates it with half hour periods
     * @param localDate the day of the week represented by this schedule.
     */

    public DaySchedule(LocalDate localDate) {
        requireNonNull(localDate);
        this.localDate = localDate;
        this.dayOfWeek = localDate.getDayOfWeek();
        // initialize the timeslots
        for (int i = 0; i < NUMBER_OF_PERIODS; i++) {
            LocalTime startTime = LocalTime.MIDNIGHT.plusMinutes(i * 30);
            periodArray[i] = new HalfHourTimeSlot(startTime);
        }
    }

    /**
     * returns the i th timeslot.
     * @param period
     * @return
     */
    public HalfHourTimeSlot getPeriod(int period) {
        return periodArray[period];
    }

    /**
     * gets the index of the half - hour period containing the given time. A period can be treated as the interval
     * [start,start + 30), so it will return the period with whose [start, start + 30) contains the given time.
     * @param localTime
     * @return
     */

    public int getPeriodIndex(LocalTime localTime) {
        int period = localTime.getHour() * 2 + (int) Math.floor(localTime.getMinute() / 30);
        return period;
    }

    /**
     * book a timeslot at a specific start time. Note the startTime must be valid.
     * @return
     */
    public void bookSlot(LocalTime localTime) throws BookingException {
        int period = getPeriodIndex(localTime);
        //mark as booked
        if (periodArray[period].isBooked()) {
            throw new BookingException(MESSAGE_BOOKING_FAILURE);
        }
        periodArray[period].setBooked(true);
    }

    /**
     * Frees the time slot containing a specified time.
     * @param localTime
     */

    public void freeSlot(LocalTime localTime) {
        int period = getPeriodIndex(localTime);
        //mark the period as free
        periodArray[period].setBooked(false);
    }

    /**
     * Books all slots at index from start to end, inclusive.
     * @param start
     * @param end
     */

    public void bookByIndexRange(int start, int end) throws BookingException {
        for (int i = start; i <= end; i++) {
            if (periodArray[i].isBooked()) {
                throw new BookingException(String.format(MESSAGE_BOOKING_FAILURE, periodArray[i]));
            }
        }
        for (int i = start; i <= end; i++) {
            periodArray[i].setBooked(true);
        }
    }

    /**
     * Given an interval containing start time and end time , book all slots containing that interval.
     */
    public void bookTimeRange(LocalTime startTime, LocalTime endTime) throws BookingException {
        assert endTime.compareTo(startTime) == 1;
        int start = getPeriodIndex(startTime);
        int end = getPeriodIndex(endTime);
        // resolve the case when the endpoint coincide with the end of a slot
        if (endTime.getMinute() == 30 || endTime.getMinute() == 0) {
            end -= 1;
        }
        bookByIndexRange(start, end);
    }

    /**
     * Given a time interval [startTime endTime], free all space containing that time interval.
     * @param startTime
     * @param endTime
     */

    public void freeTimeRange(LocalTime startTime, LocalTime endTime) {
        assert endTime.compareTo(startTime) == 1;
        int start = getPeriodIndex(startTime);
        int end = getPeriodIndex(endTime);
        // resolve the case when the endpoint coincide with the end of a slot
        if (endTime.getMinute() == 30 || endTime.getMinute() == 0) {
            end -= 1;
        }
        for (int i = start; i <= end; i++) {
            periodArray[i].setBooked(false);
        }
    }


    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < NUMBER_OF_PERIODS; i++) {
            returnString += periodArray[i].toString() + ", \n";
        }
        return localDate + "," + dayOfWeek + " [" + returnString + "]";
    }


    public LocalDate getLocalDate() {
        return this.localDate;
    }
    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }
}
