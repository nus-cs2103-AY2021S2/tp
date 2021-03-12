package seedu.address.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ThemeTest {

    private static final String INVALID_COLOR = "#abcdez";

    private static final String DEFAULT_FOREGROUND = "#f8f8f2";
    private static final String DEFAULT_BACKGROUND = "#272822";
    private static final String[] DEFAULT_COLOR = new String[] {
            "#272822", "#f92672", "#a6e22e", "#f4bf75",
            "#66d9ef", "#ae81ff", "#a1efe4", "#f8f8f2",
            "#75715e", "#f92672", "#a6e22e", "#f4bf75",
            "#66d9ef", "#ae81ff", "#a1efe4", "#f9f8f5"
    };
    private static final String[] DEFAULT_COLOR_INVALID_LENGTH = new String[] {
            "#272822", "#f92672", "#a6e22e", "#f4bf75"
    };
    private static final String[] DEFAULT_COLOR_INVALID_COLOR = new String[] {
            "#272822", "#f92672", "#a6e22e", "#f4bf75",
            "#66d9ef", "#ae81ff", "#a1efe4", "#f8f8f2",
            "#75715e", "#f92672", "#a6e22e", "#f4bf75",
            "#66d9ef", "#ae81ff", "#a1efe4", INVALID_COLOR
    };

    private final Theme t1 = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR);
    private final Theme t2 = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR);
    private final Theme t3 = new Theme("#FFFFFF", DEFAULT_BACKGROUND, DEFAULT_COLOR);
    private final Theme invalidLength = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR_INVALID_LENGTH);
    private final Theme invalidForeground = new Theme(INVALID_COLOR, DEFAULT_BACKGROUND, DEFAULT_COLOR_INVALID_COLOR);
    private final Theme invalidBackground = new Theme(DEFAULT_FOREGROUND, INVALID_COLOR, DEFAULT_COLOR_INVALID_COLOR);
    private final Theme emptyColor = new Theme("", "", DEFAULT_COLOR);
    private final Theme invalidColor = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, DEFAULT_COLOR_INVALID_COLOR);
    private final Theme nullColor = new Theme(DEFAULT_FOREGROUND, DEFAULT_BACKGROUND, null);

    @Test
    public void theme_equalTheme_success() {
        assertEquals(t1, t1);
        assertEquals(t1, t2);
    }

    @Test
    public void theme_equalTheme_failure() {
        assertNotEquals(t1, t3);
    }

    @Test
    public void theme_validTheme_success() {
        assertTrue(t1.isValid());
    }

    @Test
    public void theme_invalidTheme_failure() {
        assertFalse(invalidColor.isValid());
        assertFalse(invalidLength.isValid());
        assertFalse(invalidForeground.isValid());
        assertFalse(invalidBackground.isValid());
        assertFalse(emptyColor.isValid());
        assertFalse(nullColor.isValid());
    }
}
