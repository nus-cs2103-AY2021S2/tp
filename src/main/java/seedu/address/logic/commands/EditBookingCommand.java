package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BOOKING_START_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RESIDENCE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_RESIDENCES;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.Phone;
import seedu.address.model.booking.TenantName;
import seedu.address.model.residence.BookingList;
import seedu.address.model.residence.Residence;

/**
 * Edits the details of an existing booking in the specified residence.
 */
public class EditBookingCommand extends Command {

    public static final String COMMAND_WORD = "editb";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the booking identified by "
            + "booking index of the residence identified based on the residence index provided"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_RESIDENCE + "RESIDENCE_INDEX "
            + PREFIX_BOOKING + "BOOKING_INDEX "
            + "[" + PREFIX_NAME + "TENANT_NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_BOOKING_START_DATE + "START_DATE] "
            + "[" + PREFIX_BOOKING_END_DATE + "END_DATE]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_RESIDENCE + "1 "
            + PREFIX_BOOKING + "1 "
            + PREFIX_BOOKING_START_DATE + "01-01-2020";

    public static final String MESSAGE_EDIT_BOOKING_SUCCESS = "Edited Booking: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_OVERLAP_BOOKING = "The edited booking overlaps with other existing bookings.";
    public static final String MESSAGE_NOT_VALID_START_DATE = "Invalid start date, should not be later than end date.";

    private final Index residenceIndex;
    private final Index bookingIndex;
    private final EditBookingDescriptor editBookingDescriptor;

    /**
     * @param residenceIndex index of the residence in the filtered residence list to edit
     * @param bookingIndex index of the booking in the booking list of the residence to edit
     * @param editBookingDescriptor details to edit the booking with
     */
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
        Residence targetResidence = lastShownList.get(residenceIndex.getZeroBased());
        Residence residenceToEdit = lastShownList.get(residenceIndex.getZeroBased());
        BookingList bookingListToEdit = residenceToEdit.getBookingList();
        if (bookingIndex.getZeroBased() >= bookingListToEdit.getBookingListSize()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
        }
        Booking bookingToEdit = bookingListToEdit.getBooking(bookingIndex.getZeroBased());
        Booking editedBooking = createEditedBooking(bookingToEdit, editBookingDescriptor);

        if (!editedBooking.isValidBookingTime(editedBooking.getStart(), editedBooking.getEnd())) {
            throw new CommandException(MESSAGE_NOT_VALID_START_DATE);
        }

        if (residenceToEdit.getBookingList().containsExclude(bookingToEdit, editedBooking)) {
            throw new CommandException(MESSAGE_OVERLAP_BOOKING);
        }
        bookingListToEdit.setBooking(bookingToEdit, editedBooking);
        model.setResidence(targetResidence, residenceToEdit);
        model.updateFilteredResidenceList(PREDICATE_SHOW_ALL_RESIDENCES);
        return new CommandResult(String.format(MESSAGE_EDIT_BOOKING_SUCCESS, editedBooking));
    }

    /**
     * Creates and returns a {@code Booking} with the details of {@code bookingToEdit}
     * edited with {@code editBookingDescriptor}.
     */
    private static Booking createEditedBooking(Booking bookingToEdit,
                                               EditBookingDescriptor editBookingDescriptor) {
        assert bookingToEdit != null;

        TenantName updatedTenantName = editBookingDescriptor.getName()
                .orElse(bookingToEdit.getTenantName());
        Phone updatedPhone = editBookingDescriptor.getPhone()
                .orElse(bookingToEdit.getPhone());
        LocalDate updatedStartTime = editBookingDescriptor.getStartDate()
                .orElse(bookingToEdit.getStart());
        LocalDate updatedEndTime = editBookingDescriptor.getEndDate()
                .orElse(bookingToEdit.getEnd());

        return new Booking(updatedTenantName, updatedPhone, updatedStartTime, updatedEndTime);
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

    public static String getCommandWord() {
        return COMMAND_WORD;
    }

    /**
     * Stores the details to edit the booking with. Each non-empty field value will replace the
     * corresponding field value of the booking.
     */
    public static class EditBookingDescriptor {
        private TenantName tenantName;
        private Phone phone;
        private LocalDate startDate;
        private LocalDate endDate;

        public EditBookingDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditBookingDescriptor(EditBookingDescriptor toCopy) {
            setName(toCopy.tenantName);
            setPhone(toCopy.phone);
            setStartDate(toCopy.startDate);
            setEndDate(toCopy.endDate);
        }
        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(tenantName, phone, startDate, endDate);
        }

        public void setName(TenantName tenantName) {
            this.tenantName = tenantName;
        }

        public Optional<TenantName> getName() {
            return Optional.ofNullable(tenantName);
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
