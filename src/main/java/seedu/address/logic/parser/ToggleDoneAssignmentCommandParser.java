package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ToggleDoneAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Title;

/**
 * Parses input arguments and creates new parseCommand Object
 */
public class ToggleDoneAssignmentCommandParser {
    /**
     * Parses the {@code String} of arguments of a delete command
     * to execute the specific delete command
     * @param args
     * @return parseCommand
     * @throws ParseException
     */
    public ToggleDoneAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_ASSIGNMENT);
        if (toggleDoneAssignmentCondition(argMultimap)) {
            Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_MODULE).get());
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_ASSIGNMENT).get());
            return new ToggleDoneAssignmentCommand(title, index);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ToggleDoneAssignmentCommand.MESSAGE_USAGE));
        }
    }


    /**
     * returns true when arguments match input for toggleAssignment command
     * @param argMultimap
     * @return
     */
    public boolean toggleDoneAssignmentCondition(ArgumentMultimap argMultimap) {
        return arePrefixesPresent(argMultimap, PREFIX_MODULE)
                && argMultimap.getPreamble().isEmpty()
                && arePrefixesPresent(argMultimap, PREFIX_ASSIGNMENT);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}

