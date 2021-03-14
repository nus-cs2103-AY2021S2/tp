package seedu.address.model.medical;

import seedu.address.model.person.Person;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static seedu.address.model.medical.DateFormat.DATE_FORMAT_DISPLAY;
import static seedu.address.model.medical.DateFormat.DATE_FORMAT_STORAGE;

public class MedicalRecord {
    // attributes from appointment
    Person person;
    LocalDateTime date;
    String zoomMeetingURL;

    // related to the medical record
    List<Section> sections;

    // create medical record manually
    public MedicalRecord(Person person, LocalDateTime date, List<String> sections) {
        this.person = person;
        this.date = date;
        this.sections = new ArrayList<>();
        for (String section : sections){
            this.sections.add(new Section(section));
        }
    }
//TODO REMOVE PERSON FROM MEDICAL RECORD
    public MedicalRecord(LocalDateTime date, List<Section> sections) {
        this.date = date;
        this.sections = sections;
    }

    // create medical record from appointment
    public MedicalRecord(Appointment appointment, List<String> sections) {
        this.person = appointment.person;
        this.date = appointment.date;
        this.zoomMeetingURL = appointment.zoomMeetingURL;
        this.sections = new ArrayList<>();
        for (String section : sections){
            this.sections.add(new Section(section));
        }
    }

    // for storage into JSON
    public String getDateStorage() {
        return date.format(DATE_FORMAT_STORAGE);
    }
    // for displaying, doesnt have year
    public String getDateDisplay() {
        return date.format(DATE_FORMAT_DISPLAY);
    }

    public List<Section> getSections() { return Collections.unmodifiableList(sections);}
}
