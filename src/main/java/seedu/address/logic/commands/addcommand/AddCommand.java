package seedu.address.logic.commands.addcommand;

import seedu.address.logic.commands.Command;

public abstract class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = "Command: " + COMMAND_WORD + "\n"
            + "Person: add n/NAME b/BIRTHDAY [t/TAG...]\n"
            + "Module: add m/TITLE\n"
            + "Assignment: add m/TITLE a/DESCRIPTION by/DEADLINE\n"
            + "Exam: add m/TITLE e/EXAM DATE\n"
            + "Event: add g/EVENT DESCRIPTION on/EVENT DATE";
}
