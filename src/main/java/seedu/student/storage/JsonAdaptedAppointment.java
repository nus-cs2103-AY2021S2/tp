package seedu.student.storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.student.commons.exceptions.IllegalValueException;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public static final String INVALID_DATETIME_FORMAT_MESSAGE = "Invalid date or time is given";

    private final String matriculationNumber;
    private final String date;
    private final String startTime;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(
                              @JsonProperty("matriculationNumber") String matriculationNumber,
                              @JsonProperty("date") String date,
                              @JsonProperty("startTime") String startTime) {
        this.matriculationNumber = matriculationNumber;
        this.date = date;
        this.startTime = startTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        matriculationNumber = source.getMatriculationNumber().value;
        date = source.getDate().toString();
        startTime = source.getStartTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Appointment toModelType() throws IllegalValueException {

        LocalDate modelDate;
        LocalTime modelStartTime;

        if (matriculationNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MatriculationNumber.class.getSimpleName()));
        }
        if (!MatriculationNumber.isValidMatric(matriculationNumber)) {
            throw new IllegalValueException(MatriculationNumber.MESSAGE_CONSTRAINTS);
        }
        final MatriculationNumber modelMatric = new MatriculationNumber(matriculationNumber);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Date.class.getSimpleName()));
        }

        if (startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "startTime"));
        }

        try {
            modelDate = LocalDate.parse(date);
            modelStartTime = LocalTime.parse(startTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(INVALID_DATETIME_FORMAT_MESSAGE, ""));
        }

        if (!Appointment.isValidTime(modelStartTime)) {
            throw new IllegalValueException(String.format(Appointment.MESSAGE_TIME_CONSTRAINTS, ""));
        }

        return new Appointment(modelMatric, modelDate, modelStartTime);
    }

}
