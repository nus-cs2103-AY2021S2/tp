package seedu.partyplanet.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import seedu.partyplanet.commons.core.index.Index;
import seedu.partyplanet.logic.commands.EEditCommand;
import seedu.partyplanet.logic.commands.EEditCommand.EditEventDescriptor;
import seedu.partyplanet.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EEditCommand object.
 */
public class EEditCommandParser implements Parser<EEditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EEditCommand
     * and returns an EEditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_REMARK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EEditCommand.MESSAGE_USAGE), pe);
        }

        EditEventDescriptor editEventDescriptor = new EditEventDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editEventDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editEventDescriptor.setDate(ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            editEventDescriptor.setRemark(ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get()));
        }

        if (!editEventDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EEditCommand.MESSAGE_NOT_EDITED);
        }

        return new EEditCommand(index, editEventDescriptor);
    }

}
