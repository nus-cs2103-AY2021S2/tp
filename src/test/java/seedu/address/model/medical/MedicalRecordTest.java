package seedu.address.model.medical;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;


public class MedicalRecordTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MedicalRecord(LocalDateTime.of(2021, 12, 12, 18, 00), null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        LocalDateTime dateTime = LocalDateTime.of(2021, 12, 12, 18, 00);
        Section section = new Section("test");
        List<Section> sections = new ArrayList<>();
        sections.add(section);
        MedicalRecord medicalRecord = new MedicalRecord(dateTime, sections);


        // same object -> returns true
        assertTrue(medicalRecord.equals(medicalRecord));

        // null -> returns false
        assertFalse(medicalRecord.equals(null));

        // different type -> returns false
        assertFalse(medicalRecord.equals(5));

        // different DateTime and same sections -> returns false
        LocalDateTime dateTimeDifferentCopy = LocalDateTime.of(2022, 12, 12, 18, 00);
        MedicalRecord medicalRecordDifferentDate = new MedicalRecord(dateTimeDifferentCopy, sections);
        assertFalse(medicalRecord.equals(medicalRecordDifferentDate));

        // same Datetime and different sections -> returns true
        Section sectionEdited = new Section("different text");
        List<Section> sectionsEdited = new ArrayList<>();
        sectionsEdited.add(sectionEdited);
        MedicalRecord medicalRecordDifferentSection = new MedicalRecord(dateTime, sectionsEdited);
        assertTrue(medicalRecord.equals(medicalRecordDifferentSection));

    }
}
