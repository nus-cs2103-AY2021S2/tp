package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.model.tag.UniqueTagListTestUtil.SET_TAG_FRIEND;
import static seedu.address.model.tag.UniqueTagListTestUtil.SET_TAG_HUSBAND;
import static seedu.address.model.tag.UniqueTagListTestUtil.TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagListTest {
    private static final Tag TAG_FRIEND = new Tag(VALID_TAG_FRIEND);

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(TAG_HUSBAND));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(TAG_HUSBAND);
        assertTrue(uniqueTagList.contains(TAG_HUSBAND));
    }

    @Test
    public void contains_tagWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTagList.add(TAG_HUSBAND);
        Tag similarTag = new Tag(VALID_TAG_HUSBAND);
        assertTrue(uniqueTagList.contains(similarTag));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_correctMapCount() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.add(TAG_HUSBAND);

        Map<Tag, Integer> expectedMap = new HashMap<>();
        expectedMap.put(TAG_HUSBAND, 2);
        assertTrue(uniqueTagList.hasEqualMap(expectedMap));
        assertTrue(uniqueTagList.contains(TAG_HUSBAND));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(TAG_HUSBAND));
    }

    @Test
    public void remove_existingSingleTag_removesTag() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.remove(TAG_HUSBAND);

        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void remove_existingTagWithMultipleCount_editsTagCount() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.remove(TAG_HUSBAND);

        Map<Tag, Integer> expectedMap = new HashMap<>();
        expectedMap.put(TAG_HUSBAND, 1);
        assertTrue(uniqueTagList.hasEqualMap(expectedMap));
        assertTrue(uniqueTagList.contains(TAG_HUSBAND));
    }

    // setTags(Set<Tag>, Set<Tag>)
    @Test
    public void setTags_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags(null, SET_TAG_HUSBAND));
    }

    @Test
    public void setTags_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags(SET_TAG_HUSBAND, null));
    }

    @Test
    public void setTags_targetTagNotInList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.setTags(SET_TAG_HUSBAND, SET_TAG_HUSBAND));
    }

    @Test
    public void setTags_editedTagIsSameTag_success() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.setTags(SET_TAG_HUSBAND, SET_TAG_HUSBAND);

        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(TAG_HUSBAND);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_editedTagHasSameIdentity_success() {
        uniqueTagList.add(TAG_HUSBAND);
        Tag similarTagHusband = new Tag(VALID_TAG_HUSBAND);
        Set<Tag> similarSet = new HashSet<>();
        similarSet.add(similarTagHusband);
        uniqueTagList.setTags(SET_TAG_HUSBAND, similarSet);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(similarTagHusband);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.setTags(SET_TAG_HUSBAND, SET_TAG_FRIEND);

        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(TAG_FRIEND);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_editedTagHasNonUniqueIdentity_editsTagCount() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.add(TAG_FRIEND);
        uniqueTagList.setTags(SET_TAG_HUSBAND, SET_TAG_FRIEND);

        Map<Tag, Integer> expectedMap = new HashMap<>();
        expectedMap.put(TAG_FRIEND, 2);
        assertTrue(uniqueTagList.hasEqualMap(expectedMap));
        assertFalse(uniqueTagList.contains(TAG_HUSBAND));
        assertTrue(uniqueTagList.contains(TAG_FRIEND));
    }

    // setTags(UniqueTagList)
    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(TAG_HUSBAND);
        uniqueTagList.add(TAG_FRIEND);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(TAG_FRIEND);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    // setTags(List<Tag>)
    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(TAG_HUSBAND);
        List<Tag> tagList = Collections.singletonList(TAG_FRIEND);
        uniqueTagList.setTags(tagList);

        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(TAG_FRIEND);
        assertEquals(expectedUniqueTagList.asUnmodifiableObservableList(),
                uniqueTagList.asUnmodifiableObservableList());
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(TAG_HUSBAND, TAG_HUSBAND);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }
}
