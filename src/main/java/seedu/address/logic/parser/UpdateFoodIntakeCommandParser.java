package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARBOS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROTEINS;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.UpdateFoodIntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.food.Food;

public class UpdateFoodIntakeCommandParser implements Parser<UpdateFoodIntakeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public UpdateFoodIntakeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_NAME, PREFIX_CARBOS, PREFIX_FATS, PREFIX_PROTEINS);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateFoodIntakeCommand.MESSAGE_USAGE));
        }

        String carbos;
        String fats;
        String proteins;

        String name = ParserUtil.parseFoodName(argMultimap.getValue(PREFIX_NAME).get());
        LocalDate date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        if (!isPrefixPresent(argMultimap, PREFIX_CARBOS)) {
            carbos = null;
        } else {
            carbos = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_CARBOS).get()).toString();
        }
        if (!isPrefixPresent(argMultimap, PREFIX_FATS)) {
            fats = null;
        } else {
            fats = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_FATS).get()).toString();
        }
        if (!isPrefixPresent(argMultimap, PREFIX_PROTEINS)) {
            proteins = null;
        } else {
            proteins = ParserUtil.parseDouble(argMultimap.getValue(PREFIX_PROTEINS).get()).toString();
        }

        return new UpdateFoodIntakeCommand(date, name, fats, carbos, proteins);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static boolean isPrefixPresent(ArgumentMultimap argumentMultimap, Prefix prefix) {
        return argumentMultimap.getValue(prefix).isPresent();
    }
}
