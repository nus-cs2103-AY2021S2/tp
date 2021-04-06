package seedu.address.ui;

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
import seedu.address.model.person.Doctor;
import seedu.address.model.person.DoctorMap;
import seedu.address.model.person.Patient;
import seedu.address.model.person.PatientMap;

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
    public AppointmentListPanel(ObservableList<Patient> patientList,
            ObservableList<Doctor> doctorList, ObservableList<Appointment> appointmentList) {
        super(FXML);

        // maintain a hashmap to improve speed of searching
        ObservableList<AppointmentDisplay> displayAppointmentList = FXCollections.observableArrayList();
        Map<UUID, Patient> patientHashMap = PatientMap.getPatientMap();
        Map<UUID, Doctor> doctorHashMap = DoctorMap.getDoctorMap();

        PatientMap.updatePatientHashMap(patientList);
        DoctorMap.updateDoctorHashMap(doctorList);
        for (int i = 0; i < appointmentList.size(); i++) {
            displayAppointmentList.add(mapToDisplayAppointment(
                    patientHashMap, doctorHashMap, appointmentList.get(i)));
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
                        patientHashMap, doctorHashMap, appt));
                }
            }
        });

        // patientList also needs listener to update the appointment display list
        patientList.addListener(new ListChangeListener<Patient>() {
            @Override
            public void onChanged(Change<? extends Patient> change) {
                PatientMap.updatePatientHashMap(patientList);
                while (change.next());
                displayAppointmentList.clear();
                for (Appointment appt: appointmentList) {
                    displayAppointmentList.add(mapToDisplayAppointment(
                        patientHashMap, doctorHashMap, appt));
                }
            }
        });

        // doctorList also needs listener to update the appointment display list
        doctorList.addListener(new ListChangeListener<Doctor>() {
            @Override
            public void onChanged(Change<? extends Doctor> change) {
                DoctorMap.updateDoctorHashMap(doctorList);
                while (change.next());
                displayAppointmentList.clear();
                for (Appointment appt: appointmentList) {
                    displayAppointmentList.add(mapToDisplayAppointment(
                        patientHashMap, doctorHashMap, appt));
                }
            }
        });

        appointmentListView.setItems(FXCollections.unmodifiableObservableList(displayAppointmentList));
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * With the given {@code Appointment}, gets the corresponding {@code Patient} from
     * {@code Map} using the {@code Appointment}'s PatientUuid as the key.
     * If {@code Patient} does not exist in the {@code Map}, then seaches
     * through {@code patientList} to find the corresponding {@code Patient} and add it
     * to the {@code Map}.
     */
    public AppointmentDisplay mapToDisplayAppointment(Map<UUID, Patient> patientHashMap,
            Map<UUID, Doctor> doctorHashMap, Appointment appt) {
        // mutates hashmap
        assert patientHashMap.containsKey(appt.getPatientUuid())
                : "patientHashMap should always contain the appointment's patient UUID";
        assert doctorHashMap.containsKey(appt.getDoctorUuid())
                : "doctorHashMap should always contain the appointment's doctor UUID";
        return new AppointmentDisplay(patientHashMap.get(appt.getPatientUuid()),
                doctorHashMap.get(appt.getDoctorUuid()), appt.getTimeslot(), appt.getTags());
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
