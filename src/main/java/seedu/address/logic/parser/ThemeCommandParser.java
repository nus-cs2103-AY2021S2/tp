package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_DARK;
import static seedu.address.logic.parser.CliSyntax.OPTION_LIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.Optional;

import seedu.address.logic.commands.ThemeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ThemeCommandParser implements Parser<ThemeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ThemeCommand
     * and returns a ThemeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public ThemeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args,
                    PREFIX_OPTION);

        Optional<String> option = argumentMultimap.getValue(PREFIX_OPTION);

        if (option.isPresent()) {
            String unboxedOption = option.get();
            if (!unboxedOption.equals(OPTION_DARK)
                    && !unboxedOption.equals(OPTION_LIGHT)) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
            } else {
                return new ThemeCommand(unboxedOption);
            }
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ThemeCommand.MESSAGE_USAGE));
        }
    }
}
