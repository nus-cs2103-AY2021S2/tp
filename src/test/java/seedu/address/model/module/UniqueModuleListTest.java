package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.exceptions.DuplicateModuleException;
import seedu.address.model.module.exceptions.ModuleNotFoundException;

public class UniqueModuleListTest {
    private final Title title1 = new Title("MOD1");
    private final Title title2 = new Title("MOD2");
    private final Module mod1 = new Module(title1);
    private final Module mod2 = new Module(title2);

    @Test
    public void add() {
        UniqueModuleList uniqueModuleList = new UniqueModuleList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> uniqueModuleList.add(null));

        // add a module
        uniqueModuleList.add(mod1);
        assertEquals(uniqueModuleList.size(), 1);

        // add another event
        uniqueModuleList.add(mod2);
        assertEquals(uniqueModuleList.size(), 2);

        // add duplicate event -> throws
        assertThrows(DuplicateModuleException.class, () -> uniqueModuleList.add(mod2));
    }

    @Test
    public void contains() {
        UniqueModuleList uniqueModuleList = new UniqueModuleList();

        // empty list -> returns false
        assertFalse(uniqueModuleList.contains(mod1));

        // list contains the module -> returns true
        uniqueModuleList.add(mod1);
        assertTrue(uniqueModuleList.contains(mod1));

        // list does not contain the module -> returns false
        assertFalse(uniqueModuleList.contains(mod2));

        // null -> throws
        assertThrows(NullPointerException.class, () -> uniqueModuleList.contains(null));
    }

    @Test
    public void getModule() {
        UniqueModuleList uniqueModuleList = new UniqueModuleList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> uniqueModuleList.getModule(null));

        // module does not exist with empty list -> throws
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.getModule(mod1));

        uniqueModuleList.add(mod1);

        // module does not exist and list is not empty -> throws
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList.getModule(mod2));

        // module exists -> returns true
        assertEquals(mod1, uniqueModuleList.getModule(mod1));
        uniqueModuleList.add(mod2);
        assertEquals(mod2, uniqueModuleList.getModule(mod2));

        // Using index --------------------------------------------------------------------
        UniqueModuleList uniqueModuleList2 = new UniqueModuleList();

        // empty list -> throws
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList2.getModule(0));

        // index < 1 -> throws
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList2.getModule(0));

        uniqueModuleList2.add(mod1);

        // index > size -> throws
        assertThrows(ModuleNotFoundException.class, () -> uniqueModuleList2.getModule(2));

        // get module at index 1 -> returns true
        assertEquals(mod1, uniqueModuleList2.getModule(1));

        uniqueModuleList2.add(mod2);

        // get module at index 2 -> returns true
        assertEquals(mod2, uniqueModuleList2.getModule(2));
    }

    @Test
    public void setModule() {
        UniqueModuleList list1 = new UniqueModuleList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list1.setModule(null, null));
        assertThrows(NullPointerException.class, () -> list1.setModule(null, mod1));
        assertThrows(NullPointerException.class, () -> list1.setModule(mod1, null));

        list1.add(mod1);

        // set mod1 to mod2 for list1
        UniqueModuleList list2 = new UniqueModuleList();
        list2.add(mod2);
        list1.setModule(mod1, mod2);
        assertEquals(list1, list2);

        // duplicate module -> throws
        UniqueModuleList list3 = new UniqueModuleList();
        list3.add(mod1);
        list3.add(mod2);
        assertThrows(DuplicateModuleException.class, () -> list3.setModule(mod1, mod2));
    }

    @Test
    public void remove() {
        UniqueModuleList list1 = new UniqueModuleList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list1.remove(null));

        list1.add(mod1);

        // remove event not found -> throws
        assertThrows(ModuleNotFoundException.class, () -> list1.remove(mod2));

        UniqueModuleList list2 = new UniqueModuleList();
        list2.add(mod1);
        list2.add(mod2);
        list2.remove(mod2);
        assertEquals(list1, list2);
    }

    @Test
    public void setModules() {
        // List<Module> ---------------------------------------------------------------
        UniqueModuleList list1 = new UniqueModuleList();
        UniqueModuleList list2 = new UniqueModuleList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list1.setModules((List<Module>) null));
        assertThrows(NullPointerException.class, () -> list2.setModules((UniqueModuleList) null));

        List<Module> input1 = List.<Module>of(mod1, mod1);

        // set input contains duplicate -> throws
        assertThrows(DuplicateModuleException.class, () -> list1.setModules(input1));

        List<Module> input2 = List.<Module>of(mod1, mod2);
        list1.add(mod1);
        list1.add(mod2);
        list2.setModules(input2);

        // same mods -> returns true
        assertEquals(list1, list2);

        // UniqueModuleList -------------------------------------------------------------
        UniqueModuleList inputUniqueList = new UniqueModuleList();
        inputUniqueList.setModules(List.of(mod1, mod2));
        UniqueModuleList list3 = new UniqueModuleList();

        // null -> throws
        assertThrows(NullPointerException.class, () -> list3.setModules((UniqueModuleList) null));

        // set list3 with inputUniqueList
        list3.setModules(inputUniqueList);
        assertEquals(list3, inputUniqueList);
    }

    @Test
    public void equals() {
        UniqueModuleList list1 = new UniqueModuleList();
        UniqueModuleList list2 = new UniqueModuleList();

        // null -> returns false
        assertFalse(list1.equals(null));

        // different instance -> returns false
        list2.setModules(Collections.singletonList(mod2));
        assertFalse(list2.equals(2));

        // different modules -> returns false
        list1.setModules(Collections.singletonList(mod1));
        list2.setModules(Collections.singletonList(mod2));
        assertFalse(list1.equals(list2));

        // different size -> returns false
        list2.setModules(List.of(mod2, mod1));
        assertFalse(list1.equals(list2));

        // different order -> returns false
        list1.setModules(List.of(mod1, mod2));
        assertFalse(list1.equals(list2));

        // same object -> returns true
        assertTrue(list1.equals(list1));

        // same attributes -> returns true
        list1.setModules(List.of(mod1, mod2));
        list2.setModules(List.of(mod1, mod2));
        assertTrue(list1.equals(list2));
    }
}
