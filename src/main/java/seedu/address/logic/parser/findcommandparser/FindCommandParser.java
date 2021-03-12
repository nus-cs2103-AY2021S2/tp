package seedu.address.logic.parser.findcommandparser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.findcommand.FindCommand;
import seedu.address.logic.commands.findcommand.FindModuleCommand;
import seedu.address.logic.commands.findcommand.FindPersonCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public abstract class FindCommandParser {

    public Command parseCommand(String args) throws ParseException {
        Command command;
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_EXAM,
                PREFIX_ASSIGNMENT);

        if (findModuleCondition(argumentMultimap)) {
            command = new FindModuleCommandParser().parse(args);
        } /*else if (findAssignmentCondition(argumentMultimap)) {
            command = new FindAssignmentCommandPaser().parse(args);
        } else if (findExamCondition(argumentMultimap)) {
            command = new FindExamCommandParser().parse(args);
        }*/ else if (findPersonCondition(argumentMultimap)) {
            command = new FindPersonCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        return command;
    }

    private boolean findModuleCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_MODULE)
                && argumentMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argumentMultimap, PREFIX_ASSIGNMENT)
                && !arePrefixesPresent(argumentMultimap, PREFIX_EXAM);
    }

    private boolean findAssignmentCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_MODULE)
                && arePrefixesPresent(argumentMultimap, PREFIX_ASSIGNMENT)
                && argumentMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argumentMultimap, PREFIX_EXAM);
    }

    private boolean findExamCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_MODULE)
                && arePrefixesPresent(argumentMultimap, PREFIX_EXAM)
                && argumentMultimap.getPreamble().isEmpty()
                && !arePrefixesPresent(argumentMultimap, PREFIX_ASSIGNMENT);
    }

    private boolean findPersonCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_NAME)
                && argumentMultimap.getPreamble().isEmpty();
    }

    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                                Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
