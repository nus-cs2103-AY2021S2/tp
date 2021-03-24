package seedu.address.model.task;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.function.Predicate;
import java.time.LocalDate;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

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
                || (other instanceof  DeadlineBeforeDatePredicate// instanceof handles nulls
                && date.equals(((DeadlineBeforeDatePredicate) other).date)); // state check
    }
}
