package seedu.iscam.model.client;

import java.util.List;
import java.util.function.Predicate;

import seedu.iscam.commons.util.StringUtil;

/**
 * Tests that a {@code Client}'s {@code InsurancePlan} matches any of the keywords given.
 */
public class PlanContainsKeywordsPredicate implements Predicate<Client> {
    private final List<String> keywords;

    public PlanContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Client client) {
        return keywords.stream().anyMatch(keyword -> client.getPlans().stream()
                .anyMatch(plan -> StringUtil.containsIgnoreCase(plan.planName, keyword))
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PlanContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PlanContainsKeywordsPredicate) other).keywords)); // state check
    }

}
