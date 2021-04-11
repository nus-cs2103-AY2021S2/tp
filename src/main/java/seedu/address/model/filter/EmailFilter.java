package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.tutor.Email;

public class EmailFilter implements Predicate<Email> {
    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.\\-@";
    public static final String MESSAGE_CONSTRAINTS =
            "Email filters should only contain alphanumeric characters and these special characters, "
            + "excluding the parentheses, (" + SPECIAL_CHARACTERS + "), and it should not be blank.";
    public static final String VALIDATION_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";

    public final String emailFilter;

    /**
     * Constructs a {@code EmailFilter}.
     *
     * @param emailFilter A valid email filter.
     */
    public EmailFilter(String emailFilter) {
        requireNonNull(emailFilter);
        checkArgument(isValidEmailFilter(emailFilter), MESSAGE_CONSTRAINTS);
        this.emailFilter = emailFilter.toLowerCase();
    }

    /**
     * Returns true if a given string is a valid email filter.
     */
    public static boolean isValidEmailFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Email: " + emailFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailFilter // instanceof handles nulls
                && emailFilter.equals(((EmailFilter) other).emailFilter)); // state check
    }

    @Override
    public int hashCode() {
        return emailFilter.hashCode();
    }

    @Override
    public boolean test(Email email) {
        if (email == null) {
            return false;
        }

        return email.value.toLowerCase().contains(emailFilter);
    }
}
