package seedu.address.model.plan;

import java.util.HashMap;

public class Grade {
    public static final HashMap<String, Double> GRADE = new HashMap<String, Double>() {
        {
            put("A+", 5.0);
            put("A", 5.0);
            put("A-", 4.5);
            put("B+", 4.0);
            put("B", 3.5);
            put("B-", 3.0);
            put("C+", 2.5);
            put("C", 2.0);
            put("D+", 1.5);
            put("D", 1.0);
            put("F", 0.0);
        }
    };
}
