package seedu.address.model.scheduler;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * A weekly timetable class, starting from Monday to Sunday, containing half hour slots that can be booked.
 * Internals should not be modified except for booking purposes.
 *
 */

public class Timetable {
    public static final int NUMBER_OF_DAYS = 7;

    private final DaySchedule[] weeklySchedule = new DaySchedule[NUMBER_OF_DAYS];
    private final LocalDate startOfTheWeek;

    /**
     * Constructor takes in the startDate of the timetable.
     * It is required that the startDate must start on a Monday.
     */

    public Timetable(LocalDate startOfTheWeek) {
        assert startOfTheWeek.getDayOfWeek().equals(DayOfWeek.MONDAY);
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
     * Return a Day Schedule Object corresponding to the dayOfTheWeek.
     * @param dayOfWeek
     * @return
     */

    public DaySchedule getDaySchedule(DayOfWeek dayOfWeek) {
        dayOfWeek.getValue();
        return weeklySchedule[dayOfWeek.getValue()];
    }


}

