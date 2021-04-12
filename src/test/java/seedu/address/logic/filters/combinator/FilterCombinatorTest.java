package seedu.address.logic.filters.combinator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FilterCombinatorTest {

    @Test
    public void filterCombinatorTest1() throws ParseException {
        String expression = "O1 /AND O2 /OR /NOT O3";
        System.out.println(expression);
        FilterCombinator filterCombinator = new FilterCombinator(expression);
        System.out.println(filterCombinator);
    }

}
