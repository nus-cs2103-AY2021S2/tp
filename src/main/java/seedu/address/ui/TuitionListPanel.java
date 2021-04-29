package seedu.address.ui;

import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of tuition.
 */
public class TuitionListPanel extends UiPart<Region> {
    private static final String FXML = "TuitionListPanel.fxml";

    @FXML
    private Label studentName;

    @FXML
    private ListView<Student> tuitionListView;

    /**
     * Creates a {@code TuitionListPanel} with the given {@code ObservableList}.
     */
    public TuitionListPanel(ObservableList<Student> studentList) {
        super(FXML);
        addListener(studentList);
        populateTuitionListView(studentList);
    }

    private void addListener(ObservableList<Student> studentList) {
        studentList.addListener((ListChangeListener<Student>) change -> {
            while (change.next()) {
                populateTuitionListView(studentList);
            }
        });
    }

    /**
     * Populates the tuitionListView with the given {@code ObservableList}
     */
    private void populateTuitionListView(ObservableList<Student> studentList) {
        ObservableList<Student> studentWithSessionsList = filterStudentsWithSessions(studentList);
        tuitionListView.setItems(studentWithSessionsList);
        tuitionListView.setCellFactory(listView -> new TuitionListViewCell());
    }

    /**
     * Filters and keep only students with at least 1 session in the list
     */
    private ObservableList<Student> filterStudentsWithSessions(ObservableList<Student> studentList) {
        return studentList.stream().filter(student -> student.getListOfSessions().size() != 0)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} and {@code Session}
     * using a {@code TuitionCard}.
     */
    class TuitionListViewCell extends ListCell<Student> {
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TuitionCard(student).getRoot());
            }
        }
    }
}
