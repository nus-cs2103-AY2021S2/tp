package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.common.Category;
import seedu.address.model.common.Date;
import seedu.address.model.common.Name;
import seedu.address.model.common.Tag;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;


/**
 * Parses input arguments and creates a new AddTaskCommand object
 */
public class AddTaskCommandParser implements Parser<AddTaskCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTaskCommand
     * and returns an AddTaskCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTaskCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_PRIORITY,
                        PREFIX_CATEGORY, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DEADLINE, PREFIX_PRIORITY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        Name name = SocheduleParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Date deadline = SocheduleParserUtil.parseDate(argMultimap.getValue(PREFIX_DEADLINE).get());
        Priority priority = SocheduleParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        Set<Category> categoryList = SocheduleParserUtil.parseCategories(argMultimap.getAllValues(PREFIX_CATEGORY));
        Set<Tag> tagList = SocheduleParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Task task = new Task(name, deadline, priority, categoryList, tagList);

        return new AddTaskCommand(task);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
