package seedu.booking.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.booking.commons.core.Messages.PROMPT_EMAIL_PERSON_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_PHONE_MESSAGE;
import static seedu.booking.commons.core.Messages.PROMPT_TAG_MESSAGE;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.booking.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.booking.logic.commands.states.AddPersonCommandState.STATE_EMAIL;
import static seedu.booking.logic.commands.states.AddPersonCommandState.STATE_PHONE;
import static seedu.booking.logic.commands.states.AddPersonCommandState.STATE_TAG;
import static seedu.booking.testutil.TypicalPersons.getTypicalBookingSystem;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.booking.logic.commands.exceptions.CommandException;
import seedu.booking.logic.commands.states.AddPersonCommandState;
import seedu.booking.logic.commands.states.CommandState;
import seedu.booking.model.ModelManager;
import seedu.booking.model.Tag;
import seedu.booking.model.UserPrefs;
import seedu.booking.model.person.Email;
import seedu.booking.model.person.Name;
import seedu.booking.model.person.Phone;

public class PromptAddPersonCommandTest {
    private final ModelManager model = new ModelManager(getTypicalBookingSystem(), new UserPrefs());

    @Test
    void execute_enterName_stateChangeToEmailSuccessful() {
        CommandResult expectedResult = new CommandResult(PROMPT_EMAIL_PERSON_MESSAGE);
        CommandResult result = new PromptAddPersonCommand(new Name((VALID_NAME_AMY))).execute(model);
        assertEquals(expectedResult, result);

        String state = ModelManager.getState();
        assertEquals(STATE_EMAIL, state);
        assertTrue(ModelManager.isStateActive());

        ModelManager.resetCommandState();
    }

    @Nested
    class PromptAddPersonCommandActiveStatesTest {

        @BeforeEach
        public void setUp() {
            CommandState commandState = new AddPersonCommandState(new Name(VALID_NAME_AMY));
            ModelManager.setCommandState(commandState);
            ModelManager.setStateActive();
        }

        @Test
        void execute_enterEmail_stateChangeToPhoneSuccessful() {
            ModelManager.setState(STATE_EMAIL);

            PromptPersonEmailCommand command = new PromptPersonEmailCommand(
                    new Email(VALID_EMAIL_AMY));
            CommandResult expectedResult = new CommandResult(PROMPT_PHONE_MESSAGE);
            CommandResult result;

            try {
                result = command.execute(model);
                assertEquals(expectedResult, result);
            } catch (CommandException ex) {
                throw new AssertionError("Execution of command should not fail.");
            }

            String state = ModelManager.getState();
            assertEquals(STATE_PHONE, state);
            assertTrue(ModelManager.isStateActive());

            ModelManager.resetCommandState();
        }

        @Test
        void execute_enterPhone_stateChangeToTagsSuccessful() {
            ModelManager.setState(STATE_PHONE);

            PromptPersonPhoneCommand command = new PromptPersonPhoneCommand(
                    new Phone(VALID_PHONE_AMY));
            CommandResult expectedResult = new CommandResult(PROMPT_TAG_MESSAGE);
            CommandResult result;

            try {
                result = command.execute(model);
                assertEquals(expectedResult, result);
            } catch (CommandException ex) {
                throw new AssertionError("Execution of command should not fail.");
            }

            String state = ModelManager.getState();
            assertEquals(STATE_TAG, state);
            assertTrue(ModelManager.isStateActive());

            ModelManager.resetCommandState();
        }
    }

    @Test
    void execute_enterTags_endActiveStateSuccessful() {
        CommandState commandState = new AddPersonCommandState(new Name(VALID_NAME_AMY));
        ModelManager.setCommandState(commandState);
        ModelManager.setStateActive();
        ModelManager.setState(STATE_EMAIL);
        commandState.processInput(new Email(VALID_EMAIL_AMY));
        ModelManager.setState(STATE_PHONE);
        commandState.processInput(new Phone(VALID_PHONE_AMY));
        ModelManager.setState(STATE_TAG);

        Set<Tag> set = new HashSet<>();
        set.add(new Tag(VALID_TAG_FRIEND + VALID_TAG_HUSBAND));
        PromptPersonTagsCommand command = new PromptPersonTagsCommand(set);

        try {
            command.execute(model);
        } catch (CommandException ex) {
            throw new AssertionError("Execution of command should not fail.");
        }

        String state = ModelManager.getState();
        assertNull(state);
        assertFalse(ModelManager.isStateActive());

        ModelManager.resetCommandState();
    }

    @Test
    void execute_changeStateAfterTagState_invalidState() {
        CommandState commandState = new AddPersonCommandState(new Name(VALID_NAME_AMY));
        ModelManager.setState(STATE_TAG);
        commandState.setNextState();
        String state = ModelManager.getState();

        assertEquals(STATE_TAG, state);
        assertFalse(ModelManager.isStateActive());

        ModelManager.resetCommandState();
    }
}
