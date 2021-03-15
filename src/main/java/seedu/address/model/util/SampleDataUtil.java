package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ModulePlanner;
import seedu.address.model.ReadOnlyModulePlanner;
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
            new Person(new Name("Alice Pauline"),
                getTagSet("friends")),
            new Person(new Name("Benson Meier"),
                getTagSet("owesMoney", "friends")),
            new Person(new Name("Carl Kurz"),
                getTagSet()),
            new Person(new Name("Daniel Meier"),
                getTagSet("friends")),
            new Person(new Name("Elle Meyer"),
                getTagSet()),
            new Person(new Name("Fiona Kunz"),
                getTagSet()),
            new Person(new Name("George Best"),
                getTagSet())
        };
    }

    public static Module[] getSampleModules() {
        return new Module[] {
            new Module(new Title("CS2103"), new AssignmentList(),
                        new ExamList())
        };
    }


    public static ReadOnlyModulePlanner getSampleRemindMe() {
        ModulePlanner sampleRm = new ModulePlanner();
        for (Module sampleMod : getSampleModules()) {
            sampleRm.addModule(sampleMod);
        }

        for (Person samplePerson : getSamplePersons()) {
            sampleRm.addPerson(samplePerson);
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
