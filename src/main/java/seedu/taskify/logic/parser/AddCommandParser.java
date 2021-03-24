package seedu.taskify.logic.parser;

import static seedu.taskify.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.taskify.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import seedu.taskify.commons.core.LogsCenter;
import seedu.taskify.logic.commands.AddCommand;
import seedu.taskify.logic.parser.exceptions.ParseException;
import seedu.taskify.model.tag.Tag;
import seedu.taskify.model.task.Date;
import seedu.taskify.model.task.Description;
import seedu.taskify.model.task.Name;
import seedu.taskify.model.task.Status;
import seedu.taskify.model.task.StatusType;
import seedu.taskify.model.task.Task;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    private static Logger logger = LogsCenter.getLogger(AddCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_DATE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DESCRIPTION)
                    || !argMultimap.getPreamble().isEmpty()) {
            logger.log(Level.WARNING, "Parse error");
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Status status = new Status(StatusType.NOT_DONE); // New task default StatusType is NOT_DONE.
        Date date = generateDate(argMultimap);
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Task task = new Task(name, description, status, date, tagList);

        return new AddCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Returns a {@code Date} depending on whether a date is specified in the arguments. If a date is
     * specified in the arguments, return the corresponding Date. Else, returns a Date with today as the
     * date with time of 23:59.
     */
    private static Date generateDate(ArgumentMultimap argumentMultimap) throws ParseException {
        Date date;
        if (arePrefixesPresent(argumentMultimap, PREFIX_DATE)) {
            date = ParserUtil.parseDate(argumentMultimap.getValue(PREFIX_DATE).get());
        } else {
            date = Date.endOfToday();
        }
        return date;
    }

}
