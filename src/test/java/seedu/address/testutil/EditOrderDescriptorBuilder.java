package seedu.address.testutil;

import seedu.address.logic.commands.EditOrderCommand;
import seedu.address.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.Phone;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.Quantity;

public class EditOrderDescriptorBuilder {
    private EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderDescriptor();
    }

    /**
     * Returns an {@code EditOrderDescriptorBuilder} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditOrderDescriptor();
        descriptor.setOrderDate(order.getOrderDate());
        descriptor.setCheeseType(order.getCheeseType());
        descriptor.setQuantity(order.getQuantity());
    }

    public EditOrderDescriptorBuilder(EditOrderDescriptor descriptor) {
        this.descriptor = new EditOrderCommand.EditOrderDescriptor(descriptor);
    }

    /**
     * Sets the {@code CheeseType} of the {@code EditOrderDescriptorBuilder} that we are building.
     */
    public EditOrderDescriptorBuilder withCheeseType(String cheeseType) {
        descriptor.setCheeseType(CheeseType.getCheeseType(cheeseType));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditOrderDescriptorBuilder} that we are building.
     */
    public EditOrderDescriptorBuilder withQuantity (int quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code OrderDate} of the {@code EditOrderDescriptorBuilder} that we are building.
     */
    public EditOrderDescriptorBuilder withOrderDate (String orderDate) {
        descriptor.setOrderDate(new OrderDate(orderDate));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditOrderDescriptorBuilder} that we are building.
     */
    public EditOrderDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    public EditOrderDescriptor build() {
        return descriptor;
    }
}
