package seedu.weeblingo.model.flashcard;

import static java.util.Objects.requireNonNull;
import static seedu.weeblingo.commons.util.AppUtil.checkArgument;

import seedu.weeblingo.commons.util.RegexUtil;

/**
 * Represents a Flashcard's Question.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuestion(String)}
 */
public class Question {

    public static final String MESSAGE_CONSTRAINTS = "A question can only be a japanese word for now.";
    public static final String VALIDATION_REGEX = RegexUtil.REGEX_JAP_WORD;


    public final String value;

    /**
     * Constructs an {@code Question}.
     *
     * @param question A valid question.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        value = question;
    }

    /**
     * Returns if a given string is a valid question.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Question // instanceof handles nulls
                && value.equals(((Question) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
