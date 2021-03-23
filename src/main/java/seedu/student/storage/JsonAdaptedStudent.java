package seedu.student.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.student.commons.exceptions.IllegalValueException;
import seedu.student.model.student.Address;
import seedu.student.model.student.Email;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.MedicalDetails;
import seedu.student.model.student.Name;
import seedu.student.model.student.Phone;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;
import seedu.student.model.student.VaccinationStatus;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String matriculationNumber;
    private final String faculty;
    private final String phone;
    private final String email;
    private final String address;
    private final String vaccinationStatus;
    private final String medicalDetails;
    private final String schoolResidence;

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name,
                              @JsonProperty("matriculationNumber") String matriculationNumber,
                              @JsonProperty("faculty") String faculty,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("address") String address,
                              @JsonProperty("vaccinationStatus") String vaccinationStatus,
                              @JsonProperty("medicalDetails") String medicalDetails,
                              @JsonProperty("schoolResidence") String schoolResidence) {
        this.name = name;
        this.matriculationNumber = matriculationNumber;
        this.faculty = faculty;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.vaccinationStatus = vaccinationStatus;
        this.medicalDetails = medicalDetails;
        this.schoolResidence = schoolResidence;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        matriculationNumber = source.getMatriculationNumber().value;
        faculty = source.getFaculty().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        vaccinationStatus = source.getVaccinationStatus().textUI;
        medicalDetails = source.getMedicalDetails().value;
        schoolResidence = source.getSchoolResidence().residenceAbbreviation.name();
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (matriculationNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MatriculationNumber.class.getSimpleName()));
        }
        if (!MatriculationNumber.isValidMatric(matriculationNumber)) {
            throw new IllegalValueException(MatriculationNumber.MESSAGE_CONSTRAINTS);
        }
        final MatriculationNumber modelMatric = new MatriculationNumber(matriculationNumber);


        if (faculty == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Faculty.class.getSimpleName()));
        }
        if (!Faculty.isValidFaculty(faculty)) {
            throw new IllegalValueException(Faculty.MESSAGE_CONSTRAINTS);
        }

        final Faculty modelFaculty = new Faculty(faculty);


        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (vaccinationStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    VaccinationStatus.class.getSimpleName()));
        }
        if (!VaccinationStatus.isValidStatus(vaccinationStatus)) {
            throw new IllegalValueException(VaccinationStatus.MESSAGE_CONSTRAINTS);
        }

        final VaccinationStatus modelVacStatus = new VaccinationStatus(vaccinationStatus);


        if (medicalDetails == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    MedicalDetails.class.getSimpleName()));
        }
        if (!MedicalDetails.isValidMedicalDetails(medicalDetails)) {
            throw new IllegalValueException(MedicalDetails.MESSAGE_CONSTRAINTS);
        }
        final MedicalDetails modelMedDetails = new MedicalDetails(medicalDetails);

        if (!SchoolResidence.isValidResidence(schoolResidence)) {
            throw new IllegalValueException(SchoolResidence.MESSAGE_CONSTRAINTS);
        }

        final SchoolResidence modelSchoolRes = new SchoolResidence(schoolResidence);

        return new Student(modelName, modelMatric, modelFaculty, modelPhone, modelEmail, modelAddress, modelVacStatus,
                modelMedDetails, modelSchoolRes);
    }

}
