package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.session.Session;

/**
 * A utility class containing a list of {@code Session} objects to be used in tests.
 */
public class TypicalSessions {

    public static final Session FIRST_SESSION = new SessionBuilder().withSessionId("c/1")
            .withDay("MONDAY").withTimeslot("09:30 to 12:00")
            .withSubject("Piano").withTags("Morning").build();
    public static final Session SECOND_SESSION = new SessionBuilder().withSessionId("c/2")
            .withDay("TUESDAY").withTimeslot("12:00 to 13:00")
            .withSubject("CHEMISTRY").withTags("AFTERNOON").build();
    public static final Session THIRD_SESSION = new SessionBuilder().withSessionId("c/3")
            .withDay("WEDNESDAY").withTimeslot("14:30 to 16:00")
            .withSubject("MATH").withTags("EVENING").build();
    public static final Session FOURTH_SESSION = new SessionBuilder().withSessionId("c/4")
            .withDay("THURSDAY").withTimeslot("12:30 to 13:00")
            .withSubject("SCIENCE").withTags("AFTERNOON").build();
    public static final Session FIFTH_SESSION = new SessionBuilder().withSessionId("c/5")
            .withDay("FRIDAY").withTimeslot("08:30 to 10:00")
            .withSubject("CHINESE").withTags("Morning").build();
    public static final Session SIXTH_SESSION = new SessionBuilder().withSessionId("c/6")
            .withDay("SATURDAY").withTimeslot("12:30 to 13:00")
            .withSubject("Piano").withTags("Morning").build();
    public static final Session SEVENTH_SESSION = new SessionBuilder().withSessionId("c/7")
            .withDay("SUNDAY").withTimeslot("15:00 to 16:00")
            .withSubject("PHYSICS").withTags("AFTERNOON").build();


    private TypicalSessions() {
    } // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical sessions.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Session session : getTypicalSessions()) {
            ab.addSession(session);
        }
        Session.setSessionCount("7");
        return ab;
    }

    public static List<Session> getTypicalSessions() {
        return new ArrayList<>(Arrays.asList(FIRST_SESSION , SECOND_SESSION , THIRD_SESSION,
                FOURTH_SESSION , FIFTH_SESSION , SIXTH_SESSION , SEVENTH_SESSION));
    }
}
