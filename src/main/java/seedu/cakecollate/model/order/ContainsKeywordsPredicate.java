package seedu.cakecollate.model.order;

import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ALL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_DELIVERY_STATUS;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_ORDER_DESCRIPTION;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_REQUEST;
import static seedu.cakecollate.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.cakecollate.commons.util.StringUtil;
import seedu.cakecollate.logic.parser.Prefix;
import seedu.cakecollate.model.tag.Tag;

/**
 * Tests that an {@code Order}'s {@code Name} matches any of the keywords given.
 */
public class ContainsKeywordsPredicate implements Predicate<Order> {
    private final HashMap<Prefix, List<String>> keywords;

    public ContainsKeywordsPredicate(HashMap<Prefix, List<String>> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Order order) {
        boolean result = true;
        for (Map.Entry<Prefix, List<String>> entry : keywords.entrySet()) {
            Prefix prefix = entry.getKey();
            List<String> value = entry.getValue();
            String testString = getTestString(prefix, order);
            result = result && prefixTest(value, testString);
        }
        return result;
    }

    /**
     * Test each {@code String} retrieved from {@code Order} to see if {@code String}
     * contains any of the provided keywords.
     */
    public boolean prefixTest(List<String> toTest, String toTestFrom) {
        return toTest.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(toTestFrom, keyword));
    }

    /**
     * For the specified {@code Order}, retrieve, process and return the {@code String} to test keywords against
     * based on the provided {@code Prefix}.
     */
    public String getTestString(Prefix prefix, Order order) {
        // Cannot use switch-case unless I edit the Prefix class...
        if (prefix.equals(PREFIX_NAME)) {
            return order.getName().fullName;
        } else if (prefix.equals(PREFIX_PHONE)) {
            return order.getPhone().value;
        } else if (prefix.equals(PREFIX_EMAIL)) {
            return order.getEmail().value;
        } else if (prefix.equals(PREFIX_ADDRESS)) {
            return order.getAddress().value;
        } else if (prefix.equals(PREFIX_ORDER_DESCRIPTION)) {
            Map<OrderDescription, Integer> orderDescriptions = order.getOrderDescriptions();
            Set<String> orderDescriptionsString = orderDescriptions.keySet().stream()
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
            return order.getDeliveryDate().getTestString();
        } else if (prefix.equals(PREFIX_REQUEST)) {
            return order.getRequest().value;
        } else if (prefix.equals(PREFIX_DELIVERY_STATUS)) {
            return order.getDeliveryStatus().toString();
        } else if (prefix.equals(PREFIX_ALL)) {
            List<Prefix> prefixes = Arrays.asList(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                    PREFIX_ORDER_DESCRIPTION, PREFIX_TAG, PREFIX_DATE, PREFIX_REQUEST, PREFIX_DELIVERY_STATUS);
            StringBuilder returnResult = new StringBuilder();
            prefixes.forEach(px -> returnResult.append(getTestString(px, order)).append(" "));
            return returnResult.toString();
        } else {
            return "";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContainsKeywordsPredicate) other).keywords)); // state check
    }
}
