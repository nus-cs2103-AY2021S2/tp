package seedu.address.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AgeParser {

    private List<Integer> ageRangeList = new ArrayList<>();

    /**
     * Constructs an {@code AgeParser}.
     *
     * @param age an integer in String format.
     */
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

    /**
     * Returns a list of parsed age integer values.
     */
    public List<Integer> value() {
        return this.ageRangeList;
    }

}
