package seedu.dictionote.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_ALL_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_CONTACT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_DICTIONARY_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_CONTENT_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_LIST_PANEL;
import static seedu.dictionote.logic.parser.CliSyntax.OPTION_NOTE_PANEL;

import seedu.dictionote.logic.commands.enums.UiAction;
import seedu.dictionote.logic.commands.enums.UiActionOption;
import seedu.dictionote.model.Model;


/**
 * Opens a specific UI panel specify by the user.
 */
public class OpenCommand extends Command {

    public static final String COMMAND_WORD = "open";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Open UI Panel.\n"
        + "Option Available: "
        + OPTION_ALL_PANEL + ", "
        + OPTION_CONTACT_PANEL + ", "
        + OPTION_DICTIONARY_CONTENT_PANEL + ", "
        + OPTION_DICTIONARY_LIST_PANEL + ", "
        + OPTION_DICTIONARY_PANEL + ", "
        + OPTION_LIST_PANEL + ", "
        + OPTION_NOTE_CONTENT_PANEL + ", "
        + OPTION_NOTE_LIST_PANEL + ", "
        + OPTION_NOTE_PANEL + ".\n"
        + "Example: " + COMMAND_WORD + " -c";

    public static final String SHOWING_OPEN_MESSAGE = "Panel opened.";


    private final UiActionOption uiActionOption;

    /**
     * Creates an OpenCommand of a the specified UI Action Option.
     *
     * @param uiActionOption UI action option for UI.
     */
    public OpenCommand(UiActionOption uiActionOption) {
        requireNonNull(uiActionOption);

        this.uiActionOption = uiActionOption;
    }


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_OPEN_MESSAGE, UiAction.OPEN, uiActionOption);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof OpenCommand // instanceof handles nulls
            && uiActionOption.equals(((OpenCommand) other).uiActionOption));
    }
}
