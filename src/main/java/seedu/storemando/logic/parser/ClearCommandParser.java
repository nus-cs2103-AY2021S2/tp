package seedu.storemando.logic.parser;

import static seedu.storemando.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.storemando.logic.parser.CliSyntax.PREFIX_LOCATION;

import seedu.storemando.logic.commands.ClearAllCommand;
import seedu.storemando.logic.commands.ClearCommand;
import seedu.storemando.logic.commands.ClearLocationCommand;
import seedu.storemando.logic.parser.exceptions.ParseException;
import seedu.storemando.model.item.predicate.LocationContainsPredicate;

/**
 * Parses input arguments and creates a new ClearCommand object.
 */
public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of ClearCommand
     * and returns a ClearCommand object for execution.
     *
     * @param args Parses the given {@code String} of arguments in the context of the ClearCommand.
     * @return a ClearCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public ClearCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ClearAllCommand();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_LOCATION);

        if (argMultimap.getPreamble().isEmpty() && argMultimap.getValue(PREFIX_LOCATION).isPresent()) {
            String location = ParserUtil.parseLocation(argMultimap.getValue(PREFIX_LOCATION).get()).toString();
            return new ClearLocationCommand(new LocationContainsPredicate(location), location);
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
        }
    }
}
