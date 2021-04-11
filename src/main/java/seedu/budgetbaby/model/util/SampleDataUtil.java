package seedu.budgetbaby.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.budgetbaby.abmodel.AddressBook;
import seedu.budgetbaby.abmodel.ReadOnlyAddressBook;
import seedu.budgetbaby.abmodel.person.Address;
import seedu.budgetbaby.abmodel.person.Email;
import seedu.budgetbaby.abmodel.person.Name;
import seedu.budgetbaby.abmodel.person.Person;
import seedu.budgetbaby.abmodel.person.Phone;
import seedu.budgetbaby.model.BudgetTracker;
import seedu.budgetbaby.model.ReadOnlyBudgetTracker;
import seedu.budgetbaby.model.record.Amount;
import seedu.budgetbaby.model.record.Category;
import seedu.budgetbaby.model.record.Description;
import seedu.budgetbaby.model.record.FinancialRecord;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                getCategorySet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getCategorySet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getCategorySet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getCategorySet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getCategorySet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getCategorySet("colleagues"))
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
    public static Set<Category> getCategorySet(String... strings) {
        return Arrays.stream(strings)
            .map(Category::new)
            .collect(Collectors.toSet());
    }

    public static FinancialRecord[] getSampleFinancialRecords() {
        return new FinancialRecord[]{
            new FinancialRecord(new Description("[Sample Financial Record] Lunch"),
                new Amount("10"), getCategorySet("Food")),
            new FinancialRecord(new Description("[Sample Financial Record] Dinner"),
                new Amount("10.50"), getCategorySet("Food")),
        };
    }

    public static ReadOnlyBudgetTracker getSampleBudgetTracker() {
        BudgetTracker sample = new BudgetTracker();
        for (FinancialRecord sampleRecord : getSampleFinancialRecords()) {
            sample.addFinancialRecord(sampleRecord);
        }
        return sample;
    }
}
