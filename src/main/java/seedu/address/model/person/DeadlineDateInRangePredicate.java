package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

/**
 * Tests that a {@code Task}'s {@code DeadlineDate} within the range of the given days/weeks.
 */
public class DeadlineDateInRangePredicate implements Predicate<Task> {
    private static final DateTimeFormatter dateDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static DeadlineDate maxDate = new DeadlineDate("31-12-2099"); // Latest date allowed
    private static DeadlineDate today = new DeadlineDate(LocalDate.now().format(dateDateFormatter));

    private final DeadlineDate endDate;

    /**
     * A predicate to check deadlinedate of a task
     * @param numberOfDays number of days ahead from today's date
     */
    public DeadlineDateInRangePredicate(long numberOfDays) {
        DeadlineDate dateSpecifiedByUser = new DeadlineDate(
                LocalDate.now().plusDays(numberOfDays).format(dateDateFormatter));
        this.endDate = minimumOf(dateSpecifiedByUser, maxDate);
    }

    /**
     * Compare 2 deadlineDate and returns the minimum
     * @param firstDeadlineDate
     * @param secondDeadlineDate
     * @return DeadlineDate with smaller date
     */

    private DeadlineDate minimumOf(DeadlineDate firstDeadlineDate, DeadlineDate secondDeadlineDate) {
        if (firstDeadlineDate.compareTo(secondDeadlineDate) < 0) {
            return firstDeadlineDate;
        } else {
            return secondDeadlineDate;
        }
    }

    @Override
    public boolean test(Task task) {
        return task.getDeadlineDate().compareTo(endDate) <= 0
                && task.getDeadlineDate().compareTo(today) >= 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineDateInRangePredicate // instanceof handles nulls
                && endDate.equals(((DeadlineDateInRangePredicate) other).endDate)); // task's deadline within range
    }
}
