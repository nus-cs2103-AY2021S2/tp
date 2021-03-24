package seedu.address.model.property.client;

import seedu.address.commons.util.StringUtil;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Client}'s {@code Name} matches any of the keywords given.
 */
public class ClientNamePredicate implements Predicate<Client> {
    private final List<String> keywords;

    public ClientNamePredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(client.getClientName().name, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClientNamePredicate // instanceof handles nulls
                && keywords.equals(((ClientNamePredicate) other).keywords)); // state check
    }

}
