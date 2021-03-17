package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAliases.ADD_ALIAS_STRING;
import static seedu.address.testutil.TypicalAliases.getTypicalAliases;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.alias.CommandAlias;
import seedu.address.testutil.CommandAliasBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddAliasCommand}.
 */
public class AddAliasCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalAliases());
    }

    @Test
    public void execute_newAlias_success() {
        CommandAlias validCommandAlias = new CommandAliasBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), model.getAliases());
        expectedModel.addAlias(validCommandAlias);

        assertCommandSuccess(new AddAliasCommand(validCommandAlias), model,
                String.format(AddAliasCommand.MESSAGE_SUCCESS, validCommandAlias), expectedModel);
    }

    @Test
    public void execute_duplicateAlias_throwsCommandException() {
        CommandAlias commandAliasInList = new CommandAliasBuilder().withAlias(ADD_ALIAS_STRING)
                .withCommand(AddCommand.COMMAND_WORD).build();
        assertCommandFailure(new AddAliasCommand(commandAliasInList), model, AddAliasCommand.MESSAGE_DUPLICATE_ALIAS);
    }

}
