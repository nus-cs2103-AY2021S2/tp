package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_OWNED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CARS_PREFERRED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.customer.Address;
import seedu.address.model.customer.Car;
import seedu.address.model.customer.CoeExpiry;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.DateOfBirth;
import seedu.address.model.customer.Email;
import seedu.address.model.customer.Name;
import seedu.address.model.customer.Phone;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
        + "by the index number used in the displayed person list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_PHONE + "PHONE] "
        + "[" + PREFIX_EMAIL + "EMAIL] "
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_DOB + "DATE_OF_BIRTH] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "[" + PREFIX_CARS_OWNED + "TAG]...\n"
        + "[" + PREFIX_CARS_PREFERRED + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_PHONE + "91234567 "
        + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_CUSTOMER_SUCCESS = "Edited Customer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CUSTOMER = "This customer already exists in the address book.";

    private final Index index;
    private final EditCustomerDescriptor editCustomerDescriptor;

    /**
     * @param index                  of the customer in the filtered customer list to edit
     * @param editCustomerDescriptor details to edit the customer with
     */
    public EditCommand(Index index, EditCustomerDescriptor editCustomerDescriptor) {
        requireNonNull(index);
        requireNonNull(editCustomerDescriptor);

        this.index = index;
        this.editCustomerDescriptor = new EditCustomerDescriptor(editCustomerDescriptor);
    }

    /**
     * Creates and returns a {@code Customer} with the details of {@code customerToEdit} edited with {@code
     * editCustomerDescriptor}.
     */
    private static Customer createEditedCustomer(Customer customerToEdit,
                                                 EditCustomerDescriptor editCustomerDescriptor) {
        assert customerToEdit != null;

        Name updatedName = editCustomerDescriptor.getName().orElse(customerToEdit.getName());
        Phone updatedPhone = editCustomerDescriptor.getPhone().orElse(customerToEdit.getPhone());
        Email updatedEmail = editCustomerDescriptor.getEmail().orElse(customerToEdit.getEmail());
        Address updatedAddress = editCustomerDescriptor.getAddress().orElse(customerToEdit.getAddress());
        DateOfBirth updatedDateOfBirth =
                editCustomerDescriptor.getDateOfBirth().orElse(customerToEdit.getDateOfBirth());
        Set<Tag> updatedTags = editCustomerDescriptor.getTags().orElse(customerToEdit.getTags());
        Map<Car, CoeExpiry> updatedCarsOwned =
            editCustomerDescriptor.getCarsOwned().orElse(customerToEdit.getCarsOwned());
        Set<Car> updatedCarsPreferred = editCustomerDescriptor.getCarsPreferred()
                .orElse(customerToEdit.getCarsPreferred());

        return new Customer(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedDateOfBirth,
                updatedTags, updatedCarsOwned, updatedCarsPreferred);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Customer> lastShownList = model.getFilteredCustomerList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }

        Customer customerToEdit = lastShownList.get(index.getZeroBased());
        Customer editedCustomer = createEditedCustomer(customerToEdit, editCustomerDescriptor);

        if (!customerToEdit.isSameCustomer(editedCustomer) && model.hasCustomer(editedCustomer)) {
            throw new CommandException(MESSAGE_DUPLICATE_CUSTOMER);
        }

        model.setCustomer(customerToEdit, editedCustomer);
        model.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);
        return new CommandResult(String.format(MESSAGE_EDIT_CUSTOMER_SUCCESS, editedCustomer));
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
            && editCustomerDescriptor.equals(e.editCustomerDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the corresponding field value
     * of the person.
     */
    public static class EditCustomerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private DateOfBirth dateOfBirth;
        private Set<Tag> tags;
        private Map<Car, CoeExpiry> carsOwned;
        private Set<Car> carsPreferred;

        public EditCustomerDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditCustomerDescriptor(EditCustomerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setDateOfBirth(toCopy.dateOfBirth);
            setTags(toCopy.tags);
            setCarsOwned(toCopy.carsOwned);
            setCarsPreferred(toCopy.carsPreferred);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, dateOfBirth, tags, carsPreferred);
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<DateOfBirth> getDateOfBirth() {
            return Optional.ofNullable(dateOfBirth);
        }

        public void setDateOfBirth(DateOfBirth dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }
        /**
         * Returns an unmodifiable carsOwned map, which throws {@code UnsupportedOperationException} if modification is
         * attempted. Returns {@code Optional#empty()} if {@code carsOwned} is null.
         */
        public Optional<Map<Car, CoeExpiry>> getCarsOwned() {
            return Optional.ofNullable(carsOwned).map(Collections::unmodifiableMap);
        }

        /**
         * Sets {@code carsOwned} to this object's {@code carsOwned}. A defensive copy of {@code carsOwned} is used
         * internally.
         */
        public void setCarsOwned(Map<Car, CoeExpiry> carsOwned) {
            this.carsOwned = carsOwned != null ? new HashMap<>(carsOwned) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException} if modification is
         * attempted. Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return Optional.ofNullable(tags).map(Collections::unmodifiableSet);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns {@code Optional#empty()} if {@code carsPreferred} is null.
         */
        public Optional<Set<Car>> getCarsPreferred() {
            return Optional.ofNullable(carsPreferred);
        }
        /**
         * Sets {@code carsPreferred} to this object's {@code carsPreferred}.
         * A defensive copy of {@code carsPreferred} is used internally.
         */
        public void setCarsPreferred(Set<Car> carsPreferred) {
            this.carsPreferred = (carsPreferred != null) ? new HashSet<>(carsPreferred) : null;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCustomerDescriptor)) {
                return false;
            }

            // state check
            EditCustomerDescriptor e = (EditCustomerDescriptor) other;

            return getName().equals(e.getName())
                && getPhone().equals(e.getPhone())
                && getEmail().equals(e.getEmail())
                && getAddress().equals(e.getAddress())
                && getDateOfBirth().equals(e.getDateOfBirth())
                && getTags().equals(e.getTags());
        }
    }
}
