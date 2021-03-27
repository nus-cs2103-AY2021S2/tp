package seedu.address.ui;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.ui.exceptions.InvalidThemeException;

public class ThemeFactory {

    // Properties of the default theme
    // Can be found on https://terminal.sexy, monokai.dark
    private static final String DEFAULT_FOREGROUND = "#f8f8f2";
    private static final String DEFAULT_BACKGROUND = "#272822";
    private static final String[] DEFAULT_COLOR;

    static {
        DEFAULT_COLOR = new String[] {
            "#272822", "#f92672", "#a6e22e", "#f4bf75",
            "#66d9ef", "#ae81ff", "#a1efe4", "#f8f8f2",
            "#75715e", "#f92672", "#a6e22e", "#f4bf75",
            "#66d9ef", "#ae81ff", "#a1efe4", "#f9f8f5"
        };
    }

    /**
     * Loads a theme from a given file path.
     *
     * @param path The path of the JSON theme file.
     * @return The loaded Theme instance.
     * @throws InvalidThemeException   The theme is malformed or is missing certain variables.
     * @throws FileNotFoundException   The theme file cannot be located.
     * @throws DataConversionException An error occurred when parsing the theme.
     */
    public static Theme load(Path path) throws InvalidThemeException, FileNotFoundException, DataConversionException {
        Optional<Theme> optionalTheme = JsonUtil.readJsonFile(path, Theme.class);
        if (optionalTheme.isEmpty()) {
            throw new FileNotFoundException();
        }
        if (!optionalTheme.get().isValid()) {
            throw new InvalidThemeException("Invalid theme supplied");
        } else {
            return optionalTheme.get();
        }
    }

    /**
     * Returns the default theme.
     *
     * @return The default Theme instance.
     */
    public static Theme getDefaultTheme() {
        return new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR);
    }
}
