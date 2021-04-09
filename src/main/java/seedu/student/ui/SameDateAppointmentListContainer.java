package seedu.student.ui;

import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.student.commons.core.LogsCenter;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.student.Student;

/**
 * Panel containing the list of appointments on the same date.
 */
public class SameDateAppointmentListContainer extends UiPart<Region> {

    private static final String FXML = "SameDateAppointmentListContainer.fxml";
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yy");
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private Label date;
    @FXML
    private ListView<Appointment> appointmentListView;

    /**
     * Creates a {@code ameDateAppointmentListContainer} with the given {@code ObservableList}.
     */
    public SameDateAppointmentListContainer(SameDateAppointmentList appointmentList,
                                            ObservableList<Student> studentList) {
        super(FXML);
        date.setText(appointmentList.getDate().format(dateFormatter).toUpperCase());
        ObservableList<Appointment> filteredAppointments = appointmentList.getFilteredAppointmentList();
        appointmentListView.setItems(filteredAppointments);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell(studentList,
                listView.prefWidthProperty()));
        appointmentListView.prefHeightProperty().bind(Bindings.size(filteredAppointments)
                .multiply(100));
    }


    /**
     * Custom {@code ListCell} that displays the graphics of an {@code Appointment} using an {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
        private ObservableList<Student> studentList;
        private DoubleProperty width;

        public AppointmentListViewCell(ObservableList<Student> studentList, DoubleProperty width) {
            super();
            this.studentList = studentList;
            this.width = width;
        }

        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);

            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(appointment, getIndex() + 1, studentList).getRoot());
                prefWidthProperty().bind(width.subtract(20));
            }
        }
    }
}
