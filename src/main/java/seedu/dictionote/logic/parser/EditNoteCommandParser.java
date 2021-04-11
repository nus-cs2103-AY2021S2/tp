package seedu.dictionote.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.dictionote.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.dictionote.commons.core.index.Index;
import seedu.dictionote.logic.commands.EditNoteCommand;
import seedu.dictionote.logic.commands.EditNoteCommand.EditNoteDescriptor;
import seedu.dictionote.logic.parser.exceptions.ParseException;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditNoteCommandParser implements Parser<EditNoteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditNoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CONTENT, PREFIX_TAG);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditNoteCommand.MESSAGE_USAGE),
                    pe);
        }

        EditNoteDescriptor editNoteDescriptor = new EditNoteDescriptor();

        if (!argMultimap.getValue(PREFIX_CONTENT).isPresent() && argMultimap.getAllValues(PREFIX_TAG).size() == 0) {
            throw new ParseException(EditNoteCommand.MESSAGE_NOT_EDITED);
        }

        if (argMultimap.getValue(PREFIX_CONTENT).isPresent()) {
            String content = argMultimap.getValue(PREFIX_CONTENT).get();
            if (content.equals("")) {
                throw new ParseException(EditNoteCommand.MESSAGE_EMPTY_NOTE);
            }
            editNoteDescriptor.setNote(ParserUtil.parseNote(content));
        } else {
            editNoteDescriptor.setNote(new Note(""));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editNoteDescriptor::setTags);
        return new EditNoteCommand(index, editNoteDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
