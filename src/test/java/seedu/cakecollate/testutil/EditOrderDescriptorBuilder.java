package seedu.cakecollate.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.cakecollate.logic.commands.EditCommand;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.tag.Tag;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {

    private EditCommand.EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditCommand.EditOrderDescriptor();
    }

    public EditOrderDescriptorBuilder(EditCommand.EditOrderDescriptor descriptor) {
        this.descriptor = new EditCommand.EditOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditCommand.EditOrderDescriptor();
        descriptor.setName(order.getName());
        descriptor.setPhone(order.getPhone());
        descriptor.setEmail(order.getEmail());
        descriptor.setAddress(order.getAddress());
        descriptor.setOrderDescriptions(order.getOrderDescriptions());
        descriptor.setTags(order.getTags());
        descriptor.setDeliveryDate(order.getDeliveryDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code OrderDescription} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withOrderDescriptions(String... orderDescriptions) {
        Set<OrderDescription> orderDescriptionSet = Stream.of(orderDescriptions)
                .map(OrderDescription::new).collect(Collectors.toSet());
        descriptor.setOrderDescriptions(orderDescriptionSet);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditOrderDescriptor}
     * that we are building.
     */
    public EditOrderDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code DeliveryDate} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withDeliveryDate(String deliveryDate) {
        descriptor.setDeliveryDate(new DeliveryDate(deliveryDate));
        return this;
    }

    public EditCommand.EditOrderDescriptor build() {
        return descriptor;
    }
}
