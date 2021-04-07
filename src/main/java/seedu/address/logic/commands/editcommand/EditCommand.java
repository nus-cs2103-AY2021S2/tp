package seedu.address.logic.commands.editcommand;

import seedu.address.logic.commands.Command;

public abstract class EditCommand extends Command {
    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "Command: " + COMMAND_WORD + "\n"
            + "Person: edit INDEX n/NAME [b/BIRTHDAY] [t/TAG ...]\n"
            + "Module: edit INDEX m/TITLE\n"
            + "Assignment: edit m/TITLE a/INDEX d/DESCRIPTION OR by/DEADLINE\n"
            + "Exam: edit m/TITLE e/INDEX on/EXAM DATE\n"
            + "Event: add INDEX g/DESCRIPTION OR on/EVENT DATE";
}
