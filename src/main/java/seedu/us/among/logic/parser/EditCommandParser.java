package seedu.us.among.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.us.among.commons.core.index.Index;
import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.logic.commands.EditCommand.EditEndpointDescriptor;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditCommand and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_METHOD,
                PREFIX_ADDRESS,
                PREFIX_DATA,
                PREFIX_HEADER,
                PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditEndpointDescriptor editEndpointDescriptor = new EditEndpointDescriptor();
        if (argMultimap.getValue(PREFIX_METHOD).isPresent()) {
            editEndpointDescriptor.setMethod(ParserUtil.parseMethod(argMultimap.getValue(PREFIX_METHOD).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editEndpointDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_DATA).isPresent()) {
            editEndpointDescriptor.setData(ParserUtil.parseData(argMultimap.getValue(PREFIX_DATA).get()));
        }

        parseHeadersForEdit(argMultimap.getAllValues(PREFIX_HEADER)).ifPresent(editEndpointDescriptor::setHeaders);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editEndpointDescriptor::setTags);

        if (!editEndpointDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editEndpointDescriptor);
    }

    /**
     * Parses {@code Collection<String> headers} into a {@code Set<Header>} if
     * {@code headers} is non-empty. If {@code headers} contain only one element which is
     * an empty string, it will be parsed into a {@code Set<Header>} containing zero
     * tags.
     */
    private Optional<Set<Header>> parseHeadersForEdit(Collection<String> headers) throws ParseException {
        assert headers != null;

        if (headers.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> headersSet = headers.size() == 1 && headers.contains("") ? Collections.emptySet() : headers;
        return Optional.of(ParserUtil.parseHeaders(headersSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if
     * {@code tags} is non-empty. If {@code tags} contain only one element which is
     * an empty string, it will be parsed into a {@code Set<Tag>} containing zero
     * tags.
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
