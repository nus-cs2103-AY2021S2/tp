package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.EditShortcutCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditShortcutCommand object
 */
public class EditShortcutCommandParser implements Parser<EditShortcutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditShortcutCommand
     * and returns an EditShortcutCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public EditShortcutCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_SHORTCUT_NAME, PREFIX_SHORTCUT_COMMAND);

        if (!arePrefixesPresent(argMultimap, PREFIX_SHORTCUT_NAME, PREFIX_SHORTCUT_COMMAND)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditShortcutCommand.MESSAGE_USAGE));
        }

        String target = ParserUtil.formatShortcutName(argMultimap.getValue(PREFIX_SHORTCUT_NAME).get());
        String shortcutCommand = ParserUtil.formatShortcutCommand(argMultimap.getValue(PREFIX_SHORTCUT_COMMAND).get());

        return new EditShortcutCommand(target, shortcutCommand);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
