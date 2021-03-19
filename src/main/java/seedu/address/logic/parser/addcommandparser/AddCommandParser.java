package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.addcommand.AddCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments, identifies which Add Command it refers to and
 * creates the corresponding Add Command.
 */
public class AddCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of an Add Command
     * and returns an Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String args) throws ParseException {
        Command command;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_EXAM, PREFIX_ASSIGNMENT);

        if (addModuleCondition(argMultimap)) {
            command = new AddModuleCommandParser().parse(args);
        } else if (addAssigmentCondition(argMultimap)) {
            command = new AddAssignmentCommandParser().parse(args);
        } else if (addExamCondition(argMultimap)) {
            command = new AddExamCommandParser().parse(args);
        } else if (addPersonCondition(argMultimap)) {
            command = new AddPersonCommandParser().parse(args);
        } else if (addEventCondition(argMultimap)) {
            command = new AddEventCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddCommand.MESSAGE_USAGE));
        }
        return command;
    }

    private boolean addModuleCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                && !arePrefixesPresent(argMultimap, PREFIX_EXAM);
    }

    private boolean addAssigmentCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                && argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_EXAM);
    }

    private boolean addExamCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && arePrefixesPresent(argMultimap, PREFIX_EXAM)
                && argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT);
    }

    private boolean addPersonCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_NAME)
                && argMultimap.getPreamble().isEmpty();
    }

    private boolean addEventCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_GENERAL_EVENT)
                && argMultimap.getPreamble().isEmpty();
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

