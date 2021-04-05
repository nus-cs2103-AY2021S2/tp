package seedu.address.model.person;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import seedu.address.commons.util.DateUtil;
import seedu.address.commons.util.TimeUtil;

public class SpecialDate extends Event {

    public static final String DESCRIPTION_VALIDATION_REGEX = Event.DESCRIPTION_VALIDATION_REGEX;
    public static final String DESCRIPTION_MESSAGE_CONSTRAINTS = Event.DESCRIPTION_MESSAGE_CONSTRAINTS;

    public SpecialDate(LocalDate date, String description) {
        super(date, description);
    }

    public static boolean isValidSpecialDate(LocalDate date, String description) {
        return !DateUtil.afterToday(date)
                && description.matches(DESCRIPTION_VALIDATION_REGEX);
    }

    @Override
    public String toUi() {
        return String.format("%s %s\n", DateUtil.toUi(date), description);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SpecialDate)) {
            return false;
        }

        SpecialDate otherSpecialDate = (SpecialDate)other;
        return otherSpecialDate.getDate().equals(getDate())
                && otherSpecialDate.getDescription().equals(getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(date)
                .append("; ")
                .append(description);
        return builder.toString();
    }
    
}
