package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import seedu.address.commons.core.LogsCenter;

public class TimetableView extends UiPart<Region> {
    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetableView.class);

    @FXML
    private GridPane timetableGrid;

    @FXML
    private AnchorPane dayScheduleOne;

    @FXML
    private AnchorPane dayScheduleTwo;

    @FXML
    private AnchorPane dayScheduleThree;

    @FXML
    private AnchorPane dayScheduleFour;

    @FXML
    private AnchorPane dayScheduleFive;

    @FXML
    private AnchorPane dayScheduleSix;


    public TimetableView() {
        super(FXML);
        TimetableSlot timetableSlot = new TimetableSlot(200.0, "CS NONONOPE");
        dayScheduleOne.setTopAnchor(timetableSlot.getRoot(), 700.0);
        dayScheduleOne.setLeftAnchor(timetableSlot.getRoot(), 0.0);
        dayScheduleOne.setRightAnchor(timetableSlot.getRoot(), 0.0);
        dayScheduleOne.getChildren().addAll(timetableSlot.getRoot());
    }

}
