package seedu.cakecollate.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cakecollate.commons.exceptions.IllegalValueException;
import seedu.cakecollate.model.order.Address;
import seedu.cakecollate.model.order.DeliveryDate;
import seedu.cakecollate.model.order.Email;
import seedu.cakecollate.model.order.Name;
import seedu.cakecollate.model.order.Order;
import seedu.cakecollate.model.order.OrderDescription;
import seedu.cakecollate.model.order.Phone;
import seedu.cakecollate.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final List<JsonAdaptedOrderDescription> orderDescriptions = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final String deliveryDate;

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("cakecollate") String address,
                            @JsonProperty("orderDescriptions") List<JsonAdaptedOrderDescription> orderDescriptions,
                            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                            @JsonProperty("deliveryDate") String deliveryDate) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        if (orderDescriptions != null) { // handle null below
            this.orderDescriptions.addAll(orderDescriptions);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        this.deliveryDate = deliveryDate;
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        orderDescriptions.addAll(
                source.getOrderDescriptions().stream()
                .map(JsonAdaptedOrderDescription::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        deliveryDate = source.getDeliveryDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        final List<Tag> orderTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            orderTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (deliveryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DeliveryDate.class.getSimpleName()));
        }
        if (!DeliveryDate.isValidDeliveryDate(deliveryDate)) {
            throw new IllegalValueException(DeliveryDate.MESSAGE_CONSTRAINTS);
        }
        final DeliveryDate modelDeliveryDate = new DeliveryDate(deliveryDate);

        final Set<Tag> modelTags = new HashSet<>(orderTags);

        if (orderDescriptions.isEmpty() || orderDescriptions == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderDescription.class.getSimpleName()));
        }

        final List<OrderDescription> orderOrderDescriptions = new ArrayList<>();
        for (JsonAdaptedOrderDescription o : orderDescriptions) {
            orderOrderDescriptions.add(o.toModelType());
        }
        final Set<OrderDescription> modelOrderDescriptions = new HashSet<>(orderOrderDescriptions);

        return new Order(modelName, modelPhone, modelEmail, modelAddress, modelOrderDescriptions, modelTags,
                modelDeliveryDate);
    }

}
