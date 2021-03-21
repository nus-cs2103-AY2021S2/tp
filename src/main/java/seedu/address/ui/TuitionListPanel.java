package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.session.Session;
import seedu.address.model.student.Student;
import seedu.address.model.tuition.Tuition;

/**
 * Panel containing the list of tuition.
 */
public class TuitionListPanel extends UiPart<Region> {
    private static final String FXML = "TuitionListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TuitionListPanel.class);

    @FXML
    private ListView<Tuition> tuitionListView;

    /**
     * Creates a {@code TuitionListPanel} with the given {@code ObservableList}.
     */
    public TuitionListPanel(ObservableList<Student> studentList) {
        super(FXML);
        ObservableList<Tuition> tuitionList = FXCollections.observableArrayList();
        for (int i = 0; i < studentList.size(); i++) {
            for (int j = 0; j < studentList.get(i).getListOfSessions().size(); j++) {
                Student currStudent = studentList.get(i);
                Session currSession = currStudent.getListOfSessions().get(j);
                tuitionList.add(new Tuition(currStudent, currSession, i, j));
            }
        }
        studentList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                tuitionList.clear();
                for (int i = 0; i < studentList.size(); i++) {
                    for (int j = 0; j < studentList.get(i).getListOfSessions().size(); j++) {
                        Student currStudent = studentList.get(i);
                        Session currSession = currStudent.getListOfSessions().get(j);
                        tuitionList.add(new Tuition(currStudent, currSession, i, j));
                    }
                }
            }
        });
        tuitionListView.setItems(tuitionList);
        tuitionListView.setCellFactory(listView -> new TuitionListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Session} using a {@code SessionCard}.
     */
    class TuitionListViewCell extends ListCell<Tuition> {
        @Override
        protected void updateItem(Tuition tuition, boolean empty) {
            super.updateItem(tuition, empty);

            if (empty || tuition == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TuitionCard(tuition).getRoot());
            }
        }
    }
}
