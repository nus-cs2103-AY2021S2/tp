package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TRIPTIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PASSENGERS;

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
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.passenger.Address;
import seedu.address.model.person.passenger.Passenger;
import seedu.address.model.person.passenger.Price;
import seedu.address.model.person.passenger.TripDay;
import seedu.address.model.person.passenger.TripTime;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing passenger in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the passenger identified "
            + "by the index number used in the displayed passenger list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TRIPDAY + "DAY] "
            + "[" + PREFIX_TRIPTIME + "TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_TRIPDAY + "friday";

    public static final String MESSAGE_EDIT_PASSENGER_SUCCESS = "Edited Passenger: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PASSENGER = "This passenger already exists in the GME Terminal.";
    //todo remove STUB_VALID_PRICE declaration
    private static final Optional<Price> STUB_VALID_PRICE = Optional.of(new Price("1.69"));

    private final Index index;
    private final EditPassengerDescriptor editPassengerDescriptor;

    /**
     * @param index of the passenger in the filtered passenger list to edit
     * @param editPassengerDescriptor details to edit the passenger with
     */
    public EditCommand(Index index, EditPassengerDescriptor editPassengerDescriptor) {
        requireNonNull(index);
        requireNonNull(editPassengerDescriptor);

        this.index = index;
        this.editPassengerDescriptor = new EditPassengerDescriptor(editPassengerDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Passenger> lastShownList = model.getFilteredPassengerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PASSENGER_DISPLAYED_INDEX);
        }

        Passenger passengerToEdit = lastShownList.get(index.getZeroBased());
        Passenger editedPassenger = createEditedPassenger(passengerToEdit, editPassengerDescriptor);

        if (!passengerToEdit.isSamePassenger(editedPassenger) && model.hasPassenger(editedPassenger)) {
            throw new CommandException(MESSAGE_DUPLICATE_PASSENGER);
        }

        model.setPassenger(passengerToEdit, editedPassenger);
        model.updateFilteredPassengerList(PREDICATE_SHOW_ALL_PASSENGERS);
        return new CommandResult(String.format(MESSAGE_EDIT_PASSENGER_SUCCESS, editedPassenger));
    }

    /**
     * Creates and returns a {@code Passenger} with the details of {@code passengerToEdit}
     * edited with {@code editPassengerDescriptor}.
     */
    private static Passenger createEditedPassenger(Passenger passengerToEdit,
                                                   EditPassengerDescriptor editPassengerDescriptor) {
        assert passengerToEdit != null;

        Name updatedName = editPassengerDescriptor.getName().orElse(passengerToEdit.getName());
        Phone updatedPhone = editPassengerDescriptor.getPhone().orElse(passengerToEdit.getPhone());
        Address updatedAddress = editPassengerDescriptor.getAddress().orElse(passengerToEdit.getAddress());
        Set<Tag> updatedTags = editPassengerDescriptor.getTags().orElse(passengerToEdit.getTags());
        TripDay updatedTripDay = editPassengerDescriptor.getTripDay().orElse(passengerToEdit.getTripDay());
        TripTime updatedTripTime = editPassengerDescriptor.getTripTime().orElse(passengerToEdit.getTripTime());

        //todo remove STUB_VALID_PRICE
        Optional<Price> updatedPrice = STUB_VALID_PRICE;

        return new Passenger(updatedName, updatedPhone, updatedAddress, updatedTripDay, updatedTripTime, updatedPrice,
                updatedTags);
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
                && editPassengerDescriptor.equals(e.editPassengerDescriptor);
    }

    /**
     * Stores the details to edit the passenger with. Each non-empty field value will replace the
     * corresponding field value of the passenger.
     */
    public static class EditPassengerDescriptor {
        private Name name;
        private Phone phone;
        private Address address;
        private Set<Tag> tags;
        private TripDay tripDay;
        private TripTime tripTime;
        private Price price;

        public EditPassengerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPassengerDescriptor(EditPassengerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setTripDay(toCopy.tripDay);
            setTripTime(toCopy.tripTime);
            setPrice(price);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, address, tripDay, tripTime, tags);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setTripDay(TripDay tripDay) {
            this.tripDay = tripDay;
        }

        public Optional<TripDay> getTripDay() {
            return Optional.ofNullable(tripDay);
        }

        public void setTripTime(TripTime tripTime) {
            this.tripTime = tripTime;
        }

        public Optional<TripTime> getTripTime() {
            return Optional.ofNullable(tripTime);
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Optional<Price> getPrice() {
            return Optional.ofNullable(price);
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
            if (!(other instanceof EditPassengerDescriptor)) {
                return false;
            }

            // state check
            EditPassengerDescriptor e = (EditPassengerDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getAddress().equals(e.getAddress())
                    && getTripDay().equals(e.getTripDay())
                    && getTripTime().equals(e.getTripTime())
                    && getPrice().equals(e.getPrice())
                    && getTags().equals(e.getTags());
        }
    }
}
