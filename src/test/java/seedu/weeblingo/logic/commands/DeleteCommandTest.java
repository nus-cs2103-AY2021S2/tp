package seedu.weeblingo.logic.commands;

import static seedu.weeblingo.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.weeblingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.weeblingo.testutil.TypicalFlashcards.getTypicalFlashcardBook;
import static seedu.weeblingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.weeblingo.commons.core.index.Index;
import seedu.weeblingo.logic.commands.exceptions.CommandException;
import seedu.weeblingo.model.FlashcardBook;
import seedu.weeblingo.model.Model;
import seedu.weeblingo.model.ModelManager;
import seedu.weeblingo.model.UserPrefs;
import seedu.weeblingo.model.flashcard.Flashcard;
import seedu.weeblingo.model.tag.Tag;
import seedu.weeblingo.testutil.FlashcardBuilder;

public class DeleteCommandTest {

    private ModelManager modelManager = new ModelManager(getTypicalFlashcardBook(), new UserPrefs());

    @Test
    public void execute_delete_success() throws CommandException {
        Index targetIndex = INDEX_FIRST_FLASHCARD;

        Set<Tag> oneTag = new HashSet<>();
        oneTag.add(new Tag(VALID_TAG_EASY));

        DeleteTagCommand deleteCommand = new DeleteTagCommand(targetIndex, oneTag);

        String expectedMessage = DeleteTagCommand.MESSAGE_SUCCESS;

        Flashcard taggedFlashcard = new FlashcardBuilder().withUserTags(VALID_TAG_EASY).build();
        modelManager.switchModeLearn();
        Model model = modelManager;
        model.setFlashcard(model.getFilteredFlashcardList().get(0), taggedFlashcard);

        ModelManager expectedModelManager = new ModelManager(
                new FlashcardBook(model.getFlashcardBook()), new UserPrefs());
        expectedModelManager.switchModeLearn();
        Model expectedModel = expectedModelManager;

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    //more tests to be added...

}
