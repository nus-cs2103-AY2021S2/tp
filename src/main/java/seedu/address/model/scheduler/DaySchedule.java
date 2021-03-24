package seedu.address.model.scheduler;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static java.util.Objects.requireNonNull;

/**
 * Represents a day in a timetable. Starting from 0000, ending at next day 0000, subdivided into intervals.
 */

public class DaySchedule {
    public static final int NUMBER_OF_PERIODS = 48;
    private final DayOfWeek dayOfWeek;
    private final HalfHourTimeSlot[] periodArray = new HalfHourTimeSlot[NUMBER_OF_PERIODS];

    /**
     * Creates the day schedule of a specified dayOfWeek and populates it with half hour periods
     * @param dayOfWeek the day of the week represented by this schedule.
     */

    public DaySchedule(DayOfWeek dayOfWeek) {
        requireNonNull(dayOfWeek);
        this.dayOfWeek = dayOfWeek;
        // initialize the timeslots
        for (int i = 0; i < NUMBER_OF_PERIODS; i++) {
            LocalTime startTime = LocalTime.MIDNIGHT.plusMinutes(i * 30);
            periodArray[i] = new HalfHourTimeSlot(startTime);
        }
    }

    /**
     * returns the i th period.
     * @param period
     * @return
     */
    public HalfHourTimeSlot getPeriod(int period) {
        return periodArray[period];
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 0; i < NUMBER_OF_PERIODS; i++) {
            returnString += periodArray[i].toString() + ", \n";
        }
        return dayOfWeek + " [" + returnString + "]";
    }

    public static void main(String args[]) {
        System.out.println(new DaySchedule(DayOfWeek.MONDAY));
    }

}
