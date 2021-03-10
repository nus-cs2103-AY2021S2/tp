package seedu.address.model.tag;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.TagList;

import static seedu.address.testutil.Assert.assertThrows;

public class TagListTest {
    private final TagList tagList = new TagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagList.contains(null));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagList.add(null));
    }
}
