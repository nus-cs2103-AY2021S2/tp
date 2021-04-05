package seedu.student.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.student.commons.core.LogsCenter;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.student.Student;

/**
 * Panel containing the list of appointments grouped by date.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<SameDateAppointmentList> appointmentsGroupListView;

    /**
     * Creates an {@code AppointmentListPanel} with the given {@code ObservableList}.
     */

    public AppointmentListPanel(ObservableList<SameDateAppointmentList> appointmentLists,
                                ObservableList<Student> studentList) {
        super(FXML);
        appointmentsGroupListView.setItems(appointmentLists);
        appointmentsGroupListView.setCellFactory(listView -> new AppointmentsGroupListViewCell(studentList));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code SameDateAppointmentList}
     * using a {@code SameDateAppointmentListContainer}.
     */
    class AppointmentsGroupListViewCell extends ListCell<SameDateAppointmentList> {
        private ObservableList<Student> studentList;

        public AppointmentsGroupListViewCell(ObservableList<Student> studentList) {
            super();
            this.studentList = studentList;
        }

        @Override
        protected void updateItem(SameDateAppointmentList list, boolean empty) {
            super.updateItem(list, empty);

            if (empty || list == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SameDateAppointmentListContainer(list, studentList).getRoot());
            }
        }
    }

}
