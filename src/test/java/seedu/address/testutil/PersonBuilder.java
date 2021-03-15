package seedu.address.testutil;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MedicalDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.VaccinationStatus;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

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
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
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
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        matriculationNumber = personToCopy.getMatriculationNumber();
        faculty = personToCopy.getFaculty();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        vaccinationStatus = personToCopy.getVaccinationStatus();
        medicalDetails = personToCopy.getMedicalDetails();
        schoolResidence = personToCopy.getSchoolResidence();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     *Sets the {@code MatriculationNumber} of the {@code Person} that we are building
     */
    public PersonBuilder withMatric(String matric) {
        this.matriculationNumber = new MatriculationNumber(matric);
        return this;
    }

    /**
     *Sets the {@code Faculty} of the {@code Person} that we are building
     */
    public PersonBuilder withFaculty(String faculty) {
        this.faculty = new Faculty(faculty);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     *Sets the {@code VaccinationStatus} of the {@code Person} that we are building
     */
    public PersonBuilder withVacStatus(String vaccinationStatus) {
        this.vaccinationStatus = new VaccinationStatus(vaccinationStatus);
        return this;
    }

    /**
     *Sets the {@code MedicalDetails} of the {@code Person} that we are building
     */
    public PersonBuilder withMedDetails(String medicalDetails) {
        this.medicalDetails = new MedicalDetails(medicalDetails);
        return this;
    }

    /**
     *Sets the {@code schoolResidence} of the {@code Person} that we are building
     */
    public PersonBuilder withSchoolRes(String schoolResidence) {
        this.schoolResidence = new SchoolResidence(schoolResidence);
        return this;
    }

    /**
     * Builds a new Person object.
     *
     * @return a person object.
     */
    public Person build() {
        return new Person(name, matriculationNumber, faculty, phone, email, address, vaccinationStatus, medicalDetails,
                schoolResidence);
    }

}
