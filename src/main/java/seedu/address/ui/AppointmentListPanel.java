package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.transformation.FilteredList;
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

        final Map<UUID, Patient> patientHashMap = new HashMap<>();
        ObservableList<AppointmentDisplay> displayAppointmentList = FXCollections.observableArrayList();
        // for (int i = 0; i < appointmentList.size(); i++) {
        //     Appointment appt = appointmentList.get(i);
        //     Patient patient = patientList
        //             .stream()
        //             .filter(
        //                 pt -> {
        //                     return pt.getUuid().equals(appt.getPatientUuid());
        //                 }
        //             ).filter(pt -> {
        //                 System.out.println("Found match: " + pt);
        //                 return true;
        //             })
        //             .findFirst()
        //             .get();
        //     displayAppointmentList.add(new Appointment(appt.getPatientUuid(), appt.getDoctor(), appt.getTimeslot(), appt.getTags()));
        // }

        for (int i = 0; i < appointmentList.size(); i++) {
            displayAppointmentList.add(mapToDisplayAppointment(patientHashMap, patientList, appointmentList.get(i)));
        }

        appointmentList.addListener(new ListChangeListener<Appointment>(){
            // maintain a hashmap to improve speed of searching
            @Override
            public void onChanged(Change<? extends Appointment> change) {
                while (change.next()) {
                    if (change.wasReplaced()) {
                        System.out.println("replaced");

                    } else if (change.wasPermutated()) {
                        System.out.println("permutated");
                        displayAppointmentList.clear();
                        for (int i = 0; i < change.getList().size(); i++) {
                            displayAppointmentList.add(mapToDisplayAppointment(
                                    patientHashMap, patientList, change.getList().get(i)));
                        }

                    } else if (change.wasRemoved()) {
                        System.out.println("removed");
                        System.out.println(change.getFrom() + "    " + change.getTo() + "    " + change.getRemovedSize());
                        displayAppointmentList.remove(mapToDisplayAppointment(
                                patientHashMap, patientList, change.getRemoved().get(0)));

                    } else if (change.wasAdded()) {
                        System.out.println("added");
                        displayAppointmentList.clear();
                        System.out.println(change.getFrom() + "    " + change.getTo() + "    " + change.getAddedSize());
                        for (int i = 0; i < change.getAddedSize(); i++) {
                            displayAppointmentList.add(mapToDisplayAppointment(
                                    patientHashMap, patientList, change.getAddedSubList().get(i)));
                        }

                    } else if (change.wasUpdated()) {
                        System.out.println("updated");
                    }
                }
            }
        });
        appointmentListView.setItems(FXCollections.unmodifiableObservableList(displayAppointmentList));
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    public AppointmentDisplay mapToDisplayAppointment(Map<UUID, Patient> patientHashMap, ObservableList<Patient> patientList, Appointment appt) {
        // mutates hashmap
        System.out.println("size of hashmap is: " + patientHashMap.size());
        if (patientHashMap.containsKey(appt.getPatientUuid())) {
            return new AppointmentDisplay(patientHashMap.get(appt.getPatientUuid()),
                    appt.getDoctor(), appt.getTimeslot(), appt.getTags());
        }
        Patient patient = patientList
                    .stream()
                    .filter(
                        pt -> {
                            return pt.getUuid().equals(appt.getPatientUuid());
                        }
                    ).filter(pt -> {
                        System.out.println("Found match: " + pt);
                        return true;
                    })
                    .findFirst()
                    .get();
        patientHashMap.put(appt.getPatientUuid(), patient);
        return new AppointmentDisplay(patient, appt.getDoctor(), appt.getTimeslot(), appt.getTags());
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
                System.out.println("in updateItem with " + appointment);
                // assert appointment instanceof AppointmentDisplay;
                // AppointmentDisplay appointmentDisplay = (AppointmentDisplay) appointment;
                setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
            }
        }
    }

}
