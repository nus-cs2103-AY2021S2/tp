package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final UUID patient;
    private final String doctor;
    private final JsonAdaptedTimeslot timeslot;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patient") UUID patient,
                                  @JsonProperty("doctor") String doctor,
                                  @JsonProperty("timeslot") JsonAdaptedTimeslot timeslot,
                                  @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.patient = patient;
        this.doctor = doctor;
        this.timeslot = timeslot;

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        patient = source.getPatientUuid();
        doctor = source.getDoctor();
        timeslot = new JsonAdaptedTimeslot(source.getTimeslot());

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Appointment toModelType() throws IllegalValueException {
        final List<Tag> appointmentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            appointmentTags.add(tag.toModelType());
        }

        if (patient == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "patient"));
        }

        final UUID modelPatientUuid = patient;

        // final Patient modelPatient = patient.toModelType();

        if (doctor == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "doctor"));
        }

        final String modelDoctor = doctor;

        if (timeslot == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Timeslot.class.getSimpleName()));
        }

        final Timeslot modelTimeslot = timeslot.toModelType();
        final Set<Tag> modelTags = new HashSet<>(appointmentTags);
        return new Appointment(modelPatientUuid, modelDoctor, modelTimeslot, modelTags);
    }

}
