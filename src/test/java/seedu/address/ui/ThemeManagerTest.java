package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import javafx.scene.Group;
import javafx.scene.Scene;

public class ThemeManagerTest {

    @Test
    public void themeManager_init_success() {
        ThemeManager.init();
        assertEquals(ThemeManager.getTheme(), ThemeFactory.getDefaultTheme());
    }

    @Test
    public void themeManager_setTheme_success() {
        Group root = new Group();
        Scene sceneStub = new Scene(root);

        ThemeManager.init();
        ThemeManager.setScene(sceneStub);
        ThemeManager.applyThemeToScene();
        try {
            String defaultCss = Files.readString(Paths.get("src/test/data/ThemeTest/default.template.css"));
            System.out.println(sceneStub.getStylesheets().get(0).substring(8));
            String tempCss = Files.readString(Paths.get(sceneStub.getStylesheets().get(0).substring(5)));
            assertEquals(defaultCss, tempCss);
        } catch (Exception e) {
            System.out.println("err:" + e.toString());
        }
    }
}
