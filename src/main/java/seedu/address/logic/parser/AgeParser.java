package seedu.address.logic.parser;

import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgeParser {

    List<Integer> ageRangeList = new ArrayList<>();
// filter age/25-40
// age = "25-40"
    public AgeParser(String age) {
        if (age == null) {
            ageRangeList.add(Integer.MIN_VALUE);
            ageRangeList.add(Integer.MAX_VALUE);
        } else if (age.contains("-")) {
            Arrays.asList(age.split("-")).stream().forEach(num ->
                    ageRangeList.add(Integer.parseInt(num))
            );
            Collections.sort(ageRangeList);
        } else {
            ageRangeList.add(Integer.parseInt(age));
        }
    }

    public List<Integer> value() {
        return this.ageRangeList;
    }

}
