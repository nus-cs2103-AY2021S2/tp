package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.util.Optional;

import seedu.address.logic.commands.ShowCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DeadlineDate;
import seedu.address.model.person.DeadlineDateInRangePredicate;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class ShowCommandParser implements Parser<ShowCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ShowCommand
     * and returns a ShowCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ShowCommand parse(String args) throws ParseException {
        Optional<DeadlineDate> startDate = Optional.empty();
        Optional<DeadlineDate> endDate = Optional.empty();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATE, PREFIX_END_DATE);
        if (argMultimap.getValue(PREFIX_START_DATE).isPresent()) {
            if (!DeadlineDate.isValidDeadlineDate(argMultimap.getValue(PREFIX_START_DATE).get())) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
            }
            startDate = Optional.of(new DeadlineDate(argMultimap.getValue(PREFIX_START_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_END_DATE).isPresent()) {
            if (!DeadlineDate.isValidDeadlineDate(argMultimap.getValue(PREFIX_END_DATE).get())) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShowCommand.MESSAGE_USAGE));
            }
            endDate = Optional.of(new DeadlineDate(argMultimap.getValue(PREFIX_END_DATE).get()));
        }

        return new ShowCommand(new DeadlineDateInRangePredicate(startDate, endDate));
    }

}
