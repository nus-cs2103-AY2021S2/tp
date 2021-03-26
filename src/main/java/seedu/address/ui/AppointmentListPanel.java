package seedu.address.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDisplay;
import seedu.address.model.person.Patient;

/**
 * Panel containing the list of persons.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<AppointmentDisplay> appointmentListView;

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code patientList}, {@code appointmentList}.
     */
    public AppointmentListPanel(ObservableList<Patient> patientList, ObservableList<Appointment> appointmentList) {
        super(FXML);

        // maintain a hashmap to improve speed of searching
        final Map<UUID, Patient> patientHashMap = new HashMap<>();
        ObservableList<AppointmentDisplay> displayAppointmentList = FXCollections.observableArrayList();

        updatePatientHashMap(patientHashMap, patientList);
        for (int i = 0; i < appointmentList.size(); i++) {
            displayAppointmentList.add(mapToDisplayAppointment(patientHashMap, patientList, appointmentList.get(i)));
        }

        appointmentList.addListener(new ListChangeListener<Appointment>() {
            // maintain a hashmap to improve speed of searching
            @Override
            public void onChanged(Change<? extends Appointment> change) {
                while (change.next());
                // everything can be handled with just 1 clear and add of all appointments.
                displayAppointmentList.clear();
                for (Appointment appt: change.getList()) {
                    displayAppointmentList.add(mapToDisplayAppointment(
                        patientHashMap, patientList, appt));
                }
            }
        });

        // patientList also needs listener to update the appointment display list
        patientList.addListener(new ListChangeListener<Patient>() {
            @Override
            public void onChanged(Change<? extends Patient> change) {
                updatePatientHashMap(patientHashMap, patientList);
                while (change.next());
                displayAppointmentList.clear();
                for (Appointment appt: appointmentList) {
                    displayAppointmentList.add(mapToDisplayAppointment(
                        patientHashMap, patientList, appt));
                }
            }
        });
        appointmentListView.setItems(FXCollections.unmodifiableObservableList(displayAppointmentList));
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * With the given {@code Appointment}, gets the corresponding {@code Patient} from
     * {@code patientHashMap} using the {@code Appointment}'s PatientUuid as the key.
     * If {@code Patient} does not exist in the {@code patientHashMap}, then seaches
     * through {@code patientList} to find the corresponding {@code Patient} and add it
     * to the {@code patientHashMap}.
     */
    public AppointmentDisplay mapToDisplayAppointment(Map<UUID, Patient> patientHashMap,
            ObservableList<Patient> patientList, Appointment appt) {
        // mutates hashmap
        assert patientHashMap.containsKey(appt.getPatientUuid())
                : "patientHashMap should always contain the appointment's patient UUID";
        return new AppointmentDisplay(patientHashMap.get(appt.getPatientUuid()),
                appt.getDoctor(), appt.getTimeslot(), appt.getTags());
        // TODO: remove if verified to be unnecessary
        // Don't think this part is needed, since if there is a mutation to patientList,
        // updatePatientHashMap will be called, which means the entry will definitely be in the hashMap
        // Patient patient = patientList
        //             .stream()
        //             .filter(
        //                 pt -> {
        //                     return pt.getUuid().equals(appt.getPatientUuid());
        //                 }
        //             ).filter(pt -> {
        //                 return true;
        //             })
        //             .findFirst()
        //             .get();
        // patientHashMap.put(appt.getPatientUuid(), patient);
        // return new AppointmentDisplay(patient, appt.getDoctor(), appt.getTimeslot(), appt.getTags());
    }

    /**
     * With the given {@code patientList}, updates the values of {@code patientHashMap}
     * using the {@code Patient}'s UUID as the key.
     */
    public void updatePatientHashMap(Map<UUID, Patient> patientHashMap, ObservableList<Patient> patientList) {
        for (Patient pt: patientList) {
            patientHashMap.put(pt.getUuid(), pt);
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class AppointmentListViewCell extends ListCell<AppointmentDisplay> {
        @Override
        protected void updateItem(AppointmentDisplay appointment, boolean empty) {
            super.updateItem(appointment, empty);
            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
            }
        }
    }

}
