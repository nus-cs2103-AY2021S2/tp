package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_SCHOOL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_CATEGORY_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_ENDTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTDATE_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_STARTTIME_EVENTTWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FINAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_TAG_FUN;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Sochedule;
import seedu.address.model.common.Date;
import seedu.address.model.event.Event;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final String TODAY_DATE = LocalDate.now().toString();

    public static final String MEETING_DATE = LocalDate.now().plusDays(6).toString();

    public static final String DATING_DATE = LocalDate.now().plusDays(7).toString();

    public static final String CAMPTING_DATE = LocalDate.now().plusMonths(1).toString();

    public static final String HACKATHON_START_DATE = LocalDate.now().plusMonths(1).plusDays(1).toString();

    public static final String HACKATHON_END_DATE = LocalDate.now().plusMonths(2).toString();

    public static final String WORKSHOP_DATE = LocalDate.now().plusMonths(1).plusDays(3).toString();

    public static final String COMPETITION_DATE = LocalDate.now().plusMonths(2).plusDays(10).toString();

    // for find_schedule related testing
    // Last date is later than the latest date used in testing
    public static final Date LAST_DATE = new Date(LocalDate.now().plusYears(3).toString());

    // First date is early than the earliest date used in testing
    public static final Date EARLIEST_DATE = new Date(LocalDate.now().minusYears(3).toString());



    public static final Event MEETING = new EventBuilder().withName("CS2103 Meeting")
            .withStartDate(MEETING_DATE).withStartTime("21:00")
            .withEndDate(MEETING_DATE).withEndTime("23:00")
            .withTags("SocheduleBest").withCategories("SchoolWork")
            .build();

    public static final Event DATE = new EventBuilder().withName("1 Year Anniversary")
            .withStartDate(DATING_DATE).withStartTime("08:00")
            .withEndDate(DATING_DATE).withEndTime("21:00")
            .withTags("LoveYou").withCategories("Love")
            .build();

    public static final Event CAMP = new EventBuilder().withName("RVRC Camping")
            .withStartDate(CAMPTING_DATE).withStartTime("08:00")
            .withEndDate(CAMPTING_DATE).withEndTime("22:00")
            .withTags("Boring").withCategories("Leisure")
            .build();

    public static final Event HACKATHON = new EventBuilder().withName("Google Hash Code") // No tags
            .withStartDate(HACKATHON_START_DATE).withStartTime("21:00")
            .withEndDate(HACKATHON_END_DATE).withEndTime("23:00")
            .withCategories("Competition")
            .build();

    public static final Event WORKSHOP = new EventBuilder().withName("Shopee Code League Workshop") // No category
            .withStartDate(WORKSHOP_DATE).withStartTime("21:00")
            .withEndDate(WORKSHOP_DATE).withEndTime("23:00")
            .withTags("Learning")
            .build();

    public static final Event COMPETITION = new EventBuilder().withName("Inter College Game Basketball")
            .withStartDate(COMPETITION_DATE).withStartTime("21:00")
            .withEndDate(COMPETITION_DATE).withEndTime("23:00")
            .withTags("Semifinal").withCategories("Exercise")
            .build();

    // Manually added
    public static final Event CONCERT = new EventBuilder().withName("JJ Lin Concert")
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2022-03-17").withEndTime("23:00")
            .withTags("FirstConcert").withCategories("Leisure")
            .build();
    public static final Event PICNIC = new EventBuilder().withName("Sentosa Picnic")
            .withStartDate("2021-03-17").withStartTime("21:00")
            .withEndDate("2022-03-17").withEndTime("23:00")
            .withTags("Chill").withCategories("Leisure")
            .build();

    // With duration on today
    public static final Event HAPPENING = new EventBuilder().withName("Today Event")
            .withStartDate(TODAY_DATE).withStartTime("09:00")
            .withEndDate(TODAY_DATE).withEndTime("14:00")
            .withTags("Today").withCategories("Cool")
            .build();

    public static final Event ANOTHER_HAPPENING = new EventBuilder().withName("Another Today Event")
            .withStartDate(TODAY_DATE).withStartTime("15:00")
            .withEndDate(TODAY_DATE).withEndTime("17:00")
            .withTags("Today").withCategories("VeryCool")
            .build();

    // Manually added - Event's details found in {@code CommandTestUtil}
    public static final Event EVENTONE = new EventBuilder().withName(VALID_EVENT_NAME_EVENTONE)
            .withStartDate(VALID_EVENT_STARTDATE_EVENTONE).withStartTime(VALID_EVENT_STARTTIME_EVENTONE)
            .withEndDate(VALID_EVENT_ENDDATE_EVENTONE).withEndTime(VALID_EVENT_ENDTIME_EVENTONE)
            .withCategories(VALID_EVENT_CATEGORY_WORK).withTags(VALID_EVENT_TAG_FINAL)
            .build();
    public static final Event EVENTTWO = new EventBuilder().withName(VALID_EVENT_NAME_EVENTTWO)
            .withStartDate(VALID_EVENT_STARTDATE_EVENTTWO).withStartTime(VALID_EVENT_STARTTIME_EVENTTWO)
            .withEndDate(VALID_EVENT_ENDDATE_EVENTTWO).withEndTime(VALID_EVENT_ENDTIME_EVENTTWO)
            .withCategories(VALID_EVENT_CATEGORY_SCHOOL).withTags(VALID_EVENT_TAG_FUN)
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

    /**
     * Returns an {@Code Sochedule} with two additional events on today added.
     */
    public static Sochedule getTypicalSocheduleWithTodayEvent() {
        Sochedule sochedule = new Sochedule();
        for (Event event : getTypicalEvents()) {
            sochedule.addEvent(event);
        }
        sochedule.addEvent(HAPPENING);
        sochedule.addEvent(ANOTHER_HAPPENING);
        return sochedule;
    }

    public static List<Event> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(MEETING, DATE, CAMP, HACKATHON, WORKSHOP, COMPETITION));
    }
}
