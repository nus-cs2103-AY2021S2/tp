package seedu.address.storage;

import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.medical.Appointment;
import seedu.address.model.medical.MedicalRecord;
import seedu.address.model.medical.Section;

public class JsonAdaptedRecord {
    private final String dateString;
    private final List<JsonAdaptedSection> sections = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedRecord(@JsonProperty("dateString") String dateString,
                             @JsonProperty("sections") List<JsonAdaptedSection> sections) {
        this.dateString = dateString;
        if (sections != null) {
            this.sections.addAll(sections);
        }
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedRecord(MedicalRecord source) {
        dateString = source.getDateStorage();
        sections.addAll(source.getSections().stream()
                .map(JsonAdaptedSection::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public MedicalRecord toModelType() throws IllegalValueException {
        final LocalDateTime date;
        try {
            date = LocalDateTime.parse(dateString, DATE_FORMAT_STORAGE);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(Appointment.MESSAGE_CONSTRAINTS_DATE_FORMAT);
        }

        final List<Section> recordSections = new ArrayList<>();
        for (JsonAdaptedSection section : sections) {
            recordSections.add(section.toModelType());
        }
        return new MedicalRecord(date, recordSections);
    }
}
