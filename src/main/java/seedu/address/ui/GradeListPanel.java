package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.grade.Grade;

/**
 * Panel containing the list of grades.
 */
public class GradeListPanel extends UiPart<Region> {
    private static final String FXML = "GradeListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GradeListPanel.class);

    @javafx.fxml.FXML
    private ListView<Grade> gradeListView;

    /**
     * Creates a {@code GradeListPanel} with the given {@code ObservableList}.
     */
    public GradeListPanel(ObservableList<Grade> gradeList) {
        super(FXML);
        gradeListView.setItems(gradeList);
        gradeListView.setCellFactory(listView -> new GradeListPanel.GradeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Grade} using a {@code GradeCard}.
     */
    class GradeListViewCell extends ListCell<Grade> {
        @Override
        protected void updateItem(Grade grade, boolean empty) {
            super.updateItem(grade, empty);

            if (empty || grade == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GradeCard(grade, getIndex() + 1).getRoot());
            }
        }
    }
}
