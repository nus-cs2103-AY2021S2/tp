package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import seedu.address.model.Model;

/**
 * Changes the theme of Helibook.
 */
public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";
    public static final String OPTION_DARK = "dark";
    public static final String OPTION_LIGHT = "light";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the theme of HeliBook.\n"
            + "Parameters: " + PREFIX_OPTION + "OPTION\n"
            + "Options: dark, light\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_DARK + "\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + OPTION_LIGHT;
    public static final String MESSAGE_SUCCESS = "Theme has been changed.";

    private final String option;

    public ThemeCommand(String option) {
        this.option = option;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (option.equals(OPTION_DARK)) {
            model.setTheme(OPTION_DARK);
        } else {
            model.setTheme(OPTION_LIGHT);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
