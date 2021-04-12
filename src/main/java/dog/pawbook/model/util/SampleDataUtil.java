package dog.pawbook.model.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import dog.pawbook.model.Database;
import dog.pawbook.model.ReadOnlyDatabase;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.dog.Breed;
import dog.pawbook.model.managedentity.dog.DateOfBirth;
import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.dog.Sex;
import dog.pawbook.model.managedentity.owner.Address;
import dog.pawbook.model.managedentity.owner.Email;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.owner.Phone;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;

/**
 * Contains utility methods for populating {@code Database} with sample data.
 */
public class SampleDataUtil {
    public static Entity[] getSampleEntities() {
        return new Entity[] {
            new Owner(new Name("Alice Pauline"), new Phone("94351253"), new Email("alice@example.com"),
                new Address("123, Jurong West Ave 6, #08-111"),
                getTagSet("friends"), Collections.singleton(2)),
            new Dog(new Name("Apple"), new Breed("Golden Retriever"), new DateOfBirth("11-02-2020"),
                    new Sex("female"), 1, getTagSet("friendly")),
            new Owner(new Name("Benson Meier"), new Phone("98765432"), new Email("johnd@example.com"),
                new Address("311, Clementi Ave 2, #02-25"),
                getTagSet("owesMoney", "friends"), Collections.singleton(4)),
            new Dog(new Name("Bubbles"), new Breed("Bulldog"), new DateOfBirth("01-01-2021"),
                    new Sex("female"), 3, getTagSet("cheerful")),
            new Program(new Name("Active Listening"), getSessionSet("12-12-2021 18:00"),
                    getTagSet("Puppies"), Collections.singleton(2)),
            new Program(new Name("Behaving"), getSessionSet("11-11-2021 20:00"),
                getTagSet("Puppies"), Collections.singleton(4)),
        };
    }

    public static ReadOnlyDatabase getSampleDatabase() {
        Database sampleAb = new Database();
        for (Entity sampleEntity : getSampleEntities()) {
            sampleAb.addEntity(sampleEntity);
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

    /**
     * Returns a ID set containing the list of integers given.
     */
    public static Set<Integer> getIdSet(Integer... ids) {
        return Arrays.stream(ids).collect(Collectors.toSet());
    }

    /**
     * Returns a session set containing the list of strings given.
     */
    public static Set<Session> getSessionSet(String... strings) {
        return Arrays.stream(strings)
                .map(Session::new)
                .collect(Collectors.toSet());
    }
}
