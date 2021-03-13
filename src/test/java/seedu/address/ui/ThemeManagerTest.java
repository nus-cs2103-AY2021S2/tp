package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

public class ThemeManagerTest {

    @Test
    public void themeManager_init_success() {
        ThemeManager.init();
        assertEquals(ThemeManager.getTheme(), ThemeFactory.getDefaultTheme());
    }

    @Test
    public void themeManager_setTheme_success() {
        ThemeManager.setTheme(
            ThemeFactory.getDefaultTheme(), "src/test/data/ThemeTest/valid.theme.json");
        String defaultCss = assertDoesNotThrow(() -> Files
            .readString(Path.of("src/test/data/ThemeTest/default.template.css")));
        assertEquals(ThemeFactory.getDefaultTheme(), ThemeManager.getTheme());
        assertEquals("src/test/data/ThemeTest/valid.theme.json", ThemeManager.getThemePath());
        String cssCache = assertDoesNotThrow(() -> Files.readString(Path.of(ThemeManager.getCssCacheUri())));
        assertEquals(defaultCss.replaceAll("\\s", ""), cssCache.replaceAll("\\s", ""));
    }

}
