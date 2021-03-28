package seedu.address.model.scheduler;

import java.time.LocalDate;

/**
 * Internally manages booking of slots in a timetable.
 * A day split into time intervals that can be "booked" by a scheduler object.
 */
public class TimetableScheduler {

    private final Timetable timetable;
    private final LocalDate startOfTimetableDate;

    /**
     * creates the timetable schedule given a startOfTimetableDate
     * @param startOfTimetableDate
     */

    public TimetableScheduler(LocalDate startOfTimetableDate) {
        this.startOfTimetableDate = startOfTimetableDate;
        this.timetable = new Timetable(startOfTimetableDate);
    }

    public void addToSchedule(Schedulable schedulable) {

    }

    public void freeSchedule(Schedulable schedulable) {

    }
}
