package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ThemeManagerTest {

    @BeforeAll
    public static void initializeThemeManager() {
        ThemeManager.init();
    }

    @Test
    public void themeManager_getDefaultTheme() {
        assertEquals(ThemeFactory.getDefaultTheme(), ThemeManager.getTheme());
    }

}
