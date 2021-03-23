---
layout: page
title: Developer Guide
---

* Table of Contents {:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

![Architecture Diagram of StoreMando](images/ArchitectureDiagram.png)

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of
each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/AY2021S2-CS2103T-W10-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

**`Main`** has two classes
called [`Main`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/Main.java)
and [`MainApp`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/MainApp.java).
It is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component) The UI of the App.
* [**`Logic`**](#logic-component) The command executor.
* [**`Model`**](#model-component) Holds the data of the App in memory.
* [**`Storage`**](#storage-component) Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding
  API `interface` mentioned in the previous point).

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

![Sequence Diagram of the Architecture](images/ArchitectureSequenceDiagram.png)

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/logic/Logic.java)

1. `Logic` uses the `StoreMandoParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding an item).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying
   help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API
call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**
API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the storemando data.
* exposes an unmodifiable `ObservableList<Item>` that can be 'observed' e.g. the UI can be bound to this list so that
  the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**
API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W10-2/tp/blob/master/src/main/java/seedu/storemando/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the StoreMando data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.storemando.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedStoreMando`. It extends `StoreMando` with undo/redo
history, stored internally as an `storeMandoStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedStoreMando#commit()` — Saves the current location book state in its history.
* `VersionedStoreMando#undo()` — Restores the previous location book state from its history.
* `VersionedStoreMando#redo()` — Restores a previously undone location book state from its history.

These operations are exposed in the `Model` interface as `Model#commitStoreMando()`, `Model#undoStoreMando()`
and `Model#redoStoreMando()` respectively.

Given below is an example usage scenario and how undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedStoreMando` will be initialized with the
initial location book state, and the `currentStatePointer` pointing to that single location book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th item in the inventory. The `delete` command
calls `Model#commitStoreMando()`, causing the modified state of the inventory after the `delete 5` command executes to
be saved in the `storeMandoStateList`, and the `currentStatePointer` is shifted to the newly inserted inventory state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new item. The `add` command also calls `Model#commitStoreMando()`,
causing another modified inventory state to be saved into the `storeMandoStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitStoreMando()`, so the location book state will not be saved into the `storeMandoStateList`.

</div>

Step 4. The user now decides that adding the item was a mistake, and decides to undo that action by executing the `undo`
command. The `undo` command will call `Model#undoStoreMando()`, which will shift the `currentStatePointer` once to the
left, pointing it to the previous inventory state, and restores the inventory to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial StoreMando state, then there are no previous StoreMando states to restore. The `undo` command uses `Model#canUndoStoreMando()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoStoreMando()`, which shifts the `currentStatePointer` once to
the right, pointing to the previously undone state, and restores the location book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `storeMandoStateList.size() - 1`, pointing to the latest location book state, then there are no undone StoreMando states to restore. The `redo` command uses `Model#canRedoStoreMando()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the location book, such
as `list`, will usually not call `Model#commitStoreMando()`, `Model#undoStoreMando()` or `Model#redoStoreMando()`. Thus,
the `storeMandoStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitStoreMando()`. Since the `currentStatePointer` is not
pointing at the end of the `storeMandoStateList`, all location book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire location book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the item being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* residents in a household
* has a need to manage a significant number of household items
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: A centralized inventory manager that helps to track the location of items in a household, their
individual quantities and respective expiry dates.

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​ | So that I can…​ | 
| -------- | -----------| ----------------| -------------------| 
| `* * *`  | user | delete an item | remove it when it is expired or used up | | `* * *`  | impulsive buyer | add a new item | keep track of it | 
| `* * *`  | user with many items | list down all items | know in one glance all the items I have | 
| `* * *`  | forgetful user with many items | search for an item quickly | locate them easily | 
| `*`      | user who stocks up items daily | update my items | change the items' expiry dates and quantities accordingly | 
| `* *`    | user who likes to tidy up my room | see all items in the same location | see what items I have in that particular location | 
| `* *`    | user who tags my items meaningfully | see all items with the same tag | see what items I have with that particular tag |
| `* *`    | forgetful user | be aware of my expiring items | replace them before it is expired |

_**(more to be added)**_

### Use cases

(For all use cases below, the **System** is the `StoreMando` and the **Actor** is the `user`, unless specified
otherwise)

**Use case: UC1 - Add an item**

**MSS**

1. User requests for the storage list.
2. StoreMando displays the storage list.
3. User input the add command with the item details.
4. StoreMando adds the item into the storage and displays the updated list.

   Use case ends.

* 3a. The command has invalid date-time format.

    * 3a1. StoreMando shows an error message.

    * 3a2. StoreMando prompt the user for a correct input.

      Use case resumes at step 3.

**Use case: UC2 - Delete an item in a specific area**

**MSS**

1. User wants to <u> list all items in a specific location (UC3). </u>
2. StoreMando displays all items in the location.
3. User deletes the item in the location.
4. StoreMando displays the updated storage.

   Use case ends.

**Extensions**

* 3a. The item details don't match any item in the storage.

    * 3a1. StoreMando shows an error message.

    * 3a2. StoreMando prompt the user for a correct input.

      Use case resumes at step 3.

* 3b. The command has an out-of-range index.

    * 3b1. StoreMando shows an error message.

    * 3b2. StoreMando prompt the user for a correct input.

      Use case resumes at step 3.

**Use case: UC3 - List all items in a specific location**

**MSS**

1. User requests to display all items in that specific location.
2. StoreMando displays all items in that specific location, can be 0 item.

   Use case ends.

**Use case: UC4 - Find an item**

**MSS**

1. User wants to find a particular item with the item’s name.
2. StoreMando returns a list of all items whose name contains the given keyword.

   Use case ends.

**Use case: UC5 - Update an item**

**MSS**

1. User finds a <u>specific item (UC4)</u> that he wants to update.
2. StoreMando returns a list of all items whose name contains the given keyword.
3. User requests to update that particular item with the new values.
4. StoreMando updates and stores the new data, then displays it.

   Use case ends.

**Extensions**

* 3a. The item details do not match any item in the storage.

    * 3a1. StoreMando shows an error message.

    * 3a2. StoreMando prompt the user for a correct input.

      Use case resumes at step 3.

**Use case: UC6 - Check for expiring items**

**MSS**

1. User wants to look for items that are expiring soon.
2. StoreMando returns a list of expiring items.

   Use case ends.

**Use case: UC7 - List all items**

**MSS**

1. User requests to display all items.
2. StoreMando displays all items, can be 0 item.

   Use case ends.

**Use case: UC8 - List all items with a specific tag**

**MSS**

1. User requests to display all items with that specific tag.
2. StoreMando displays all items with that specific tag, can be 0 item.

   Use case ends.

*{More to be added}*

### Non-Functional Requirements

1. Should be able to hold up to 1000 line items in the house without any sluggish performance for typical usage.
2. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be
       optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting an item

1. Deleting an item while all items are being shown

    1. Prerequisites: List all household items using the `list` command. Multiple items in the list.

    1. Test case: `delete 1`<br>
       Expected: First item is deleted from the list. Details of the deleted item shown in the status message.

    1. Test case: `delete 0`<br>
       Expected: No item is deleted. Error details shown in the status message.

    1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Adding an item

1. Adding an item to StoreMando

    1. Prerequisites: Arguments are valid and compulsory parameters are provided. No duplicate item or similar item 
       exists in the list.

    1. Test case: `add n/Banana q/1 l/kitchen e/2020-10-10 `<br>
       Expected: Item is added into the displayed list. Details of the added item shown in the status message.

    1. Test case: `add `<br>
       Expected: No item is added. Error details shown in the status message.

    1. Other incorrect delete commands to try: `add n/`, `add l/kitchen`, `...` (where compulsory fields are not specified)<br>
       Expected: Similar to previous.

       
### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
