package seedu.address.logic.commands;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.logic.commands.exceptions.CommandException;

public class SwitchCommand extends Command{

    public static final String COMMAND_WORD = "switch";

    @Override
    public CommandResult execute(Model model){
        ModelManager.switchView();
        return new CommandResult("",false, false, true);
    }
}
