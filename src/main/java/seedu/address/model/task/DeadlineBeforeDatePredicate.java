package seedu.address.model.task;

import java.time.LocalDate;
import java.util.function.Predicate;

public class DeadlineBeforeDatePredicate implements Predicate<Task> {
    private final String date;

    public DeadlineBeforeDatePredicate(String date) {
        this.date = date;
    }

    @Override
    public boolean test(Task task) {
        return task.getDeadline().getDeadline().compareTo(LocalDate.parse(this.date)) < 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineBeforeDatePredicate// instanceof handles nulls
                && date.equals(((DeadlineBeforeDatePredicate) other).date)); // state check
    }
}
