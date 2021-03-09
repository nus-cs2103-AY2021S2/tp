package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class NameContainsPatternPredicateTest {

    final Pattern subStringPattern = Pattern.compile("et");
    final Pattern matchAllPattern = Pattern.compile(".*");
    final Pattern startsWithAPattern = Pattern.compile("^a");
    final Pattern endsWithAPattern = Pattern.compile("a$");
    final Pattern startsAndEndWithAPattern = Pattern.compile("^a.*a$");

    String[] nameArray = new String[] {
            "alpha beta charlie",
            "beta charlie",
            "delta charlie alpha",
            "beta beta beta",
            "alpha alpha charlie delta",
            "alpha  alpha    charlie delta"
    };

    List<String> nameList = Arrays.stream(nameArray).collect(Collectors.toList());

    List<String> filterList(List<String> nameList, Pattern pattern) {
        return nameList.stream()
                .map(name -> new PersonBuilder().withName(name).build())
                .filter(new NameContainsPatternPredicate(pattern))
                .map(person -> person.getName().toString())
                .collect(Collectors.toList());
    }

    @Test
    public void test_nameContainsPattern_returnsTrue() {
        List<String> correctList = new ArrayList<>();

        correctList.add("alpha beta charlie");
        correctList.add("beta charlie");
        correctList.add("beta beta beta");
        assertEquals(correctList, filterList(nameList, subStringPattern));
        correctList.clear();

        assertEquals(filterList(nameList, matchAllPattern), nameList);

        correctList.add("alpha beta charlie");
        correctList.add("alpha alpha charlie delta");
        correctList.add("alpha  alpha    charlie delta");
        assertEquals(correctList, filterList(nameList, startsWithAPattern));
        correctList.clear();

        correctList.add("delta charlie alpha");
        correctList.add("beta beta beta");
        correctList.add("alpha alpha charlie delta");
        correctList.add("alpha  alpha    charlie delta");
        assertEquals(correctList, filterList(nameList, endsWithAPattern));
        correctList.clear();

        correctList.add("alpha alpha charlie delta");
        correctList.add("alpha  alpha    charlie delta");
        assertEquals(correctList, filterList(nameList, startsAndEndWithAPattern));
    }
}
