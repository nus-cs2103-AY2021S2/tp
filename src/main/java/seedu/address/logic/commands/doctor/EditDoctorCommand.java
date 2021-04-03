package seedu.address.logic.commands.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DOCTORS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.tag.Tag;


/**
 * Edits the details of an existing person in the DoctorRecords.
 */
public class EditDoctorCommand extends Command {

    public static final String COMMAND_WORD = "edit-doctor";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the doctor identified "
            + "by the index number used in the displayed doctor list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_TAG + "TAG]...\n";

    public static final String MESSAGE_EDIT_DOCTOR_SUCCESS = "Edited Doctor: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_DOCTOR = "This Doctor already exists in the Doctor list.";

    private final Index index;
    private final EditDoctorDescriptor editDoctorDescriptor;

    /**
     * @param index of the person in the filtered doctor list to edit
     * @param editDoctorDescriptor details to edit the doctor with
     */
    public EditDoctorCommand(Index index, EditDoctorDescriptor editDoctorDescriptor) {
        requireNonNull(index);
        requireNonNull(editDoctorDescriptor);

        this.index = index;
        this.editDoctorDescriptor = new EditDoctorDescriptor(editDoctorDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Doctor> lastShownList = model.getFilteredDoctorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
        }

        Doctor doctorToEdit = lastShownList.get(index.getZeroBased());
        Doctor editedDoctor = createEditedDoctor(doctorToEdit, editDoctorDescriptor);

        if (!doctorToEdit.isSamePerson(editedDoctor) && model.hasDoctor(editedDoctor)) {
            throw new CommandException(MESSAGE_DUPLICATE_DOCTOR);
        }

        model.setDoctor(doctorToEdit, editedDoctor);
        model.updateFilteredDoctorList(PREDICATE_SHOW_ALL_DOCTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor));
    }

    /**
     * Creates and returns a {@code Doctor} with the details of {@code doctorToEdit}
     * edited with {@code editDoctorDescriptor}.
     */
    private static Doctor createEditedDoctor(Doctor doctorToEdit, EditDoctorDescriptor editDoctorDescriptor) {
        assert doctorToEdit != null;

        UUID doctorUuid = doctorToEdit.getUuid();
        Name updatedName = editDoctorDescriptor.getName().orElse(doctorToEdit.getName());
        Set<Tag> updatedTags = editDoctorDescriptor.getTags().orElse(doctorToEdit.getTags());

        return new Doctor(doctorUuid, updatedName, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditDoctorCommand)) {
            return false;
        }

        // state check
        EditDoctorCommand e = (EditDoctorCommand) other;
        return index.equals(e.index)
                && editDoctorDescriptor.equals(e.editDoctorDescriptor);
    }

    /**
     * Stores the details to edit the doctor with. Each non-empty field value will replace the
     * corresponding field value of the doctor.
     */
    public static class EditDoctorDescriptor {
        private Name name;
        private Set<Tag> tags;

        public EditDoctorDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditDoctorDescriptor(EditDoctorDescriptor toCopy) {
            setName(toCopy.name);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         * UUID is not checked since an edited doctor can have a different UUID,
         * since it may be automatically generated.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
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

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditDoctorDescriptor)) {
                return false;
            }

            // state check
            EditDoctorDescriptor e = (EditDoctorDescriptor) other;

            return getName().equals(e.getName())
                    && getTags().equals(e.getTags());
        }
    }
}
