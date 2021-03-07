package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Flashcard's Question.
 * Guarantees: immutable; is valid as declared in {@link #isValidQuestion(String)}
 */
public class Question {

    public static final String MESSAGE_CONSTRAINTS = "";
    public static final String VALIDATION_REGEX = generatePublicFields();

    // alphanumeric and special characters
    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]"; // alphanumeric characters except underscore
    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*"; // alphanumeric, period and hyphen
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";

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

    /**
     * Returns a string regex for emails.
     *
     * @return String representing regex for emails.
     */
    public static String generatePublicFields() {
        return LOCAL_PART_REGEX + "@" + DOMAIN_FIRST_CHARACTER_REGEX
                + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;
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
