package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.ui.exceptions.InvalidThemeException;

public class ThemeFactoryTest {

    private static final String VALID_PATH = "src/test/data/ThemeTest/valid.theme.json";
    private static final String NOT_EXIST_PATH = "abc";
    private static final String INVALID_CONTENT_PATH = "src/test/data/ThemeTest/invalid.content.txt";
    private static final String INVALID_THEME_PATH = "src/test/data/ThemeTest/invalid.theme.json";
    private static final String INVALID_JSON_PATH = "src/test/data/ThemeTest/malformed.theme.json";

    private static final String DEFAULT_FOREGROUND = "#f8f8f2";
    private static final String DEFAULT_BACKGROUND = "#272822";
    private static final String[] DEFAULT_COLOR = new String[] {
        "#272822", "#f92672", "#a6e22e", "#f4bf75",
        "#66d9ef", "#ae81ff", "#a1efe4", "#f8f8f2",
        "#75715e", "#f92672", "#a6e22e", "#f4bf75",
        "#66d9ef", "#ae81ff", "#a1efe4", "#f9f8f5"
    };

    private static final Theme DEFAULT_THEME = ThemeFactory.getDefaultTheme();
    private static final Theme t1 = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR);

    @Test
    public void themeFactory_defaultThemeEquals_success() {
        assertEquals(t1, DEFAULT_THEME);
    }

    @Test
    public void themeFactory_loadValidTheme_success() {
        Theme t = assertDoesNotThrow(() -> ThemeFactory.load(VALID_PATH));
        assertEquals(ThemeFactory.getDefaultTheme(), t);
    }

    @Test
    void themeFactory_loadInvalidTheme_failure() {
        assertThrows(FileNotFoundException.class, () -> ThemeFactory.load(NOT_EXIST_PATH),
                new FileNotFoundException().toString());

        assertThrows(InvalidThemeException.class, () -> ThemeFactory.load(INVALID_THEME_PATH),
                new InvalidThemeException("Invalid theme supplied").toString());

        assertThrows(DataConversionException.class, () -> ThemeFactory.load(INVALID_JSON_PATH));

        assertThrows(DataConversionException.class, () -> ThemeFactory.load(INVALID_CONTENT_PATH));
    }

}
