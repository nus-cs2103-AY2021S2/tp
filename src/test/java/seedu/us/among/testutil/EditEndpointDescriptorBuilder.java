package seedu.us.among.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.us.among.logic.commands.EditCommand;
import seedu.us.among.model.endpoint.Address;
import seedu.us.among.model.endpoint.Data;
import seedu.us.among.model.endpoint.Endpoint;
import seedu.us.among.model.endpoint.Method;
import seedu.us.among.model.header.Header;
import seedu.us.among.model.tag.Tag;

/**
 * A utility class to help with building EditEndpointDescriptor objects.
 */
public class EditEndpointDescriptorBuilder {

    private EditCommand.EditEndpointDescriptor descriptor;

    public EditEndpointDescriptorBuilder() {
        descriptor = new EditCommand.EditEndpointDescriptor();
    }

    public EditEndpointDescriptorBuilder(EditCommand.EditEndpointDescriptor descriptor) {
        this.descriptor = new EditCommand.EditEndpointDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEndpointDescriptor} with fields containing
     * {@code endpoint}'s details
     */
    public EditEndpointDescriptorBuilder(Endpoint endpoint) {
        descriptor = new EditCommand.EditEndpointDescriptor();
        descriptor.setMethod(endpoint.getMethod());
        descriptor.setAddress(endpoint.getAddress());
        descriptor.setData(endpoint.getData());
        descriptor.setHeaders(endpoint.getHeaders());
        descriptor.setTags(endpoint.getTags());
        descriptor.setResponse(endpoint.getResponse());
    }

    /**
     * Sets the {@code Name} of the {@code EditEndpointDescriptor} that we are
     * building. //to-do refactor this from withName to withMethod (CommandTestUtil Class)
     */
    public EditEndpointDescriptorBuilder withName(String name) {
        descriptor.setMethod(new Method(name));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditEndpointDescriptor} that we are
     * building.
     */
    public EditEndpointDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Data} of the {@code EditEndpointDescriptor} that we are
     * building.
     */
    public EditEndpointDescriptorBuilder withData(String data) {
        descriptor.setData(new Data(data));
        return this;
    }

    /**
     * Parses the {@code headers} into a {@code Set<Header>} and set it to the
     * {@code EditEndpointDescriptor} that we are building.
     */
    public EditEndpointDescriptorBuilder withHeaders(String... headers) {
        Set<Header> headerSet = Stream.of(headers).map(Header::new).collect(Collectors.toSet());
        descriptor.setHeaders(headerSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the
     * {@code EditEndpointDescriptor} that we are building.
     */
    public EditEndpointDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditCommand.EditEndpointDescriptor build() {
        return descriptor;
    }
}
