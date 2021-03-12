package seedu.address.storage;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Time;
import seedu.address.model.name.Name;
import seedu.address.model.person.Person;
import seedu.address.model.remark.Remark;
import seedu.address.model.util.DateTimeFormat;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String name;
    private final String remark;
    private final String date;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("name") String name, @JsonProperty("remark") String remark,
                                  @JsonProperty("date") String date, @JsonProperty("time") String time) {
        this.name = name;
        this.remark = remark;
        this.date = date;
        this.time = time;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        name = source.getName().name;
        remark = source.getRemarks().remark;
        date = source.getDate().toString();
        if (source.getTime() == null) {
            time = null;
        } else {
            time = source.getTime().toString();
        }

    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (remark == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Remark.class.getSimpleName()));
        }

        final Remark modelRemark = new Remark(remark);

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }

        final Date modelDate = new Date(LocalDate.parse(date, DateTimeFormat.OUTPUT_DATE_FORMAT));

        if (time == null) {
            return new Appointment(modelName, modelRemark, modelDate);
        } else {
            final Time modelTime = new Time(LocalTime.parse(time, DateTimeFormat.OUTPUT_TIME_FORMAT));
            return new Appointment(modelName, modelRemark, modelDate, modelTime);
        }
    }
}
