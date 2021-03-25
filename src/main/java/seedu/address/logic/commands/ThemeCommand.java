package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_FILE_NOT_FOUND;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.ui.ThemeFactory;
import seedu.address.ui.ThemeManager;
import seedu.address.ui.exceptions.InvalidThemeException;

public class ThemeCommand extends Command {

    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects and apply a theme to the application. "
        + "Parameters: THEME_FILE";

    public static final String MESSAGE_SUCCESS = "Theme %1$s applied";
    public static final String MESSAGE_INVALID_THEME = "Invalid theme %1$s given";

    private final Path themePath;

    /**
     * Creates a ThemeCommand to apply the theme located at specified {@code path}
     *
     * @param themePathStr The path to the theme to be applied.
     */
    public ThemeCommand(String themePathStr) {
        requireNonNull(themePathStr);
        this.themePath = Paths.get(themePathStr);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String feedback = String.format(MESSAGE_SUCCESS, this.themePath.toString());
        try {
            ThemeManager.setTheme(ThemeFactory.load(themePath), this.themePath.toString());
            ThemeManager.applyThemeToScene();
        } catch (DataConversionException | InvalidThemeException exception) {
            feedback = String.format(MESSAGE_INVALID_THEME, this.themePath.toString());
        } catch (FileNotFoundException fileNotFoundException) {
            feedback = String.format(MESSAGE_FILE_NOT_FOUND, this.themePath.toString());
        }
        return new CommandResult(feedback);
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

        return Objects.equals(themePath, that.themePath);
    }

    @Override
    public int hashCode() {
        return themePath != null ? themePath.hashCode() : 0;
    }
}
