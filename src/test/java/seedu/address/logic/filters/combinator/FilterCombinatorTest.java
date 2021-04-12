package seedu.address.logic.filters.combinator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FilterCombinatorTest {

    @Test
    public void filterCombinatorTestExpressionsWithoutBrackets() {
        String[] expressions = new String[]{
            "n/Abc asd n/dsfd",
            "n/Abc and n/dsfd",
            "n/Abc /and n/dsfd e/fsdfsd",
            "n/Abc /or ",
            "n/Abc /not n/dsfd",
            "n/Abc /and /or /not n/dsfd",
            "n/Abc /and /and e/dsfd",
            "  ",
            "[]",
            "p/123 []",
            "[p/123] []"
        };

        for (String s : expressions) {
            Assertions.assertThrows(ParseException.class, () -> new FilterCombinator(s));
        }
    }

    @Test
    public void validFilterCommandExpression() {
        String[] expressions = new String[]{
            "n/John",
            "n/Jojo /and e/some",
            "n/JOjoj /or e/some",
            "n/Hsdf /or /not e/fsdfsad",
            "n/sfdsf /and e/sdfsfd /or e/sdfsaf",
            "a/something",
            "ex/exp",
            "ex/10",
            "e/sfsadf",
            "b/1999",
            "/not /not e/ema /and /not /not n/fdfd ",
            "[/not /not e/ema] /and [/not /not n/fdfd]",
            "/not [/not e/ema /and /not /not n/fdfd]",
            "/not [/not [/not n/sfsaf]]"
        };

        for (String s : expressions) {
            Assertions.assertDoesNotThrow(() -> new FilterCombinator(s));
        }
    }

}
