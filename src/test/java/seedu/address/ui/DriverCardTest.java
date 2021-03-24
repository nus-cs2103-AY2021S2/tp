package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalDrivers.ALICE;
import static seedu.address.testutil.TypicalDrivers.BENSON;
import static seedu.address.testutil.TypicalPassengers.BOB;
import static seedu.address.ui.DriverCard.POOL_MESSAGE;

import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class DriverCardTest {
    private DriverCard driverCardForTest;
    private Stage stage;

    @BeforeEach
    private void setUp() throws TimeoutException {
        driverCardForTest = DriverCard.newDriverCard(ALICE);
        FxToolkit.setupStage(stage -> {
            this.stage = stage;
            this.stage.setScene(new Scene(new StackPane(driverCardForTest.getRoot())));
            this.stage.show();
        });
    }

    /**
     * Test message displayed to user is same as intended
     */
    @Test
    public void constructor_messageContainsCorrectText() {
        assertEquals(new FxRobot().interact(stage::requestFocus).lookup("#message")
                .queryAs(Label.class).getText(), POOL_MESSAGE);
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
