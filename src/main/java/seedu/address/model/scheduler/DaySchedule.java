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
     * gets the index of the period starting at a specified time. Note localTime must be a valid start time.
     * @param localTime
     * @return
     */

    public int getPeriodIndex(LocalTime localTime) {
        // it must be a valid startTime of a given bookable half hour slot.
        assert localTime.getMinute() == 0 || localTime.getMinute() == 30;
        int period = localTime.getHour() * 2 + localTime.getMinute() / 30;
        return period;
    }

    /**
     * book a timeslot at a specific start time. Note the startTime must be valid.
     * @return
     */
    public void bookSlot(LocalTime localTime) {
        int period = getPeriodIndex(localTime);
        //mark as booked
        periodArray[period].setBooked(true);
    }

    /**
     * Frees the time slot with a specified start time.
     * @param localTime
     */

    public void freeSlot(LocalTime localTime) {
        int period = getPeriodIndex(localTime);
        //mark the period as free
        periodArray[period].setBooked(false);
    }

    /**
     * Given a time valid interval with startTime and endTime being multiple of 30, books all the slots in that time
     * range.
     */
    public void bookTimeRange() {

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
