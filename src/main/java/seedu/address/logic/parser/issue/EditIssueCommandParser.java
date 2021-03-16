package seedu.address.logic.parser.issue;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand.EditIssueDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

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
                PREFIX_TIMESTAMP,
                PREFIX_STATUS, PREFIX_CATEGORY);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditIssueCommand.MESSAGE_USAGE), pe);
        }

        EditIssueDescriptor editPersonDescriptor = new EditIssueDescriptor();
        if (argMultimap.getValue(PREFIX_ROOM_NUMBER).isPresent()) {
            editPersonDescriptor
                    .setRoomNumber(ParserUtil.parseIssueRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editPersonDescriptor
                    .setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_TIMESTAMP).isPresent()) {
            editPersonDescriptor.setTimestamp(ParserUtil.parseTimestamp(argMultimap.getValue(PREFIX_TIMESTAMP).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editPersonDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editPersonDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditIssueCommand.MESSAGE_NOT_EDITED);
        }

        return new EditIssueCommand(index, editPersonDescriptor);
    }

}
