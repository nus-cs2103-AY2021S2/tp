package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.storage.JsonModule;

import static seedu.address.testutil.TypicalPlans.getTypicalAddressBook;

public class InfoCommandTest {

    @Test
    public void noArgumentTest() {
        assertTrue(new InfoCommand().noArgument());
        assertFalse(new InfoCommand(" ").noArgument());
        assertTrue(new InfoCommand("").noArgument());
        assertFalse(new InfoCommand("CS2103").noArgument());
    }

    @Test
    public void findMatchingModuleTest() {
        JsonModule[] informationOfModules = new JsonModule[10];
        for(int i = 0; i < informationOfModules.length; i++) {
            informationOfModules[i] = new JsonModule();
            informationOfModules[i].setModuleCode("CS" + i);
        }

        for(int i = 0; i < informationOfModules.length; i++) {
            assertEquals(new InfoCommand("CS" + i).findMatchingModule(informationOfModules)
                    , informationOfModules[i]);
        }
    }

    @Test
    public void execute_success_AllModuleInfo() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        InfoCommand testCommand = new InfoCommand();
        assertEquals(testCommand.execute(model), new CommandResult(InfoCommand.MESSAGE_SUCCESS));
        assertEquals(model.getCurrentCommand().getValue(), InfoCommand.COMMAND_WORD);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        testCommand = new InfoCommand("");
        assertEquals(testCommand.execute(model), new CommandResult(InfoCommand.MESSAGE_SUCCESS));
        assertEquals(model.getCurrentCommand().getValue(), InfoCommand.COMMAND_WORD);

        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        testCommand = new InfoCommand("" + "");
        assertEquals(testCommand.execute(model), new CommandResult(InfoCommand.MESSAGE_SUCCESS));
        assertEquals(model.getCurrentCommand().getValue(), InfoCommand.COMMAND_WORD);
    }

    @Test
    public void execute_success_SingleModuleInfo() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        InfoCommand testCommand = new InfoCommand("ABIDE");
        assertEquals(testCommand.execute(model), new CommandResult(InfoCommand.MESSAGE_NOT_FOUND));

        testCommand = new InfoCommand("CS2309");
        JsonModule foundModule = null;
        JsonModule[] ls = model.getPlans().getModuleInfo();
        for(int i = 0; i < ls.length; i++) {
            if(ls[i].getModuleCode().equals("CS2309")) {
                foundModule = ls[i];
                break;
            }
        }
        assertEquals(testCommand.execute(model), new CommandResult(InfoCommand.MESSAGE_FOUND));
        assertEquals(model.getCurrentCommand().getValue(), InfoCommand.COMMAND_WORD_SINGLE_MODULE);
        assertEquals(model.getPlans().getFoundModule(), foundModule);
    }
}
