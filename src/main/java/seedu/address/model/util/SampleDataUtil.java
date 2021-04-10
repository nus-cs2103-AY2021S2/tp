package seedu.address.model.util;

import static seedu.address.model.resident.Room.UNALLOCATED_REGEX;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.issue.Category;
import seedu.address.model.issue.Description;
import seedu.address.model.issue.Issue;
import seedu.address.model.issue.IssueStatus;
import seedu.address.model.issue.Status;
import seedu.address.model.issue.Timestamp;
import seedu.address.model.resident.Email;
import seedu.address.model.resident.Name;
import seedu.address.model.resident.Phone;
import seedu.address.model.resident.Resident;
import seedu.address.model.resident.Room;
import seedu.address.model.resident.Year;
import seedu.address.model.residentroom.ResidentRoom;
import seedu.address.model.room.IsOccupied;
import seedu.address.model.room.RoomNumber;
import seedu.address.model.room.RoomType;
import seedu.address.model.room.RoomTypeOptions;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Resident[] getSampleResidents() {
        return new Resident[] {
            new Resident(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                    new Year("1"),
                    new Room(UNALLOCATED_REGEX)),
            new Resident(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                    new Year("2"),
                    new Room(UNALLOCATED_REGEX)),
            new Resident(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                    new Year("3"),
                    new Room("05-672")),
            new Resident(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                    new Year("4"),
                    new Room(UNALLOCATED_REGEX)),
            new Resident(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                    new Year("4"),
                    new Room(UNALLOCATED_REGEX)),
            new Resident(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                    new Year("4"),
                    new Room("01-234"))
        };
    }

    public static seedu.address.model.room.Room[] getSampleRooms() {
        return new seedu.address.model.room.Room[] {
            new seedu.address.model.room.Room(new RoomNumber("01-234"),
                    new RoomType(RoomTypeOptions.CORRIDOR_AC.name()),
                    new IsOccupied(IsOccupied.OCCUPIED),
                    new HashSet<>(Arrays.asList(new Tag("SHN")))),
            new seedu.address.model.room.Room(new RoomNumber("03-325"),
                    new RoomType(RoomTypeOptions.CORRIDOR_NON_AC.name()),
                    new IsOccupied(IsOccupied.UNOCCUPIED),
                    new HashSet<>()),
            new seedu.address.model.room.Room(new RoomNumber("08-514"),
                    new RoomType(RoomTypeOptions.SUITE_AC.name()),
                    new IsOccupied(IsOccupied.UNOCCUPIED),
                    new HashSet<>()),
            new seedu.address.model.room.Room(new RoomNumber("09-513"),
                    new RoomType(RoomTypeOptions.SUITE_NON_AC.name()),
                    new IsOccupied(IsOccupied.UNOCCUPIED),
                    new HashSet<>(Arrays.asList(new Tag("SHN")))),
            new seedu.address.model.room.Room(new RoomNumber("05-672"),
                    new RoomType(RoomTypeOptions.SUITE_NON_AC.name()),
                    new IsOccupied(IsOccupied.OCCUPIED),
                    new HashSet<>(Arrays.asList(new Tag("SHN")))),
            new seedu.address.model.room.Room(new RoomNumber("08-912"),
                    new RoomType(RoomTypeOptions.CORRIDOR_AC.name()),
                    new IsOccupied(IsOccupied.UNOCCUPIED),
                    new HashSet<>())
        };
    }

    public static Issue[] getSampleIssues() {
        return new Issue[] {
            new Issue(new seedu.address.model.issue.RoomNumber("01-234"), new Description("Broken light"),
                    new Timestamp("2020/01/01 12:00PM"), new Status(IssueStatus.Closed),
                    new Category("Fixtures"), new HashSet<>()),
            new Issue(new seedu.address.model.issue.RoomNumber("01-234"), new Description("Cupboard door missing"),
                    new Timestamp("2021/01/31 04:12PM"), new Status(IssueStatus.Pending),
                    new Category("Furniture"), new HashSet<>(Arrays.asList(new Tag("MEDIUM")))),
            new Issue(new seedu.address.model.issue.RoomNumber("03-325"), new Description("Leaking ceiling"),
                    new Timestamp("2020/08/10 03:21PM"), new Status(IssueStatus.Closed),
                    new Category("Building Works"), new HashSet<>(Arrays.asList(new Tag("HIGH")))),
            new Issue(new seedu.address.model.issue.RoomNumber("05-672"), new Description("Lumpy mattress"),
                    new Timestamp("2020/11/12 12:11AM"), new Status(IssueStatus.Pending),
                    new Category("Furniture"), new HashSet<>(Arrays.asList(new Tag("LOW")))),
        };
    }


    public static ResidentRoom[] getSampleResidentRooms() {
        return new ResidentRoom[] {
            new ResidentRoom(new seedu.address.model.resident.Name("Charlotte Oliveiro"), new RoomNumber("05-672")),
            new ResidentRoom(new seedu.address.model.resident.Name("Roy Balakrishnan"), new RoomNumber("01-234"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Resident sampleResident : getSampleResidents()) {
            sampleAb.addResident(sampleResident);
        }
        for (seedu.address.model.room.Room sampleRoom : getSampleRooms()) {
            sampleAb.addRoom(sampleRoom);
        }
        for (Issue sampleIssue : getSampleIssues()) {
            sampleAb.addIssue(sampleIssue);
        }
        for (ResidentRoom sampleResidentRoom : getSampleResidentRooms()) {
            sampleAb.addResidentRoom(sampleResidentRoom);
        }
        return sampleAb;
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
