package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalDogs.APPLE;
import static dog.pawbook.testutil.TypicalDogs.ASHER;
import static dog.pawbook.testutil.TypicalDogs.BUBBLES;
import static dog.pawbook.testutil.TypicalDogs.DOG_OWNER_ID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Set;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.DogBuilder;


public class DogTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Dog dog = new DogBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> dog.getTags().remove(0));
    }

    @Test
    public void getName() {
        assertEquals(new Name("Apple"), APPLE.getName());
        assertEquals(new Name("Bubbles"), BUBBLES.getName());
    }

    @Test
    public void getBreed() {
        assertEquals(new Breed("Golden Retriever"), APPLE.getBreed());
        assertEquals(new Breed("Bulldog"), BUBBLES.getBreed());
    }

    @Test
    public void getSex() {
        assertEquals(new Sex("female"), APPLE.getSex());
        assertEquals(new Sex("female"), BUBBLES.getSex());
    }

    @Test
    public void getDob() {
        assertEquals(new DateOfBirth("11-2-2020"), APPLE.getDob());
        assertEquals(new DateOfBirth("1-1-2021"), BUBBLES.getDob());
    }

    @Test
    public void getOwnerId() {
        assertEquals(APPLE.getOwnerId(), DOG_OWNER_ID);
        assertEquals(BUBBLES.getOwnerId(), DOG_OWNER_ID);
    }

    @Test
    public void getTags() {
        assertEquals(Set.of(new Tag("friendly")), APPLE.getTags());
        assertEquals(Set.of(new Tag("cheerful")), BUBBLES.getTags());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Dog asherCopy = new DogBuilder(ASHER).build();
        assertEquals(asherCopy, ASHER);

        // same object -> returns true
        assertEquals(ASHER, ASHER);

        // null -> returns false
        assertNotEquals(ASHER, null);

        // different type -> returns false
        assertNotEquals(ASHER, 5);

        // different dog -> returns false
        assertNotEquals(BUBBLES, ASHER);

        // different name -> returns false
        Dog editedAsher = new DogBuilder(ASHER).withName(VALID_NAME_BELL).build();
        assertNotEquals(editedAsher, ASHER);

        // different sex -> returns false
        editedAsher = new DogBuilder(ASHER).withSex(VALID_SEX_BELL).build();
        assertNotEquals(editedAsher, ASHER);

        // different breed -> returns false
        editedAsher = new DogBuilder(ASHER).withBreed(VALID_BREED_BELL).build();
        assertNotEquals(editedAsher, ASHER);

        // different date of birth -> returns false
        editedAsher = new DogBuilder(ASHER).withDateOfBirth(VALID_DATEOFBIRTH_BELL).build();
        assertNotEquals(editedAsher, ASHER);

        // different tags -> returns false
        editedAsher = new DogBuilder(ASHER).withTags(VALID_TAG_QUIET).build();
        assertNotEquals(editedAsher, ASHER);
    }
}
