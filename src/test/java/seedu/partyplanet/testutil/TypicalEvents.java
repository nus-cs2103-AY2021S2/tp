package seedu.partyplanet.testutil;

import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DATE_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DATE_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DETAIL_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_DETAIL_EASTER;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_CNY;
import static seedu.partyplanet.logic.commands.CommandTestUtil.VALID_NAME_EASTER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.partyplanet.model.EventBook;
import seedu.partyplanet.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event JAN = new EventBuilder().withName("Jan Celebration").withDate("2020-01-01").withDetail(
        "5 people").build();
    public static final Event FEB = new EventBuilder().withName("Feb Celebration").withDate("2020-02-01").withDetail(
        "10 people").build();
    public static final Event MAR = new EventBuilder().withName("Mar Celebration").withDate("2020-03-01").withDetail(
        "PLAN NOW!").build();
    public static final Event APR = new EventBuilder().withName("CNY Celebration").withDate("2020-04-01").withDetail(
        "no rush to plan").build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event CNY = new EventBuilder().withName(VALID_NAME_CNY).withDate(VALID_DATE_CNY)
            .withDetail(VALID_DETAIL_CNY).build();
    public static final Event EASTER = new EventBuilder().withName(VALID_NAME_EASTER).withDate(VALID_DATE_EASTER)
        .withDetail(VALID_DETAIL_EASTER).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

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
        return new ArrayList<>(Arrays.asList(JAN, FEB, MAR, APR));
    }
}
