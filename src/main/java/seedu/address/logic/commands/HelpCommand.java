package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Here is a list of commands available, "
            + " for more information refer to https://ay2021s2-cs2103-w16-3.github.io/tp/UserGuide.html\n"
            + "Adding contacts: "
            + "add NAME [-p PHONE_NUM] [-e EMAIL] [-a ADDRESS] [-t TAG]…\u200B [-b BIRTHDAY]\u200B\n"
            + "Clearing all data: clear\n"
            + "Deleting contacts: "
            + "delete INDEX [INDEX…]\n"
            + "Editing contacts: "
            + "edit INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]…\u200B [-b BIRTHDAY]\n"
            + "Finding contacts: "
            + "find [-n NAME] [-t TAG]\n"
            + "Listing contacts: "
            + "list [-s SORT_ORDER]\n"
            + "Finding tags: "
            + "tags [-f KEYWORD]\n"
            + "Showing help: "
            + "help [COMMAND]\n";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, false);
    }
}
