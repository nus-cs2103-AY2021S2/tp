package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.subject.SubjectName;


public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field"
            + " is missing!";

    //TODO Replace all email with name in next iteration
    private String email;
    private final String subject;
    private final String dateTime;
    private final String location;

    /**
     * Primary Constructor to create Json Adapted Appointment
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("email") String email,
                                  @JsonProperty("subjectname") String subject,
                                  @JsonProperty("datetime") String dateTime,
                                  @JsonProperty("address") String address) {
        this.email = email;
        this.subject = subject;
        this.dateTime = dateTime;
        this.location = address;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment appointment) {
        this.email = appointment.getEmail().value;
        this.subject = appointment.getSubject().name;
        this.dateTime = appointment.getDateTime().toStorageString();
        this.location = appointment.getLocation().value;
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code
     * Appointment } object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(location)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(location);


        if (dateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentDateTime.class.getSimpleName()));
        }

        if (!AppointmentDateTime.isValidDateTime(dateTime)) {
            throw new IllegalValueException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }

        final AppointmentDateTime appointmentDateTime =
                new AppointmentDateTime(dateTime);


        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SubjectName.class.getSimpleName()));
        }

        if (!SubjectName.isValidName(subject)) {
            throw new IllegalValueException(SubjectName.MESSAGE_CONSTRAINTS);
        }

        final SubjectName modelSubjectName = new SubjectName(subject);

        return new Appointment(modelEmail, modelSubjectName, appointmentDateTime,
                modelAddress);

    }
}
