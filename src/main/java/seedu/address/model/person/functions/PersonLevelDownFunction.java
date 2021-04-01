package seedu.address.model.person.functions;

import java.util.function.UnaryOperator;

import seedu.address.model.person.Person;
import seedu.address.model.person.level.Level;



public class PersonLevelDownFunction implements UnaryOperator<Person> {
    public PersonLevelDownFunction() {}
    @Override
    public Person apply(Person p) {
        /*
        Optional<Level> newLevel;
        if (p.getLevel().isPresent()) {
            newLevel = Optional.of(Level.levelUp(p.getLevel().get()));
        } else {
            newLevel = Optional.empty();
        }
        return Level.changeLevel(p, newLevel);
        */
        // TODO: change to an immutable implementation; this is a temporary immplementation
        Person newPerson = Level.clonePerson(p);
        newPerson.getLevel().ifPresent(x -> x.levelDown());
        return newPerson;

    }

}
