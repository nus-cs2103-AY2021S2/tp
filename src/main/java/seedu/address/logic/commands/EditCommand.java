package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLEAN_STATUS_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.ResidenceAddress;
import seedu.address.model.residence.ResidenceName;
import seedu.address.model.tag.CleanStatusTag;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing residence in the residence tracker.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the residence identified "
            + "by the index number used in the displayed residence list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CLEAN_STATUS_TAG + " y/n] "
            + "Example: " + COMMAND_WORD
            + PREFIX_CLEAN_STATUS_TAG + " y 1 ";

    public static final String MESSAGE_EDIT_RESIDENCE_SUCCESS = "Edited Residence: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_RESIDENCE = "This residence already exists in the residence tracker.";

    private final Index index;
    private final EditResidenceDescriptor editResidenceDescriptor;

    /**
     * @param index of the residence in the filtered residence list to edit
     * @param editResidenceDescriptor details to edit the residence with
     */
    public EditCommand(Index index, EditResidenceDescriptor editResidenceDescriptor) {
        requireNonNull(index);
        requireNonNull(editResidenceDescriptor);

        this.index = index;
        this.editResidenceDescriptor = new EditResidenceDescriptor(editResidenceDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Residence> lastShownList = model.getFilteredResidenceList();

        if (index.getZeroBased() >= lastShownList.size()) {
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
    private static Residence createEditedResidence(Residence residenceToEdit,
                                                   EditResidenceDescriptor editResidenceDescriptor) {
        assert residenceToEdit != null;

        ResidenceName updatedName = editResidenceDescriptor.getResidenceName()
                .orElse(residenceToEdit.getResidenceName());
        ResidenceAddress updatedAddress = editResidenceDescriptor.getResidenceAddress()
                .orElse(residenceToEdit.getResidenceAddress());
        BookingList updatedBookingList = editResidenceDescriptor.getBookingDetails()
                .orElse(residenceToEdit.getBookingDetails());
        CleanStatusTag updatedCleanStatus = editResidenceDescriptor.getCleanStatusTag().orElse(
                residenceToEdit.getCleanStatusTag());
        Set<Tag> updatedTags = editResidenceDescriptor.getTags().orElse(residenceToEdit.getTags());

        return new Residence(updatedName, updatedAddress, updatedBookingList, updatedCleanStatus, updatedTags);
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
                && editResidenceDescriptor.equals(e.editResidenceDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditResidenceDescriptor {
        private ResidenceName residenceName;
        private ResidenceAddress residenceAddress;
        // because CleanStatusTag has default value
        private BookingList bookingList = new BookingList();
        // because CleanStatusTag has default value
        private CleanStatusTag cleanStatusTag = new CleanStatusTag();
        private Set<Tag> tags;

        public EditResidenceDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditResidenceDescriptor(EditResidenceDescriptor toCopy) {
            setResidenceName(toCopy.residenceName);
            setResidenceAddress(toCopy.residenceAddress);
            setBookingDetails(toCopy.bookingList);
            setCleanStatusTag(toCopy.cleanStatusTag);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(residenceName, residenceAddress, bookingList, cleanStatusTag, tags);
        }

        public void setResidenceName(ResidenceName name) {
            this.residenceName = name;
        }

        public Optional<ResidenceName> getResidenceName() {
            return Optional.ofNullable(residenceName);
        }

        public void setResidenceAddress(ResidenceAddress address) {
            this.residenceAddress = address;
        }

        public Optional<ResidenceAddress> getResidenceAddress() {
            return Optional.ofNullable(residenceAddress);
        }

        public void setBookingDetails(BookingList bookingList) {
            this.bookingList = bookingList;
        }

        public Optional<BookingList> getBookingDetails() {
            return Optional.ofNullable(bookingList);
        }

        /**
         * Sets {@code cleanStatusTag} to this object's {@code cleanStatusTag}.
         */
        public void setCleanStatusTag(CleanStatusTag cleanStatusTag) {
            this.cleanStatusTag = new CleanStatusTag(cleanStatusTag.getDesc());
        }

        public Optional<CleanStatusTag> getCleanStatusTag() {
            return Optional.ofNullable(cleanStatusTag);
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
            if (!(other instanceof EditResidenceDescriptor)) {
                return false;
            }

            // state check
            EditResidenceDescriptor e = (EditResidenceDescriptor) other;

            return getResidenceName().equals(e.getResidenceName())
                    && getResidenceAddress().equals(e.getResidenceAddress())
                    && getBookingDetails().equals(e.getBookingDetails())
                    && getCleanStatusTag().equals(e.getCleanStatusTag())
                    && getTags().equals(e.getTags());
        }
    }
}
