package seedu.weeblingo.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.commons.util.AppUtil.checkArgument;

import seedu.weeblingo.commons.util.RegexUtil;

/**
 * Represents a Flashcard's answer in the answer book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAnswer(String)}
 */
public class Answer {

    public static final String MESSAGE_CONSTRAINTS = "An answer can only be an English word for now.";

    /*
     * The first character of the answer must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = RegexUtil.REGEX_ENG_WORD;
    public final String value;

    /**
     * Constructs an {@code answer}.
     *
     * @param answer A valid answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        value = answer;
    }

    /**
     * Returns true if a given string is a valid email.
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
