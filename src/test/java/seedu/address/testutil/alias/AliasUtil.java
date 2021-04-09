package seedu.address.testutil.alias;

import seedu.address.commons.core.Alias;
import seedu.address.commons.core.AliasMapping;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.alias.AliasCommand;
import seedu.address.logic.commands.alias.ListAliasCommand;
import seedu.address.logic.commands.alias.UnaliasCommand;
import seedu.address.logic.commands.commandhistory.ViewHistoryCommand;
import seedu.address.logic.commands.issue.AddIssueCommand;
import seedu.address.logic.commands.issue.CloseIssueCommand;
import seedu.address.logic.commands.issue.DeleteIssueCommand;
import seedu.address.logic.commands.issue.EditIssueCommand;
import seedu.address.logic.commands.issue.FindIssueCommand;
import seedu.address.logic.commands.issue.ListIssueCommand;
import seedu.address.logic.commands.resident.AddResidentCommand;
import seedu.address.logic.commands.resident.DeleteResidentCommand;
import seedu.address.logic.commands.resident.EditResidentCommand;
import seedu.address.logic.commands.resident.FindResidentCommand;
import seedu.address.logic.commands.resident.ListResidentCommand;
import seedu.address.logic.commands.resident.ListUnallocatedResidentsCommand;
import seedu.address.logic.commands.residentroom.AllocateResidentRoomCommand;
import seedu.address.logic.commands.residentroom.DeallocateResidentRoomCommand;
import seedu.address.logic.commands.room.AddRoomCommand;
import seedu.address.logic.commands.room.DeleteRoomCommand;
import seedu.address.logic.commands.room.EditRoomCommand;
import seedu.address.logic.commands.room.FindRoomCommand;
import seedu.address.logic.commands.room.ListRoomCommand;
import seedu.address.logic.commands.undoredo.RedoCommand;
import seedu.address.logic.commands.undoredo.UndoCommand;

/**
 * A utility class for Alias.
 */
public class AliasUtil {

    // recursive aliases
    public static final Alias ALIAS_1_TO_2 = new Alias("1", "2");
    public static final Alias ALIAS_2_TO_1 = new Alias("3", "1");

    // valid aliases
    public static final Alias VALID_ALIAS_1 = new Alias("h", "help");
    public static final Alias VALID_ALIAS_2 = new Alias("rls", "rlist");
    public static final Alias VALID_ALIAS_3 = new Alias(
            "pest",
            "iadd r/12-200 d/Cockroach infestation c/Pest");
    public static final Alias VALID_ALIAS_4 = new Alias("pest", "help");

    // alias mapping valid aliases
    public static final AliasMapping VALID_ALIAS_MAPPING;

    private static final String VALID_COMMAND = "validCommand";

    // reserved alias
    public static final Alias ALIAS_HELP = new Alias(HelpCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_HISTORY = new Alias(ViewHistoryCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_EXIT = new Alias(ExitCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_CLEAR = new Alias(ClearCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ALIAS_ADD = new Alias(AliasCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ALIAS_LIST = new Alias(ListAliasCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ALIAS_DELETE = new Alias(UnaliasCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_UNDO = new Alias(UndoCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_REDO = new Alias(RedoCommand.COMMAND_WORD, VALID_COMMAND);

    public static final Alias ALIAS_RESIDENT_ADD = new Alias(AddResidentCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_RESIDENT_LIST = new Alias(ListResidentCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_RESIDENT_FIND = new Alias(FindResidentCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_RESIDENT_EDIT = new Alias(EditResidentCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_RESIDENT_DELETE = new Alias(DeleteResidentCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_RESIDENT_LIST_UNALLOC = new Alias(ListUnallocatedResidentsCommand.COMMAND_WORD,
            VALID_COMMAND);

    public static final Alias ALIAS_ROOM_ADD = new Alias(AddRoomCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ROOM_LIST = new Alias(ListRoomCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ROOM_FIND = new Alias(FindRoomCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ROOM_EDIT = new Alias(EditRoomCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ROOM_DELETE = new Alias(DeleteRoomCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ROOM_ALLOC = new Alias(AllocateResidentRoomCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ROOM_DEALLOC = new Alias(DeallocateResidentRoomCommand.COMMAND_WORD, VALID_COMMAND);

    public static final Alias ALIAS_ISSUE_ADD = new Alias(AddIssueCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ISSUE_LIST = new Alias(ListIssueCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ISSUE_FIND = new Alias(FindIssueCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ISSUE_EDIT = new Alias(EditIssueCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ISSUE_DELETE = new Alias(DeleteIssueCommand.COMMAND_WORD, VALID_COMMAND);
    public static final Alias ALIAS_ISSUE_CLOSE = new Alias(CloseIssueCommand.COMMAND_WORD, VALID_COMMAND);

    static {
        AliasMapping temp = new AliasMapping();
        temp.addAlias(VALID_ALIAS_1);
        temp.addAlias(VALID_ALIAS_2);
        temp.addAlias(VALID_ALIAS_3);
        VALID_ALIAS_MAPPING = temp;
    }
}
