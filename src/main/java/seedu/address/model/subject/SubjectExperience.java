package seedu.address.model.subject;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Tutor's years of experience in a subject in Tutor Tracker.
 * Guarantees: immutable; is valid as declared in {@link #isValidExperience(String)}
 */
public class SubjectExperience {
    public static final String MESSAGE_CONSTRAINTS =
            "Subject experience should only contain numbers, and it should be at least 1 digit long "
                    + "and at most 2 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,2}";
    public final Integer experience;

    /**
     * Constructs a {@code SubjectExperience}.
     *
     * @param experience A valid subject experience.
     */
    public SubjectExperience(String experience) {
        requireNonNull(experience);
        checkArgument(isValidExperience(experience), MESSAGE_CONSTRAINTS);
        this.experience = Integer.parseInt(experience);
    }

    /**
     * Returns true if a given string is a valid subject experience.
     */
    public static boolean isValidExperience(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return experience.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubjectExperience // instanceof handles nulls
                && experience.equals(((SubjectExperience) other).experience)); // state check
    }

    @Override
    public int hashCode() {
        return experience.hashCode();
    }
}
