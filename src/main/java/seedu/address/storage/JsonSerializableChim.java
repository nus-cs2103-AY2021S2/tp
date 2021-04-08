package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Chim;
import seedu.address.model.ReadOnlyChim;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;

/**
 * An Immutable Chim that is serializable to JSON format.
 */
@JsonRootName(value = "chim")
class JsonSerializableChim {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_CHEESE = "Cheese list contains duplicate cheese(s).";
    public static final String MESSAGE_DUPLICATE_ORDER = "Order list contains duplicate order(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedCheese> cheeses = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableChim} with the given customers, cheeses and orders.
     */
    @JsonCreator
    public JsonSerializableChim(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                @JsonProperty("cheeses") List<JsonAdaptedCheese> cheeses,
                                @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.customers.addAll(customers);
        this.cheeses.addAll(cheeses);
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyChim} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableChim}.
     */
    public JsonSerializableChim(ReadOnlyChim source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        cheeses.addAll(source.getCheeseList().stream().map(JsonAdaptedCheese::new).collect(Collectors.toList()));
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this CHIM into the model's {@code Chim} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Chim toModelType() throws IllegalValueException {
        Chim chim = new Chim();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (chim.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            chim.addCustomer(customer);
        }

        for (JsonAdaptedCheese jsonAdaptedCheese : cheeses) {
            Cheese cheese = jsonAdaptedCheese.toModelType();
            if (chim.hasCheese(cheese)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CHEESE);
            }
            chim.addCheese(cheese);
        }

        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (chim.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            chim.addOrder(order);
        }

        chim.checkChim();

        return chim;
    }

}
