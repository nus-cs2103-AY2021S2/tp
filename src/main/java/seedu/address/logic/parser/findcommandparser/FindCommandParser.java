package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXAM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.findcommand.FindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

public class FindCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of an Find Command and returns
     * a Command object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Command parseCommand(String args) throws ParseException {
        Command command;
        ArgumentMultimap argumentMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME, PREFIX_EXAM,
                PREFIX_ASSIGNMENT, PREFIX_GENERAL_EVENT);

        //todo searching based on assignment and exam.
        // todo can we incorporate a switch stt?
        if (findModuleCondition(argumentMultimap)) {
            command = new FindModuleCommandParser().parse(args);
        } else if (findPersonCondition(argumentMultimap)) {
            command = new FindPersonCommandParser().parse(args);
        } else if (findEventCondition(argumentMultimap)) {
            command = new FindEventCommandParser().parse(args);
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

    private boolean findEventCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_GENERAL_EVENT)
                && argumentMultimap.getPreamble().isEmpty();
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
