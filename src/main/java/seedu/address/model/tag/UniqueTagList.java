package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
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
    private static final Logger logger = LogsCenter.getLogger(UniqueTagList.class);

    private final ObservableList<Tag> internalList = FXCollections.observableArrayList();
    private final ObservableList<Tag> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);
    private final Map<Tag, Integer> mapOfTagCount = new HashMap<>();

    /**
     * Returns true if the list contains an equivalent tag as the given argument.
     *
     * @param toCheck Tag to check.
     * @return Boolean indicating whether the list contains the tag.
     */
    public boolean contains(Tag toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a tag to the list.
     * Checks if the tag is already in the list and add to map that counts the tags.
     *
     * @param toAdd Tag to add.
     */
    public void add(Tag toAdd) {
        requireNonNull(toAdd);
        if (!contains(toAdd)) {
            internalList.add(toAdd);
        }
        mapOfTagCount.merge(toAdd, 1, Integer::sum);
    }

    /**
     * Gets a tag from the list.
     * The tag must already exist in the list.
     *
     * @param toGet Tag to find.
     * @return Tag to return.
     */
    public Tag get(Tag toGet) {
        requireNonNull(toGet);
        if (!contains(toGet)) {
            throw new TagNotFoundException();
        }
        return internalList.get(internalList.indexOf(toGet));
    }

    /**
     * Checks if there are tasks with the tag and removes the equivalent tag from the list if there are no more tasks
     * with this tag.
     * The tag must exist in the list.
     *
     * @param toRemove Tag to remove.
     */
    public void remove(Tag toRemove) {
        requireNonNull(toRemove);
        Integer countOfTag = mapOfTagCount.get(toRemove);
        if (countOfTag == null) {
            throw new TagNotFoundException();
        } else if (countOfTag == 1) {
            if (!internalList.remove(toRemove)) {
                throw new TagNotFoundException();
            }
            mapOfTagCount.remove(toRemove);
        } else {
            mapOfTagCount.put(toRemove, countOfTag - 1);
        }

    }

    /**
     * Sets the tags from the provided replacement into this list, and record the tag counts in the map.
     *
     * @param replacement UniqueTagList that is used to replace the data in this list.
     */
    public void setTags(UniqueTagList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        mapOfTagCount.clear();
        replacement.mapOfTagCount.forEach(mapOfTagCount::put);
    }

    /**
     * Replaces the set of tags {@code target} in the list with {@code editedTags}.
     * {@code target} must exist in the list.
     * The tag identities of {@code editedTags} must not be the same as other existing tags in the list.
     * If any target and editedTag are equal, there will be no change.
     *
     * @param target    Set of Tags to be replaced.
     * @param editedTag Set of Tags to replace.
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
     * Replaces the contents of this list with {@code tags}.
     * {@code tags} must not contain duplicate tags.
     *
     * @param tags List of tags that is to be set in this internal list.
     */
    public void setTags(List<Tag> tags) {
        requireAllNonNull(tags);
        if (!tagsAreUnique(tags)) {
            throw new DuplicateTagException();
        }

        internalList.setAll(tags);

        logger.info("Tag Count: " + mapOfTagCount);
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
     *
     * @param tags List of tags to check if all are unique.
     * @return Boolean indicating if the list of tags are unique.
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
