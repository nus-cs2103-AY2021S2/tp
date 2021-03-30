package seedu.address.model.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.Pair;
import seedu.address.model.Item;
import seedu.address.model.dish.Dish;
import seedu.address.model.person.Person;


public class Order implements Item {
    private LocalDateTime datetime;
    private Person customer;
    private List<Pair<Dish, Integer>> dishQuantityList;

    /**
     * Order constructor
     * @param datetime
     * @param customer
     * @param dishQuantityList
     */
    @JsonCreator
    public Order(@JsonProperty("datetime") LocalDateTime datetime, @JsonProperty("customer") Person customer,
                 @JsonProperty("dishQuantityList") List<Pair<Dish, Integer>> dishQuantityList) {
        this.datetime = datetime;
        this.customer = customer;
        this.dishQuantityList = dishQuantityList;
    }

    public String getStrDatetime() {
        return datetime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public Person getCustomer() {
        return customer;
    }

    public boolean isFromCustomer(Person target) {
        return customer.equals(target);
    }

    public List<Pair<Dish, Integer>> getDishQuantityList() {
        return dishQuantityList;
    }

    public String getDetails() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Datetime: ")
                .append(getStrDatetime())
                .append("\nCustomer: ")
                .append(getCustomer());

        if (!dishQuantityList.isEmpty()) {
            builder.append("\nDishes: ");
            for (Pair<Dish, Integer> pair : dishQuantityList) {
                builder.append(pair.getKey());
                builder.append(" x");
                builder.append(pair.getValue());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public boolean isSame(Item other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder != null
                && this.getStrDatetime().equals(otherOrder.getStrDatetime());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Dish)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getStrDatetime().equals(getStrDatetime())
                && otherOrder.getCustomer().equals(getCustomer())
                && listEquals(otherOrder.getDishQuantityList());
    }

    private boolean listEquals(List<Pair<Dish, Integer>> otherList) {
        if (otherList.size() != dishQuantityList.size()) {
            return false;
        }

        boolean result = true;
        for (int i = 0; i < dishQuantityList.size(); i++) {
            Pair<Dish, Integer> current = dishQuantityList.get(i);
            Pair<Dish, Integer> other = otherList.get(i);
            result = current.equals(other);
        }

        return result;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Datetime: ")
                .append(getStrDatetime())
                .append("; Customer: ")
                .append(getCustomer());

        if (!dishQuantityList.isEmpty()) {
            builder.append("; Dishes: ");
            for (Pair<Dish, Integer> pair : dishQuantityList) {
                builder.append(pair.getKey());
                builder.append(" x");
                builder.append(pair.getValue());
            }
        }
        return builder.toString();
    }
}
