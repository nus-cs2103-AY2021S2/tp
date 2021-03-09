package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NameContainsPatternPredicateTest {

    public final Pattern subStringPattern = Pattern.compile("et");
    public final Pattern matchAllPattern = Pattern.compile(".*");
    public final Pattern startsWithAPattern = Pattern.compile("^a");
    public final Pattern endsWithAPattern = Pattern.compile("a$");
    public final Pattern startsAndEndWithAPattern = Pattern.compile("^a.*a$");

    private String[] nameArray = new String[] {
        "alpha beta charlie",
        "beta charlie",
        "delta charlie alpha",
        "beta beta beta",
        "alpha alpha charlie delta",
        "alpha  alpha    charlie delta"
    };

    private List<String> nameList = Arrays.stream(nameArray).collect(Collectors.toList());

    public List<String> filterList(List<String> nameList, Pattern pattern) {
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
