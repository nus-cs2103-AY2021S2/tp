package seedu.address.logic.commands.editcommand;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DATE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DESCRIPTION_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENERAL_EVENT_DESCRIPTION_2;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showEventAtIndex;
import static seedu.address.testutil.GeneralEventBuilder.DEFAULT_DATE;
import static seedu.address.testutil.GeneralEventBuilder.DEFAULT_DESCRIPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalRemindMe.getTypicalRemindMe;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.LocalDateTimeUtil;
import seedu.address.logic.commands.clearcommand.ClearModulesCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.RemindMe;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.GeneralEvent;
import seedu.address.model.module.Description;
import seedu.address.testutil.GeneralEventBuilder;

public class EditEventCommandTest {
    private Model model = new ModelManager(getTypicalRemindMe(), new UserPrefs());

    @Test
    public void execute_descriptionFieldsSpecifiedUnfilteredList_success() {
        Description edit = new Description(DEFAULT_DESCRIPTION);
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(), edit, null);

        GeneralEvent editedEvent = new GeneralEventBuilder().withDescription(DEFAULT_DESCRIPTION)
                                        .withDate(LocalDateTime.parse(VALID_GENERAL_EVENT_DATE_1,
                                                LocalDateTimeUtil.DATETIME_FORMATTER))
                                        .build();
        String expectedMessage = String.format(EditEventCommand.MESSAGE_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(
                new RemindMe(model.getRemindMe()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateFieldsSpecifiedUnfilteredList_success() {
        LocalDateTime edit = DEFAULT_DATE;
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(), null, edit);

        GeneralEvent editedEvent = new GeneralEventBuilder().withDescription(VALID_GENERAL_EVENT_DESCRIPTION_1)
                                        .withDate(DEFAULT_DATE)
                                        .build();
        String expectedMessage = String.format(EditEventCommand.MESSAGE_SUCCESS, editedEvent);

        Model expectedModel = new ModelManager(
                new RemindMe(model.getRemindMe()), new UserPrefs());
        expectedModel.setEvent(model.getFilteredEventList().get(0), editedEvent);

        assertCommandSuccess(editEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_descriptionDuplicateGeneralEventUnfilteredList_failure() {
        Description edit = new Description(VALID_GENERAL_EVENT_DESCRIPTION_1);
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT.getOneBased(), edit, null);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_DUPLICATE_EVENT);

        assertCommandFailure(editEventCommand, model, expectedMessage);
    }

    @Test
    public void execute_dateDuplicateGeneralEventUnfilteredList_failure() {
        LocalDateTime edit = LocalDateTime.parse(VALID_GENERAL_EVENT_DATE_2, LocalDateTimeUtil.DATETIME_FORMATTER);
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(), null, edit);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_DUPLICATE_EVENT);

        assertCommandFailure(editEventCommand, model, expectedMessage);
    }

    @Test
    public void execute_descriptionDuplicateGeneralEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_FIRST_EVENT);
        Description edit = new Description(VALID_GENERAL_EVENT_DESCRIPTION_1);
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_SECOND_EVENT.getOneBased(), edit, null);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_DUPLICATE_EVENT);

        assertCommandFailure(editEventCommand, model, expectedMessage);
    }

    @Test
    public void execute_dateDuplicateGeneralEventFilteredList_failure() {
        showEventAtIndex(model, INDEX_SECOND_EVENT);
        LocalDateTime edit = LocalDateTime.parse(VALID_GENERAL_EVENT_DATE_2, LocalDateTimeUtil.DATETIME_FORMATTER);
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(), null, edit);

        String expectedMessage = String.format(EditEventCommand.MESSAGE_DUPLICATE_EVENT);

        assertCommandFailure(editEventCommand, model, expectedMessage);
    }

    @Test
    public void execute_descriptionInvalidGeneralEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        Description edit = new Description(DEFAULT_DESCRIPTION);
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex.getOneBased(), edit, null);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_NO_EVENT);
    }

    @Test
    public void execute_dateInvalidGeneralEventIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredEventList().size() + 1);
        LocalDateTime edit = DEFAULT_DATE;
        EditEventCommand editEventCommand = new EditEventCommand(outOfBoundIndex.getOneBased(), null, edit);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_NO_EVENT);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_failure() {
        Description descriptionEdit = new Description(DEFAULT_DESCRIPTION);
        LocalDateTime dateEdit = DEFAULT_DATE;
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                                    descriptionEdit, dateEdit);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_EDIT_BOTH_PARTS);
    }

    @Test
    public void execute_noFieldsSpecifiedUnfilteredList_failure() {
        EditEventCommand editEventCommand = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(), null, null);

        assertCommandFailure(editEventCommand, model, EditEventCommand.MESSAGE_NULL_EDIT);
    }

    @Test
    public void equals() {
        Description descriptionEdit = new Description(DEFAULT_DESCRIPTION);
        LocalDateTime dateEdit = DEFAULT_DATE;
        final EditEventCommand standardCommandDescription = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                                                    descriptionEdit, null);
        final EditEventCommand standardCommandDate = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                                    null, dateEdit);

        // same values -> returns true
        Description descriptionCopy = new Description(DEFAULT_DESCRIPTION);
        LocalDateTime dateCopy = DEFAULT_DATE;
        EditEventCommand commandWithSameValuesDescription = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                                                    descriptionCopy, null);
        EditEventCommand commandWithSameValuesDate = new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                                            null, dateCopy);
        assertTrue(standardCommandDescription.equals(commandWithSameValuesDescription));
        assertTrue(standardCommandDate.equals(commandWithSameValuesDate));

        // same object -> returns true
        assertTrue(standardCommandDescription.equals(standardCommandDescription));
        assertTrue(standardCommandDate.equals(standardCommandDate));

        // null -> returns false
        assertFalse(standardCommandDescription.equals(null));
        assertFalse(standardCommandDate.equals(null));

        // different types -> returns false
        assertFalse(standardCommandDescription.equals(new ClearModulesCommand()));
        assertFalse(standardCommandDate.equals(new ClearModulesCommand()));

        // different index -> returns false
        assertFalse(standardCommandDescription.equals(new EditEventCommand(INDEX_SECOND_EVENT.getOneBased(),
                                                                            descriptionEdit, null)));
        assertFalse(standardCommandDate.equals(new EditEventCommand(INDEX_SECOND_EVENT.getOneBased(),
                                                            null, dateEdit)));

        // different description -> returns false
        Description diffDescription = new Description(VALID_GENERAL_EVENT_DESCRIPTION_2);
        LocalDateTime diffDate = LocalDateTime.parse(VALID_GENERAL_EVENT_DATE_2, LocalDateTimeUtil.DATETIME_FORMATTER);
        assertFalse(standardCommandDescription.equals(new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                                            diffDescription, null)));
        assertFalse(standardCommandDate.equals(new EditEventCommand(INDEX_FIRST_EVENT.getOneBased(),
                                                            null, diffDate)));
    }
}
