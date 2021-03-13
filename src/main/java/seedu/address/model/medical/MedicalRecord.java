package seedu.address.model.medical;

import seedu.address.model.person.Person;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    // attributes from appointment
    Person person;
    LocalDateTime date;
    String zoomMeetingURL;

    // related to the medical record
    List<Section> record;

    // create medical record manually
    public MedicalRecord(Person person, LocalDateTime date, List<String> sections) {
        this.person = person;
        this.date = date;
        this.record = new ArrayList<>();
        for (String section : sections){
            this.record.add(new Section(section));
        }
    }

    // create medical record from appointment
    public MedicalRecord(Appointment appointment, List<String> sections) {
        this.person = appointment.person;
        this.date = appointment.date;
        this.zoomMeetingURL = appointment.zoomMeetingURL;
        this.record = new ArrayList<>();
        for (String section : sections){
            this.record.add(new Section(section));
        }
    }
}
