package seedu.timeforwheels.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.timeforwheels.commons.exceptions.IllegalValueException;
import seedu.timeforwheels.model.DeliveryList;
import seedu.timeforwheels.model.ReadOnlyDeliveryList;
import seedu.timeforwheels.model.customer.Customer;

/**
 * An Immutable DeliveryList that is serializable to JSON format.
 */
@JsonRootName(value = "deliverylist")
class JsonSerializableDeliveryList {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableDeliveryList} with the given customers.
     */
    @JsonCreator
    public JsonSerializableDeliveryList(@JsonProperty("customers") List<JsonAdaptedCustomer> customers) {
        this.customers.addAll(customers);
    }

    /**
     * Converts a given {@code ReadOnlyDeliveryList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableDeliveryList}.
     */
    public JsonSerializableDeliveryList(ReadOnlyDeliveryList source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
    }

    /**
     * Converts this delivery list into the model's {@code DeliveryList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public DeliveryList toModelType() throws IllegalValueException {
        DeliveryList deliveryList = new DeliveryList();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (deliveryList.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            deliveryList.addCustomer(customer);
        }
        return deliveryList;
    }

}
