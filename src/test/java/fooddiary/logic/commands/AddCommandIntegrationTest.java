package fooddiary.logic.commands;

import fooddiary.model.Model;
import fooddiary.model.ModelManager;
import fooddiary.model.UserPrefs;
import fooddiary.model.entry.Entry;
import fooddiary.testutil.EntryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static fooddiary.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fooddiary.testutil.TypicalEntries.getTypicalFoodDiary;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFoodDiary(), new UserPrefs());
    }

    @Test
    public void execute_newEntry_success() {
        Entry validEntry = new EntryBuilder().build();

        Model expectedModel = new ModelManager(model.getFoodDiary(), new UserPrefs());
        expectedModel.addEntry(validEntry);

        assertCommandSuccess(new AddCommand(validEntry), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validEntry), expectedModel);
    }

    @Test
    public void execute_duplicateEntry_throwsCommandException() {
        Entry entryInList = model.getFoodDiary().getEntryList().get(0);
        CommandTestUtil.assertCommandFailure(new AddCommand(entryInList), model, AddCommand.MESSAGE_DUPLICATE_ENTRY);
    }

}
