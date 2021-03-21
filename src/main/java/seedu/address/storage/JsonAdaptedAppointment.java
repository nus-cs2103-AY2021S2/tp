package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDateTime;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.subject.SubjectName;


public class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field"
            + " is missing!";

    //TODO Replace all email with name in next iteration
    private String name;
    private final String subject;
    private final String timeFrom;
    private final String timeTo;
    private final String location;

    /**
     * Primary Constructor to create Json Adapted Appointment
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name,
                                  @JsonProperty("subjectname") String subject,
                                  @JsonProperty("timeFrom") String timeFrom,
                                  @JsonProperty("timeTo") String timeTo,
                                  @JsonProperty("address") String address) {
        this.name = name;
        this.subject = subject;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.location = address;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment appointment) {
        this.name = appointment.getName().fullName;
        this.subject = appointment.getSubject().name;
        this.timeFrom = appointment.getTimeFrom().toStorageString();
        this.timeTo = appointment.getTimeTo().toStorageString();
        this.location = appointment.getLocation().value;
    }


    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code
     * Appointment } object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(location)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(location);


        if (timeFrom == null || timeTo == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AppointmentDateTime.class.getSimpleName()));
        }

        if (!AppointmentDateTime.isValidDateTime(timeFrom)
                || !AppointmentDateTime.isValidDateTime(timeTo)) {
            throw new IllegalValueException(AppointmentDateTime.MESSAGE_CONSTRAINTS);
        }

        final AppointmentDateTime fromDateTime = new AppointmentDateTime(timeFrom);
        final AppointmentDateTime toDateTime = new AppointmentDateTime(timeTo);


        if (subject == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SubjectName.class.getSimpleName()));
        }

        if (!SubjectName.isValidName(subject)) {
            throw new IllegalValueException(SubjectName.MESSAGE_CONSTRAINTS);
        }

        final SubjectName modelSubjectName = new SubjectName(subject);

        return new Appointment(modelName, modelSubjectName, fromDateTime, toDateTime,
                modelAddress);

    }
}
