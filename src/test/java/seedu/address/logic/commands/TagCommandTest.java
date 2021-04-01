package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

/** Contains unit tests for TagCommand */
public class TagCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new AppointmentBook(), new UserPrefs());

    @Test
    public void execute_singleTagAppendedToNoTags_success() {
        Person taggedPerson = new PersonBuilder(model.getFilteredPersonList().get(2)).withTags("formTeacher").build();
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag("formTeacher"));
        TagCommand tagCommand = new TagCommand(INDEX_THIRD_PERSON, tagsToAdd);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_PERSON_SUCCESS, taggedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new AppointmentBook(),
                new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(2), taggedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_replaceTagsWithSingleTag_success() {
        Person taggedPerson = new PersonBuilder(model.getFilteredPersonList().get(1))
                .withTags("englishTeacher").build();
        Set<Tag> tagsToReplace = new HashSet<>();
        tagsToReplace.add(new Tag("englishTeacher"));
        TagCommand tagCommand = new TagCommand(INDEX_SECOND_PERSON, tagsToReplace, true);

        String expectedMessage = String.format(TagCommand.MESSAGE_TAG_REPLACE_PERSON_SUCCESS, taggedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new AppointmentBook(),
                new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), taggedPerson);

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }
}
