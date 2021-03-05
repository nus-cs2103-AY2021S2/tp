package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_METHOD;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.us.among.commons.core.Messages;
import seedu.us.among.commons.core.index.Index;
import seedu.us.among.commons.util.CollectionUtil;
import seedu.us.among.logic.commands.exceptions.CommandException;
import seedu.us.among.model.Model;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.tag.Tag;

/**
 * Edits the details of an existing API endpoint identified using it's displayed
 * index from the API endpoint list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of an existing API endpoint "
            + "identified using it's displayed index from the API endpoint list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_METHOD + "METHOD] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ADDRESS + "wall street ";

    public static final String MESSAGE_EDIT_ENDPOINT_SUCCESS = "Edited endpoint: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ENDPOINT = "This API endpoint already exists in the "
            + "API endpoint list.";

    private final Index index;
    private final EditEndpointDescriptor editEndpointDescriptor;

    /**
     * @param index of the endpoint in the filtered endpoint list to edit
     * @param editEndpointDescriptor details to edit the endpoint with
     */
    public EditCommand(Index index, EditEndpointDescriptor editEndpointDescriptor) {
        requireNonNull(index);
        requireNonNull(editEndpointDescriptor);

        this.index = index;
        this.editEndpointDescriptor = new EditEndpointDescriptor(editEndpointDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Endpoint> lastShownList = model.getFilteredEndpointList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ENDPOINT_DISPLAYED_INDEX);
        }

        Endpoint endpointToEdit = lastShownList.get(index.getZeroBased());
        Endpoint editedEndpoint = createEditedEndpoint(endpointToEdit, editEndpointDescriptor);

        if (!endpointToEdit.isSameEndpoint(editedEndpoint) && model.hasEndpoint(editedEndpoint)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENDPOINT);
        }

        model.setEndpoint(endpointToEdit, editedEndpoint);
        model.updateFilteredEndpointList(Model.PREDICATE_SHOW_ALL_ENDPOINTS);
        return new CommandResult(String.format(MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint));
    }

    /**
     * Creates and returns a {@code Endpoint} with the details of
     * {@code endpointToEdit} edited with {@code editEndpointDescriptor}.
     */
    private static Endpoint createEditedEndpoint(Endpoint endpointToEdit,
            EditEndpointDescriptor editEndpointDescriptor) {
        assert endpointToEdit != null;

        Method updatedMethod = editEndpointDescriptor.getMethod().orElse(endpointToEdit.getMethod());
        Address updatedAddress = editEndpointDescriptor.getAddress().orElse(endpointToEdit.getAddress());
        Set<Tag> updatedTags = editEndpointDescriptor.getTags().orElse(endpointToEdit.getTags());

        return new Endpoint(updatedMethod, updatedAddress, updatedTags);
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
        return index.equals(e.index) && editEndpointDescriptor.equals(e.editEndpointDescriptor);
    }

    /**
     * Stores the details to edit the endpoint with. Each non-empty field value will
     * replace the corresponding field value of the endpoint.
     */
    public static class EditEndpointDescriptor {
        private Method method;
        private Address address;
        private Set<Tag> tags;

        public EditEndpointDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditEndpointDescriptor(EditEndpointDescriptor toCopy) {
            setMethod(toCopy.method);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(method, address, tags);
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Optional<Method> getMethod() {
            return Optional.ofNullable(method);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}. A defensive copy of
         * {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code tags} is null.
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
            if (!(other instanceof EditEndpointDescriptor)) {
                return false;
            }

            // state check
            EditEndpointDescriptor e = (EditEndpointDescriptor) other;

            return getMethod().equals(e.getMethod()) && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
