package seedu.student.testutil;

import seedu.student.logic.commands.EditCommand.EditStudentDescriptor;
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
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setMatriculationNumber(student.getMatriculationNumber());
        descriptor.setFaculty(student.getFaculty());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setVaccinationStatus(student.getVaccinationStatus());
        descriptor.setMedicalDetails(student.getMedicalDetails());
        descriptor.setSchoolResidence(student.getSchoolResidence());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code MatricuationNumber} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withMatric(String matric) {
        descriptor.setMatriculationNumber(new MatriculationNumber(matric));
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withFaculty(String faculty) {
        descriptor.setFaculty(new Faculty(faculty));
        return this;
    }
    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code vaccinationStatus} of the {@code vaccinationStatus} that we are building.
     */
    public EditStudentDescriptorBuilder withVacStatus(String vaccinationStatus) {
        descriptor.setVaccinationStatus(new VaccinationStatus(vaccinationStatus));
        return this;
    }

    /**
     * Sets the {@code medicalDetails} of the {@code medicalDetails} that we are building.
     */
    public EditStudentDescriptorBuilder withMedDetails(String medicalDetails) {
        descriptor.setMedicalDetails(new MedicalDetails(medicalDetails));
        return this;
    }

    /**
     * Sets the {@code schoolResidence} of the {@code schoolResidence} that we are building.
     */
    public EditStudentDescriptorBuilder withSchoolRes(String schoolResidence) {
        descriptor.setSchoolResidence(new SchoolResidence(schoolResidence));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
