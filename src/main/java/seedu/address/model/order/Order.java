package seedu.address.model.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.core.Pair;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.Aggregator;
import seedu.address.model.Item;
import seedu.address.model.dish.Dish;
import seedu.address.model.person.Person;


public class Order implements Item, Aggregator<Dish> {
    public enum State {
        UNCOMPLETED, COMPLETED, CANCELLED
    }

    private LocalDateTime dateTime;
    private Person customer;
    private List<Pair<Dish, Integer>> dishQuantityList;
    private State state = State.UNCOMPLETED;

    /**
     * Order constructor
     * @param dateTime
     * @param customer
     * @param dishQuantityList
     */
    @JsonCreator
    public Order(@JsonProperty("datetime") LocalDateTime dateTime, @JsonProperty("customer") Person customer,
                 @JsonProperty("dishQuantityList") List<Pair<Dish, Integer>> dishQuantityList) {
        this.dateTime = dateTime;
        this.customer = customer;
        this.dishQuantityList = dishQuantityList;
    }

    public String getStrDatetime() {
        return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    public LocalDateTime getDatetime() {
        return dateTime;
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

    public String getDishesString() {
        StringBuilder dishesBuilder = new StringBuilder();

        for (Pair<Dish, Integer> dishPair: dishQuantityList) {
            String dishesStr = dishPair.getValue() + " x " + dishPair.getKey().getName()
                    + " - SGD " + String.format("%.2f", dishPair.getKey().getPrice()) + "\n";
            dishesBuilder.append(dishesStr);
        }

        return dishesBuilder.toString();
    }

    /**
     * Returns a new Order object which is a copy of the current one but
     * with {@code customer} replaced with {@code editedPerson}.
     * @param editedPerson New (updated) {@code Customer} object to replace with
     * @return Copy of {@code Order} object with {@code editedPerson} in the customer attribute instead
     */
    public Order updateCustomer(Person editedPerson) {
        return new Order(dateTime, editedPerson, dishQuantityList);
    }

    /**
     * Returns a new Order object which is a copy of the current one but
     * with {@code target} dish replaced with {@code editedDish}.
     * @param target {@code Dish} object to be replaced with {@code editedDish}
     * @param editedDish New (updated) {@code Dish} object
     * @return Copy of {@code Order} object with {@code editedDish} in the {@code dishQuantityList} attribute instead
     */
    public Order updateDish(Dish target, Dish editedDish) {
        List<Pair<Dish, Integer>> updatedQuantityList = new ArrayList<>();
        for (Pair<Dish, Integer> p : dishQuantityList) {
            if (p.getKey().equals(target)) {
                updatedQuantityList.add(new Pair<>(editedDish, p.getValue()));
            } else {
                updatedQuantityList.add(p);
            }
        }
        return new Order(dateTime, customer, updatedQuantityList);
    }

    public double getTotalPrice() {
        double totalPrice = 0.0;

        for (Pair<Dish, Integer> dishPair: dishQuantityList) {
            totalPrice += dishPair.getKey().getPrice();
        }

        return totalPrice;
    }

    public String getDetails() {
        final StringBuilder builder = new StringBuilder();
        String state;
        if (getState() == State.UNCOMPLETED) {
            state = "(Uncompleted)";
        } else if (getState() == State.COMPLETED) {
            state = "(Completed) ";
        } else {
            state = "(Cancelled) ";
        }
        builder.append(state)
                .append("Datetime: ")
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

    public State getState() {
        return state;
    }

    public void setState(State newState) {
        state = newState;
    }

    // Predicate methods
    public boolean containsCustomerKeyword(String name) {
        return StringUtil.containsWordIgnoreCase(customer.getName(), name);
    }

    /**
     * Checks if dish contains given keyword
     * @param keyword keyword to be checked
     * @return true if dish contans given keyword
     */
    public boolean containsDishKeyword(String keyword) {
        for (Pair<Dish, Integer> pair : getDishQuantityList()) {
            if (StringUtil.containsWordIgnoreCase(pair.getKey().getName(), keyword)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if a particular object is contained within the current one
     *
     * @param dish
     */
    @Override
    public boolean contains(Dish dish) {
        for (Pair<Dish, Integer> p : dishQuantityList) {
            if (p.getKey().isSame(dish)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Two orders are only the same if every one of their fields matches.
     * Otherwise, they are different orders.
     */
    @Override
    public boolean isSame(Item other) {
        return this.equals(other);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
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
