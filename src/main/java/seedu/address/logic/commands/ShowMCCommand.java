package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.plan.Semester;

import static java.util.Objects.requireNonNull;

public class ShowMCCommand extends ShowCommand {

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Semester currentSem = model.getCurrentSemester();
        int totalMCs = currentSem.getTotalMCs();
        return new CommandResult(String.format(MESSAGE_SUCCESS_MCS, totalMCs));
    }

    @Override
    public boolean equals(Object other) {
        return other == this || other instanceof ShowMCCommand;
    }
}
