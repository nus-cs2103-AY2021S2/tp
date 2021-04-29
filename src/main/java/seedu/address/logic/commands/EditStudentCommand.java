package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GUARDIAN_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDY_LEVEL;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.session.Session;
import seedu.address.model.student.Address;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;

/**
 * Edits the details of an existing student in the address book.
 */
public class EditStudentCommand extends Command {

    public static final String COMMAND_WORD = "edit_student";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values."
            + "\nParameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_STUDY_LEVEL + "STUDY LEVEL] "
            + "[" + PREFIX_GUARDIAN_PHONE + "GUARDIAN PHONE] "
            + "[" + PREFIX_RELATIONSHIP + "RELATIONSHIP] "
            + "\nExample: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the address book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
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
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setStudent(studentToEdit, editedStudent);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        String updatedStudyLevel = editStudentDescriptor.getStudyLevel().orElse(studentToEdit.getStudyLevel());
        Phone updatedGuardianPhone = editStudentDescriptor.getGuardianPhone().orElse(studentToEdit.getGuardianPhone());
        String updatedRelationship = editStudentDescriptor.getRelationship().orElse(studentToEdit.getRelationship());
        List<Session> updatedSessions = editStudentDescriptor.getSessions().orElse(studentToEdit.getListOfSessions());

        return new Student(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedStudyLevel,
            updatedGuardianPhone, updatedRelationship, updatedSessions);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private String studyLevel;
        private Phone guardianPhone;
        private String relationship;
        private List<Session> sessions;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setStudyLevel(toCopy.studyLevel);
            setGuardianPhone(toCopy.guardianPhone);
            setRelationship(toCopy.relationship);
            setSessions(toCopy.sessions);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, studyLevel, guardianPhone, relationship);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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

        public void setStudyLevel(String studyLevel) {
            this.studyLevel = studyLevel;
        }

        public Optional<String> getStudyLevel() {
            return Optional.ofNullable(studyLevel);
        }

        public void setGuardianPhone(Phone guardianPhone) {
            this.guardianPhone = guardianPhone;
        }

        public Optional<Phone> getGuardianPhone() {
            return Optional.ofNullable(guardianPhone);
        }

        public void setRelationship(String relationship) {
            this.relationship = relationship;
        }

        public Optional<String> getRelationship() {
            return Optional.ofNullable(relationship);
        }

        public void setSessions(List<Session> sessions) {
            this.sessions = sessions;
        }

        public Optional<List<Session>> getSessions() {
            return Optional.ofNullable(sessions);
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
            EditStudentDescriptor otherStudentDescriptor = (EditStudentDescriptor) other;

            return getName().equals(otherStudentDescriptor.getName())
                    && getPhone().equals(otherStudentDescriptor.getPhone())
                    && getEmail().equals(otherStudentDescriptor.getEmail())
                    && getAddress().equals(otherStudentDescriptor.getAddress())
                    && getStudyLevel().equals(otherStudentDescriptor.getStudyLevel())
                    && getGuardianPhone().equals(otherStudentDescriptor.getGuardianPhone())
                    && getRelationship().equals(otherStudentDescriptor.getRelationship());
        }
    }
}
