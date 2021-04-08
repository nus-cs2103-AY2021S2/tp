package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUMBER_OF_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUMBER_OF_WEEK;

import seedu.address.logic.commands.DueInCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.DeadlineDateInRangePredicate;

/**
 * Parses input arguments and creates a new ShowCommand object
 */
public class DueInCommandParser implements Parser<DueInCommand> {
    private static final long DEFAULT_NUMBER_OF_DAYS = 7; // If no parameters given by the user

    /**
     * Parses the given {@code String} of arguments in the context of the DueInCommand
     * and returns a DueInCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DueInCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NUMBER_OF_DAY, PREFIX_NUMBER_OF_WEEK);
        long numberOfDays;
        if (argMultimap.getValue(PREFIX_NUMBER_OF_DAY).isPresent()
                && argMultimap.getValue(PREFIX_NUMBER_OF_WEEK).isPresent()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueInCommand.MESSAGE_USAGE));
        }
        if (argMultimap.getValue(PREFIX_NUMBER_OF_DAY).isPresent()) {
            try {
                numberOfDays = ParserUtil.parseNumberOfDays(argMultimap.getValue(PREFIX_NUMBER_OF_DAY).get());
            } catch (ParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueInCommand.MESSAGE_USAGE));
            }
        } else if (argMultimap.getValue(PREFIX_NUMBER_OF_WEEK).isPresent()) {
            try {
                long numberOfWeeks = ParserUtil.parseNumberOfWeeks(argMultimap.getValue(PREFIX_NUMBER_OF_WEEK).get());
                numberOfDays = numberOfWeeks * 7; // One week consists of 7 days
            } catch (ParseException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, DueInCommand.MESSAGE_USAGE));
            }
        } else {
            numberOfDays = DEFAULT_NUMBER_OF_DAYS;
        }
        return new DueInCommand(new DeadlineDateInRangePredicate(numberOfDays));
    }

}
