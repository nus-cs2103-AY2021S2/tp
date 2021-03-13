package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final mod[] moduleInfo = readModuleInfo();
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the plan list with {@code plans}.
     * {@code plans} must not contain duplicate plans.
     */
    public void setPersons(List<Plan> plans) {
        this.persons.setPersons(plans);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// plan-level operations

    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    public boolean hasPerson(Plan plan) {
        requireNonNull(plan);
        return persons.contains(plan);
    }

    /**
     * Adds a plan to the address book.
     * The plan must not already exist in the address book.
     */
    public void addPerson(Plan p) {
        persons.add(p);
    }

    /**
     * Replaces the given plan {@code target} in the list with {@code editedPlan}.
     * {@code target} must exist in the address book.
     * The plan identity of {@code editedPlan} must not be the same as another existing plan in the address book.
     */
    public void setPerson(Plan target, Plan editedPlan) {
        requireNonNull(editedPlan);

        persons.setPerson(target, editedPlan);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Plan key) {
        persons.remove(key);
    }

    //// util methods

    private mod[] readModuleInfo() {
        mod[] md = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path addressBookFilePath = Paths.get("data", "moduleinfo.json");
            String json = Files.readAllLines(addressBookFilePath).get(0);
            Optional<mod[]> opt = JsonUtil.readJsonFile(addressBookFilePath, mod[].class);
            md = opt.get();
        } catch (Exception e) {
            System.out.println("There is an error in reading moduleinfo.json file please check");
            System.out.println(e.getMessage());
        }
        return md;
    }

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Plan> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && persons.equals(((AddressBook) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}

// class for json file to populate
class mod {
    public String module_code;
    public String moduleTitle;
    public String num_mc;
    public String avail_sems;
    public String[] prereqs;
    public String[] preclusions;
}