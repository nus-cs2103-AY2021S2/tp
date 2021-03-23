package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MedicalDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.VaccinationStatus;

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
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStudent person = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(INVALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY , VALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                        VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(null, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMatriculationNumber_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, INVALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = MatriculationNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMatriculationNumber_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, null, VALID_PHONE, VALID_FACULTY,
                VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS, VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MatriculationNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidFaculty_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, INVALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Faculty.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullFaculty_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, null,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Faculty.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }



    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY, INVALID_PHONE,
                        VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                        VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                null, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY, VALID_PHONE,
                        INVALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                        VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, null, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStudent person =
                new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY, VALID_PHONE, VALID_EMAIL,
                        INVALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS, VALID_SCHOOL_RESIDENCE);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, null, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidVaccinationStatus_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, INVALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = VaccinationStatus.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullVaccinationStatus_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, null, VALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, VaccinationStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidMedicalDetails_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, INVALID_MEDICAL_DETAILS,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = MedicalDetails.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullMedicalDetails_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, null,
                VALID_SCHOOL_RESIDENCE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, MedicalDetails.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidSchoolResidence_throwsIllegalValueException() {
        JsonAdaptedStudent person = new JsonAdaptedStudent(VALID_NAME, VALID_MATRICULATION_NUMBER, VALID_FACULTY,
                VALID_PHONE, VALID_EMAIL, VALID_ADDRESS, VALID_VACCINATION_STATUS, VALID_MEDICAL_DETAILS,
                INVALID_SCHOOL_RESIDENCE);
        String expectedMessage = SchoolResidence.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
