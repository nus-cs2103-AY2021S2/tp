package seedu.address.logic.commands;

/**
 * Represents functions involving all the commands
 */
public class Commands {

    public static String[] getAutoCompleteStrings() {
        return new String[]{
                AddCommand.COMMAND_WORD,
                AddDateCommand.COMMAND_WORD,
                AddGroupCommand.COMMAND_WORD,
                AddMeetingCommand.COMMAND_WORD,
                AddPictureCommand.COMMAND_WORD,
                ClearCommand.COMMAND_WORD,
                DeleteCommand.COMMAND_WORD,
                DeleteGroupCommand.COMMAND_WORD,
                DeleteDateCommand.COMMAND_WORD,
                DeleteMeetingCommand.COMMAND_WORD,
                DetailsCommand.COMMAND_WORD,
                EditCommand.COMMAND_WORD,
                ExitCommand.COMMAND_WORD,
                FindCommand.COMMAND_WORD,
                HelpCommand.COMMAND_WORD,
                ListCommand.COMMAND_WORD,
                ThemeCommand.COMMAND_WORD,
                ChangeDebtCommand.COMMAND_WORD_SUBTRACT,
                ChangeDebtCommand.COMMAND_WORD_ADD,
                ViewCommand.COMMAND_WORD
        };
    }
}
