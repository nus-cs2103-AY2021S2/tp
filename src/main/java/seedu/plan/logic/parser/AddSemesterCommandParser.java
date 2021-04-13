package seedu.plan.logic.parser;

import static seedu.plan.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_PLAN_NUMBER;
import static seedu.plan.logic.parser.CliSyntax.PREFIX_SEM_NUMBER;

import java.util.stream.Stream;

import seedu.plan.commons.core.index.Index;
import seedu.plan.logic.commands.AddSemesterCommand;
import seedu.plan.logic.parser.exceptions.ParseException;
import seedu.plan.model.plan.Semester;

/**
 * Parses input arguments and creates a new AddSemesterCommand object
 */
public class AddSemesterCommandParser implements Parser<AddSemesterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddSemesterCommand
     * and returns an AddSemesterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddSemesterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER);

        if (!arePrefixesPresent(argMultimap, PREFIX_PLAN_NUMBER, PREFIX_SEM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSemesterCommand.MESSAGE_USAGE));
        }

        int semNumber = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_SEM_NUMBER).get()).getOneBased();
        Index planNumber = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_PLAN_NUMBER).get());


        Semester semester = new Semester(semNumber);
        return new AddSemesterCommand(planNumber, semester);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
