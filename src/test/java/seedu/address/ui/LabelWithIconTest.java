package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class LabelWithIconTest {
    private static final String CORRECT_IMG_PATH = "/view.images/fail.png";
    private static final String WRONG_IMG_PATH = "/view.images/wrong.png";
    private LabelWithIcon labelWithIconForTest;

    @Start
    private void start(Stage stage) {
        labelWithIconForTest = new LabelWithIcon(CORRECT_IMG_PATH, "wrong");
        stage.setScene(new Scene(new StackPane(labelWithIconForTest.getRoot()), 0, 0));
        stage.show();
    }

    /**
     * Check if style of the elements are not overwritten by CSS styles or any other factor
     */
    @Test
    public void constructor_checkStyleCorrect(FxRobot robot) {
        assertEquals(robot.lookup("#labelWithIcon").queryAs(HBox.class).getAlignment(), Pos.CENTER_LEFT);
        assertEquals(robot.lookup("#text").queryAs(Label.class).getTextOverrun(), OverrunStyle.CLIP);
    }

    @Test
    public void constructor_nullIconImgPath_throwsAssertionError() {
        assertThrows(NullPointerException.class, () -> new LabelWithIcon(null, ""));
    }

    @Test
    public void constructor_wrongIconImgPath_throwsAssertionError() {
        assertThrows(NullPointerException.class, () -> new LabelWithIcon(WRONG_IMG_PATH, ""));
    }
}
