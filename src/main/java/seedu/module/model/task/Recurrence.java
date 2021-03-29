package seedu.module.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.module.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Recurrence {
    public static final String MESSAGE_CONSTRAINTS = "recurrence can only be daily, weekly or monthly.";
    public final String value;
    private final RecurrenceType recurrenceType;

    /**
     * Constructor of Recurrence object.
     *
     * @param recurrenceString String used to construct Recurrence Object.
     * @throws IllegalArgumentException when checking if {@code recurrenceString} is valid.
     */
    public Recurrence(String recurrenceString) {
        checkArgument(isValidRecurrence(recurrenceString), MESSAGE_CONSTRAINTS);

        recurrenceType = RecurrenceType.valueOf(recurrenceString);
        value = recurrenceString.toLowerCase();
    }

    /**
     * Checks if recurrence is daily, weekly or monthly.
     *
     * @param recurrenceString is the recurrence in type String.
     * @return true if recurrence is valid. false otherwise.
     */
    public static boolean isValidRecurrence(String recurrenceString) {
        requireNonNull(recurrenceString);

        try {
            String recurrenceStringCopy = recurrenceString.toLowerCase().trim();
            RecurrenceType taskRecurrence = RecurrenceType.valueOf(recurrenceStringCopy);
            boolean isValidRecurrence = false;

            RecurrenceType[] allValidRecurrences = RecurrenceType.values();
            for (RecurrenceType validRecurrenceType : allValidRecurrences) {
                if (taskRecurrence.equals(validRecurrenceType)) {
                    isValidRecurrence = true;
                    break;
                }
            }
            return isValidRecurrence;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public RecurrenceType getRecurrenceType() {
        return recurrenceType;
    }

    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Recurrence) // instanceof handles nulls
                && recurrenceType.equals(((Recurrence) other).recurrenceType); // enums can use == and .equals()
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, recurrenceType);
    }
}
