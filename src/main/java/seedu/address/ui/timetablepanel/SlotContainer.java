package seedu.address.ui.timetablepanel;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI Component that that constitutes a region of a calendar view.
 * Solution below adapted from
 * https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
 * SlotContainer.java
 */
public abstract class SlotContainer extends UiPart<Region> {

    public static final double WIDTH_SCALING_FACTOR = 100; // Value chosen to fit within the screen

    @FXML
    protected HBox slotPane;

    // @@author RuiFengg-reused
    // Reused from
    // https://github.com/AY2021S1-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/homerce/ui/schedulepanel/
    // SlotContainer.java
    // with no modifications.
    /**
     * Constructor for a slot container that sets the width of a slot
     */
    public SlotContainer(String fxml) {
        super(fxml);
        slotPane.setPrefWidth(WIDTH_SCALING_FACTOR);
    }
}
