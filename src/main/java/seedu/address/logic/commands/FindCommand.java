package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Finds and lists all flashcards in FlashBack with a search criteria containing any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards with a search criteria "
            + "that contains any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters:  CRITERIA KEYWORD [MORE_KEYWORDS]...\n"
            + "CRITERIA: " + PREFIX_QUESTION + " for questions, " + PREFIX_CATEGORY + " for category, "
            + PREFIX_TAG + " for tags, or " + PREFIX_PRIORITY + " for priority.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_QUESTION + " equation";
}
