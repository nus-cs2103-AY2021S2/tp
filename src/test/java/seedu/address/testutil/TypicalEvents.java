package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_ORIENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_INTERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_ORIENTATION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Sochedule;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final Event MEETING = new EventBuilder().withName("CS2103 Meeting")
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2021-03-17").withEndTime("23:00")
            .withTags("SocheduleBest").withCategories("SchoolWork")
            .build();
    public static final Event DATE = new EventBuilder().withName("1 Year Anniversary")
            .withStartDate("2021-03-14").withStartTime("08:00")
            .withEndDate("2021-03-17").withEndTime("21:00")
            .withTags("LoveYou").withCategories("Love")
            .build();
    public static final Event CAMP = new EventBuilder().withName("RVRC Camping")
            .withStartDate("2021-05-11").withStartTime("08:00")
            .withEndDate("2021-05-15").withEndTime("22:00")
            .withTags("Boring").withCategories("Leisure")
            .build();
    public static final Event HACKATHON = new EventBuilder().withName("Google Hash Code") // No tags
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2021-03-17").withEndTime("23:00")
            .withCategories("Competition")
            .build();
    public static final Event WORKSHOP = new EventBuilder().withName("Shopee Code League Workshop") // No category
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2021-03-17").withEndTime("23:00")
            .withTags("Learning")
            .build();
    public static final Event COMPETITION = new EventBuilder().withName("Inter College Game Basketball")
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2021-03-17").withEndTime("23:00")
            .withTags("Semifinal").withCategories("Exercise")
            .build();

    // Manually added
    public static final Event CONCERT = new EventBuilder().withName("JJ Lin Concert")
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2021-03-17").withEndTime("23:00")
            .withTags("FirstConcert").withCategories("Leisure")
            .build();
    public static final Event PICNIC = new EventBuilder().withName("Sentosa Picnic")
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2021-03-17").withEndTime("23:00")
            .withTags("Chill").withCategories("Leisure")
            .build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event INTERVIEW = new EventBuilder().withName(VALID_EVENT_NAME_INTERVIEW)
            .withStartDate(VALID_EVENT_STARTDATE_INTERVIEW).withStartTime(VALID_EVENT_STARTTIME_INTERVIEW)
            .withEndDate(VALID_EVENT_ENDDATE_INTERVIEW).withEndTime(VALID_EVENT_ENDTIME_INTERVIEW)
            .withTags(VALID_EVENT_TAG_INTERVIEW).withCategories(VALID_EVENT_CATEGORY_INTERVIEW)
            .build();
    public static final Event ORIENTATION = new EventBuilder().withName(VALID_EVENT_NAME_ORIENTATION)
            .withStartDate(VALID_EVENT_STARTDATE_ORIENTATION).withStartTime(VALID_EVENT_STARTTIME_ORIENTATION)
            .withEndDate(VALID_EVENT_ENDDATE_ORIENTATION).withEndTime(VALID_EVENT_ENDTIME_ORIENTATION)
            .withTags(VALID_EVENT_TAG_ORIENTATION).withCategories(VALID_EVENT_CATEGORY_ORIENTATION)
            .build();

    public static final String KEYWORD_MATCHING_CODE = "Code"; // A keyword that matches MEIER

    private TypicalEvents() {} // prevents instantiation

    /**
     * Returns an {@code Sochedule} with all the typical events.
     */
    public static Sochedule getTypicalSochedule() {
        Sochedule sochedule = new Sochedule();
        for (Event event : getTypicalEvents()) {
            sochedule.addEvent(event);
        }
        return sochedule;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, DATE, CAMP, HACKATHON, WORKSHOP, COMPETITION));
    }
}
