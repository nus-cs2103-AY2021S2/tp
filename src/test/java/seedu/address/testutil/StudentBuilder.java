package seedu.address.testutil;

import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Faculty;
import seedu.address.model.student.MatriculationNumber;
import seedu.address.model.student.MedicalDetails;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.SchoolResidence;
import seedu.address.model.student.Student;
import seedu.address.model.student.VaccinationStatus;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_MATRIC = "A0192376F";
    public static final String DEFAULT_FACULTY = "COM";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_VAC_STATUS = "not vaccinated";
    public static final String DEFAULT_MED_DETAILS = "none";
    public static final String DEFAULT_SCHOOL_RESIDENCE = "RVRC";

    private Name name;
    private MatriculationNumber matriculationNumber;
    private Faculty faculty;
    private Phone phone;
    private Email email;
    private Address address;
    private VaccinationStatus vaccinationStatus;
    private MedicalDetails medicalDetails;
    private SchoolResidence schoolResidence;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        matriculationNumber = new MatriculationNumber(DEFAULT_MATRIC);
        faculty = new Faculty(DEFAULT_FACULTY);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        vaccinationStatus = new VaccinationStatus(DEFAULT_VAC_STATUS);
        medicalDetails = new MedicalDetails(DEFAULT_MED_DETAILS);
        schoolResidence = new SchoolResidence(DEFAULT_SCHOOL_RESIDENCE);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        matriculationNumber = studentToCopy.getMatriculationNumber();
        faculty = studentToCopy.getFaculty();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        vaccinationStatus = studentToCopy.getVaccinationStatus();
        medicalDetails = studentToCopy.getMedicalDetails();
        schoolResidence = studentToCopy.getSchoolResidence();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     *Sets the {@code MatriculationNumber} of the {@code Student} that we are building
     */
    public StudentBuilder withMatric(String matric) {
        this.matriculationNumber = new MatriculationNumber(matric);
        return this;
    }

    /**
     *Sets the {@code Faculty} of the {@code Student} that we are building
     */
    public StudentBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     *Sets the {@code VaccinationStatus} of the {@code Student} that we are building
     */
    public StudentBuilder withVacStatus(String vaccinationStatus) {
        this.vaccinationStatus = new VaccinationStatus(vaccinationStatus);
        return this;
    }

    /**
     *Sets the {@code MedicalDetails} of the {@code Student} that we are building
     */
    public StudentBuilder withMedDetails(String medicalDetails) {
        this.medicalDetails = new MedicalDetails(medicalDetails);
        return this;
    }

    /**
     *Sets the {@code schoolResidence} of the {@code Student} that we are building
     */
    public StudentBuilder withSchoolRes(String schoolResidence) {
        this.schoolResidence = new SchoolResidence(schoolResidence);
        return this;
    }

    /**
     * Builds a new Student object.
     *
     * @return a student object.
     */
    public Student build() {
        return new Student(name, matriculationNumber, faculty, phone, email, address, vaccinationStatus, medicalDetails,
                schoolResidence);
    }

}
