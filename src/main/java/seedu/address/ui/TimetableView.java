package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.scheduler.Timetable;

import java.util.logging.Logger;

public class TimetableView extends UiPart<Region> {
    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetableView.class);

    @FXML
    private TableView timetableView;
    private Timetable timetable;

    public TimetableView(Timetable timetable) {
        super(FXML);
        this.timetable = timetable;
    }





}
