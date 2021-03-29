package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class ThemeManagerTest {

    static {
        ThemeManager.init();
    }

    @Test
    public void themeManager_init_success() {
        assertEquals(ThemeManager.getTheme(), ThemeFactory.getDefaultTheme());
    }
}
