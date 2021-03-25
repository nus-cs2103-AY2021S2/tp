package seedu.student.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.student.commons.core.LogsCenter;
import seedu.student.model.appointment.SameDateAppointmentList;

/**
 * Panel containing the list of persons.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<SameDateAppointmentList> appointmentsGroupListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */

    public AppointmentListPanel(ObservableList<SameDateAppointmentList> lists) {
        super(FXML);
        appointmentsGroupListView.setItems(lists);
        appointmentsGroupListView.setCellFactory(listView -> new AppointmentsGroupListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code SameDateAppointmentList}
     * using a {@code SameDateAppointmentListContainer}.
     */
    class AppointmentsGroupListViewCell extends ListCell<SameDateAppointmentList> {
        @Override
        protected void updateItem(SameDateAppointmentList list, boolean empty) {
            super.updateItem(list, empty);

            if (empty || list == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SameDateAppointmentListContainer(list).getRoot());
            }
        }
    }

}
