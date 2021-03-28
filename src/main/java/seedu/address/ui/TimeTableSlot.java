package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;


public class TimeTableSlot extends UiPart<Region> {
    private static final String FXML = "TimeTableSlot.fxml";

    @FXML
    private StackPane meetingSlot;
    @FXML
    private Label meetingLabel;

    public TimeTableSlot(int slotLength, String header) {
        super(FXML);
        this.meetingLabel.setText(header);
        meetingSlot.setPrefHeight(slotLength);
        meetingSlot.setMinHeight(slotLength);
        meetingSlot.setMaxHeight(slotLength);
    }

}
