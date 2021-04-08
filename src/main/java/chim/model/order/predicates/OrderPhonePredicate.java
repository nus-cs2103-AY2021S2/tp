package chim.model.order.predicates;

import static java.util.Objects.requireNonNull;

import java.util.List;

import chim.commons.util.PredicateUtil;
import chim.model.customer.Customer;
import chim.model.order.Order;
import chim.model.util.predicate.SingleFieldPredicate;

/**
 * Predicate for filtering orders by their customers' phone numbers.
 */
public class OrderPhonePredicate extends SingleFieldPredicate<Order> {

    public static final String MESSAGE_CONSTRAINTS = "Phone number keywords must be numerical and not empty.";

    private final List<Customer> customerList;

    /**
     * Creates a predicate to filter orders by the orders' customers' phone numbers.
     * The customer list is needed here to access the orders' customers' information.
     */
    public OrderPhonePredicate(List<String> keywords, List<Customer> customerList) {
        super(keywords);
        this.customerList = customerList;
    }

    /**
     * Checks whether a given list of phone keywords is valid.
     */
    public static boolean isValidKeywords(List<String> keywords) {
        return SingleFieldPredicate.isValidKeywords(keywords)
                && keywords.stream().allMatch(word -> word.matches("\\d+"));
    }

    @Override
    public double getSimilarityScore(Order order) {
        String phone = getPhoneByOrder(order);
        requireNonNull(phone);
        return PredicateUtil.getWordSimilarityScoreIgnoreCase(phone, getKeywords());
    }

    @Override
    public boolean test(Order order) {
        String phone = getPhoneByOrder(order);
        requireNonNull(phone);
        return PredicateUtil.containsPrefixWordIgnoreCase(phone, getKeywords());
    }

    @Override
    public boolean equals(Object other) {
        return (other instanceof SingleFieldPredicate)
                && super.equals(other)
                && this.customerList.equals(((OrderPhonePredicate) other).customerList);
    }

    private String getPhoneByOrder(Order order) {
        Customer matchingCustomer = customerList
                .stream()
                .filter(customer -> customer.getId().equals(order.getCustomerId()))
                .findAny()
                .orElse(null);
        return matchingCustomer != null ? matchingCustomer.getPhone().toString() : null;
    }

    @Override
    public String toString() {
        return String.format("customer's phone starting with %s",
                super.toString());
    }
}
