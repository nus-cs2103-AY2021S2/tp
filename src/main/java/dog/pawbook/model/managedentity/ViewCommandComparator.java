package dog.pawbook.model.managedentity;

import java.util.Comparator;

import dog.pawbook.model.managedentity.dog.Dog;
import dog.pawbook.model.managedentity.owner.Owner;
import dog.pawbook.model.managedentity.program.Program;
import javafx.util.Pair;

public class ViewCommandComparator implements Comparator<Pair<Integer, Entity>> {

    private final Class<? extends Entity> firstClass;
    private final Class<? extends Entity> secondClass;
    private final Class<? extends Entity> thirdClass;

    public ViewCommandComparator(Class<? extends Entity> targetEntityClass) {
        this.firstClass = targetEntityClass;
        if (targetEntityClass.equals(Dog.class)) {
            this.secondClass = Owner.class;
            this.thirdClass = Program.class;
        } else if (targetEntityClass.equals(Owner.class)) {
            this.secondClass = Dog.class;
            this.thirdClass = Program.class;
        } else {
            this.secondClass = Dog.class;
            this.thirdClass = Owner.class;
        }
    }

    @Override
    public int compare(Pair<Integer, Entity> firstPair, Pair<Integer, Entity> secondPair ) {

        Class firstEntityClass = firstPair.getValue().getClass();
        Class secondEntityClass = secondPair.getValue().getClass();

        int firstEntityPriority;
        int secondEntityPriority;

        if (firstEntityClass.equals(firstClass)) {
            firstEntityPriority = 0;
        } else if (firstEntityClass.equals(secondClass)) {
            firstEntityPriority = 1;
        } else {
            firstEntityPriority = 2;
        }

        if (secondEntityClass.equals(firstClass)) {
            secondEntityPriority = 0;
        } else if (secondEntityClass.equals(secondClass)) {
            secondEntityPriority = 1;
        } else {
            secondEntityPriority = 2;
        }

        return firstEntityPriority - secondEntityPriority;

    }
}
