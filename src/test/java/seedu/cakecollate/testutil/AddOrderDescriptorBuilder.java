package seedu.cakecollate.testutil;

import seedu.cakecollate.logic.commands.AddCommand;
import seedu.cakecollate.model.order.Order;

public class AddOrderDescriptorBuilder {
    private AddCommand.AddOrderDescriptor descriptor;

    public AddOrderDescriptorBuilder() {
        descriptor = new AddCommand.AddOrderDescriptor();
    }

    public AddOrderDescriptorBuilder(AddCommand.AddOrderDescriptor a) {
        descriptor = new AddCommand.AddOrderDescriptor(a);
    }

    /**
     * Builds a descriptor based on an existing order object's fields
     * @param order
     */
    public AddOrderDescriptorBuilder(Order order) {
        descriptor = new AddCommand.AddOrderDescriptor();

        // check these
        descriptor.setName(order.getName());
        descriptor.setPhone(order.getPhone());
        descriptor.setEmail(order.getEmail());
        descriptor.setAddress(order.getAddress());
        descriptor.setOrderDescriptions(order.getOrderDescriptions());
        descriptor.setTags(order.getTags());
        descriptor.setDeliveryDate(order.getDeliveryDate());
        descriptor.setRequest(order.getRequest());
    }

    public AddCommand.AddOrderDescriptor build() {
        return descriptor;
    }
}
