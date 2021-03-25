package seedu.address.logic.commands.resident;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_YEAR;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENTS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;
import seedu.address.model.resident.Year;

/**
 * Edits the details of an existing resident in the address book.
 */
public class EditResidentCommand extends Command {

    public static final String COMMAND_WORD = "redit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the resident identified "
            + "by the index number used in the displayed resident list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_YEAR + "YEAR] "
            + "[" + PREFIX_ROOM + "ROOM]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "e0123456@u.nus.edu "
            + PREFIX_ROOM + "01-234";


    public static final String MESSAGE_EDIT_RESIDENT_SUCCESS = "Edited Resident: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RESIDENT = "This resident already exists in the address book.";

    private final Index index;
    private final EditResidentDescriptor editResidentDescriptor;

    /**
     * @param index of the resident in the filtered resident list to edit
     * @param editResidentDescriptor details to edit the resident with
     */
    public EditResidentCommand(Index index, EditResidentDescriptor editResidentDescriptor) {
        requireNonNull(index);
        requireNonNull(editResidentDescriptor);

        this.index = index;
        this.editResidentDescriptor = new EditResidentDescriptor(editResidentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Resident> lastShownList = model.getFilteredResidentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENT_DISPLAYED_INDEX);
        }

        Resident residentToEdit = lastShownList.get(index.getZeroBased());
        Resident editedResident = createEditedResident(residentToEdit, editResidentDescriptor);

        if (!residentToEdit.isSameResident(editedResident) && model.hasResident(editedResident)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENT);
        }

        model.setResident(residentToEdit, editedResident);
        model.updateFilteredResidentList(PREDICATE_SHOW_ALL_RESIDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_RESIDENT_SUCCESS, editedResident)).setResidentCommand();
    }

    /**
     * Creates and returns a {@code Resident} with the details of {@code residentToEdit}
     * edited with {@code editResidentDescriptor}.
     */
    public static Resident createEditedResident(Resident residentToEdit,
            EditResidentDescriptor editResidentDescriptor) {
        assert residentToEdit != null;

        Name updatedName = editResidentDescriptor.getName().orElse(residentToEdit.getName());
        Phone updatedPhone = editResidentDescriptor.getPhone().orElse(residentToEdit.getPhone());
        Email updatedEmail = editResidentDescriptor.getEmail().orElse(residentToEdit.getEmail());
        Year updatedYear = editResidentDescriptor.getYear().orElse(residentToEdit.getYear());
        Room updatedRoom = editResidentDescriptor.getRoom().orElse(residentToEdit.getRoom());

        return new Resident(updatedName, updatedPhone, updatedEmail, updatedYear, updatedRoom);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditResidentCommand)) {
            return false;
        }

        // state check
        EditResidentCommand e = (EditResidentCommand) other;
        return index.equals(e.index)
                && editResidentDescriptor.equals(e.editResidentDescriptor);
    }

    /**
     * Stores the details to edit the resident with. Each non-empty field value will replace the
     * corresponding field value of the resident.
     */
    public static class EditResidentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Year year;
        private Room room;

        public EditResidentDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditResidentDescriptor(EditResidentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setYear(toCopy.year);
            setRoom(toCopy.room);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, room);
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

        public void setYear(Year year) {
            this.year = year;
        }

        public Optional<Year> getYear() {
            return Optional.ofNullable(year);
        }

        public void setRoom(Room room) {
            this.room = room;
        }

        public Optional<Room> getRoom() {
            return Optional.ofNullable(room);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditResidentDescriptor)) {
                return false;
            }

            // state check
            EditResidentDescriptor e = (EditResidentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getYear().equals(e.getYear())
                    && getRoom().equals(e.getRoom());
        }
    }
}
