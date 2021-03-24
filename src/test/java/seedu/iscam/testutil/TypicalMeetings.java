package seedu.iscam.testutil;

import seedu.iscam.model.MeetingBook;
import seedu.iscam.model.meeting.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypicalMeetings {

    public static final Meeting ALICE_1 = new MeetingBuilder()
            .withName("Alice Pauline").withDateTime("24-03-2025 15:30").withLocation("Starbucks, Jurong West")
            .withDescription("Discuss lifetime policy.").withTags("friends").withIsDone(false)
            .build();
    public static final Meeting ALICE_2 = new MeetingBuilder()
            .withName("Alice Pauline").withDateTime("29-03-2025 20:00").withLocation("Starbucks, Jurong West")
            .withDescription("Finalize lifetime policy.").withTags("friends").withIsDone(false)
            .build();
    public static final Meeting BENSON_1 = new MeetingBuilder()
            .withName("Benson Meier").withDateTime("30-03-2025 15:30").withLocation("Kopitiam, Clementi Central")
            .withDescription("Brief introduction of company's policy.").withTags("friends").withIsDone(false)
            .build();
    public static final Meeting CARL_1 = new MeetingBuilder()
            .withName("Carl Kurz").withDateTime("21-01-2021 10:00").withLocation("Dio Cafe, Central")
            .withDescription("Explain workflow of policyholder and underwrite.").withIsDone(true)
            .build();
    public static final Meeting DANIEL_1 = new MeetingBuilder()
            .withName("Daniel Meier").withDateTime("09-05-2025 14:15").withLocation("Macdonald, Bedok North")
            .withDescription("Recommend policies for his family.").withIsDone(false)
            .build();
    public static final Meeting ELLE_1 = new MeetingBuilder()
            .withName("Elle Meyer").withDateTime("10-05-2025 17:30").withLocation("Macdonald, Serengoon")
            .withDescription("Extension of policy.").withIsDone(false)
            .build();
    public static final Meeting FIONA_1 = new MeetingBuilder()
            .withName("Fiona Kunz").withDateTime("03-10-2025 16:30").withLocation("Doot Cafe, One North")
            .withDescription("Discuss additional policies.").withIsDone(false)
            .build();
    public static final Meeting FIONA_2 = new MeetingBuilder()
            .withName("Fiona Kunz").withDateTime("05-01-2021 16:30").withLocation("Doot Cafe, One North")
            .withDescription("Introducing policies.").withIsDone(true)
            .build();

    public static MeetingBook getTypicalMeetingBook() {
        MeetingBook mb = new MeetingBook();
        for (Meeting meeting : getTypicalMeetings()) {
            mb.addMeeting(meeting);
        }
        return mb;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(ALICE_1, ALICE_2, BENSON_1, CARL_1, DANIEL_1, ELLE_1, FIONA_1, FIONA_2));
    }
}
