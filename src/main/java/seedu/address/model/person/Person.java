package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final MatriculationNumber matriculationNumber;
    private final Faculty faculty;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final VaccinationStatus vaccinationStatus;
    private final MedicalDetails medicalDetails;
    private final SchoolResidence schoolResidence;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, MatriculationNumber matriculationNumber, Faculty faculty, Phone phone, Email email,
                  Address address, VaccinationStatus vaccinationStatus, MedicalDetails medicalDetails,
                  SchoolResidence schoolResidence) {
        requireAllNonNull(name, phone, email, address);
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

    public Name getName() {
        return name;
    }

    public MatriculationNumber getMatriculationNumber() {
        return this.matriculationNumber;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public MedicalDetails getMedicalDetails() {
        return medicalDetails;
    }

    public SchoolResidence getSchoolResidence() {
        return schoolResidence;
    }

    /**
     * Returns true if both persons have the same matriculation number.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getMatriculationNumber().equals(getMatriculationNumber());
    }

    public boolean isVaccinated() {
        return this.vaccinationStatus.status == VaccinationStatus.VaccinationStatusAbbreviation.VACCINATED;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getMatriculationNumber().equals(getMatriculationNumber())
                && otherPerson.getFaculty().equals(getFaculty())
                && otherPerson.getMedicalDetails().equals(getMedicalDetails())
                && otherPerson.getVaccinationStatus().equals(getVaccinationStatus())
                && otherPerson.getSchoolResidence().equals(getSchoolResidence());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Matriculation Number: ")
                .append(getMatriculationNumber())
                .append("; Faculty: ")
                .append(getFaculty())
                .append("; Vaccination Status: ")
                .append(getVaccinationStatus())
                .append("; Medical Details: ")
                .append(getMedicalDetails())
                .append("; Vaccination Status: ")
                .append(getVaccinationStatus())
                .append("; School Residence: ")
                .append(getSchoolResidence().toString()); // DOES NOT LIVE ON CAMPUS -> For UI
        return builder.toString();
    }
}
