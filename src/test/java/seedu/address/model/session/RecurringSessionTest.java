package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_END_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_END_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_START_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RECURRING_START_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.SessionBuilder.DEFAULT_DATE;
import static seedu.address.testutil.SessionBuilder.DEFAULT_DURATION;
import static seedu.address.testutil.SessionBuilder.DEFAULT_FEE;
import static seedu.address.testutil.SessionBuilder.DEFAULT_SUBJECT;
import static seedu.address.testutil.SessionBuilder.DEFAULT_TIME;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_SESSION;
import static seedu.address.testutil.TypicalStudents.CARL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.RecurringSessionBuilder;
import seedu.address.testutil.SessionBuilder;

class RecurringSessionTest extends SessionTest {
    static final Interval INTERVAL = new Interval("7");
    static final SessionDate SESSION_DATE = new SessionDate("2021-01-01", "10:00");
    static final Duration DURATION = new Duration("60");
    static final Subject SUBJECT = new Subject("English");
    static final Fee FEE = new Fee("40");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RecurringSession(SESSION_DATE,
                DURATION, SUBJECT, FEE, INTERVAL, null));
    }

    @Test
    public void isValidEndTest() {
        SessionDate consistent = new SessionDate("2021-01-15", "10:00");
        SessionDate inconsistentDate = new SessionDate("2021-01-14", "10:00");
        SessionDate inconsistentTime = new SessionDate("2021-01-15", "12:00");


        assertTrue(RecurringSession.isValidEnd(SESSION_DATE, consistent, INTERVAL));
        assertFalse(RecurringSession.isValidEnd(SESSION_DATE, inconsistentDate, INTERVAL));
        assertFalse(RecurringSession.isValidEnd(SESSION_DATE, inconsistentTime, INTERVAL));
    }

    @Test
    public void lastValidDateOnOrBeforeTest() {
        //end is on start, suggest after 1 recurrence, thus invalid
        assertEquals(SESSION_DATE.getDate(),
                RecurringSession.lastValidDateOnOrBefore(SESSION_DATE, SESSION_DATE, INTERVAL));

        //end is one interval after, thus valid
        assertEquals(SESSION_DATE.getDate().plusDays(INTERVAL.getValue()),
                RecurringSession
                        .lastValidDateOnOrBefore(SESSION_DATE.addDays(INTERVAL.getValue()), SESSION_DATE, INTERVAL));

        // end is one day after one interval, thus invalid
        assertEquals(SESSION_DATE.getDate().plusDays(INTERVAL.getValue()),
                RecurringSession
                        .lastValidDateOnOrBefore(
                                SESSION_DATE.addDays(INTERVAL.getValue() + 1), SESSION_DATE, INTERVAL));
    }

    @Test
    public void isConsistentDatesAndIntervalTest() {
        SessionDate consistentDateAndInterval = new SessionDate("2021-01-15", "12:00");
        SessionDate same = new SessionDate("2021-01-01", "10:00");
        SessionDate inconsistentDate = new SessionDate("2021-01-14", "10:00");
        Interval inconsistentInterval = new Interval("3");
        Interval nextDay = new Interval("1");


        assertTrue(RecurringSession.isConsistentDatesAndInterval(SESSION_DATE, consistentDateAndInterval, INTERVAL));
        assertTrue(RecurringSession.isConsistentDatesAndInterval(SESSION_DATE, same, nextDay));
        assertFalse(RecurringSession.isConsistentDatesAndInterval(SESSION_DATE, inconsistentDate, INTERVAL));
        assertFalse(RecurringSession.isConsistentDatesAndInterval(
                SESSION_DATE, consistentDateAndInterval, inconsistentInterval));
    }

    @Test
    void getIntervalTest() {
        if (CARL.getListOfSessions().get(0) instanceof RecurringSession) {
            assertEquals(INTERVAL, (
                    (RecurringSession) CARL.getListOfSessions().get(0)).getInterval());
        }
    }

    @Test
    void getLastSessionDateTest() {
        if (CARL.getListOfSessions().get(0) instanceof RecurringSession) {
            assertEquals(new SessionDate("2021-01-15", DEFAULT_TIME), ((
                    (RecurringSession) CARL.getListOfSessions().get(0)).getLastSessionDate()));
        }
    }

    @Test
    void endBeforeTest() {
        RecurringSession toTest = new RecurringSession(SESSION_DATE, DURATION, SUBJECT, FEE, INTERVAL,
                SESSION_DATE.addDays(INTERVAL.getValue() * 2));

        assertFalse(toTest.endBefore(SESSION_DATE.addDays(INTERVAL.getValue())));
        assertFalse(toTest.endBefore(SESSION_DATE.addDays(INTERVAL.getValue() * 2)));
        assertTrue(toTest.endBefore(SESSION_DATE.addDays(INTERVAL.getValue() * 2 + 1)));
    }

    @Test
    void startAfterTest() {
        RecurringSession toTest = new RecurringSession(SESSION_DATE, DURATION, SUBJECT, FEE, INTERVAL,
                SESSION_DATE.addDays(INTERVAL.getValue()));

        assertFalse(toTest.startAfter(SESSION_DATE));
        assertFalse(toTest.startAfter(SESSION_DATE.addDays(1)));
        assertTrue(toTest.startAfter(SESSION_DATE.minusDays(1)));
    }

    @Test
    void hasSessionOnDateTest() {
        RecurringSession startAfter = new RecurringSession(new SessionDate("2021-03-01", "10:00"),
                DURATION, SUBJECT, FEE, INTERVAL, new SessionDate("2021-03-15", "10:00"));
        RecurringSession endBefore = new RecurringSession(new SessionDate("2020-03-01", "10:00"),
                DURATION, SUBJECT, FEE, INTERVAL, new SessionDate("2020-03-15", "10:00"));
        RecurringSession notOnDate = new RecurringSession(new SessionDate("2020-12-31", "10:00"),
                DURATION, SUBJECT, FEE, INTERVAL, new SessionDate("2021-01-14", "10:00"));
        RecurringSession onDate = new RecurringSession(new SessionDate("2020-12-11", "10:00"),
                DURATION, SUBJECT, FEE, INTERVAL, new SessionDate("2021-01-15", "10:00"));

        assertFalse(startAfter.hasSessionOnDate(SESSION_DATE));
        assertFalse(endBefore.hasSessionOnDate(SESSION_DATE));
        assertFalse(notOnDate.hasSessionOnDate(SESSION_DATE));
        assertTrue(onDate.hasSessionOnDate(SESSION_DATE));
    }

    @Test
    void buildSessionOnDateTest_sessionDateInput() {
        RecurringSession recurringSession = new RecurringSession(SESSION_DATE, DURATION, SUBJECT, FEE, INTERVAL,
                SESSION_DATE.addDays(INTERVAL.getValue() * 3));
        Session firstSession = new Session(SESSION_DATE, DURATION, SUBJECT, FEE);
        Session lastSession = new Session(SESSION_DATE.addDays(INTERVAL.getValue() * 3), DURATION, SUBJECT, FEE);

        assertEquals(firstSession, recurringSession.buildSessionOnDate(SESSION_DATE));
        assertEquals(lastSession, recurringSession.buildSessionOnDate(SESSION_DATE.addDays(INTERVAL.getValue() * 3)));
        assertThrows(AssertionError.class, () -> recurringSession.buildSessionOnDate(SESSION_DATE.addDays(1)));
        assertThrows(NullPointerException.class, () -> recurringSession.buildSessionOnDate((SessionDate) null));
    }

    @Test
    void buildSessionOnDateTest_localDateInput() {
        RecurringSession recurringSession = new RecurringSession(SESSION_DATE, DURATION, SUBJECT, FEE, INTERVAL,
                SESSION_DATE.addDays(INTERVAL.getValue() * 3));
        Session firstSession = new Session(SESSION_DATE, DURATION, SUBJECT, FEE);
        Session lastSession = new Session(SESSION_DATE.addDays(INTERVAL.getValue() * 3), DURATION, SUBJECT, FEE);

        assertEquals(firstSession, recurringSession.buildSessionOnDate(SESSION_DATE.getDate()));
        assertEquals(lastSession, recurringSession
                .buildSessionOnDate(SESSION_DATE.addDays(INTERVAL.getValue() * 3).getDate()));
        assertThrows(AssertionError.class, () -> recurringSession
                .buildSessionOnDate(SESSION_DATE.addDays(1).getDate()));
        assertThrows(NullPointerException.class, () -> recurringSession.buildSessionOnDate((LocalDate) null));
    }


    @Test
    void isRecurringOverlappingRecurring() {
        // EndDate in relation to when the first session begins

        // Days Between: 8, Interval1: 3, Interval2: 7, EndDate1: 15, EndDate2: 15
        RecurringSession recurringSessionTest1 = new RecurringSession(new SessionDate("2021-01-01", "10:00"),
                DURATION, SUBJECT, FEE, new Interval("3"), new SessionDate("2021-01-16", "10:00"));
        RecurringSession recurringSession2Test1 = new RecurringSession(new SessionDate("2021-01-09", "10:00"),
                DURATION, SUBJECT, FEE, new Interval("7"), new SessionDate("2021-01-16", "10:00"));
        assertTrue(recurringSessionTest1.isOverlapping(recurringSession2Test1));

        // Days Between: 10, Interval1: 14, Interval2: 3, EndDate1: 28, EndDate2: 31
        RecurringSession recurringSession1Test2 = new RecurringSession(new SessionDate("2021-01-01", "10:00"),
                DURATION, SUBJECT, FEE, new Interval("14"), new SessionDate("2021-01-29", "10:00"));
        RecurringSession recurringSession2Test2 = new RecurringSession(new SessionDate("2021-01-11", "10:00"),
                DURATION, SUBJECT, FEE, new Interval("3"), new SessionDate("2021-02-01", "10:00"));
        assertTrue(recurringSession1Test2.isOverlapping(recurringSession2Test2));

        // Days Between: 10, Interval1: 14, Interval2: 3, EndDate: 28, EndDate2: 25
        RecurringSession recurringSession1Test3 = new RecurringSession(new SessionDate("2021-01-01", "12:00"),
                DURATION, SUBJECT, FEE, new Interval("14"), new SessionDate("2021-01-29", "12:00"));
        RecurringSession recurringSession2Test3 = new RecurringSession(new SessionDate("2021-01-11", "12:00"),
                DURATION, SUBJECT, FEE, new Interval("3"), new SessionDate("2021-01-26", "12:00"));
        assertFalse(recurringSession1Test3.isOverlapping(recurringSession2Test3));

        // Days Between: 1, Interval1: 2, Interval2: 1, EndDate1: 14, EndDate2: 15
        RecurringSession recurringSession1Test4 = new RecurringSession(new SessionDate("2021-01-01", "12:00"),
                DURATION, SUBJECT, FEE, new Interval("2"), new SessionDate("2021-01-15", "12:00"));
        RecurringSession recurringSession2Test4 = new RecurringSession(new SessionDate("2021-01-02", "12:00"),
                DURATION, SUBJECT, FEE, new Interval("1"), new SessionDate("2021-01-14", "12:00"));
        assertTrue(recurringSession1Test4.isOverlapping(recurringSession2Test4));

    }

    @Test
    void lastSessionOnOrBeforeTest() {
        RecurringSession recurringSession = new RecurringSession(new SessionDate("2020-12-11", "10:00"),
                DURATION, SUBJECT, FEE, INTERVAL, new SessionDate("2021-01-15", "10:00"));
        Session session = new Session(new SessionDate("2021-01-01", "10:00"), DURATION, SUBJECT, FEE);
        assertEquals(session, recurringSession.lastSessionOnOrBefore(SESSION_DATE));

        RecurringSession startAfter = new RecurringSession(new SessionDate("2021-12-11", "10:00"),
                DURATION, SUBJECT, FEE, INTERVAL, new SessionDate("2022-01-15", "10:00"));
        assertThrows(IllegalArgumentException.class, () -> startAfter.lastSessionOnOrBefore(SESSION_DATE));
    }

    @Test
    void removeSessionInRecurringSession_validRemoveSessionAtSessionStart() {
        List<Session> sessionList = new ArrayList<>();
        List<Session> expectedSessionList = new ArrayList<>();
        RecurringSession recurringSession = new RecurringSessionBuilder()
                .withSessionDate(VALID_RECURRING_START_DATE_BOB, VALID_TIME)
                .withLastSessionDate(VALID_RECURRING_END_DATE_BOB, VALID_TIME).build();
        RecurringSession expectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_BOB, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_BOB, VALID_TIME).build();
        expectedRecurringSession = expectedRecurringSession.withStartDate(expectedRecurringSession
                .getSessionDate().addDays(7));
        sessionList.add(recurringSession);
        expectedSessionList.add(expectedRecurringSession);
        SessionDate sessionDateToDelete = recurringSession.getSessionDate();
        recurringSession.removeSessionInRecurringSession(INDEX_FIRST_SESSION, sessionDateToDelete, sessionList);

        assertEquals(sessionList, expectedSessionList);
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveSessionAtSessionEnd() {
        List<Session> sessionList = new ArrayList<>();
        List<Session> expectedSessionList = new ArrayList<>();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        RecurringSession expectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        expectedRecurringSession = expectedRecurringSession.withLastSessionDate(expectedRecurringSession
                .getLastSessionDate().minusDays(7));
        sessionList.add(recurringSession);
        expectedSessionList.add(expectedRecurringSession);
        SessionDate sessionDateToDelete = recurringSession.getLastSessionDate();
        recurringSession.removeSessionInRecurringSession(INDEX_FIRST_SESSION, sessionDateToDelete, sessionList);

        assertEquals(sessionList, expectedSessionList);
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveMiddleSession() {
        List<Session> sessionList = new ArrayList<>();
        List<Session> expectedSessionList = new ArrayList<>();
        SessionDate sessionDateToDelete = new SessionDate("2021-04-22", VALID_TIME);
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        RecurringSession firstHalfExpectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(sessionDateToDelete.minusDays(7).getDate().toString(), VALID_TIME).build();
        RecurringSession secondHalfExpectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(sessionDateToDelete.addDays(7).getDate().toString(), VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        sessionList.add(recurringSession);
        expectedSessionList.add(firstHalfExpectedRecurringSession);
        expectedSessionList.add(secondHalfExpectedRecurringSession);
        recurringSession.removeSessionInRecurringSession(INDEX_FIRST_SESSION, sessionDateToDelete, sessionList);

        assertEquals(sessionList, expectedSessionList);
    }

    @Test
    public void removeSessionInRecurringSession_validRemoveSingleDayRecurringSession() {
        List<Session> sessionList = new ArrayList<>();
        List<Session> expectedSessionList = new ArrayList<>();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME).build();
        sessionList.add(recurringSession);
        SessionDate sessionDateToDelete = recurringSession.getSessionDate();
        recurringSession.removeSessionInRecurringSession(INDEX_FIRST_SESSION, sessionDateToDelete , sessionList);

        assertEquals(sessionList, expectedSessionList);
    }

    @Test
    public void removeSessionInRecurringSession_validFirstHalfNonRecurring() {
        List<Session> sessionList = new ArrayList<>();
        List<Session> expectedSessionList = new ArrayList<>();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        Session firstHalfExpectedSession =
                new SessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME).build();
        RecurringSession secondHalfExpectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(recurringSession.getSessionDate().addDays(14).getDate().toString(), VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();

        SessionDate sessionDateToDelete = recurringSession.getSessionDate().addDays(7);
        sessionList.add(recurringSession);
        expectedSessionList.add(firstHalfExpectedSession);
        expectedSessionList.add(secondHalfExpectedRecurringSession);
        recurringSession.removeSessionInRecurringSession(INDEX_FIRST_SESSION, sessionDateToDelete, sessionList);

        assertEquals(sessionList, expectedSessionList);
    }

    @Test
    public void removeSessionInRecurringSession_validSecondHalfNonRecurring() {
        List<Session> sessionList = new ArrayList<>();
        List<Session> expectedSessionList = new ArrayList<>();
        RecurringSession recurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();
        RecurringSession firstHalfExpectedRecurringSession =
                new RecurringSessionBuilder()
                        .withSessionDate(VALID_RECURRING_START_DATE_AMY, VALID_TIME)
                        .withLastSessionDate(recurringSession.getLastSessionDate().minusDays(14).getDate().toString(),
                                VALID_TIME).build();
        Session secondHalfExpectedSession =
                new SessionBuilder()
                        .withSessionDate(VALID_RECURRING_END_DATE_AMY, VALID_TIME).build();

        SessionDate sessionDateToDelete = recurringSession.getLastSessionDate().minusDays(7);
        sessionList.add(recurringSession);
        expectedSessionList.add(firstHalfExpectedRecurringSession);
        expectedSessionList.add(secondHalfExpectedSession);
        recurringSession.removeSessionInRecurringSession(INDEX_FIRST_SESSION, sessionDateToDelete, sessionList);

        assertEquals(sessionList, expectedSessionList);
    }


    @Test
    void numOfSessionBetweenTest() {
        SessionDate firstSessionDate = new SessionDate("2021-02-28", "10:00");
        SessionDate lastSessionDate = new SessionDate("2021-04-11", "10:00");
        SessionDate firstOfMarch = new SessionDate("2021-03-01", "00:00");
        SessionDate lastOfMarch = new SessionDate("2021-03-31", "23:59");


        Interval twoWeekInterval = new Interval("14");
        RecurringSession recurringSession1 = new RecurringSession(
                firstSessionDate, new Duration("100"), new Subject("Math"),
                new Fee("40"), twoWeekInterval, lastSessionDate);
        int ans1 = recurringSession1.numOfSessionBetween(firstOfMarch, lastOfMarch);
        assertEquals(2, ans1);

        Interval oneDayInterval = new Interval("1");
        RecurringSession recurringSession2 = new RecurringSession(
                firstSessionDate, new Duration("100"), new Subject("Math"),
                new Fee("40"), oneDayInterval, lastSessionDate);
        int ans2 = recurringSession2.numOfSessionBetween(firstOfMarch, lastOfMarch);
        assertEquals(31, ans2);

        RecurringSession recurringSession = new RecurringSession(SESSION_DATE, DURATION, SUBJECT, FEE, INTERVAL,
                SESSION_DATE.addDays(INTERVAL.getValue() * 3));
        //same dates
        assertEquals(1, recurringSession.numOfSessionBetween(SESSION_DATE, SESSION_DATE));
        //inclusive end before start
        assertEquals(0, recurringSession.numOfSessionBetween(SESSION_DATE, SESSION_DATE.minusDays(1)));
    }

    @Test
    void equalsTest() {
        RecurringSession recurringSession = new RecurringSessionBuilder().build();
        RecurringSessionBuilder copiedUnBuilt = new RecurringSessionBuilder()
                .withSessionDate(DEFAULT_DATE, DEFAULT_TIME)
                .withDuration(DEFAULT_DURATION)
                .withSubject(DEFAULT_SUBJECT)
                .withFee(DEFAULT_FEE)
                .withInterval(RecurringSessionBuilder.DEFAULT_INTERVAL)
                .withLastSessionDate(RecurringSessionBuilder.DEFAULT_END_DATE, DEFAULT_TIME);

        assertTrue(recurringSession.equals(copiedUnBuilt.build()));
        assertFalse(recurringSession.equals(new SessionBuilder().build()));

        RecurringSession editedSubject = copiedUnBuilt.withSubject(SUBJECT.toString()).build();
        assertFalse(recurringSession.equals(editedSubject));

        RecurringSession editedLastSession = copiedUnBuilt.withLastSessionDate("2021-01-08", "00:00")
                .build();
        assertFalse(recurringSession.equals(editedLastSession));

        RecurringSession editedInterval = copiedUnBuilt.withInterval("1").build();
        assertFalse(recurringSession.equals(editedInterval));
    }

}
