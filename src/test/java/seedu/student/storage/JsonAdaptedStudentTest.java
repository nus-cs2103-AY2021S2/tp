package seedu.student.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.student.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.student.testutil.Assert.assertThrows;
import static seedu.student.testutil.TypicalStudents.BENSON;

import org.junit.jupiter.api.Test;

import seedu.student.commons.exceptions.IllegalValueException;
import seedu.student.model.student.Address;
import seedu.student.model.student.Email;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MedicalDetails;
import seedu.student.model.student.Name;
import seedu.student.model.student.Phone;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.VaccinationStatus;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_MATRICULATION_NUMBER = "A12345X";
    private static final String INVALID_FACULTY = "FOS";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_VACCINATION_STATUS = "vaccinate";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_MEDICAL_DETAILS = " ";
    private static final String INVALID_SCHOOL_RESIDENCE = "tembu";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_MATRICULATION_NUMBER = BENSON.getMatriculationNumber().toString();
    private static final String VALID_FACULTY = BENSON.getFaculty().value;
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_VACCINATION_STATUS = BENSON.getVaccinationStatus().toString();
    private static final String VALID_MEDICAL_DETAILS = BENSON.getMedicalDetails().value;
    private static final String VALID_SCHOOL_RESIDENCE = BENSON.getSchoolResidence().toSavingString();

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY , VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                        VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidMatriculationNumber_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, INVALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = MatriculationNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullMatriculationNumber_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_PHONE, VALID_FACULTY,
                VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS, VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MatriculationNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidFaculty_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, INVALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Faculty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullFaculty_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                        VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                null, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                        VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, null, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS, VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, null, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidVaccinationStatus_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = VaccinationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullVaccinationStatus_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, VaccinationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidMedicalDetails_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, INVALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = MedicalDetails.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullMedicalDetails_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, null,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MedicalDetails.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidSchoolResidence_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                INVALID_SCHOOL_RESIDENCE);
        String expectedMessage = SchoolResidence.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullSchoolResidence_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SchoolResidence.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }
}
