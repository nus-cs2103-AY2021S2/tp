package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.dictionote.commons.core.Messages;
import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;
import seedu.dictionote.model.note.NoteContainsKeywordsPredicate;
import seedu.dictionote.model.note.TagNoteContainKeywordsPredicate;

/**
 * Finds and lists all note in the note list whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindNoteCommand extends Command {

    public static final String COMMAND_WORD = "findnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all note whose content contain any of, "
            + "and tags contain all of, the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: "
            + "[" + PREFIX_CONTENT + "CONTENT_KEYWORDS]... "
            + "[" + PREFIX_TAG + "TAG_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CONTENT + "CS2103 "
            + PREFIX_CONTENT + "Midterms "
            + PREFIX_TAG + "important ";

    private final NoteContainsKeywordsPredicate notePredicate;
    private final TagNoteContainKeywordsPredicate tagsPredicate;

    /**
     * Creates a new {@code FindContactCommand} with two predicates (i.e., conditions):
     * one applies to the contacts' names, and the other applies to their tags.
     * @param notePredicate The predicate to be evaluated against the contacts' names.
     * @param tagsPredicate The predicate to be evaluated against the contacts' tags.
     */
    public FindNoteCommand(NoteContainsKeywordsPredicate notePredicate,
                              TagNoteContainKeywordsPredicate tagsPredicate) {
        this.notePredicate = notePredicate;
        this.tagsPredicate = tagsPredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        // satisfy both name- and tag-matching.
        model.updateFilteredNoteList(notePredicate.and(tagsPredicate));
        return new CommandResult(
                String.format(Messages.MESSAGE_NOTES_LISTED_OVERVIEW, model.getFilteredNoteList().size()),
                UiAction.OPEN, UiActionOption.NOTE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindNoteCommand // instanceof handles nulls
                && notePredicate.equals(((FindNoteCommand) other).notePredicate)
                && tagsPredicate.equals(((FindNoteCommand) other).tagsPredicate)); // state check
    }
}
