package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.scheduler.DaySchedule;

import java.util.logging.Logger;

public class TimetableView extends UiPart<Region> {
    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetableView.class);

    @FXML
    private TableView timetableView;

    private ObservableList<DaySchedule> timetableList;

    public TimetableView(ObservableList<DaySchedule> timetableList) {
        super(FXML);
        this.timetableList = timetableList;
        timetableView.setItems(timetableList);

        //set all 48 columns of the table

        for (int i = 0; i < DaySchedule.NUMBER_OF_PERIODS; i++) {
            String columnName = timetableList.get(i).toString();
            TableColumn<DaySchedule, String> currentCol = new TableColumn<DaySchedule, String>(columnName);
            //rowItem.getValue() returns the DaySchedule instance for particular row
            int finalI = i;
            currentCol.setCellValueFactory(rowItem -> rowItem.getValue().getStringProperty(finalI));

            timetableView.getColumns().add(currentCol);
        }

    }











}
