package seedu.cakecollate.model.order;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.cakecollate.commons.util.StringUtil;
import seedu.cakecollate.logic.parser.Prefix;
import seedu.cakecollate.model.tag.Tag;

/**
 * Tests that an {@code Order}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Order> {
    private final HashMap<Prefix, List<String>> keywords;

    public NameContainsKeywordsPredicate(HashMap<Prefix, List<String>> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        boolean result = false;
        for (Map.Entry<Prefix, List<String>> entry : keywords.entrySet()) {
            Prefix prefix = entry.getKey();
            List<String> value = entry.getValue();
            String testString = getTestString(prefix, order);
            result = result || prefixTest(value, testString);
        }
        return result;
    }

    public boolean prefixTest(List<String> toTest, String toTestFrom) {
        return toTest.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(toTestFrom, keyword));
    }

    public String getTestString(Prefix prefix, Order order) {
        if (prefix.equals(PREFIX_NAME)) {
            return order.getName().fullName;
        } else if (prefix.equals(PREFIX_PHONE)) {
            return order.getPhone().value;
        } else if (prefix.equals(PREFIX_EMAIL)) {
            return order.getEmail().value;
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            return order.getAddress().value;
        } else if (prefix.equals(PREFIX_ORDER_DESCRIPTION)) {
            Set<OrderDescription> orderDescriptions = order.getOrderDescriptions();
            Set<String> orderDescriptionsString = orderDescriptions.stream()
                    .map(OrderDescription::toString)
                    .collect(Collectors.toSet());
            return String.join(" ", orderDescriptionsString);
        } else if (prefix.equals(PREFIX_TAG)) {
            Set<Tag> tags = order.getTags();
            Set<String> tagsString = tags.stream()
                    .map(tag -> tag.tagName)
                    .collect(Collectors.toSet());
            return String.join(" ", tagsString);
        } else if (prefix.equals(PREFIX_DATE)) {
            LocalDate deliveryDate = order.getDeliveryDate().value;
            return deliveryDate.getDayOfMonth() + " "
                    + deliveryDate.getMonthValue() + " "
                    + deliveryDate.getMonth().toString() + " "
                    + deliveryDate.getYear();
        } else if (prefix.equals(PREFIX_REQUEST)) {
            return order.getRequest().value;
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
