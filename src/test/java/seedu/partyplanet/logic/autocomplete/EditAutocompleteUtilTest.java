package seedu.partyplanet.logic.autocomplete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.partyplanet.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.partyplanet.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.partyplanet.model.Model;
import seedu.partyplanet.model.ModelManager;
import seedu.partyplanet.model.UserPrefs;
import seedu.partyplanet.model.person.Person;
import seedu.partyplanet.model.tag.Tag;

public class EditAutocompleteUtilTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void parseCommmand() throws Exception {
        String arguments = " 1 -n -p -e -a -t -b -r";
        EditAutocompleteUtil editAutocompleteUtil = new EditAutocompleteUtil(arguments);

        String returnCommand = editAutocompleteUtil.parse(model);

        Person person = model.getAddressBook().getPersonList().get(0);

        String name = person.getName().fullName;
        String phone = person.getPhone().value;
        String email = person.getEmail().value;
        String address = person.getAddress().value;
        String birthday = person.getBirthday().value;
        String remark = person.getRemark().value;

        Set<Tag> tags = person.getTags();
        String tagsString = EditAutocompleteUtil.getTagsAsAutocompletedString(tags);

        String expectedReturnCommand = "edit 1 -n " + name
            + " -p " + phone
            + " -e " + email
            + " -a " + address
            + " " + tagsString
            + " -b " + birthday
            + " -r " + remark;

        assertEquals(returnCommand, expectedReturnCommand);
    }

}
