package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.garment.Garment;
import seedu.address.model.garment.UniqueGarmentList;

/**
 * Wraps all data at the wardrobe level
 * Duplicates are not allowed (by .isSameGarment comparison)
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
        UniqueGarmentList garmentList = new UniqueGarmentList();
        garmentList.sort();
        garments = garmentList;
    }

    public Wardrobe() {}

    /**
     * Creates an Wardrobe using the Garments in the {@code toBeCopied}
     */
    public Wardrobe(ReadOnlyWardrobe toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the garment list with {@code garments}.
     * {@code garments} must not contain duplicate garments.
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

    //// garment-level operations

    /**
     * Returns true if a garment with the same identity as {@code garment} exists in the wardrobe.
     */
    public boolean hasGarment(Garment garment) {
        requireNonNull(garment);
        return garments.contains(garment);

    }

    /**
     * Adds a garment to the wardrobe.
     * The garment must not already exist in the wardrobe.
     */
    public void addGarment(Garment g) {
        garments.add(g);
    }

    /**
     * Replaces the given garment {@code target} in the list with {@code editedGarment}.
     * {@code target} must exist in the wardrobe.
     * The garment identity of {@code editedGarment} must not be the same as another existing garment in the wardrobe.
     */
    public void setGarment(Garment target, Garment editedGarment) {
        requireNonNull(editedGarment);

        garments.setGarment(target, editedGarment);
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
        return garments.asUnmodifiableObservableList().size() + " garments";
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
