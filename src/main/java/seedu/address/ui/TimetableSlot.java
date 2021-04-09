package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Renders a slot in the timetable.
 */
public class TimetableSlot extends UiPart<Region> {
    private static final String FXML = "TimetableSlot.fxml";

    @FXML
    private StackPane meetingSlot;
    @FXML
    private Label meetingLabel;

    /**
     * Constructs the Ui for the slot given the (pixel) length of the slot and the header string.
     * @param slotLength
     * @param header
     */

    public TimetableSlot(double slotLength, String header) {
        super(FXML);
        if (slotLength < 13) {
            this.meetingLabel.setText(""); // Prevent overflow of text
        } else {
            this.meetingLabel.setText(header);
        }
        meetingSlot.setPrefHeight(slotLength);
        meetingSlot.setMinHeight(slotLength);
        meetingSlot.setMaxHeight(slotLength);
    }

}
