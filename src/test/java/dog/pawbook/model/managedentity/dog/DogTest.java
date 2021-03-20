package dog.pawbook.model.managedentity.dog;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_BREED_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_DATEOFBIRTH_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_OWNERID_9;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_ASHER;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SEX_BELL;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_FRIENDLY;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_QUIET;
import static dog.pawbook.testutil.Assert.assertThrows;
import static dog.pawbook.testutil.TypicalDogs.APPLE;
import static dog.pawbook.testutil.TypicalDogs.ASHER;
import static dog.pawbook.testutil.TypicalDogs.BUBBLES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        assertEquals(APPLE.getOwnerId(), 1);
        assertEquals(BUBBLES.getOwnerId(), 2);
    }

    @Test
    public void getTags() {
        assertEquals(Set.of(new Tag("friendly")), APPLE.getTags());
        assertEquals(Set.of(new Tag("cheerful")), BUBBLES.getTags());
    }

    @Test
    public void isSameDog() {
        // same object -> returns true
        assertTrue(APPLE.isSameEntity(APPLE));

        // null -> returns false
        assertFalse(APPLE.isSameEntity(null));

        // same name, all other attributes different -> returns true
        Dog editedApple = new DogBuilder(APPLE).withBreed(VALID_BREED_ASHER).withDateOfBirth(VALID_DATEOFBIRTH_ASHER)
                .withSex(VALID_SEX_ASHER).withOwnerID(VALID_OWNERID_9).withTags(VALID_TAG_FRIENDLY).build();
        assertTrue(APPLE.isSameEntity(editedApple));

        // different name, all other attributes same -> returns false
        editedApple = new DogBuilder(APPLE).withName(VALID_SEX_ASHER).build();
        assertFalse(APPLE.isSameEntity(editedApple));

        // name differs in case, all other attributes same -> returns false
        Dog editedAsher = new DogBuilder(ASHER).withName(VALID_NAME_ASHER.toLowerCase()).build();
        assertFalse(ASHER.isSameEntity(editedAsher));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_ASHER + " ";
        editedAsher = new DogBuilder(ASHER).withName(nameWithTrailingSpaces).build();
        assertFalse(ASHER.isSameEntity(editedAsher));
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
