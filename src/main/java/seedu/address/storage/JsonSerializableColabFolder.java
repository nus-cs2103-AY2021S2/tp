package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ColabFolder;
import seedu.address.model.ReadOnlyColabFolder;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * An Immutable ColabFolder that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableColabFolder {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_PROJECTS = "Projects list contains duplicate project(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();
    private final List<JsonAdaptedProject> projects = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableColabFolder} with the given persons.
     */
    @JsonCreator
    public JsonSerializableColabFolder(@JsonProperty("persons") List<JsonAdaptedPerson> persons,
                                       @JsonProperty("projects") List<JsonAdaptedProject> projects) {
        this.persons.addAll(persons);
        this.projects.addAll(projects);
    }

    /**
     * Converts a given {@code ReadOnlyColabFolder} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableColabFolder}.
     */
    public JsonSerializableColabFolder(ReadOnlyColabFolder source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
        projects.addAll(source.getProjectsList().stream().map(JsonAdaptedProject::new).collect(Collectors.toList()));
    }

    /**
     * Converts this CoLAB folder into the model's {@code ColabFolder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public ColabFolder toModelType() throws IllegalValueException {
        ColabFolder colabFolder = new ColabFolder();

        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (colabFolder.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            colabFolder.addPerson(person);
        }

        for (JsonAdaptedProject jsonAdaptedProject : projects) {
            Project project = jsonAdaptedProject.toModelType();
            if (colabFolder.hasProject(project)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PROJECTS);
            }
            colabFolder.addProject(project);
        }

        return colabFolder;
    }

}
