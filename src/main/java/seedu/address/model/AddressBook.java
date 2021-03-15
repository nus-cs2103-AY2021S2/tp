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
import seedu.address.logic.parser.Parser;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.UniquePersonList;
import seedu.address.storage.JsonModule;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final JsonModule[] moduleInfo = readModuleInfo();
    private Integer currentSemesterNumber;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        currentSemesterNumber = null;
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
        currentSemesterNumber = newData.getCurrentSemesterNumber();
    }

    //// plan-level operations

    /**
     * Returns true if a plan with the same identity as {@code plan} exists in the address book.
     */
    public boolean hasPlan(Plan plan) {
        requireNonNull(plan);
        return persons.contains(plan);
    }

    /**
     * Adds a plan to the address book.
     * The plan must not already exist in the address book.
     */
    public void addPlan(Plan p) {
        persons.add(p);
    }

    /**
     * Replaces the given plan {@code target} in the list with {@code editedPlan}.
     * {@code target} must exist in the address book.
     * The plan identity of {@code editedPlan} must not be the same as another existing plan in the address book.
     */
    public void setPlan(Plan target, Plan editedPlan) {
        requireNonNull(editedPlan);

        persons.setPerson(target, editedPlan);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePlan(Plan key) {
        persons.remove(key);
    }

    /**
     * Replaces the current semester number {@code currentSemesterNumber} with
     * an updated {@code currentSemesterNumber}.
     * {@code currentSemesterNumber} must exist in the address book.
     */
    public void setCurrentSemesterNumber(Integer currentSemesterNumber) {
        this.currentSemesterNumber = currentSemesterNumber;
    }

    /**
     * Returns the currentSemesterNumber.
     * @return current semester number
     */
    public Integer getCurrentSemesterNumber() {
        return currentSemesterNumber;
    }

    //// util methods

    /**
     * reads the moduleinfo.json located in data folder and creates an array from the info
     * @return array of module information
     */
    private JsonModule[] readModuleInfo() {
        JsonModule[] md = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path addressBookFilePath = Paths.get("data", "moduleinfo.json");
            String json = Files.readAllLines(addressBookFilePath).get(0);
            Optional<JsonModule[]> opt = JsonUtil.readJsonFile(addressBookFilePath, JsonModule[].class);
            md = opt.get();
        } catch (Exception e) {
            System.out.println("There is an error in reading moduleinfo.json file please check");
            System.out.println(e.getMessage());
        }
        return md;
    }

    public JsonModule[] getModuleInfo() {
        return moduleInfo;
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
        if (other == this) {
            return true;
        } else if (other instanceof AddressBook) {
            AddressBook o = (AddressBook) other;
            return persons.equals(o.persons);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
