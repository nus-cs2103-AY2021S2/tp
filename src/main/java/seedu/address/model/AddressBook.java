package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.plan.Plan;
import seedu.address.model.plan.UniquePersonList;
import seedu.address.storage.JsonModule;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private JsonModule[] moduleInfo;
    private Integer currentSemesterNumber;
    private ObservableList<JsonModule> foundModule;
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
        try {
            moduleInfo = readModuleInfo();
        } catch (IOException e) {
            System.out.println("There is an error in reading moduleinfo.json file please check");
            System.out.println(e.getMessage());
        }
    }

    public AddressBook() {
        foundModule = FXCollections.observableArrayList();
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
        foundModule = FXCollections.observableArrayList();
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
    private JsonModule[] readModuleInfo() throws IOException {
        JsonModule[] moduleInfo = null;
        Path filePath = Paths.get("data", "moduleinfo.json");
        try {
            Optional<JsonModule[]> opt = JsonUtil.readJsonFile(filePath, JsonModule[].class);
            moduleInfo = opt.get();
        } catch (Exception e) {
            System.out.println("There is an error in reading moduleinfo.json file please check");
            System.out.println(e.getMessage());
            FileUtil.createIfMissing(filePath);
            FileUtil.writeToFile(filePath, "[\n"
                    + "  {\n" + "    \"moduleCode\": \"CS2309\",\n"
                    + "    \"moduleTitle\": \"Research Methodology\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"B\",\n"
                    + "    \"prereqs\": [\"CS2010\", \"CS2030|CS2113T\", \"CS2040\", \"MA1101|CS1231\"],\n"
                    + "    \"preclusions\": [\"CS2305S\"]\n"
                    + "  }, {\n" + "    \"moduleCode\": \"CS1101S\",\n"
                    + "    \"moduleTitle\": \"Programming Methodology\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"B\",\n"
                    + "    \"prereqs\": [],\n"
                    + "    \"preclusions\": [\"CS1010E\", \"CS1010J\","
                    + " \"CS1010S\", \"CS1010X\", \"CS1010XCP\", \"CS1101\"]\n"
                    + "  }, {\n" + "    \"moduleCode\": \"CS2030S\",\n"
                    + "    \"moduleTitle\": \"Programming Methodology II\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"B\",\n"
                    + "    \"prereqs\": [\"CS1010\"],\n"
                    + "    \"preclusions\": [\"CS2030\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"ST2334\",\n"
                    + "    \"moduleTitle\": \"Probability and Statistics\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"MA1102R\", \"MA1312\", \"MA1505\", \"MA1507\", "
                    + "\"MA1511\", \"MA1521\", \"MA2002\"],\n"
                    + "    \"preclusions\": [\"ST1131\", \"ST1131A\", \"ST1232\", \"ST2131\", "
                    + "\"MA2216\", \"CE2407\", \"EC2231\", \"EC2303\", \"PR2103\", \"DSC2008\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2103\",\n"
                    + "    \"moduleTitle\": \"Software Engineering\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"1020\", \"2020\", \"2030 | 2040\"],\n"
                    + "    \"preclusions\": [\"CS2103T\", \"CS2113\", \"CS2113T\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS3230\",\n"
                    + "    \"moduleTitle\": \"\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"1020\", \"2020\", \"2030 | 2040\", \"MA1100 | CS1231\"],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2105\",\n"
                    + "    \"moduleTitle\": \"Introduction to Computer Networks\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"1020\", \"2020\", \"2040\"],\n"
                    + "    \"preclusions\": [\"IT2001\", \"EE3204/E\", \"EE4204/E\", \"EE4210/E\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS1231\",\n"
                    + "    \"moduleTitle\": \"Discrete Structures\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"A-level Mathematics\", \"H2 Mathematics\", \"MA1301\", "
                    + "\"MA1301FC\", \"MA1301X\"],\n"
                    + "    \"preclusions\": [\"MA1100\", \"CS1231S\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2100\",\n"
                    + "    \"moduleTitle\": \"Computer Organisation\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"CS1010\"],\n"
                    + "    \"preclusions\": [\"CS1104\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2102\",\n"
                    + "    \"moduleTitle\": \"Database Systems\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"1020\", \"2020\", \"2030 | 2040\", \"MA1100 | CS1231\"],\n"
                    + "    \"preclusions\": [\"CS2102S\", \"IT2002\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2104\",\n"
                    + "    \"moduleTitle\": \"Programming Language Concepts\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"CS1020\", \"CS2020\", \"CS2030\", \"CS2113/T\" ],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2106\",\n"
                    + "    \"moduleTitle\": \"Introduction to Operating Systems\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"CS2100\", \"EE2007\", \"EE2024\", \"EE2028\"],\n"
                    + "    \"preclusions\": [\"CG2271\", \"EE4214\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2107\",\n"
                    + "    \"moduleTitle\": \"Introduction to Information Security\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n" + "    \"prereqs\": [\"CS1010\"],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2108\",\n"
                    + "    \"moduleTitle\": \"Introduction to Media Computing\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"1020\", \"2020\", \"2040\"],\n"
                    + "    \"preclusions\": [\"CS3246\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS2220\",\n"
                    + "    \"moduleTitle\": \"Introduction to Computational Biology\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"1020\", \"2020\", \"2040\"],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"CS5260\",\n"
                    + "    \"moduleTitle\": \"Neural Networks and Deep Learning II\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"CS5242\"],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"ESE3001\",\n"
                    + "    \"moduleTitle\": \"Water Quality Engineering\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"\"],\n"
                    + "    \"preclusions\": [\"ESE2401\", \"ESE3401\", \"TCE3001\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"FE5226\",\n"
                    + "    \"moduleTitle\": \"C++ in Financial Engineering\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"\"],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"FIN2004\",\n"
                    + "    \"moduleTitle\": \"Finance\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\" BK1003\",\"BZ1002\",\"BH1002\",\"FNA1002/ACC1002\","
                    + "\"FNA1002X/ACC1002X\",\"FNA1002E\",\"BH1002E\",\"EC3212\",\"EG1422\"],\n"
                    + "    \"preclusions\": [\"CS2251\",\"EC3209\",\"EC3333\",\"BK2004\",\"BZ2004\","
                    + "\"BH2004\",\"FNA2004\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"EU4401\",\n"
                    + "    \"moduleTitle\": \"Honours Thesis\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A\",\n"
                    + "    \"prereqs\": [\"EU4660\"],\n"
                    + "    \"preclusions\": [\"Completed 110 MCs including 44 MCs in EU / LA "
                    + "[French/German/Spanis h]/recognized modules, with a minimum CAP of 3.50\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"FAS1101\",\n"
                    + "    \"moduleTitle\": \"Writing Academically: Arts and Social Sciences\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\" ES1000 Foundation Academic English\", \"ES1103\"],\n"
                    + "    \"preclusions\": [\"ES1531 | GEK1549 | GET1021\", \"ES1501\", \"ES2531\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"FIN3102B\",\n"
                    + "    \"moduleTitle\": \"Investment Analysis and Portfolio Management\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"FNA2004\",\"FIN2004\",\"BH2004\",\"BZ2004\",\"BK2004\"],\n"
                    + "    \"preclusions\": [\"BH3102\",\"BZ3302\",\"BK3101\",\"FNA3102\",\"FNA3102A/C\","
                    + "\"FIN3102\",\"FIN3102A/C\",\"FE5108\",\"EC3333\",\"CF3101/QF3101\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"GE2204\",\n"
                    + "    \"moduleTitle\": \"Cities in Transition\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"\"],\n"
                    + "    \"preclusions\": [\"\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"GEH1032\",\n"
                    + "    \"moduleTitle\": \"Modern Technology in Medicine and Health\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"\"],\n"
                    + "    \"preclusions\": [\"GEK1540\"]\n"
                    + "  }, {\n"
                    + "    \"moduleCode\": \"ZB4299\",\n"
                    + "    \"moduleTitle\": \"Applied Project in Computational Biology\",\n"
                    + "    \"numMc\": \"4\",\n"
                    + "    \"availSems\": \"A | B\",\n"
                    + "    \"prereqs\": [\"\"],\n"
                    + "    \"preclusions\": [\"ZB4199\"]\n"
                    + "  }\n" + "  \n" + "]\n");
        }
        return moduleInfo;
    }

    public JsonModule[] getModuleInfo() {
        return moduleInfo;
    }

    public void setFoundModule(JsonModule foundModule) {
        this.foundModule.setAll(foundModule);
    }

    @Override
    public ObservableList<JsonModule> getFoundModule() {
        return foundModule;
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
