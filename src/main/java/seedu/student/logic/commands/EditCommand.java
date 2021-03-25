package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.student.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.student.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.student.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.student.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.student.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import seedu.student.commons.core.Messages;
import seedu.student.commons.core.index.Index;
import seedu.student.commons.util.CollectionUtil;
import seedu.student.logic.commands.exceptions.CommandException;
import seedu.student.model.Model;
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
 * Edits the details of an existing student in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_MATRICULATION_NUMBER + "MATRICULATION NUMBER] "
            + "[" + PREFIX_FACULTY + "FACULTY] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_VACCINATION_STATUS + "VACCINATION STATUS] "
            + "[" + PREFIX_MEDICAL_DETAILS + "MEDICAL DETAILS] "
            + "[" + PREFIX_SCHOOL_RESIDENCE + "SCHOOL RESIDENCE] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the records.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_OUT_OF_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        MatriculationNumber updatedMatriculationNumber = editStudentDescriptor.getMatriculationNumber()
                .orElse(studentToEdit.getMatriculationNumber());
        Faculty updatedFaculty = editStudentDescriptor.getFaculty().orElse(studentToEdit.getFaculty());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        VaccinationStatus updatedVaccinationStatus = editStudentDescriptor.getVaccinationStatus()
                .orElse(studentToEdit.getVaccinationStatus());
        MedicalDetails updatedMedicalDetails = editStudentDescriptor.getMedicalDetails()
                .orElse(studentToEdit.getMedicalDetails());
        SchoolResidence updatedSchoolResidence = editStudentDescriptor.getSchoolResidence()
                .orElse(studentToEdit.getSchoolResidence());

        return new Student(updatedName, updatedMatriculationNumber, updatedFaculty, updatedPhone, updatedEmail,
                updatedAddress, updatedVaccinationStatus, updatedMedicalDetails, updatedSchoolResidence);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private MatriculationNumber matriculationNumber;
        private Faculty faculty;
        private Phone phone;
        private Email email;
        private Address address;
        private VaccinationStatus vaccinationStatus;
        private MedicalDetails medicalDetails;
        private SchoolResidence schoolResidence;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setMatriculationNumber(toCopy.matriculationNumber);
            setFaculty(toCopy.faculty);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setMedicalDetails(toCopy.medicalDetails);
            setVaccinationStatus(toCopy.vaccinationStatus);
            setSchoolResidence(toCopy.schoolResidence);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, matriculationNumber, vaccinationStatus,
                    medicalDetails, schoolResidence, faculty);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setMatriculationNumber(MatriculationNumber matriculationNumber) {
            this.matriculationNumber = matriculationNumber;
        }

        public Optional<MatriculationNumber> getMatriculationNumber() {
            return Optional.ofNullable(matriculationNumber);
        }

        public void setFaculty(Faculty faculty) {
            this.faculty = faculty;
        }

        public Optional<Faculty> getFaculty() {
            return Optional.ofNullable(faculty);
        }

        public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
            this.vaccinationStatus = vaccinationStatus;
        }

        public Optional<VaccinationStatus> getVaccinationStatus() {
            return Optional.ofNullable(vaccinationStatus);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setMedicalDetails(MedicalDetails medicalDetails) {
            this.medicalDetails = medicalDetails;
        }

        public Optional<MedicalDetails> getMedicalDetails() {
            return Optional.ofNullable(medicalDetails);
        }

        public void setSchoolResidence(SchoolResidence schoolResidence) {
            this.schoolResidence = schoolResidence;
        }

        public Optional<SchoolResidence> getSchoolResidence() {
            return Optional.ofNullable(schoolResidence);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress());
        }
    }
}
