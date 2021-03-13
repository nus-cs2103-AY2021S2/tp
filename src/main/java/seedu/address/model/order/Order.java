package seedu.address.model.order;

import java.util.List;

import javafx.util.Pair;
import seedu.address.model.Item;
import seedu.address.model.dish.Dish;
import seedu.address.model.person.Person;

public class Order implements Item {
    private String id;
    private Person customer;
    private List<Pair<Dish, Integer>> dishQuantityList;

    public Order(String name, Person customer, List<Pair<Dish, Integer>> dishQuantityList) {
        this.id = name;
        this.customer = customer;
        this.dishQuantityList = dishQuantityList;
    }

    public String getId() {
        return id;
    }

    public Person getCustomer() {
        return customer;
    }

    public List<Pair<Dish, Integer>> getDishQuantityList() {
        return dishQuantityList;
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
                && otherOrder.getId().equals(otherOrder.getId());
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
        return otherOrder.getId().equals(getId())
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
        builder.append("ID: ")
                .append(getId())
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
