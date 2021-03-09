package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.flashcard.Flashcard;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Flashcard validFlashcard = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
        expectedModel.addFlashcard(validFlashcard);

        assertCommandSuccess(new AddCommand(validFlashcard), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Flashcard flashcardInList = model.getFlashBack().getPersonList().get(0);
        assertCommandFailure(new AddCommand(flashcardInList), model, AddCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

}
