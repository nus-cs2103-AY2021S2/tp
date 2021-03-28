package seedu.address.ui;

import javafx.fxml.FXML;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;


import java.util.logging.Logger;

public class TimetableView extends UiPart<Region> {
    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetableView.class);

    @FXML
    private GridPane timetableGrid;

    public TimetableView() {
        super(FXML);
    }

}
