package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;

public class DoneTaskCommand extends Command {
    public static final String COMMAND_WORD = "done";

    private final Index targetIndex;

    public DoneTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }
}
