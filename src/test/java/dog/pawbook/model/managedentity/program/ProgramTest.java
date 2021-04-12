package dog.pawbook.model.managedentity.program;

import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_NAME_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_OBEDIENCE_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_SESSION_POTTY_TRAINING;
import static dog.pawbook.logic.commands.CommandTestUtil.VALID_TAG_ALL;
import static dog.pawbook.model.managedentity.program.Program.DOG_ID_PREFIX;
import static dog.pawbook.model.managedentity.program.Program.TIMESLOT_PREFIX;
import static dog.pawbook.testutil.TypicalEntities.ACTIVE_LISTENING;
import static dog.pawbook.testutil.TypicalEntities.ALICE;
import static dog.pawbook.testutil.TypicalEntities.APPLE;
import static dog.pawbook.testutil.TypicalEntities.BEHAVING;
import static dog.pawbook.testutil.TypicalEntities.DANCING;
import static dog.pawbook.testutil.TypicalEntities.GENERAL_KNOWLEDGE;
import static dog.pawbook.testutil.TypicalEntities.OBEDIENCE_TRAINING;
import static dog.pawbook.testutil.TypicalEntities.POTTY_TRAINING;
import static dog.pawbook.testutil.TypicalId.ID_EIGHT;
import static dog.pawbook.testutil.TypicalId.ID_FOURTEEN;
import static dog.pawbook.testutil.TypicalId.ID_ONE;
import static dog.pawbook.testutil.TypicalId.ID_TEN;
import static dog.pawbook.testutil.TypicalId.ID_TWELVE;
import static dog.pawbook.testutil.TypicalId.ID_TWO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.tag.Tag;
import dog.pawbook.testutil.ProgramBuilder;

public class ProgramTest {
    @Test
    public void isSameAs() {
        // same object -> returns true
        assertTrue(ACTIVE_LISTENING.isSameAs(ACTIVE_LISTENING));

        // different entity type -> return false
        assertFalse(ACTIVE_LISTENING.isSameAs(APPLE));

        // null -> returns false
        assertFalse(ACTIVE_LISTENING.isSameAs(null));

        // same name and session, tags and withDogs different -> returns true
        Program editedActiveListening = new ProgramBuilder(ACTIVE_LISTENING).withDogs(2, 4)
                .withTags(VALID_TAG_ALL).build();
        assertTrue(ACTIVE_LISTENING.isSameAs(editedActiveListening));

        // same name and tag, sessions and withDogs different -> returns true
        editedActiveListening = new ProgramBuilder(ACTIVE_LISTENING).withSessions(VALID_SESSION_OBEDIENCE_TRAINING)
                .withDogs(4, 6).build();
        assertTrue(ACTIVE_LISTENING.isSameAs(editedActiveListening));

        // same sessions and tags, name and withDogs different -> returns false
        editedActiveListening = new ProgramBuilder(ACTIVE_LISTENING).withName(VALID_NAME_OBEDIENCE_TRAINING)
               .withDogs(8).build();
        assertFalse(ACTIVE_LISTENING.isSameAs(editedActiveListening));

        // different name, all other attributes same -> returns false
        editedActiveListening = new ProgramBuilder(ACTIVE_LISTENING).withName(VALID_NAME_OBEDIENCE_TRAINING).build();
        assertFalse(ACTIVE_LISTENING.isSameAs(editedActiveListening));

        // name differs in case, all other attributes same -> returns true
        Program editedPottyTraining = new ProgramBuilder(POTTY_TRAINING)
                .withName(VALID_NAME_POTTY_TRAINING.toLowerCase()).build();
        assertTrue(POTTY_TRAINING.isSameAs(editedPottyTraining));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_POTTY_TRAINING + " ";
        editedPottyTraining = new ProgramBuilder(POTTY_TRAINING).withName(nameWithTrailingSpaces).build();
        assertFalse(POTTY_TRAINING.isSameAs(editedPottyTraining));
    }

