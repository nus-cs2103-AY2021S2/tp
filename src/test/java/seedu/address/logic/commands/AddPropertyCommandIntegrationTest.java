package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertPropertyCommandFailure;
import static seedu.address.testutil.TypicalProperties.getTypicalPropertyBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.property.Property;
import seedu.address.testutil.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPropertyCommand}.
 */
public class AddPropertyCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPropertyBook(), new UserPrefs());
    }

    @Test
    public void execute_newProperty_success() {
        Property validProperty = new PropertyBuilder().build();

        Model expectedModel = new ModelManager(model.getPropertyBook(), new UserPrefs());
        expectedModel.addProperty(validProperty);

        assertCommandSuccess(new AddPropertyCommand(validProperty), model,
                String.format(AddPropertyCommand.MESSAGE_SUCCESS, validProperty), expectedModel);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property propertyInList = model.getPropertyBook().getPropertyList().get(0);
        assertPropertyCommandFailure(new AddPropertyCommand(propertyInList), model,
                AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

}
