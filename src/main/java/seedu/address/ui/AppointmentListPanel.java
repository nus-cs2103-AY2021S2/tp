package seedu.address.ui;

import java.util.ArrayList;
import java.util.Arrays;
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
    private ListView<Appointment> appointmentListView;

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code patientList}, {@code appointmentList}.
     */
    public AppointmentListPanel(ObservableList<Patient> patientList, ObservableList<Appointment> appointmentList) {
        super(FXML);
        ObservableList<Appointment> displayAppointmentList = FXCollections.observableArrayList();
        for (int i = 0; i < appointmentList.size(); i++) {
            Appointment appt = appointmentList.get(i);
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
            displayAppointmentList.add(new Appointment(appt.getPatientUuid(), appt.getDoctor(), appt.getTimeslot(), appt.getTags()));
        }
        // ObservableList<AppointmentDisplay> displayAppointmentList = appointmentList
        //         .stream()
        //         .map(preDisplayAppt -> {
        //             Patient patient = patientList
        //                     .stream()
        //                     .filter(
        //                         pt -> {
        //                             System.out.println("PATIENT NAME IS: " + pt.getName());
        //                             System.out.println("PATIENT UUID IS: " + pt.getUuid());
        //                             System.out.println("CHECK AGAINST UUID: " + preDisplayAppt.getPatientUuid());
        //                             return pt.getUuid().equals(preDisplayAppt.getPatientUuid());
        //                         }
        //                     )
        //                     .findFirst()
        //                     .get();
        //             return new AppointmentDisplay(patient, preDisplayAppt.getDoctor(),
        //                     preDisplayAppt.getTimeslot(), preDisplayAppt.getTags());
        //         }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        
        // setRegisterCallback(appt -> Platform.runLater(() -> 
        //     displayAppointmentList.add(appt)));
        appointmentListView.setItems(FXCollections.unmodifiableObservableList(displayAppointmentList));
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
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
