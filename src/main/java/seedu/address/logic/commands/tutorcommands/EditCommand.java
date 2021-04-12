package seedu.address.logic.commands.tutorcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDUCATION_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TUTORS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.subject.SubjectList;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutor.Address;
import seedu.address.model.tutor.Email;
import seedu.address.model.tutor.Gender;
import seedu.address.model.tutor.Name;
import seedu.address.model.tutor.Notes;
import seedu.address.model.tutor.Phone;
import seedu.address.model.tutor.Tutor;

/**
 * Edits the details of an existing tutor in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit_tutor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the tutor identified "
            + "by the index number used in the displayed tutor list. "
            + "Existing values will be overwritten by the input values. The tutor must already have notes to edit it\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_GENDER + "GENDER] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[<" + PREFIX_SUBJECT_NAME + "SUBJECT_NAME"
            + " " + PREFIX_EDUCATION_LEVEL + "EDUCATION_LEVEL"
            + " " + PREFIX_RATE + "RATE"
            + " " + PREFIX_YEAR + "YEARS EXPERIENCE"
            + " " + PREFIX_QUALIFICATION + "QUALIFICATION>]... "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_NOTES + "NOTES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_SUBJECT_NAME + "English "
            + PREFIX_EDUCATION_LEVEL + "Sec 3 "
            + PREFIX_RATE + "50 "
            + PREFIX_YEAR + "5 "
            + PREFIX_QUALIFICATION + "A-Level>]...";

    public static final String MESSAGE_EDIT_TUTOR_SUCCESS = "Edited Tutor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TUTOR = "This tutor already exists "
            + "in the address book.";
    public static final String MESSAGE_DOES_NOT_HAVE_NOTES = "Tutor: %s does not have notes, "
            + "try add_note command before attempting to edit it";


    private final Index index;
    private final EditTutorDescriptor editTutorDescriptor;

    /**
     * @param index of the tutor in the filtered tutor list to edit
     * @param editTutorDescriptor details to edit the tutor with
     */
    public EditCommand(Index index, EditTutorDescriptor editTutorDescriptor) {
        requireNonNull(index);
        requireNonNull(editTutorDescriptor);

        this.index = index;
        this.editTutorDescriptor = new EditTutorDescriptor(editTutorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutorToEdit = lastShownList.get(index.getZeroBased());

        if (!tutorToEdit.hasNotes() && editTutorDescriptor.getNotes().isPresent()) {
            throw new CommandException(String.format(MESSAGE_DOES_NOT_HAVE_NOTES, tutorToEdit.getName().toString()));
        }

        Tutor editedTutor = createEditedTutor(tutorToEdit, editTutorDescriptor);

        if (!tutorToEdit.isSameTutor(editedTutor) && model.hasTutor(editedTutor)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTOR);
        }

        if (model.hasAppointmentContainingTutor(tutorToEdit.getName())) {
            model.changeAllAppointmentsToName(tutorToEdit.getName(), editedTutor.getName());
        }

        model.setTutor(tutorToEdit, editedTutor);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor));
    }

    /**
     * Creates and returns a {@code Tutor} with the details of {@code tutorToEdit}
     * edited with {@code editTutorDescriptor}.
     */
    public static Tutor createEditedTutor(Tutor tutorToEdit, EditTutorDescriptor editTutorDescriptor) {
        assert tutorToEdit != null;

        Name updatedName = editTutorDescriptor.getName().orElse(tutorToEdit.getName());
        Gender updatedGender = editTutorDescriptor.getGender().orElse(tutorToEdit.getGender());
        Phone updatedPhone = editTutorDescriptor.getPhone().orElse(tutorToEdit.getPhone());
        Email updatedEmail = editTutorDescriptor.getEmail().orElse(tutorToEdit.getEmail());
        Address updatedAddress = editTutorDescriptor.getAddress().orElse(tutorToEdit.getAddress());
        Notes updatedNotes = editTutorDescriptor.getNotes().orElse(tutorToEdit.getNotes());
        Set<Tag> updatedTags = editTutorDescriptor.getTags().orElse(tutorToEdit.getTags());
        SubjectList updatedSubjectList = editTutorDescriptor.getSubjectList().orElse(tutorToEdit.getSubjectList());
        boolean isFavourite = editTutorDescriptor.getIsFavourite().orElse(tutorToEdit.isFavourite());

        return new Tutor(updatedName, updatedGender, updatedPhone, updatedEmail, updatedAddress,
                updatedNotes, updatedSubjectList, updatedTags, isFavourite);
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
                && editTutorDescriptor.equals(e.editTutorDescriptor);
    }

    /**
     * Stores the details to edit the tutor with. Each non-empty field value will replace the
     * corresponding field value of the tutor.
     */
    public static class EditTutorDescriptor {
        private Name name;
        private Gender gender;
        private Phone phone;
        private Email email;
        private Address address;
        private Notes notes;
        private Set<Tag> tags;
        private SubjectList subjectList;

        private Boolean isFavourite;

        public EditTutorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTutorDescriptor(EditTutorDescriptor toCopy) {
            setName(toCopy.name);
            setGender(toCopy.gender);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setNotes(toCopy.notes);
            setTags(toCopy.tags);
            setSubjectList(toCopy.subjectList);
            setIsFavourite(toCopy.isFavourite);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, gender, phone, email, address, notes, tags, subjectList);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Optional<Gender> getGender() {
            return Optional.ofNullable(gender);
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

        public void setNotes(Notes notes) {
            this.notes = notes;
        }

        public Optional<Notes> getNotes() {
            return Optional.ofNullable(notes);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        public void setSubjectList(SubjectList subjectList) {
            this.subjectList = subjectList;
        }

        public Optional<SubjectList> getSubjectList() {
            return Optional.ofNullable(subjectList);
        }

        public void setIsFavourite(Boolean isFavourite) {
            this.isFavourite = isFavourite;
        }

        public Optional<Boolean> getIsFavourite() {
            return Optional.ofNullable(isFavourite);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTutorDescriptor)) {
                return false;
            }

            // state check
            EditTutorDescriptor e = (EditTutorDescriptor) other;

            // Convert empty lists to none optionals
            // Required to pass unit tests involving EditTutorDescriptor
            Optional<SubjectList> subjectList = getSubjectList();
            Optional<SubjectList> otherSubjectList = e.getSubjectList();
            if (subjectList.isPresent()
                    && subjectList.get().asUnmodifiableObservableList().isEmpty()) {
                subjectList = Optional.empty();
            }
            if (otherSubjectList.isPresent()
                    && otherSubjectList.get().asUnmodifiableObservableList().isEmpty()) {
                otherSubjectList = Optional.empty();
            }

            return getName().equals(e.getName())
                    && getGender().equals(e.getGender())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getNotes().equals(e.getNotes())
                    && getTags().equals(e.getTags())
                    && subjectList.equals(otherSubjectList)
                    && getIsFavourite().equals(e.getIsFavourite());
        }
    }
}

