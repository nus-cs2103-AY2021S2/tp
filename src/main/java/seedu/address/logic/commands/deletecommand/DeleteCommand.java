package seedu.address.logic.commands.deletecommand;

import seedu.address.logic.commands.Command;

public abstract class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = "Command: " + COMMAND_WORD + "\n"
            + "Person: delete INDEX\n"
            + "Module: delete m/TITLE\n"
            + "Assignment: delete m/TITLE a/INDEX\n"
            + "Exam: delete m/TITLE e/INDEX\n"
            + "Event: delete g/INDEX";
}
