package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;

import java.time.LocalDateTime;

import seedu.address.logic.commands.addcommand.AddEventCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;


/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser extends AddCommandParser implements Parser<AddEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns an AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GENERAL_EVENT, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_GENERAL_EVENT, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_GENERAL_EVENT).get());
        LocalDateTime date = ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DATE).get());

        //todo create a general date class
        GeneralEvent event = new GeneralEvent(description, date);
        return new AddEventCommand(event);
    }

}
