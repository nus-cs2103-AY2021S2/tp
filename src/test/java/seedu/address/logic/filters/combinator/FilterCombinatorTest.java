package seedu.address.logic.filters.combinator;

import org.junit.jupiter.api.Test;

public class FilterCombinatorTest {

    @Test
    public void filterCombinatorTest1() {
        String expression = "O1 /AND O2 /OR /NOT O3";
        System.out.println(expression);
        FilterCombinator filterCombinator = new FilterCombinator(expression);
        System.out.println(filterCombinator);
    }

}
