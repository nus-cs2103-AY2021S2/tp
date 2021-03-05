package seedu.dictionote.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.dictionote.model.AddressBook;
import seedu.dictionote.model.NoteBook;
import seedu.dictionote.model.ReadOnlyAddressBook;
import seedu.dictionote.model.ReadOnlyNoteBook;
import seedu.dictionote.model.note.Note;
import seedu.dictionote.model.person.Address;
import seedu.dictionote.model.person.Email;
import seedu.dictionote.model.person.Name;
import seedu.dictionote.model.person.Person;
import seedu.dictionote.model.person.Phone;
import seedu.dictionote.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
                new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("313 Pasir Panjang"),
                        getTagSet("friends")),
                new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Computing Drive"),
                        getTagSet("colleagues", "friends")),
                new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Chinatown"),
                        getTagSet("neighbours")),
                new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Eusoff Hall"),
                        getTagSet("family")),
                new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("28 Jalan Lempeng"),
                        getTagSet("classmates")),
                new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new Address("PGPH"),
                        getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
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

    public static Note[] getSampleNotes() {
        return new Note[] {
                new Note("CS2103T exam is coming."),
                new Note("My friend is an idiot."),
                new Note("My Teacher Best Teacher")
        };
    }

    public static ReadOnlyNoteBook getSampleNoteBook() {
        NoteBook sampleNb = new NoteBook();
        for (Note sampleNote : getSampleNotes()) {
            sampleNb.addNote(sampleNote);
        }
        return sampleNb;
    }
}
