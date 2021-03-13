package seedu.us.among.logic.parser;

import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.us.among.logic.commands.ToggleCommand;
import seedu.us.among.logic.parser.exceptions.ParseException;
import seedu.us.among.ui.ThemeType;

/**
 * Parses input arguments and creates a new ToggleCommand object
 */
public class ToggleCommandParser implements Parser<ToggleCommand> {
    public static final String MESSAGE_INVALID_THEME = "You may only toggle to supported themes."
            + " \n%1$s";

    /**
     * Parses the given {@code String} of arguments in the context of the ToggleCommand
     * and returns a ToggleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ToggleCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ToggleCommand.MESSAGE_USAGE));
        }

        String[] themedKeyword = trimmedArgs.split("\\s+");
        String theme = themedKeyword[0];
        if (!isValidTheme(theme)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_THEME, ToggleCommand.MESSAGE_USAGE));
        }

        return new ToggleCommand(theme);
    }

    /**
     * Returns true if a given string is a valid theme.
     */
    public static boolean isValidTheme(String theme) {
        for (ThemeType e : ThemeType.values()) {
            if (e.name().equalsIgnoreCase(theme)) {
                return true;
            }
        }
        return false;
    }
}
