package seedu.flashback.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashback.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashback.testutil.TypicalFlashcards.getTypicalFlashBack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashback.logic.parser.FlashBackParser;
import seedu.flashback.model.Model;
import seedu.flashback.model.ModelManager;
import seedu.flashback.model.UserPrefs;
import seedu.flashback.model.flashcard.Flashcard;
import seedu.flashback.testutil.FlashcardBuilder;

public class AliasCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashBack(), new UserPrefs());
    }

    @Test
    public void execute_newAlias_success() {
        Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
        expectedModel.addAlias(AddCommand.COMMAND_WORD, "ad");

        assertCommandSuccess(new AliasCommand(AddCommand.COMMAND_WORD, "ad"), model,
                String.format(AliasCommand.MESSAGE_SUCCESS, AddCommand.COMMAND_WORD, "ad"), expectedModel);
    }

    @Test
    public void execute_usingAlias_success() {
        try {
            Flashcard validFlashcard = new FlashcardBuilder().withQuestion("What animal cannot stick its tongue out?")
                    .withAnswer("Crocodile").withCategory("Animals").withPriority("Low").build();

            Model expectedModel = new ModelManager(model.getFlashBack(), new UserPrefs());
            expectedModel.addAlias(AddCommand.COMMAND_WORD, "ad");

            model.addAlias(AddCommand.COMMAND_WORD, "ad");
            FlashBackParser parser = new FlashBackParser();
            parser.setModel(model);
            parser.parseCommand("ad q/What animal cannot stick its tongue out?"
                + " a/Crocodile" + " c/Animals" + " p/Low").execute(model);

            assertCommandSuccess(new AddCommand(validFlashcard), expectedModel,
                    String.format(AddCommand.MESSAGE_SUCCESS, validFlashcard), model);
        } catch (Exception ex) { // should not fail
            assertTrue(false); // should not happen
        }
    }

    @Test
    public void execute_invalidAlias_throwsCommandException() {
        assertCommandFailure(new AliasCommand("add", "add"), model,
                String.format(AliasCommand.MESSAGE_ALIAS_IS_COMMAND, "add"));
        assertCommandFailure(new AliasCommand("add", "a"), model,
                String.format(AliasCommand.MESSAGE_ALIAS_IS_COMMAND, "a"));
        assertCommandFailure(new AliasCommand("a", "ad"), model,
                String.format(AliasCommand.MESSAGE_COMMAND_IS_REVIEW, "a"));
        assertCommandFailure(new AliasCommand("invalid", "ad"), model,
                String.format(AliasCommand.MESSAGE_INVALID_COMMAND, "invalid"));

        model.addAlias("add", "ad");
        assertCommandFailure(new AliasCommand("add", "ad"), model,
                String.format(AliasCommand.MESSAGE_DUPLICATE_ALIAS, "ad"));
    }

}
