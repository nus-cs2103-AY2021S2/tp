package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.residence.Residence;
import seedu.address.model.residence.UniqueResidenceList;

/**
 * Wraps all data at the residence-tracker level
 * Duplicates are not allowed (by .isSameBooking comparison)
 */
public class ResidenceTracker implements ReadOnlyResidenceTracker {

    private final UniqueResidenceList residences;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        residences = new UniqueResidenceList();
    }

    public ResidenceTracker() {}

    /**
     * Creates a ResidenceTracker using the Residences in the {@code toBeCopied}
     */

    public ResidenceTracker(ReadOnlyResidenceTracker toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the residence list with {@code residences}.
     * {@code residences} must not contain duplicate residences.
     */
    public void setResidences(List<Residence> residences) {
        this.residences.setResidences(residences);
    }

    /**
     * Resets the existing data of this {@code ResidenceTracker} with {@code newData}.
     */
    public void resetData(ReadOnlyResidenceTracker newData) {
        requireNonNull(newData);

        setResidences(newData.getResidenceList());
    }

    //// residence-level operations

    /**
     * Returns true if a residence with the same identity as {@code residence} exists in the residence tracker.
     */
    public boolean hasResidence(Residence residence) {
        requireNonNull(residence);
        return residences.contains(residence);
    }

    /**
     * Adds a residence to the residence tracker.
     * The residence must not already exist in the residence tracker.
     */
    public void addResidence(Residence r) {
        residences.add(r);
    }

    /**
     * Replaces the given residence {@code target} in the list with {@code editedResidence}.
     * {@code target} must exist in the residence tracker.
     * The residence identity of {@code editedResidence} must not be the same as another existing residence
     * in the residence tracker.
     *
     */
    public void setResidence(Residence target, Residence editedResidence) {
        requireNonNull(editedResidence);

        residences.setResidence(target, editedResidence);
    }

    /**
     * Removes {@code key} from this {@code ResidenceTracker}.
     * {@code key} must exist in the residence tracker.
     */
    public void removeResidence(Residence key) {
        residences.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return residences.asUnmodifiableObservableList().size() + " residences";
        // TODO: refine later
    }

    @Override
    public ObservableList<Residence> getResidenceList() {
        return residences.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ResidenceTracker // instanceof handles nulls
                && residences.equals(((ResidenceTracker) other).residences));
    }

    @Override
    public int hashCode() {
        return residences.hashCode();
    }
}
