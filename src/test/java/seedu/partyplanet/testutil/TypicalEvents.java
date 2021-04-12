package seedu.partyplanet.testutil;

import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DATE_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DATE_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_REMARK_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_REMARK_EASTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event APR = new EventBuilder().withName("Apr Celebration").withDate("2022-04-01").withRemark(
        "in school").build();
    public static final Event FEB = new EventBuilder().withName("Feb Celebration").withDate("2022-02-01").withRemark(
        "10 people").build();
    public static final Event JAN = new EventBuilder().withName("Jan Celebration").withDate("2022-01-01").withRemark(
        "10 people").build();
    public static final Event MAR = new EventBuilder().withName("Mar Celebration").withDate("2022-03-01").withRemark(
        "PLAN NOW!").build();


    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event CNY = new EventBuilder().withName(VALID_NAME_CNY).withDate(VALID_DATE_CNY)
            .withRemark(VALID_REMARK_CNY).build();
    public static final Event EASTER = new EventBuilder().withName(VALID_NAME_EASTER).withDate(VALID_DATE_EASTER)
            .withRemark(VALID_REMARK_EASTER).build();

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (Event event : getTypicalEvents()) {
            eb.addEvent(event);
        }
        return eb;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(APR, FEB, JAN, MAR));
    }
}
