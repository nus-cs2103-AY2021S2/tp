package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.addcommand.AddCommand;
import seedu.address.logic.commands.addcommand.AddModuleCommand;
import seedu.address.logic.commands.addcommand.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments, identifies which Add Command it refers to and
 * creates the corresponding Add Command.
 */
public abstract class AddCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of an Add Command
     * and returns an Command object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String args) throws ParseException {
        Command command;
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_NAME);

        boolean isPreambleEmpty = argMultimap.getPreamble().isEmpty();
        boolean isModulePrefixPresent = arePrefixesPresent(argMultimap, PREFIX_MODULE);
        boolean isNamePrefixPresent = arePrefixesPresent(argMultimap, PREFIX_NAME);

        if (isModulePrefixPresent && isPreambleEmpty) {
            command = new AddModuleCommandParser().parse(args);
        } else if (isNamePrefixPresent && isPreambleEmpty) {
            command = new AddPersonCommandParser().parse(args);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonCommand.MESSAGE_USAGE)
                    + "\n" + AddModuleCommand.MESSAGE_USAGE);
        }
        return command;
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

