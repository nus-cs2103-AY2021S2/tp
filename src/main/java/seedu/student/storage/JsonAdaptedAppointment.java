package seedu.student.storage;

import java.time.LocalDate;
import java.time.LocalTime;
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

    private final String matriculationNumber;
    private final LocalDate date;
    private final LocalTime startTime;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(
                              @JsonProperty("matriculationNumber") String matriculationNumber,
                              @JsonProperty("date") LocalDate date,
                              @JsonProperty("startTime") LocalTime startTime) {
        this.matriculationNumber = matriculationNumber;
        this.date = date;
        this.startTime = startTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        matriculationNumber = source.getMatriculationNumber().value;
        date = source.getDate();
        startTime = source.getStartTime();
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Appointment toModelType() throws IllegalValueException {

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

        final LocalDate modelDate = date;
        final LocalTime modelStartTime = startTime;

        return new Appointment(modelMatric, modelDate, modelStartTime);
    }

}
