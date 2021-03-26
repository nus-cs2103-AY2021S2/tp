package seedu.address.logic.parser.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand.EditIssueDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditIssueCommand object
 */
public class EditIssueCommandParser implements Parser<EditIssueCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditIssueCommand
     * and returns an EditIssueCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditIssueCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ROOM_NUMBER, PREFIX_DESCRIPTION,
                PREFIX_TIMESTAMP, PREFIX_STATUS, PREFIX_CATEGORY, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE), pe);
        }

        EditIssueDescriptor editIssueDescriptor = new EditIssueDescriptor();
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            editIssueDescriptor
                    .setRoomNumber(ParserUtil.parseIssueRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editIssueDescriptor
                    .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TIMESTAMP).isPresent()) {
            editIssueDescriptor.setTimestamp(ParserUtil.parseTimestamp(argMultimap.getValue(PREFIX_TIMESTAMP).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editIssueDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editIssueDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editIssueDescriptor::setTags);

        if (!editIssueDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditIssueCommand.MESSAGE_NOT_EDITED);
        }

        return new EditIssueCommand(index, editIssueDescriptor);
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
