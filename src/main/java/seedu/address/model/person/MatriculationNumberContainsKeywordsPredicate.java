package seedu.address.model.person;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;

import java.util.function.Predicate;
import java.util.logging.Logger;

/**
 * Tests that a {@code Person}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class MatriculationNumberContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    public MatriculationNumberContainsKeywordsPredicate(String keyword) {
        assert keyword.equals(keyword.toUpperCase());
        this.keyword = keyword;
        logger.info("----------------[MATRIC NUMBER TO BE SEARCHED:][" + keyword + "]");
    }

    @Override
    public boolean test(Person person) {
        return keyword.equals((person.getMatriculationNumber().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumberContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((MatriculationNumberContainsKeywordsPredicate) other).keyword)); // state check
    }

}
