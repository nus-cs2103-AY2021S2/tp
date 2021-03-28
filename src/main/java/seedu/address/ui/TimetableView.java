package seedu.address.ui;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.scheduler.DaySchedule;

import java.util.logging.Logger;

public class TimetableView extends UiPart<Region> {
    private static final String FXML = "TimetableWindow.fxml";
    private final Logger logger = LogsCenter.getLogger(TimetableView.class);

    @FXML
    private TableView<DaySchedule> timetableView;

    private ObservableList<DaySchedule> timetableList;

    /**
     * Renders an observable list of daySchedules in each row onto the UI,
     * with the column being the timeslots.
     * @param timetableList
     */

    public TimetableView(ObservableList<DaySchedule> timetableList) {
        super(FXML);
        this.timetableList = timetableList;
        timetableView.setItems(timetableList);
        applyTableStyle(timetableView);

        //set all 48 columns of the table

        for (int i = 0; i < DaySchedule.NUMBER_OF_PERIODS; i++) {
            String columnName = timetableList.get(0).getTimeDisplayString(i);
            TableColumn<DaySchedule, String> currentCol = new TableColumn<DaySchedule, String>(columnName);
            applyStyle(currentCol);
            //rowItem.getValue() returns the DaySchedule instance for particular row
            int finalI = i; //get around variable must be effectively final
            currentCol.setCellValueFactory(rowItem -> rowItem.getValue().getStringProperty(finalI));
            //currentCol.setCellFactory(tc -> {

            //});
            timetableView.getColumns().add(currentCol);
        }


    }

    /**
     * Solve the case where column headers are not displaying the text by wrapping properly. Credits to
     * @jewelsea for his code TableWrappedHeaders.java. Do check out his work.
     * https://gist.github.com/jewelsea/2898196
     * @param column
     */

    public void applyStyle(TableColumn<DaySchedule, String> column) {
        column.setPrefWidth(100);
        Label label = new Label(column.getText());
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-padding: 6px;-fx-font: 15 arial;");

        StackPane stack = new StackPane();
        stack.prefWidthProperty().bind(column.widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        stack.getChildren().add(label);
        column.setText(null);
        column.setGraphic(stack);
    }

    /**
     * Adjusts the cell size of the table so that the timetable is displayed.
     * @param table
     */
    public void applyTableStyle(TableView<DaySchedule> table) {
        //Ensures table matches the bottom of the screen in fullscreen when displaying tabs.
        table.setFixedCellSize(92);
        table.prefHeightProperty().bind(Bindings.size(table.getItems()).multiply(table.getFixedCellSize()).add(30));
    }

}
