package dog.pawbook.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import dog.pawbook.commons.exceptions.IllegalValueException;
import dog.pawbook.model.managedentity.Entity;
import dog.pawbook.model.managedentity.Name;
import dog.pawbook.model.managedentity.program.Program;
import dog.pawbook.model.managedentity.program.Session;
import dog.pawbook.model.managedentity.tag.Tag;
import javafx.util.Pair;

@JsonTypeName(Program.ENTITY_WORD)
public class JsonAdaptedProgram extends JsonAdaptedEntity {
    private final List<JsonAdaptedSession> sessions = new ArrayList<>();
    private final List<Integer> enrolledDogs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedEntity} with the given program details.
     */
    @JsonCreator
    public JsonAdaptedProgram(@JsonProperty("id") Integer id, @JsonProperty("name") String name,
            @JsonProperty("sessions") List<JsonAdaptedSession> sessions,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("enrolledDogs") List<Integer> enrolledDogs) {
        super(id, name, tagged);
        if (enrolledDogs != null) {
            this.enrolledDogs.addAll(enrolledDogs);
        }
        if (sessions != null) {
            this.sessions.addAll(sessions);
        }
    }

    /**
     * Converts a given {@code Entity} into this class for Jackson use.
     */
    public JsonAdaptedProgram(Pair<Integer, Program> idProgramPair) {
        super(idProgramPair);
        Program source = idProgramPair.getValue();
        sessions.addAll(source.getSessions().stream()
                .map(JsonAdaptedSession::new)
                .collect(Collectors.toList()));
        enrolledDogs.addAll(source.getDogIdSet());
    }

    /**
     * Converts this Jackson-friendly adapted program object into the model's {@code Program} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted entity.
     */
    @Override
    public Pair<Integer, Entity> toModelType() throws IllegalValueException {
        CommonAttributes commonAttributes = checkAndGetCommonAttributes();
        final int modelID = commonAttributes.id;
        final Name modelName = commonAttributes.name;
        final Set<Tag> modelTags = commonAttributes.tags;

        final List<Session> sessionList = new ArrayList<>();
        for (JsonAdaptedSession s : sessions) {
            sessionList.add(s.toModelType());
        }
        final Set<Session> modelSessions = new HashSet<>(sessionList);
        final Set<Integer> modelDogs = new HashSet<>(enrolledDogs);

        Program model = new Program(modelName, modelSessions, modelTags, modelDogs);
        return new Pair<>(modelID, model);
    }
}
