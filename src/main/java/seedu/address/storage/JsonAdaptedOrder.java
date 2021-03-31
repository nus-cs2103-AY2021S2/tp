package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.cheese.CheeseId;
import seedu.address.model.cheese.CheeseType;
import seedu.address.model.customer.CustomerId;
import seedu.address.model.order.CompletedDate;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderDate;
import seedu.address.model.order.OrderId;
import seedu.address.model.order.Quantity;

/**
 * Jackson-friendly version of {@link Order}.
 */
class JsonAdaptedOrder {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Order's %s field is missing!";

    private final Integer orderId;
    private final String cheeseType;
    private final Integer quantity;
    private final String orderDate;
    private final String completedDate;
    private final Integer customerId;
    private final List<Integer> cheeseIds = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedOrder} with the given order details.
     */
    @JsonCreator
    public JsonAdaptedOrder(@JsonProperty("orderId") Integer orderId,
                            @JsonProperty("cheeseType") String cheeseType,
                            @JsonProperty("quantity") Integer quantity,
                            @JsonProperty("orderDate") String orderDate,
                            @JsonProperty("completedDate") String completedDate,
                            @JsonProperty("customerId") Integer customerId,
                            @JsonProperty("cheeseIds") List<Integer> cheeseIds) {
        this.orderId = orderId;
        this.cheeseType = cheeseType;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.completedDate = completedDate;
        this.customerId = customerId;
        if (cheeseIds != null) {
            this.cheeseIds.addAll(cheeseIds);
        }
    }

    /**
     * Converts a given {@code Order} into this class for Jackson use.
     */
    public JsonAdaptedOrder(Order source) {
        orderId = source.getOrderId().value;
        cheeseType = source.getCheeseType().value;
        quantity = source.getQuantity().value;
        orderDate = source.getOrderDate().toJsonString();
        completedDate = source.getCompletedDate().map(CompletedDate::toJsonString).orElse(null);
        customerId = source.getCustomerId().value;
        cheeseIds.addAll(source.getCheeses().stream()
                    .map(x -> x.value)
                    .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted order object into the model's {@code Order} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted order.
     */
    public Order toModelType() throws IllegalValueException {
        final List<CheeseId> orderCheeses = new ArrayList<>();
        for (Integer cheeseId : cheeseIds) {
            orderCheeses.add(CheeseId.getNextId(cheeseId));
        }

        if (!OrderId.isValidId(orderId)) {
            throw new IllegalValueException(OrderId.MESSAGE_CONSTRAINTS);
        }
        final OrderId modelId = OrderId.getNextId(orderId);

        if (cheeseType == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CheeseType.class.getSimpleName()));
        }
        final CheeseType modelCheeseType = CheeseType.getCheeseType(cheeseType);

        if (quantity == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Quantity.class.getSimpleName()));
        }
        if (!Quantity.isValidQuantity(quantity)) {
            throw new IllegalValueException(Quantity.MESSAGE_CONSTRAINTS);
        }
        final Quantity modelQuantity = new Quantity(quantity);

        if (orderDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                OrderDate.class.getSimpleName()));
        }
        if (!OrderDate.isValidDate(orderDate)) {
            throw new IllegalValueException(OrderDate.MESSAGE_CONSTRAINTS);
        }
        final OrderDate modelOrderDate = new OrderDate(orderDate);

        final CompletedDate modelCompletedDate;
        final Set<CheeseId> modelCheeseId = new HashSet<>();

        if (completedDate == null) {
            modelCompletedDate = null;
        } else if (!CompletedDate.isValidDate(completedDate)) {
            throw new IllegalValueException(CompletedDate.MESSAGE_CONSTRAINTS);
        } else if (cheeseIds == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    OrderDate.class.getSimpleName()));
        } else {
            modelCompletedDate = new CompletedDate(completedDate);
        }

        modelCheeseId.addAll(cheeseIds.stream()
            .map(CheeseId::getNextId)
            .collect(Collectors.toList()));

        if (customerId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                CustomerId.class.getSimpleName()));
        }
        if (!CustomerId.isValidId(customerId)) {
            throw new IllegalValueException(CustomerId.MESSAGE_CONSTRAINTS);
        }
        final CustomerId modelCustomerId = CustomerId.getNextId(customerId);

        return new Order(modelCheeseType, modelQuantity, modelOrderDate,
                modelCompletedDate, modelCheeseId, modelId, modelCustomerId);
    }
}
