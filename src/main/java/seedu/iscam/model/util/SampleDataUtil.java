package seedu.iscam.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.iscam.model.client.Client;
import seedu.iscam.model.client.Email;
import seedu.iscam.model.client.Image;
import seedu.iscam.model.client.InsurancePlan;
import seedu.iscam.model.client.Phone;
import seedu.iscam.model.commons.Location;
import seedu.iscam.model.commons.Name;
import seedu.iscam.model.commons.Tag;
import seedu.iscam.model.meeting.DateTime;
import seedu.iscam.model.meeting.Description;
import seedu.iscam.model.meeting.Meeting;
import seedu.iscam.model.util.clientbook.ClientBook;
import seedu.iscam.model.util.clientbook.ReadOnlyClientBook;
import seedu.iscam.model.util.meetingbook.MeetingBook;
import seedu.iscam.model.util.meetingbook.ReadOnlyMeetingBook;

/**
 * Contains utility methods for populating {@code ClientBook} with sample data.
 */
public class SampleDataUtil {
    public static Client[] getSampleClients() {
        return new Client[]{
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Location("Blk 30 Geylang Street 29, #06-40"), getPlanSet("Medisave Shield"),
                    getTagSet("friends"), new Image("default.png")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Location("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getPlanSet("Aviva Lifecare"),
                    getTagSet("colleagues", "friends"), new Image("default.png")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Location("Blk 11 Ang Mo Kio Street 74, #11-04"), getPlanSet("Prudential MaxiLife"),
                    getTagSet("neighbours"), new Image("default.png")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Location("Blk 436 Serangoon Gardens Street 26, #16-43"), getPlanSet("LionShield"),
                    getTagSet("family"), new Image("default.png")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Location("Blk 47 Tampines Street 20, #17-35"), getPlanSet("Medicare", "NTUC Life Max"),
                    getTagSet("classmates"), new Image("default.png")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Location("Blk 45 Aljunied Street 85, #11-31"), getPlanSet("Medisave Shield"),
                    getTagSet("colleagues"), new Image("default.png"))
        };
    }

    public static Meeting[] getSampleMeetings() {
        return new Meeting[]{
            new Meeting(new Name("Client A"), new DateTime("12-10-2021 10:00"),
                    new Location("Starbucks, Paya Lebar Square"), new Description("Sell insurance plan"),
                    getTagSet("Urgent")),
            new Meeting(new Name("Client B"), new DateTime("13-10-2021 10:00"),
                    new Location("Coffee Bean, NUH"), new Description("Sell insurance plan"),
                    getTagSet("Urgent")),
            new Meeting(new Name("Client C"), new DateTime("14-10-2021 10:00"),
                    new Location("Toast Box, NEX"), new Description("Sell insurance plan"),
                    getTagSet("Urgent"))
        };
    }

    public static ReadOnlyClientBook getSampleClientBook() {
        ClientBook sampleCb = new ClientBook();
        for (Client sampleClient : getSampleClients()) {
            sampleCb.addClient(sampleClient);
        }
        return sampleCb;
    }

    public static ReadOnlyMeetingBook getSampleMeetingBook() {
        MeetingBook sampleMb = new MeetingBook();
        for (Meeting sampleMeeting : getSampleMeetings()) {
            sampleMb.addMeeting(sampleMeeting);
        }
        return sampleMb;
    }

    /**
     * Returns a plan set containing the list of strings given.
     */
    public static Set<InsurancePlan> getPlanSet(String... strings) {
        return Arrays.stream(strings)
                .map(InsurancePlan::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
