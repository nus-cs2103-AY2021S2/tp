package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.sun.javafx.scene.traversal.SceneTraversalEngine;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ThemeManagerTest {

    public class TestGUI extends Application {

        public Scene scene;

        @Override
        public void start(Stage primaryStage) throws Exception {
            Group group = new Group();
            this.scene = new Scene(group);
            primaryStage.setScene(scene);
        }
    }

    @Test
    public void themeManager_init_success() {
        ThemeManager.init();
        assertEquals(ThemeManager.getTheme(), ThemeFactory.getDefaultTheme());
    }

}
