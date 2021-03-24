package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalDrivers.ALICE;
import static seedu.address.testutil.TypicalDrivers.BENSON;
import static seedu.address.testutil.TypicalPassengers.BOB;
import static seedu.address.ui.DriverCard.POOL_MESSAGE;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class DriverCardTest {
    private DriverCard driverCardForTest;

    @Start
    private void start(Stage stage) {
        driverCardForTest = DriverCard.newDriverCard(ALICE);
        stage.setScene(new Scene(new StackPane(driverCardForTest.getRoot()), 0, 0));
        stage.show();
    }

    /**
     * Test message displayed to user is same as intended
     */
    @Test
    public void constructor_messageContainsCorrectText(FxRobot robot) {
        assertEquals(robot.lookup("#message").queryAs(Label.class).getText(), POOL_MESSAGE);
    }

    @Test
    public void equals() {
        final DriverCard standardDriverCard = DriverCard.newDriverCard(BENSON);

        // same object -> returns true
        assertTrue(standardDriverCard.equals(standardDriverCard));

        // null -> returns false
        assertFalse(standardDriverCard.equals(null));

        // different types -> returns false
        assertFalse(standardDriverCard.equals(new PassengerCard(BOB, 0)));

        // different descriptor -> returns false
        assertFalse(standardDriverCard.equals(DriverCard.newDriverCard(ALICE)));
    }
}
