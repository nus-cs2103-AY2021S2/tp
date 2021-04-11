package seedu.flashback.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.commons.util.AppUtil.checkArgument;

/**
 * Represents a flash card's answer in FlashBack.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnswer(String)}
 */
public class Answer {


    public static final String MESSAGE_CONSTRAINTS =
            "Answer can take any values, and it should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public final String value;

    /**
     * Constructs a {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        value = answer;
    }

    /**
     * Returns true if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Answer // instanceof handles nulls
                && value.equals(((Answer) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
