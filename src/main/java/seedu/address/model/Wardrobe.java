package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.Garment;
import seedu.address.model.person.UniqueGarmentList;

/**
 * Wraps all data at the wardrobe level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Wardrobe implements ReadOnlyWardrobe {

    private final UniqueGarmentList garments;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        garments = new UniqueGarmentList();
    }

    public Wardrobe() {}

    /**
     * Creates an Wardrobe using the Persons in the {@code toBeCopied}
     */
    public Wardrobe(ReadOnlyWardrobe toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setGarments(List<Garment> garments) {
        this.garments.setGarments(garments);
    }

    /**
     * Resets the existing data of this {@code Wardrobe} with {@code newData}.
     */
    public void resetData(ReadOnlyWardrobe newData) {
        requireNonNull(newData);

        setGarments(newData.getGarmentList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the wardrobe.
     */
    public boolean hasPerson(Garment garment) {
        requireNonNull(garment);
        return garments.contains(garment);

    }

    /**
     * Adds a person to the wardrobe.
     * The person must not already exist in the wardrobe.
     */
    public void addGarment(Garment g) {
        garments.add(g);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the wardrobe.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the wardrobe.
     */
    public void setGarment(Garment target, Garment editedGarment) {
        requireNonNull(editedGarment);

        garments.setPerson(target, editedGarment);
    }

    /**
     * Removes {@code key} from this {@code Wardrobe}.
     * {@code key} must exist in the wardrobe.
     */
    public void removeGarment(Garment key) {
        garments.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return garments.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Garment> getGarmentList() {
        return garments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Wardrobe // instanceof handles nulls
                && garments.equals(((Wardrobe) other).garments));
    }

    @Override
    public int hashCode() {
        return garments.hashCode();
    }
}
