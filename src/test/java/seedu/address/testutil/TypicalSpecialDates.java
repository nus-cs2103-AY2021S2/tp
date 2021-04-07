package seedu.address.testutil;

import java.time.LocalDate;

import seedu.address.model.person.SpecialDate;

public class TypicalSpecialDates {
    public static final SpecialDate DATE_ONE = new SpecialDateBuilder()
            .withDate(LocalDate.of(2019, 10, 10))
            .withDescription("Anniversary")
            .build();
}