    @Test
    public void getName() {
        assertEquals(new Name("Active Listening"), ACTIVE_LISTENING.getName());
        assertEquals(new Name("Potty Training"), POTTY_TRAINING.getName());
    }

    @Test
    public void getSession() {
        assertEquals(Set.of(new Session("12-12-2021 18:00")), ACTIVE_LISTENING.getSessions());
        assertEquals(Set.of(new Session("03-02-2022 20:30")), POTTY_TRAINING.getSessions());
    }

    @Test
    public void getDogIdSet() {
        assertEquals(Set.of(ID_EIGHT, ID_TEN, ID_TWELVE, ID_FOURTEEN), GENERAL_KNOWLEDGE.getDogIdSet());
        assertEquals(Set.of(ID_TWO), DANCING.getDogIdSet());
    }

    @Test
    public void getTags() {
        assertEquals(Set.of(new Tag("Puppies")), ACTIVE_LISTENING.getTags());
        assertEquals(Set.of(new Tag("All")), OBEDIENCE_TRAINING.getTags());
    }

    @Test
    public void getRelatedEntityIds() {
        // no dogs enrolled
        assertEquals(List.of(), ACTIVE_LISTENING.getRelatedEntityIds());

        // one dog enrolled
        assertEquals(List.of(2), DANCING.getRelatedEntityIds());

        // multiple dogs enrolled
        assertEquals(List.of(8, 10, 12, 14), GENERAL_KNOWLEDGE.getRelatedEntityIds());
    }

    @Test
    public void getOtherPropertiesAsString() {
        // Program with single session, no dogs
        assertEquals(List.of(TIMESLOT_PREFIX + VALID_SESSION_OBEDIENCE_TRAINING),
                OBEDIENCE_TRAINING.getOtherPropertiesAsString());

        // Program with no session
        assertEquals(List.of("Timeslot(s): None"), new ProgramBuilder(OBEDIENCE_TRAINING).withSessions().build()
                .getOtherPropertiesAsString());

        // Program with one session and enrolled dog
        assertEquals(List.of(TIMESLOT_PREFIX + VALID_SESSION_OBEDIENCE_TRAINING, DOG_ID_PREFIX + ID_ONE),
                new ProgramBuilder(OBEDIENCE_TRAINING).withDogs(ID_ONE).build().getOtherPropertiesAsString());

        // Program with multiple sessions and multiple enrolled dogs
        assertEquals(List.of(TIMESLOT_PREFIX + VALID_SESSION_OBEDIENCE_TRAINING + ", " + VALID_SESSION_POTTY_TRAINING,
                DOG_ID_PREFIX + ID_ONE + ", " + ID_TWO),
                new ProgramBuilder(OBEDIENCE_TRAINING)
                        .withSessions(VALID_SESSION_OBEDIENCE_TRAINING, VALID_SESSION_POTTY_TRAINING)
                        .withDogs(ID_ONE, ID_TWO)
                        .build()
                        .getOtherPropertiesAsString());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Program activeListeningCopy = new ProgramBuilder(ACTIVE_LISTENING).build();
        assertEquals(activeListeningCopy, ACTIVE_LISTENING);

        // same object -> returns true
        assertEquals(ACTIVE_LISTENING, ACTIVE_LISTENING);

        // different entity type -> return false
        assertNotEquals(ACTIVE_LISTENING, ALICE);

        // null -> returns false
        assertNotEquals(ACTIVE_LISTENING, null);

        // different type -> returns false
        assertNotEquals(ACTIVE_LISTENING, 5);

        // different program -> returns false
        assertNotEquals(BEHAVING, ACTIVE_LISTENING);

        // different name -> returns false
        Program editedActiveListening = new ProgramBuilder(ACTIVE_LISTENING)
                .withName(VALID_NAME_OBEDIENCE_TRAINING).build();
        assertNotEquals(editedActiveListening, ACTIVE_LISTENING);

        // different tags -> returns false
        editedActiveListening = new ProgramBuilder(ACTIVE_LISTENING)
                .withTags("Cat").build();
        assertNotEquals(editedActiveListening, ACTIVE_LISTENING);
    }
}
