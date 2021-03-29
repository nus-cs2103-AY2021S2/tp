package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSampleColabFolder_success() {
        assertDoesNotThrow(SampleDataUtil::getSampleColabFolder);
    }

    @Test
    public void getSampleContacts_contactsValid_success() {
        assertDoesNotThrow(SampleDataUtil::getSampleContacts);
        assertDoesNotThrow((ThrowingSupplier<Set<Tag>>) SampleDataUtil::getTagSet);
    }

    @Test
    public void getSampleProjects_projectsValid_success() {
        assertDoesNotThrow(SampleDataUtil::getSampleProjects);
    }

}
