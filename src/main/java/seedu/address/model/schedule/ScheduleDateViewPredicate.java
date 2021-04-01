package seedu.address.model.schedule;

import java.util.function.Predicate;

import seedu.address.model.appointment.AppointmentDateTime;

/**
 * Tests that an {@code Appointment}'s {@code dateTime} matches the date given.
 * This primarily to support the Calendar's Filtering.
 */
public class ScheduleDateViewPredicate implements Predicate<Schedule> {
    private final AppointmentDateTime date;

    public ScheduleDateViewPredicate(AppointmentDateTime date) {
        this.date = date;
    }

    @Override
    public boolean test(Schedule schedule) {
        return schedule.getTimeFrom().equalsDateCheck(date.value)
                || schedule.getTimeTo().equalsDateCheck(date.value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ScheduleDateViewPredicate // instanceof handles nulls
                && date.equals(((ScheduleDateViewPredicate) other).date)); // state check
    }

    @Override
    public String toString() {
        return date.toDateString();
    }
}
