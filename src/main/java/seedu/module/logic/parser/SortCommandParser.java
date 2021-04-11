package seedu.module.logic.parser;

import static seedu.module.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.module.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.module.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.module.logic.parser.CliSyntax.PREFIX_WORKLOAD;

import java.util.stream.Stream;

import seedu.module.logic.commands.SortCommand;
import seedu.module.logic.parser.exceptions.ParseException;
import seedu.module.model.task.Task;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TASK_NAME, PREFIX_MODULE,
                PREFIX_DESCRIPTION, PREFIX_DEADLINE, PREFIX_WORKLOAD, PREFIX_TAG);

        if (!isValidArgument(argMultimap)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        if (arePrefixesPresent(argMultimap, PREFIX_DEADLINE)) {
            return new SortCommand(new Task.DeadlineComparator());
        } else if (arePrefixesPresent(argMultimap, PREFIX_WORKLOAD)) {
            return new SortCommand(new Task.WorkloadComparator());
        } else if (arePrefixesPresent(argMultimap, PREFIX_MODULE)) {
            return new SortCommand(new Task.ModuleComparator());
        } else if (arePrefixesPresent(argMultimap, PREFIX_TASK_NAME)) {
            return new SortCommand(new Task.NameComparator());
        } else if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            return new SortCommand(new Task.DescriptionComparator());
        } else if (arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            return new SortCommand(new Task.TagComparator());
        }

        return new SortCommand(new Task.DeadlineComparator());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(argumentMultimap::contains);
    }

    /**
     * Returns true if there exists only one prefix without extra parameters.*
     * @param argumentMultimap the maps of the arguments.
     * @return true if the argument is valid for sort command.
     */
    private static boolean isValidArgument(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.areLessThanTwoPrefixes()
                && argumentMultimap.arePrefixesNotWithParameter();
    }

}

