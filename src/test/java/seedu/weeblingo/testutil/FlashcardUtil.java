package seedu.weeblingo.testutil;

import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.weeblingo.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.weeblingo.model.flashcard.Flashcard;

/**
 * A utility class for Flashcard.
 */
public class FlashcardUtil {

    /**
     * Returns the part of command string for the given {@code flashcard}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + flashcard.getQuestion().value + " ");
        sb.append(PREFIX_ANSWER + flashcard.getAnswer().value + " ");
        flashcard.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

}
