package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Name;
import seedu.address.model.booking.Phone;
import seedu.address.model.residence.Residence;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

public class EditBookingCommand extends Command {
    public static final String COMMAND_WORD = "editb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the booking identified "
            + "by the index number used in the displayed residence list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLEAN_STATUS_TAG + "n]\n"
            + "Example: " + COMMAND_WORD + "1 "
            + PREFIX_CLEAN_STATUS_TAG + "y";

    public static final String MESSAGE_EDIT_RESIDENCE_SUCCESS = "Edited Residence: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RESIDENCE = "This residence already exists in the residence tracker.";

    private final Index residenceIndex;
    private final Index bookingIndex;
    private final EditBookingDescriptor editBookingDescriptor;

    public EditBookingCommand(Index residenceIndex, Index bookingIndex, EditBookingDescriptor editBookingDescriptor) {
        requireNonNull(residenceIndex);
        requireNonNull(bookingIndex);

        this.residenceIndex = residenceIndex;
        this.bookingIndex = bookingIndex;
        this.editBookingDescriptor = new EditBookingDescriptor(editBookingDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Residence> lastShownList = model.getFilteredResidenceList();
        
        if (residenceIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RESIDENCE_DISPLAYED_INDEX);
        }

        Residence residenceToEdit = lastShownList.get(index.getZeroBased());
        Residence editedResidence = createEditedResidence(residenceToEdit, editResidenceDescriptor);

        if (!residenceToEdit.isSameResidence(editedResidence) && model.hasResidence(editedResidence)) {
            throw new CommandException(MESSAGE_DUPLICATE_RESIDENCE);
        }

        model.setResidence(residenceToEdit, editedResidence);
        model.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        return new CommandResult(String.format(MESSAGE_EDIT_RESIDENCE_SUCCESS, editedResidence));
    }

    /**
     * Creates and returns a {@code Residence} with the details of {@code residenceToEdit}
     * edited with {@code editResidenceDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit,
                                               EditBookingDescriptor editBookingDescriptor) {
        assert bookingToEdit != null;

        Name updatedName = editBookingDescriptor.getName()
                .orElse(bookingToEdit.getName());
        Phone updatedPhone = editBookingDescriptor.getPhone()
                .orElse(bookingToEdit.getPhone());
        LocalDate updatedStartTime = editBookingDescriptor.getStartDate()
                .orElse(bookingToEdit.getStart());
        LocalDate updatedEndTime = editBookingDescriptor.getEndDate()
                .orElse(bookingToEdit.getEnd());

        return new Booking(updatedName, updatedPhone, updatedStartTime, updatedEndTime);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditBookingCommand)) {
            return false;
        }

        // state check
        EditBookingCommand e = (EditBookingCommand) other;
        return residenceIndex.equals(e.residenceIndex)
                && bookingIndex.equals(e.bookingIndex)
                && editBookingDescriptor.equals(e.editBookingDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditBookingDescriptor {
        private Name name;
        private Phone phone;
        private LocalDate startDate;
        private LocalDate endDate;

        public EditBookingDescriptor() {
        }

        public EditBookingDescriptor(EditBookingDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, startDate, endDate);
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

        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public Optional<LocalDate> getStartDate() {
            return Optional.ofNullable(startDate);
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public Optional<LocalDate> getEndDate() {
            return Optional.ofNullable(endDate);
        }


        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditBookingDescriptor)) {
                return false;
            }

            // state check
            EditBookingDescriptor e = (EditBookingDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getStartDate().equals(e.getStartDate())
                    && getEndDate().equals(e.getEndDate());
        }
    }
}
