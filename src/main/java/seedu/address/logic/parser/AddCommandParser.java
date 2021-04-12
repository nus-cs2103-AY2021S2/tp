package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PREAMBLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRINGSCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.conditions.ConstraintManager;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.attributes.Date;
import seedu.address.model.task.attributes.Description;
import seedu.address.model.task.attributes.Duration;
import seedu.address.model.task.attributes.RecurringSchedule;
import seedu.address.model.task.attributes.Status;
import seedu.address.model.task.attributes.Title;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    public static final String MESSAGE_PREAMBLE_PARSED = String.format(MESSAGE_INVALID_COMMAND_FORMAT
                    + "\n\n" + AddCommand.MESSAGE_USAGE, MESSAGE_INVALID_PREAMBLE);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DATE, PREFIX_DURATION,
                        PREFIX_RECURRINGSCHEDULE, PREFIX_DESCRIPTION, PREFIX_STATUS, PREFIX_TAG);

        handleMissingCompulsoryFields(argMultimap);

        Task task = createTaskWithGivenArguments(argMultimap);
        return new AddCommand(task);
    }

    private void handleMissingCompulsoryFields(ArgumentMultimap argumentMultimap) throws ParseException {
        boolean hasPrefixesMissing = !arePrefixesPresent(argumentMultimap, PREFIX_TITLE);
        boolean hasInvalidPreamble = !argumentMultimap.getPreamble().isEmpty();
        if (hasPrefixesMissing) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                ConstraintManager.MESSAGE_EMPTY_TITLE));
        }
        if (hasInvalidPreamble) {
            throw new ParseException(MESSAGE_PREAMBLE_PARSED);
        }
    }

    private Task createTaskWithGivenArguments(ArgumentMultimap argMultimap) throws ParseException {
        assert argMultimap.getValue(PREFIX_TITLE).isPresent();

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).orElse(""));
        RecurringSchedule recurringSchedule = ParserUtil.parseRecurringSchedule(
                argMultimap.getValue(PREFIX_RECURRINGSCHEDULE).orElse(""));
        Duration duration = ParserUtil.parseDuration(argMultimap
                .getValue(PREFIX_DURATION).orElse(""));
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).orElse(""));
        Status status = ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).orElse("not done"));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        return new Task(title, date, duration, recurringSchedule, description, status, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
