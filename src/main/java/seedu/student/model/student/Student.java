package seedu.student.model.student;

import static seedu.student.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

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
    public Student(Name name, MatriculationNumber matriculationNumber, Faculty faculty, Phone phone, Email email,
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
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getMatriculationNumber().equals(getMatriculationNumber());
    }

    public boolean isVaccinated() {
        return this.vaccinationStatus.status == VaccinationStatus.VaccinationStatusKeywords.VACCINATED;
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

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getMatriculationNumber().equals(getMatriculationNumber())
                && otherStudent.getFaculty().equals(getFaculty())
                && otherStudent.getMedicalDetails().equals(getMedicalDetails())
                && otherStudent.getVaccinationStatus().equals(getVaccinationStatus())
                && otherStudent.getSchoolResidence().equals(getSchoolResidence());
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
                .append("; School Residence: ")
                .append(getSchoolResidence().toString()); // DOES NOT LIVE ON CAMPUS -> For UI
        return builder.toString();
    }
}
