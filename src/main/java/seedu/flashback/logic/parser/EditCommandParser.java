package seedu.flashback.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.flashback.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.flashback.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.flashback.commons.core.index.Index;
import seedu.flashback.logic.commands.EditCommand;
import seedu.flashback.logic.commands.EditCommand.EditCardDescriptor;
import seedu.flashback.logic.parser.exceptions.ParseException;
import seedu.flashback.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_QUESTION,
                PREFIX_ANSWER, PREFIX_CATEGORY, PREFIX_PRIORITY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditCardDescriptor editCardDescriptor = new EditCardDescriptor();
        if (argMultimap.getValue(PREFIX_QUESTION).isPresent()) {
            editCardDescriptor.setQuestion(ParserUtil.parseQuestion(argMultimap.getValue(PREFIX_QUESTION).get()));
        }
        if (argMultimap.getValue(PREFIX_ANSWER).isPresent()) {
            editCardDescriptor.setAnswer(ParserUtil.parseAnswer(argMultimap.getValue(PREFIX_ANSWER).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editCardDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editCardDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editCardDescriptor::setTags);

        if (!editCardDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editCardDescriptor);
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
