package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyRemindMe;
import seedu.address.model.RemindMe;
import seedu.address.model.module.AssignmentList;
import seedu.address.model.module.ExamList;
import seedu.address.model.module.Module;
import seedu.address.model.module.Title;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"),
                getTagSet("friends")),
            new Person(new Name("Bernice Yu"),
                getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"),
                getTagSet("neighbours")),
            new Person(new Name("David Li"),
                getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"),
                getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"),
                getTagSet("colleagues"))
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Title("cs2103T"), new AssignmentList(),
                        new ExamList()),
            new Module(new Title("cs2103"), new AssignmentList(),
                        new ExamList())
        };
    }


    public static ReadOnlyRemindMe getSampleRemindMe() {
        RemindMe sampleRm = new RemindMe();
        for (Module sampleMod : getSampleModules()) {
            sampleRm.addModule(sampleMod);
        }

        return sampleRm;
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
