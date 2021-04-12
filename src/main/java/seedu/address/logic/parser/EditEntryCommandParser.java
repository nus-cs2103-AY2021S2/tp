package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_EDITED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditEntryCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.entry.TemporaryEntry;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditEntryCommand object.
 */
public class EditEntryCommandParser implements Parser<EditEntryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditEntryCommand
     * and returns an EditEntryCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditEntryCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer
                .tokenize(args, PREFIX_NAME, PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_TAG);

        Index targetIndex;

        try {
            targetIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String
                    .format(MESSAGE_INVALID_COMMAND_FORMAT, EditEntryCommand.MESSAGE_USAGE), pe);
        }

        TemporaryEntry tempEntry = new TemporaryEntry();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            tempEntry.setEntryName(ParserUtil.parseEntryName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            tempEntry.setEntryStartDate(ParserUtil
                    .parseEntryDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            tempEntry.setEntryEndDate(ParserUtil
                    .parseEntryDate(argMultimap.getValue(PREFIX_END_DATE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(tempEntry::setTags);

        if (!tempEntry.isUpdated()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        if (args.trim().length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEntryCommand.MESSAGE_USAGE));
        }

        return new EditEntryCommand(targetIndex, tempEntry);
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
