package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#equals(Object)}. As such, adding and updating of
 * tag uses Tag#equals(Object) for equality so as to ensure that the tag being added or updated is
 * unique in terms of identity in the UniqueTagList. Also will be sorted in natural sort order.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Tag#equals(Object)
 */
public class UniqueTagList implements Iterable<Tag> {

    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag to the list.
     * The tag must not already exist in the list.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTagException();
        }
        internalList.add(toAdd);
    }

    /**
     * Gets a tag from the list.
     * The tag must already exist in the list.
     */
    public Tag get(Tag toGet) {
        requireNonNull(toGet);
        if (!contains(toGet)) {
            throw new TagNotFoundException();
        }
        return internalList.get(internalList.indexOf(toGet));
    }

    /**
     * Replaces the set of tags {@code target} in the list with {@code editedTags}.
     * {@code target} must exist in the list.
     * The tag identities of {@code editedTags} must not be the same as other existing tags in the list.
     * If any target and editedTag are equal, there will be no change
     */
    public void setTags(Set<Tag> target, Set<Tag> editedTag) {
        requireAllNonNull(target, editedTag);

        for (Tag oldTag : target) {
            int index = internalList.indexOf(oldTag);
            if (index == -1) {
                throw new TagNotFoundException();
            }
            remove(oldTag);
        }

        for (Tag newTag : editedTag) {
            if (contains(newTag)) {
                continue;
            }
            add(newTag);
        }
    }

    /**
     * Removes the equivalent tag from the list.
     * The tag must exist in the list.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TagNotFoundException();
        }
    }

    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);
    }

    /**
     * Provides the comparator which Tags in this list are being sorted with.
     *
     * @return Comparator that is used to sort the Tags.
     */
    public Comparator<Tag> getTagComparator() {
        return Comparator.naturalOrder();
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Tag> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                && internalList.equals(((UniqueTagList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tags} contains only unique tags.
     */
    private boolean tagsAreUnique(List<Tag> tags) {
        for (int i = 0; i < tags.size() - 1; i++) {
            for (int j = i + 1; j < tags.size(); j++) {
                if (tags.get(i).equals(tags.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

}
