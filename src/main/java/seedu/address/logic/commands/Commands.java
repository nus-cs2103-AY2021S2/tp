package seedu.address.logic.commands;

/**
 * Represents functions involving all the commands
 */
public class Commands {

    public static String[] getAutoCompleteStrings() {

        // In alphabetical order for neatness
        return new String[]{
                AddCommand.COMMAND_WORD,
                AddDateCommand.COMMAND_WORD,
                AddGroupCommand.COMMAND_WORD,
                AddMeetingCommand.COMMAND_WORD,
                AddPictureCommand.COMMAND_WORD,
                ChangeDebtCommand.COMMAND_WORD_ADD,
                ChangeDebtCommand.COMMAND_WORD_SUBTRACT,
                ClearCommand.COMMAND_WORD,
                DeleteCommand.COMMAND_WORD,
                DeleteDateCommand.COMMAND_WORD,
                DeleteGroupCommand.COMMAND_WORD,
                DeleteMeetingCommand.COMMAND_WORD,
                DeletePictureCommand.COMMAND_WORD,
                DetailsCommand.COMMAND_WORD,
                EditCommand.COMMAND_WORD,
                ExitCommand.COMMAND_WORD,
                FindCommand.COMMAND_WORD,
                HelpCommand.COMMAND_WORD,
                ListCommand.COMMAND_WORD,
                SetGoalCommand.COMMAND_WORD,
                ThemeCommand.COMMAND_WORD,
                ViewCommand.COMMAND_WORD
        };
    }
}
