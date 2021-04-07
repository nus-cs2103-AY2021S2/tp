package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTracker;

/**
 * A utility class containing a list of {@code Schedule} objects to be used in tests.
 */
public class TypicalSchedules {

    public static final Schedule MATHS_HOMEWORK_SCHEDULE = new ScheduleBuilder()
            .withTitle("Maths Homework")
            .withTimeFrom("2021-05-24 10:00AM").withTimeTo("2021-05-24 12:00PM")
            .withDescription("Chapter 5 Page 841").build();
    public static final Schedule SCIENCE_HOMEWORK_SCHEDULE = new ScheduleBuilder()
            .withTitle("Science Homework")
            .withTimeFrom("2021-05-27 12:00PM").withTimeTo("2021-05-27 2:00PM")
            .withDescription("Chapter 3 Page 21").build();

    public static final Schedule PAST_SCHEDULE_1 = new ScheduleBuilder()
            .withTitle("Science Homework")
            .withTimeFrom("2021-01-27 12:00PM").withTimeTo("2021-01-27 2:00PM")
            .withDescription("Chapter 3 Page 21").build();
    public static final Schedule PAST_SCHEDULE_2 = new ScheduleBuilder()
            .withTitle("Maths Homework")
            .withTimeFrom("2021-01-24 10:00AM").withTimeTo("2021-01-24 12:00PM")
            .withDescription("Chapter 5 Page 841").build();

    private TypicalSchedules() {
    } // prevents instantiation

    /**
     * Returns an {@code ScheduleTracker} with all the typical schedules.
     */
    public static ScheduleTracker getTypicalScheduleTracker() {
        ScheduleTracker st = new ScheduleTracker();
        for (Schedule schedule : getTypicalSchedules()) {
            st.addSchedule(schedule);
        }
        return st;
    }

    public static List<Schedule> getTypicalSchedules() {
        return new ArrayList<>(Arrays.asList(MATHS_HOMEWORK_SCHEDULE, SCIENCE_HOMEWORK_SCHEDULE));
    }

    public static List<Schedule> getTypicalPastSchedules() {
        return new ArrayList<>(Arrays.asList(PAST_SCHEDULE_1, PAST_SCHEDULE_2));
    }
}
