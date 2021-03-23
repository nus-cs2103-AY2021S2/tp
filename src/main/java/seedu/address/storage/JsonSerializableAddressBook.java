package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.cheese.Cheese;
import seedu.address.model.customer.Customer;
import seedu.address.model.order.Order;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_CUSTOMER = "Customers list contains duplicate customer(s).";
    public static final String MESSAGE_DUPLICATE_CHEESE = "Cheese list contains duplicate cheese(s).";
    public static final String MESSAGE_DUPLICATE_ORDER = "Order list contains duplicate order(s).";

    private final List<JsonAdaptedCustomer> customers = new ArrayList<>();
    private final List<JsonAdaptedCheese> cheeses = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given customers, cheeses and orders.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("customers") List<JsonAdaptedCustomer> customers,
                                       @JsonProperty("cheeses") List<JsonAdaptedCheese> cheeses,
                                       @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.customers.addAll(customers);
        this.cheeses.addAll(cheeses);
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        customers.addAll(source.getCustomerList().stream().map(JsonAdaptedCustomer::new).collect(Collectors.toList()));
        cheeses.addAll(source.getCheeseList().stream().map(JsonAdaptedCheese::new).collect(Collectors.toList()));
        orders.addAll(source.getOrderList().stream().map(JsonAdaptedOrder::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedCustomer jsonAdaptedCustomer : customers) {
            Customer customer = jsonAdaptedCustomer.toModelType();
            if (addressBook.hasCustomer(customer)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CUSTOMER);
            }
            addressBook.addCustomer(customer);
        }

        for (JsonAdaptedCheese jsonAdaptedCheese : cheeses) {
            Cheese cheese = jsonAdaptedCheese.toModelType();
            if (addressBook.hasCheese(cheese)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CHEESE);
            }
            addressBook.addCheese(cheese);
        }

        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (addressBook.hasOrder(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            addressBook.addOrder(order);
        }

        addressBook.checkAddressBook();

        return addressBook;
    }

}
