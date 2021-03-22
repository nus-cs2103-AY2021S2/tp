package seedu.partyplanet.logic.commands;

import seedu.partyplanet.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Here is a list of commands available, "
            + "for more information refer to https://ay2021s2-cs2103-w16-3.github.io/tp/UserGuide.html\n"
            + "Adding contacts: "
            + "add -n NAME [-p PHONE_NUM] [-e EMAIL] [-a ADDRESS] [-t TAG]…\u200B [-b BIRTHDAY]\u200B\n"
            + "Clearing all data: clear\n"
            + "Deleting contacts: "
            + "delete [{INDEX [INDEX]... | -t TAG [-t TAG]...}]\n"
            + "Editing contacts: "
            + "edit {INDEX [-n NAME] [-p PHONE_NUMBER] [-e EMAIL] [-a ADDRESS] [-t TAG]…\u200B [-b BIRTHDAY] "
            + "| --remove -t TAG [-t TAG]...}\n"
            + "Finding contacts: "
            + "find [-n NAME] [-t TAG]\n"
            + "Listing contacts: "
            + "list [-s SORT_ORDER]\n"
            + "Finding tags: "
            + "tags [-f KEYWORD]\n"
            + "Showing help: "
            + "help [COMMAND]\n"
            + "Leaving app: "
            + "exit";

    private final String commandWord;

    /**
     * Creates a HelpCommand to give syntax details for all possible commands
     */
    public HelpCommand() {
        this.commandWord = "";
    }

    /**
     * Creates a HelpCommand to give syntax details for a specified {@code commandWord}
     */
    public HelpCommand(String commandWord) {
        this.commandWord = commandWord;
    }

    @Override
    public CommandResult execute(Model model) {

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new CommandResult(AddCommand.MESSAGE_USAGE, false);

        case EditCommand.COMMAND_WORD:
            return new CommandResult(EditCommand.MESSAGE_USAGE, false);

        case DeleteCommand.COMMAND_WORD:
            return new CommandResult(DeleteCommand.MESSAGE_USAGE, false);

        case ClearCommand.COMMAND_WORD:
            return new CommandResult(ClearCommand.MESSAGE_USAGE, false);

        case FindCommand.COMMAND_WORD:
            return new CommandResult(FindCommand.MESSAGE_USAGE, false);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE, false);

        case EAddCommand.COMMAND_WORD:
            return new CommandResult(EAddCommand.MESSAGE_USAGE, false);

        case EDeleteCommand.COMMAND_WORD:
            return new CommandResult(EDeleteCommand.MESSAGE_USAGE, false);

        case EDoneCommand.COMMAND_WORD:
            return new CommandResult(EDoneCommand.MESSAGE_USAGE, false);

        case EListCommand.COMMAND_WORD:
            return new CommandResult(EListCommand.MESSAGE_USAGE, false);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE, false);

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(HelpCommand.MESSAGE_USAGE, false);

        case TagsCommand.COMMAND_WORD:
            return new CommandResult(TagsCommand.MESSAGE_USAGE, false);

        default:
            return new CommandResult(SHOWING_HELP_MESSAGE, false);
        }
    }


}
