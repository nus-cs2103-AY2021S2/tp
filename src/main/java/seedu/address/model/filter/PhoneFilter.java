package seedu.address.model.filter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.function.Predicate;

import seedu.address.model.tutor.Phone;

public class PhoneFilter implements Predicate<Phone> {
    public static final String MESSAGE_CONSTRAINTS =
            "Phone filters should only contain numbers, and it should be at least 1 digit long";

    public static final String VALIDATION_REGEX = "\\d{1,}";

    public final String phoneFilter;

    /**
     * Constructs a {@code PhoneFilter}.
     *
     * @param phoneFilter A valid phone filter.
     */
    public PhoneFilter(String phoneFilter) {
        requireNonNull(phoneFilter);
        checkArgument(isValidPhoneFilter(phoneFilter), MESSAGE_CONSTRAINTS);
        this.phoneFilter = phoneFilter;
    }

    /**
     * Returns true if a given string is a valid phone filter.
     */
    public static boolean isValidPhoneFilter(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Phone: " + phoneFilter;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneFilter // instanceof handles nulls
                && phoneFilter.equals(((PhoneFilter) other).phoneFilter)); // state check
    }

    @Override
    public int hashCode() {
        return phoneFilter.hashCode();
    }

    @Override
    public boolean test(Phone phone) {
        if (phone == null) {
            return false;
        }

        return phone.value.contains(phoneFilter);
    }
}
