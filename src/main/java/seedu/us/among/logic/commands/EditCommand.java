package seedu.us.among.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.us.among.commons.core.Messages.MESSAGE_INVALID_COMMAND_ERROR;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_DATA;
import static seedu.us.among.logic.parser.CliSyntax.PREFIX_HEADER;
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
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.endpoint.Response;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * Edits the details of an existing API endpoint identified using it's displayed
 * index from the API endpoint list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_API_EXAMPLE = COMMAND_WORD + " 1 "
            + PREFIX_METHOD + " POST "
            + PREFIX_ADDRESS + " https://reqres.in/api/v1/ "
            + PREFIX_DATA + " {\"name\": \"tom\", \"job\": \"cook\"} "
            + PREFIX_HEADER + " \"key: value\" "
            + PREFIX_TAG + " important\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits an endpoint using the "
            + "displayed index from the endpoint list.\n"
            + "Parameters: "
            + PREFIX_METHOD + " METHOD "
            + PREFIX_ADDRESS + " ADDRESS "
            + PREFIX_DATA + " DATA "
            + "[" + PREFIX_HEADER + " HEADER] "
            + "[" + PREFIX_TAG + " TAG]\n"
            + "Use the help command for more information.\n\n"
            + "Example: "
            + MESSAGE_API_EXAMPLE;

    public static final String MESSAGE_EDIT_ENDPOINT_SUCCESS = "Endpoint edited:\n%1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one parameter to edit must be provided.";
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
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_ERROR,
                    Messages.MESSAGE_INDEX_NOT_WITHIN_LIST));
            //throw new CommandException(Messages.MESSAGE_INDEX_NOT_WITHIN_LIST);
        }

        Endpoint endpointToEdit = lastShownList.get(index.getZeroBased());
        Endpoint editedEndpoint = createEditedEndpoint(endpointToEdit, editEndpointDescriptor);

        if (!endpointToEdit.equals(editedEndpoint) && model.hasEndpoint(editedEndpoint)) {
            throw new CommandException(MESSAGE_DUPLICATE_ENDPOINT);
        }

        model.setEndpoint(endpointToEdit, editedEndpoint);
        model.updateFilteredEndpointList(model.getFilteredPredicate());
        return new CommandResult(String.format(MESSAGE_EDIT_ENDPOINT_SUCCESS, editedEndpoint), editedEndpoint, false);
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
        Data updatedData = editEndpointDescriptor.getData().orElse(endpointToEdit.getData());
        Set<Header> updatedHeader = editEndpointDescriptor.getHeaders().orElse(endpointToEdit.getHeaders());
        Set<Tag> updatedTags = editEndpointDescriptor.getTags().orElse(endpointToEdit.getTags());

        return new Endpoint(updatedMethod, updatedAddress, updatedData, updatedHeader, updatedTags);
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
        private Data data;
        private Set<Header> headers;
        private Set<Tag> tags;
        private Response response;

        public EditEndpointDescriptor() {
        }

        /**
         * Copy constructor. A defensive copy of {@code tags} is used internally.
         */
        public EditEndpointDescriptor(EditEndpointDescriptor toCopy) {
            setMethod(toCopy.method);
            setAddress(toCopy.address);
            setData(toCopy.data);
            setHeaders(toCopy.headers);
            setTags(toCopy.tags);
            setResponse(toCopy.response);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(method, address, data, headers, tags);
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

        public void setData(Data data) {
            this.data = data;
        }

        public Optional<Data> getData() {
            return Optional.ofNullable(data);
        }

        /**
         * Sets {@code headers} to this object's {@code headers}. A defensive copy of
         * {@code headers} is used internally.
         */
        public void setHeaders(Set<Header> headers) {
            this.headers = (headers != null) ? new HashSet<>(headers) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws
         * {@code UnsupportedOperationException} if modification is attempted. Returns
         * {@code Optional#empty()} if {@code headers} is null.
         */
        public Optional<Set<Header>> getHeaders() {
            return (headers != null) ? Optional.of(Collections.unmodifiableSet(headers)) : Optional.empty();
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

        public void setResponse(Response response) {
            this.response = response;
        }

        public Optional<Response> getResponse() {
            return Optional.ofNullable(response);
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

            return getMethod().equals(e.getMethod())
                    && getAddress().equals(e.getAddress())
                    && getData().equals(e.getData())
                    && getHeaders().equals(e.getHeaders())
                    && getTags().equals(e.getTags());
        }
    }
}
