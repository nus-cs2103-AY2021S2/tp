package seedu.booking.logic.parser.promptparsers;

import static seedu.booking.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.booking.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.booking.logic.commands.PromptAddPersonCommand;
import seedu.booking.logic.parser.ArgumentMultimap;
import seedu.booking.logic.parser.ArgumentTokenizer;
import seedu.booking.logic.parser.Parser;
import seedu.booking.logic.parser.ParserUtil;
import seedu.booking.logic.parser.Prefix;
import seedu.booking.logic.parser.exceptions.ParseException;
import seedu.booking.model.person.Name;

public class PromptPersonNameParser implements Parser<PromptAddPersonCommand> {
    /**
     * Parses the given {@code String} of arguments for person in the context of adding a person
     * and returns an PromptAddPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PromptAddPersonCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    PromptAddPersonCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new PromptAddPersonCommand(name);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
