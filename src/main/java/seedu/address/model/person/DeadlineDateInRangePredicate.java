package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code DeadlineDate} in range of the given deadlines.
 */
public class DeadlineDateInRangePredicate implements Predicate<Task> {
    private static final DateTimeFormatter dateDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static DeadlineDate minDate = new DeadlineDate("01-01-2020"); // Earliest date allowed
    private static DeadlineDate maxDate = new DeadlineDate("12-12-2099"); // Latest date allowed
    private static String nextWeekStart = LocalDate.now().plusDays(7).format(dateDateFormatter); // next week
    private static String nextWeekEnd = LocalDate.now().plusDays(13).format(dateDateFormatter); // next 2 week

    private final DeadlineDate startDate;
    private final DeadlineDate endDate;

    /**
     * A predicate to check deadlinedate of a task
     * @param startDate start date specified by the user if any
     * @param endDate end date specified by the user if any
     */
    public DeadlineDateInRangePredicate(Optional<DeadlineDate> startDate,
                                                       Optional<DeadlineDate> endDate) {
        if (startDate.isEmpty() && endDate.isEmpty()) {
            this.startDate = new DeadlineDate(nextWeekStart);
            this.endDate = new DeadlineDate(nextWeekEnd);
        } else if (startDate.isEmpty()) {
            this.startDate = minDate;
            this.endDate = endDate.get();
        } else if (endDate.isEmpty()) {
            this.startDate = startDate.get();
            this.endDate = maxDate;
        } else {
            this.startDate = startDate.get();
            this.endDate = endDate.get();
        }
    }

    @Override
    public boolean test(Task task) {
        return task.getDeadlineDate().compareTo(startDate) >= 0 && task.getDeadlineDate().compareTo(endDate) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineDateInRangePredicate // instanceof handles nulls
                && startDate.equals(((DeadlineDateInRangePredicate) other).startDate)
                && endDate.equals(((DeadlineDateInRangePredicate) other).endDate)); // startDate
    }
}
