package seedu.flashback.testutil;

import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.flashback.logic.commands.AddCommand;
import seedu.flashback.logic.commands.EditCommand.EditCardDescriptor;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.model.tag.Tag;

/**
 * A utility class for Flashcard.
 */
public class FlashcardUtil {

    /**
     * Returns an add command string for adding the {@code flashcard}.
     */
    public static String getAddCommand(Flashcard flashcard) {
        return AddCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns the part of command string for the given {@code flashcard}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + flashcard.getQuestion().fullQuestion + " ");
        sb.append(PREFIX_ANSWER + flashcard.getAnswer().value + " ");
        sb.append(PREFIX_CATEGORY + flashcard.getCategory().value + " ");
        sb.append(PREFIX_PRIORITY + flashcard.getPriority().value + " ");
        flashcard.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFlashcardDescriptor}'s details.
     */
    public static String getEditFlashcardDescriptorDetails(EditCardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getQuestion().ifPresent(name -> sb.append(PREFIX_QUESTION).append(name.fullQuestion).append(" "));
        descriptor.getAnswer().ifPresent(phone -> sb.append(PREFIX_ANSWER).append(phone.value).append(" "));
        descriptor.getCategory().ifPresent(email -> sb.append(PREFIX_CATEGORY).append(email.value).append(" "));
        descriptor.getPriority().ifPresent(address -> sb.append(PREFIX_PRIORITY).append(address.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
