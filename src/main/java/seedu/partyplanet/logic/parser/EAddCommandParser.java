package seedu.partyplanet.logic.parser;

import static seedu.partyplanet.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.partyplanet.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.stream.Stream;

import seedu.partyplanet.logic.commands.EAddCommand;
import seedu.partyplanet.logic.parser.exceptions.ParseException;
import seedu.partyplanet.model.event.Event;
import seedu.partyplanet.model.event.EventDate;
import seedu.partyplanet.model.name.Name;
import seedu.partyplanet.model.remark.Remark;

/**
 * Parses input arguments and creates a new EAddCommand object.
 */
public class EAddCommandParser implements Parser<EAddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EAddCommand
     * and returns an EAddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DATE, PREFIX_REMARK);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EAddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        EventDate eventDate =
                ParserUtil.parseEventDate(argMultimap.getValue(PREFIX_DATE).orElse(EventDate.EMPTY_DATE_STRING));
        Remark remark =
                ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).orElse(Remark.EMPTY_REMARK_STRING));

        Event event = new Event(name, eventDate, remark);

        return new EAddCommand(event);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
