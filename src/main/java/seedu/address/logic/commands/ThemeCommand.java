package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FILE_NOT_FOUND;

import java.io.IOException;
import java.util.Objects;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.Theme;
import seedu.address.ui.ThemeFactory;
import seedu.address.ui.ThemeManager;
import seedu.address.ui.exceptions.InvalidThemeException;

public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects and apply a theme to the application.\n"
        + "Parameters: THEME\n"
        + "Example: " + COMMAND_WORD + " @monokai";

    public static final String MESSAGE_SUCCESS = "Theme \"%1$s\" applied";
    public static final String MESSAGE_INVALID_THEME = "Invalid theme \"%1$s\" given";

    private final String themePathString;

    /**
     * Creates a ThemeCommand to apply the theme located at specified {@code path}
     *
     * @param themePathString The path to the theme to be applied.
     */
    public ThemeCommand(String themePathString) {
        requireNonNull(themePathString);
        this.themePathString = themePathString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String feedback = String.format(MESSAGE_SUCCESS, this.themePathString);
        try {
            Theme theme = ThemeFactory.load(themePathString);
            ThemeManager.getInstance().setTheme(theme, this.themePathString);
        } catch (DataConversionException | InvalidThemeException exception) {
            feedback = String.format(MESSAGE_INVALID_THEME, this.themePathString);
            throw new CommandException(feedback);
        } catch (IOException ioException) {
            feedback = String.format(MESSAGE_FILE_NOT_FOUND, this.themePathString);
            throw new CommandException(feedback);
        }
        return new CommandResult(feedback, false, false, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ThemeCommand that = (ThemeCommand) o;

        return Objects.equals(themePathString, that.themePathString);
    }

    @Override
    public int hashCode() {
        return themePathString != null ? themePathString.hashCode() : 0;
    }
}
