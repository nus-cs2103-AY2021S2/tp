package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.logic.commands.DeleteFoodIntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteFoodIntakeCommand object
 */
public class DeleteFoodIntakeCommandParser implements Parser<DeleteFoodIntakeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteFoodIntakeCommand
     * and returns an DeleteFoodIntakeItemCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteFoodIntakeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteFoodIntakeCommand.MESSAGE_USAGE));
        }

        LocalDate date;
        String foodName;
        try {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        } catch (DateTimeParseException de) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME_FORMAT,
                    DeleteFoodIntakeCommand.MESSAGE_USAGE));
        }
        foodName = ParserUtil.parseFoodIntakeName(argMultimap.getValue(PREFIX_NAME).get());
        return new DeleteFoodIntakeCommand(date, foodName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
