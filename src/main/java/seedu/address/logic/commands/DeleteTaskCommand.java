package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete_task";

    private final Index targetIndex;

    public DeleteTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
}
