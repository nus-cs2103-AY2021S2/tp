package seedu.address.logic.parser.deletecommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.deletecommand.DeletePersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates new parseCommand Object
 */
public class DeleteCommandParser {
    /**
     * Parses the {@code String} of arguments of a delete command
     * to execute the specific delete command
     * @param args
     * @return parseCommand
     * @throws ParseException
     */
    public Command parseCommand(String args) throws ParseException {
        Command command;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_EXAM, PREFIX_ASSIGNMENT,
                        PREFIX_GENERAL_EVENT);
        if (deleteModuleCondition(argMultimap)) {
            command = new DeleteModuleCommandParser().parse(args);
        } else if (deleteAssignmentCondition(argMultimap)) {
            command = new DeleteAssignmentCommandParser().parse(args);
        } else if (deleteExamCondition(argMultimap)) {
            command = new DeleteExamCommandParser().parse(args);
        } else if (deleteGeneralEventCondition(argMultimap)) {
            command = new DeleteGeneralEventCommandParser().parse(args);
        } else if (deletePersonCondition(argMultimap)) {
            command = new DeletePersonCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeletePersonCommand.MESSAGE_USAGE));
        }
        return command;
    }

    /**
     * returns true when arguments match input for deleteModule command
     * @param argMultimap
     * @return
     */
    public boolean deleteModuleCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                && !arePrefixesPresent(argMultimap, PREFIX_EXAM);
    }

    /**
     * returns true when arguments match input for deleteAssignment command
     * @param argMultimap
     * @return
     */
    public boolean deleteAssignmentCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && argMultimap.getPreamble().isEmpty()
                && arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                && !arePrefixesPresent(argMultimap, PREFIX_EXAM);
    }

    /**
     * returns true when arguments match input for deleteExam command
     * @param argMultimap
     * @return
     */
    public boolean deleteExamCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && argMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT)
                && arePrefixesPresent(argMultimap, PREFIX_EXAM);
    }

    /**
     * returns true when arguments match input for deletePerson command
     * @param argMultimap
     * @return boolean value if it fits deletePerson conditional statement
     */
    public boolean deletePersonCondition(ArgumentMultimap argMultimap) {
        return !arePrefixesPresent(argMultimap, PREFIX_NAME);
    }

    /**
     * returns true when arguments match input for deleteGeneralEvent command
     * @param argMultimap
     * @return boolean value if it fits the deleteGeneralEvent conditional statement
     */
    public boolean deleteGeneralEventCondition(ArgumentMultimap argMultimap) {
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
