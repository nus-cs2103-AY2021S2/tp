package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditResidenceDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_RESIDENCE_ADDRESS,
                        PREFIX_CLEAN_STATUS_TAG, PREFIX_TAG);

        Index index;
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        EditResidenceDescriptor editResidenceDescriptor = new EditResidenceDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editResidenceDescriptor.setResidenceName(
                    ParserUtil.parseResidenceName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_RESIDENCE_ADDRESS).isPresent()) {
            editResidenceDescriptor.setResidenceAddress(
                    ParserUtil.parseAddress(argMultimap.getValue(PREFIX_RESIDENCE_ADDRESS).get()));
        }

        if (argMultimap.getValue(PREFIX_CLEAN_STATUS_TAG).isPresent()) {
            editResidenceDescriptor.setCleanStatusTag(
                    ParserUtil.parseCleanStatusTag(argMultimap.getValue(PREFIX_CLEAN_STATUS_TAG).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editResidenceDescriptor::setTags);

        if (argMultimap.getValue(PREFIX_CLEAN_STATUS_TAG).isPresent()) {
            editResidenceDescriptor.setCleanStatusTag(
                    ParserUtil.parseCleanStatusTag(argMultimap.getValue(PREFIX_CLEAN_STATUS_TAG).get()));
        }

        if (!editResidenceDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editResidenceDescriptor);
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
