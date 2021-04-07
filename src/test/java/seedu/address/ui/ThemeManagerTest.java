package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ThemeManagerTest {

    @Test
    public void themeManager_setTheme() {
        ThemeManager.setTheme(ThemeFactory.getDefaultTheme(), null);
        assertEquals(ThemeFactory.getDefaultTheme(), ThemeManager.getTheme());
    }

}
