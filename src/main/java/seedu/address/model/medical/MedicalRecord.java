package seedu.address.model.medical;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_DISPLAY;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_NO_TIME;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the Medical Record of a Patient.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MedicalRecord {
    // attributes from appointment
    private LocalDateTime date;
    private List<Section> sections;

    /**
     * Every field must be present and not null.
     */
    public MedicalRecord(LocalDateTime date, List<Section> sections) {
        requireAllNonNull(date, sections);
        this.date = date;
        this.sections = sections;
    }

    /**
     * Every field must be present and not null.
     */
    public MedicalRecord(Appointment appointment, List<String> sections) {
        requireAllNonNull(appointment, sections);
        this.date = appointment.getDate();
        this.sections = new ArrayList<>();
        for (String section : sections) {
            this.sections.add(new Section(section));
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Section> getSections() {
        return Collections.unmodifiableList(sections);
    }

    @Override
    public int hashCode() {
        return date.hashCode() + sections.hashCode();
    }

    // for storage into JSON
    public String getDateStorage() {
        return date.format(DATE_FORMAT_STORAGE);
    }
    // for displaying on ui
    public String getDateDisplay() {
        return date.format(DATE_FORMAT_DISPLAY);
    }
    // for displaying on ui
    public String getDateNoTime() {
        return date.format(DATE_FORMAT_NO_TIME);
    }

    @Override
    public String toString() {
        String sectionsString = "";
        for (Section s : this.sections) {
            sectionsString += s.toString();
        }
        return "Record: " + date + " - " + sectionsString;
    }

    /**
     * Two MedicalRecords are equal if they have the same LocalDateTime
     * This makes intuitive sense, since a patient cant have two medical records at the same time
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MedicalRecord)) {
            return false;
        }

        MedicalRecord otherMedicalRecord = (MedicalRecord) other;
        return otherMedicalRecord.getDate().equals(getDate());
    }
}
