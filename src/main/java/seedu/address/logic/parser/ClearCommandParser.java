package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_EVENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;
import java.util.stream.Stream;

import seedu.address.logic.commands.clearcommand.ClearCommand;
import seedu.address.logic.commands.clearcommand.ClearEventsCommand;
import seedu.address.logic.commands.clearcommand.ClearModulesCommand;
import seedu.address.logic.commands.clearcommand.ClearPersonsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and create ClearCommand object that clears the entire RemindMe, its
 * contacts, or its modules.
 */
public class ClearCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand and
     * returns a ClearCommand, ClearModulesCommand, or ClearPersonsCommand based on the input.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ClearCommand parse(String args) throws ParseException {
        ClearCommand command;
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE,
                PREFIX_NAME, PREFIX_GENERAL_EVENT);

        if (clearContactsCondition(argumentMultimap)) {
            if (!argumentMultimap.getValue(PREFIX_NAME).equals(Optional.of(""))) {
                throw new ParseException(ClearPersonsCommand.MESSAGE_USAGE);
            }
            command = new ClearPersonsCommand();
        } else if (clearModulesCondition(argumentMultimap)) {
            if (!argumentMultimap.getValue(PREFIX_MODULE).equals(Optional.of(""))) {
                throw new ParseException(ClearModulesCommand.MESSAGE_USAGE);
            }
            command = new ClearModulesCommand();
        } else if (clearEventsCondition(argumentMultimap)) {
            if (!argumentMultimap.getValue(PREFIX_GENERAL_EVENT).equals(Optional.of(""))) {
                throw new ParseException(ClearEventsCommand.MESSAGE_USAGE);
            }
            command = new ClearEventsCommand();
        } else if (clearRemindCondition(argumentMultimap)) {
            command = new ClearCommand();
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ClearCommand.MESSAGE_USAGE));
        }

        return command;
    }

    private boolean clearModulesCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_MODULE)
                && argumentMultimap.getPreamble().isEmpty();
    }

    private boolean clearContactsCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_NAME)
                && argumentMultimap.getPreamble().isEmpty();
    }

    private boolean clearEventsCondition(ArgumentMultimap argumentMultimap) {
        return arePrefixesPresent(argumentMultimap, PREFIX_GENERAL_EVENT)
                && argumentMultimap.getPreamble().isEmpty();
    }

    private boolean clearRemindCondition(ArgumentMultimap argumentMultimap) {
        return !arePrefixesPresent(argumentMultimap, PREFIX_MODULE)
                && !arePrefixesPresent(argumentMultimap, PREFIX_NAME)
                && !arePrefixesPresent(argumentMultimap, PREFIX_GENERAL_EVENT)
                && argumentMultimap.getPreamble().isEmpty();
    }

    protected static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                                Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
