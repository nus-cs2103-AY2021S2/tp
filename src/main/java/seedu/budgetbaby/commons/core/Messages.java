package seedu.budgetbaby.commons.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final LocalDate currDate = LocalDate.now();
    public static final String date = dtf.format(currDate);

    public static final String MESSAGE_TYPICAL_BUDGET_TRACKER_ORIGINAL_LIST =
            "[" + date + " | Breakfast | 5.00; Categories: [Food], "
                    + date + " | Lunch | 6.00; Categories: [Food], "
                    + date + " | Movie | 8.00; Categories: [Entertainment], "
                    + date + " | Button Shirt | 18.00; Categories: [Apparel], "
                    + date + " | Jeans | 55.00; Categories: [Apparel], "
                    + date + " | Water Bottle | 12.00; Categories: [Essentials]]";
    public static final String MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_FOOD =
            "[" + date + " | Breakfast | 5.00; Categories: "
                    + "[Food], " + date + " | Lunch | 6.00; Categories: [Food]]";
    public static final String MESSAGE_TYPICAL_BUDGET_TRACKER_FILTER_LUNCH =
            "[" + date + " | Lunch | 6.00; Categories: [Food]]";

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid!";
    public static final String MESSAGE_INVALID_FINANCIAL_RECORD_DISPLAYED_INDEX =
        "The financial record index provided is invalid!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

}
