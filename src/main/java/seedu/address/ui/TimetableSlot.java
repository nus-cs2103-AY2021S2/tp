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

    private final double slotLength;

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
        this.slotLength = slotLength;
        if (slotLength < 13) {
            this.meetingLabel.setText(""); // Prevent overflow of text
        } else {
            this.meetingLabel.setText(header);
        }
        meetingSlot.setPrefHeight(slotLength);
        meetingSlot.setMinHeight(slotLength);
        meetingSlot.setMaxHeight(slotLength);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj instanceof TimetableSlot) {
            TimetableSlot otherTimetableSlot = (TimetableSlot) obj;
            return otherTimetableSlot.slotLength == this.slotLength && otherTimetableSlot
                    .meetingLabel.getText().equals(meetingLabel.getText());
        } else {
            return false;
        }
    }

}
