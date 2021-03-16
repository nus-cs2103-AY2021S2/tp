package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AppointmentSchedule;
import seedu.address.model.ReadOnlyAppointmentSchedule;
import seedu.address.model.appointment.Appointment;

/**
 * An Immutable AppointmentSchedule that is serializable to JSON format.
 */
@JsonRootName(value = "appointmentSchedule")
public class JsonSerializableAppointmentSchedule {
    public static final String MESSAGE_CONFLICTING_APPOINTMENTS = "Appointments list contains conflicts.";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAppointmentSchedule} with the given appointments.
     */
    @JsonCreator
    public JsonSerializableAppointmentSchedule(
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments) {

        this.appointments.addAll(appointments);
    }

    /**
     * Converts a given {@code ReadOnlyAppointmentSchedule} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAppointmentSchedule}.
     */
    public JsonSerializableAppointmentSchedule(ReadOnlyAppointmentSchedule source) {
        appointments.addAll(source.getAppointmentList().stream().map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this appointment schedule into the model's {@code AppointmentSchedule} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentSchedule toModelType() throws IllegalValueException {
        AppointmentSchedule appointmentSchedule = new AppointmentSchedule();

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (appointmentSchedule.hasConflict(appointment)) {
                throw new IllegalValueException(MESSAGE_CONFLICTING_APPOINTMENTS);
            }
            appointmentSchedule.addAppointment(appointment);
        }
        return appointmentSchedule;
    }
}
