package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AppointmentBook;
import seedu.address.model.ReadOnlyAppointmentBook;
import seedu.address.model.appointment.Appointment;

public class JsonSerializableAppointmentBook {

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Persons list contains "
            + "duplicate apppointment(s).";

    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAppointmentBook(@JsonProperty("appointments") List<JsonAdaptedAppointment> appointmentList) {
        this.appointments.addAll(appointmentList);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAppointmentBook(ReadOnlyAppointmentBook source) {
        this.appointments.addAll(source.getAppointmentList().stream()
                .map(JsonAdaptedAppointment::new).collect(Collectors.toList()));
    }


    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AppointmentBook toModelType() throws IllegalValueException {
        AppointmentBook appointmentBook = new AppointmentBook();
        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (appointmentBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            appointmentBook.addAppointment(appointment);
        }
        return appointmentBook;
    }

}
