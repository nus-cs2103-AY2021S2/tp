package seedu.smartlib.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.smartlib.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartlib.model.Model.PREDICATE_SHOW_ALL_READERS;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.smartlib.commons.core.Messages;
import seedu.smartlib.commons.core.index.Index;
import seedu.smartlib.commons.core.name.Name;
import seedu.smartlib.commons.util.CollectionUtil;
import seedu.smartlib.logic.commands.exceptions.CommandException;
import seedu.smartlib.model.Model;
import seedu.smartlib.model.book.Book;
import seedu.smartlib.model.reader.Address;
import seedu.smartlib.model.reader.Email;
import seedu.smartlib.model.reader.Phone;
import seedu.smartlib.model.reader.Reader;
import seedu.smartlib.model.record.DateBorrowed;
import seedu.smartlib.model.tag.Tag;

/**
 * Edits the details of an existing reader in the SmartLib.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "editreader";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the reader identified "
            + "by the index number used in the displayed reader list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_READER_SUCCESS = "Edited Reader: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_READER = "This reader already exists in SmartLib.";

    private final Index index;
    private final EditReaderDescriptor editReaderDescriptor;

    /**
     * @param index of the reader in the filtered reader list to edit
     * @param editReaderDescriptor details to edit the reader with
     */
    public EditCommand(Index index, EditReaderDescriptor editReaderDescriptor) {
        requireNonNull(index);
        requireNonNull(editReaderDescriptor);

        this.index = index;
        this.editReaderDescriptor = new EditReaderDescriptor(editReaderDescriptor);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException if an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reader> lastShownList = model.getFilteredReaderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_READER_DISPLAYED_INDEX);
        }

        Reader readerToEdit = lastShownList.get(index.getZeroBased());
        Reader editedReader = createEditedReader(readerToEdit, editReaderDescriptor);

        if (!readerToEdit.isSameReader(editedReader) && model.hasReader(editedReader)) {
            throw new CommandException(MESSAGE_DUPLICATE_READER);
        }

        model.setReader(readerToEdit, editedReader);
        model.updateFilteredReaderList(PREDICATE_SHOW_ALL_READERS);
        return new CommandResult(String.format(MESSAGE_EDIT_READER_SUCCESS, editedReader));
    }

    /**
     * Creates and returns a {@code Reader} with the details of {@code readerToEdit}
     * edited with {@code editReaderDescriptor}.
     */
    private static Reader createEditedReader(Reader readerToEdit, EditReaderDescriptor editReaderDescriptor) {
        assert readerToEdit != null;

        Name updatedName = editReaderDescriptor.getName().orElse(readerToEdit.getName());
        Phone updatedPhone = editReaderDescriptor.getPhone().orElse(readerToEdit.getPhone());
        Email updatedEmail = editReaderDescriptor.getEmail().orElse(readerToEdit.getEmail());
        Address updatedAddress = editReaderDescriptor.getAddress().orElse(readerToEdit.getAddress());
        Set<Tag> updatedTags = editReaderDescriptor.getTags().orElse(readerToEdit.getTags());
        Map<Book, DateBorrowed> updatedBorrows = editReaderDescriptor.getBorrows().orElse(readerToEdit.getBorrows());

        return new Reader(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedBorrows);
    }

    /**
     * Checks if this EditCommand is equal to another EditCommand.
     *
     * @param other the other EditCommand to be compared.
     * @return true if this EditCommand is equal to the other EditCommand, and false otherwise.
     */
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
                && editReaderDescriptor.equals(e.editReaderDescriptor);
    }

    /**
     * Stores the details to edit the reader with. Each non-empty field value will replace the
     * corresponding field value of the reader.
     */
    public static class EditReaderDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Map<Book, DateBorrowed> borrows;

        /**
         * A constructor for the EditReaderDescriptor.
         */
        public EditReaderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param toCopy a copy of the EditReaderDescriptor.
         */
        public EditReaderDescriptor(EditReaderDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         *
         * @return true if at least one field is edited, and false otherwise.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags);
        }

        /**
         * Updates the name of this reader.
         *
         * @param name the new name of this reader.
         */
        public void setName(Name name) {
            this.name = name;
        }

        /**
         * Returns the name of this reader.
         *
         * @return an Optional containing the name of this reader.
         */
        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Updates the phone number of this reader.
         *
         * @param phone the new phone number of this reader.
         */
        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        /**
         * Returns the phone number of this reader.
         *
         * @return an Optional containing the phone number of this reader.
         */
        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        /**
         * Updates the email of this reader.
         *
         * @param email the new email of this reader.
         */
        public void setEmail(Email email) {
            this.email = email;
        }

        /**
         * Returns the email of this reader.
         *
         * @return an Optional containing the email of this reader.
         */
        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Updates the address of this reader.
         *
         * @param address the new address of this reader.
         */
        public void setAddress(Address address) {
            this.address = address;
        }

        /**
         * Returns the address of this reader.
         *
         * @return an Optional containing the address of this reader.
         */
        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this reader's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         *
         * @param tags tags to be set to this reader.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         *
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code borrows} to this reader's {@code borrows}.
         * A defensive copy of {@code borrows} is used internally.
         *
         * @param borrows borrows to be set to this reader.
         */
        public void setBorrows(Map<Book, DateBorrowed> borrows) {
            this.borrows = (borrows != null) ? new HashMap<>(borrows) : null;
        }

        /**
         * Returns an unmodifiable borrow set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         *
         * Returns {@code Optional#empty()} if {@code borrows} is null.
         */
        public Optional<Map<Book, DateBorrowed>> getBorrows() {
            return (borrows != null) ? Optional.of(Collections.unmodifiableMap(borrows)) : Optional.empty();
        }

        /**
         * Checks if this EditReaderDescriptor is equal to another EditReaderDescriptor.
         *
         * @param other the other EditReaderDescriptor to be compared.
         * @return true if this EditReaderDescriptor is equal to the other EditReaderDescriptor, and false otherwise.
         */
        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditReaderDescriptor)) {
                return false;
            }

            // state check
            EditReaderDescriptor e = (EditReaderDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }

    }

}
