package chim.testutil;

import chim.logic.commands.EditOrderCommand;
import chim.model.cheese.CheeseType;
import chim.model.customer.Phone;
import chim.model.order.Order;
import chim.model.order.OrderDate;
import chim.model.order.Quantity;

public class EditOrderDescriptorBuilder {
    private EditOrderCommand.EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
    }

    /**
     * Returns an {@code EditOrderDescriptorBuilder} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
        descriptor.setOrderDate(order.getOrderDate());
        descriptor.setCheeseType(order.getCheeseType());
        descriptor.setQuantity(order.getQuantity());
    }

    public EditOrderDescriptorBuilder(EditOrderCommand.EditOrderDescriptor descriptor) {
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

    public EditOrderCommand.EditOrderDescriptor build() {
        return descriptor;
    }
}
