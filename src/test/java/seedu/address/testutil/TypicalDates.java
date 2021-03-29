package seedu.address.testutil;

import seedu.address.model.DatesBook;
import seedu.address.model.date.ImportantDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A utility class containing a list of {@code ImportantDate} objects to be used in tests.
 */
public class TypicalDates {

    public static final ImportantDate JURONG_SEC2_EXAM = new ImportantDateBuilder()
            .withDescription("Jurong Secondary 2 Exam")
            .withDetails("2021-04-23 1300")
            .build();

    public static final ImportantDate OLEVELS_ADDITIONAL_MATHEMATICS_EXAM = new ImportantDateBuilder()
            .withDescription("O-Levels additional mathematics paper 1")
            .withDetails("2021-11-01 1500")
            .build();

    private TypicalDates() {} // prevents instantiation

    public static DatesBook getTypicalDatesBook() {
        DatesBook db = new DatesBook();
        for (ImportantDate importantDate : getTypicalImportantDates()) {
            db.addImportantDate(importantDate);
        }
        return db;
    }

    public static List<ImportantDate> getTypicalImportantDates() {
        return new ArrayList<>(Arrays.asList(JURONG_SEC2_EXAM, OLEVELS_ADDITIONAL_MATHEMATICS_EXAM));
    }
}
