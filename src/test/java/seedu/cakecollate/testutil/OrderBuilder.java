package seedu.cakecollate.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.tag.Tag;
import seedu.cakecollate.model.util.SampleDataUtil;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ORDER_DESCRIPTION = "1 x Chocolate Cake";
    public static final String DEFAULT_DELIVERY_DATE = "01/01/2022";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Set<OrderDescription> orderDescriptions;
    private Set<Tag> tags;
    private DeliveryDate deliveryDate;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        orderDescriptions = new HashSet<>();
        orderDescriptions.add(new OrderDescription(DEFAULT_ORDER_DESCRIPTION));
        tags = new HashSet<>();
        deliveryDate = new DeliveryDate(DEFAULT_DELIVERY_DATE);
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        name = orderToCopy.getName();
        phone = orderToCopy.getPhone();
        email = orderToCopy.getEmail();
        address = orderToCopy.getAddress();
        orderDescriptions = new HashSet<>(orderToCopy.getOrderDescriptions());
        tags = new HashSet<>(orderToCopy.getTags());
        deliveryDate = orderToCopy.getDeliveryDate();
    }

    /**
     * Sets the {@code Name} of the {@code Order} that we are building.
     */
    public OrderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Order} that we are building.
     */
    public OrderBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Order} that we are building.
     */
    public OrderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Order} that we are building.
     */
    public OrderBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Order} that we are building.
     */
    public OrderBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }
    /**
     * Sets the {@code OrderDescription} of the {@code OrderDescription} that we are building.
     */
    public OrderBuilder withOrderDescriptions(String ... orderDescriptions) {
        this.orderDescriptions = SampleDataUtil.getOrderDescriptionSet(orderDescriptions);
        return this;
    }

    /**
     * Sets the {@code DeliveryDate} of the {@code Order} that we are building.
     */
    public OrderBuilder withDeliveryDate(String deliveryDate) {
        this.deliveryDate = new DeliveryDate(deliveryDate);
        return this;
    }

    public Order build() {
        return new Order(name, phone, email, address, orderDescriptions, tags, deliveryDate);
    }

}
