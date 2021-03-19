package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_DATETIME_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARBOS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FATS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROTEINS;

import java.time.LocalDate;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddFoodIntakeCommand;
import seedu.address.logic.commands.AddFoodIntakeCommand.TemporaryFoodDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddFoodIntakeCommandParser implements Parser<AddFoodIntakeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddFoodIntakeCommand
     * and returns an AddFoodIntakeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddFoodIntakeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_NAME, PREFIX_CARBOS, PREFIX_FATS, PREFIX_PROTEINS);
        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddFoodIntakeCommand.MESSAGE_USAGE));
        }

        LocalDate date;
        try {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_DATETIME_FORMAT,
                    AddFoodIntakeCommand.MESSAGE_USAGE));
        }

        TemporaryFoodDescriptor tempFoodDescriptor = new TemporaryFoodDescriptor();
        tempFoodDescriptor.setName(ParserUtil.parseFoodName(argMultimap.getValue(PREFIX_NAME).get()));
        if (argMultimap.getValue(PREFIX_CARBOS).isPresent()) {
            tempFoodDescriptor.setCarbos(ParserUtil.parseDouble(argMultimap.getValue(PREFIX_CARBOS).get()));
        }
        if (argMultimap.getValue(PREFIX_FATS).isPresent()) {
            tempFoodDescriptor.setFats(ParserUtil.parseDouble(argMultimap.getValue(PREFIX_FATS).get()));
        }
        if (argMultimap.getValue(PREFIX_PROTEINS).isPresent()) {
            tempFoodDescriptor.setProteins(ParserUtil.parseDouble(argMultimap.getValue(PREFIX_PROTEINS).get()));
        }
        return new AddFoodIntakeCommand(date, tempFoodDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
