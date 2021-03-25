---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same moduleName as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `TaskListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `TaskTrackerParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the task tracker data.
* exposes an unmodifiable `ObservableList<Task>` that can be 'observed' e.g. the UI can be bound to this list so that
  the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.
* contains DeadlineDate, DeadlineTime, ModuleCode, Remark, Status, TaskName, Weightage classes which serve as
  attributes of the Task class
* Tasks stored have to be unique


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `TaskTracker`, which `Person` references. This allows `TaskTracker` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103-T14-4/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the task tracker data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedTaskTracker`. It extends `TaskTracker` with an undo/redo history, stored internally as an `taskTrackerStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedTaskTracker#commit()` — Saves the current address book state in its history.
* `VersionedTaskTracker#undo()` — Restores the previous address book state from its history.
* `VersionedTaskTracker#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitTaskTracker()`, `Model#undoTaskTracker()` and `Model#redoTaskTracker()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedTaskTracker` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command calls `Model#commitTaskTracker()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `taskTrackerStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitTaskTracker()`, causing another modified address book state to be saved into the `taskTrackerStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitTaskTracker()`, so the address book state will not be saved into the `taskTrackerStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoTaskTracker()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial TaskTracker state, then there are no previous TaskTracker states to restore. The `undo` command uses `Model#canUndoTaskTracker()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoTaskTracker()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `taskTrackerStateList.size() - 1`, pointing to the latest address book state, then there are no undone TaskTracker states to restore. The `redo` command uses `Model#canRedoTaskTracker()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitTaskTracker()`, `Model#undoTaskTracker()` or `Model#redoTaskTracker()`. Thus, the `taskTrackerStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitTaskTracker()`. Since the `currentStatePointer` is not pointing at the end of the `taskTrackerStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_

### \[Proposed\] Priority Tag Feature

#### Proposed Implementation

The proposed priority tag mechanism is facilitated by `PriorityTag` class. It extends the `Tag` class. The priority tag feature will allow using to key in 3 different `Enum` states - `LOW`, `MEDIUM` and `HIGH`. This is to aid user in identifying different CS modules that requires varying amount of attention at any point during the semester.
* include pt/<low/medium/high> input during creation of a task
* include editing of priority tag for existing tasks
* include sorting of existing task according to `LOW`, `MEDIUM` and `HIGH` respectively
* include deletion of priority tag for existing tasks


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

* is a CS student in NUS
* has a need to consolidate and manage deadlines for CS-coded module tasks
* prefer desktop apps over other types of apps
* prefers to use CLI over a GUI
* can type fast
* prefers an all-keyboard workflow without needing to use a mouse

**Value proposition**:

- manage upcoming CS assignment deadlines faster than a typical mouse/GUI driven app
- consolidates all CS-coded module tasks into a single place for a CS student to manage and view easily
- allows a CS student to prioritise his/her time by seeing which task deadlines are more pressing
- the app can also remind a CS student about his/her upcoming deadlines so that he/she does not need to remember everything all at once
- easy CLI operation would entice a CS student to use the app, as he/she probably interacts with CLI on a daily basis while coding/doing CS-coded module tasks


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new CS-coded module task                               | refer to the task in the App |
| `* * *`  | user                                       | delete a task                                                | remove entries that I no longer need                         |
| `* * *`  | user                                       | find a task by its moduleName                                      | locate a particular task in order to view its deadline and relevant details |
| `* * *`  | user                                       | add priority tags (low, medium, high)                        | categorise my tasks and know which ones I should work on first |
| `* * *`  | user who has just finished a task | mark a task as done                                          | know which tasks I have completed                            |
| `* *` | user | add notes to a particular task | jot down quick ideas or notes that I have for a particular task |
| `* *` | user with many tasks in the App | sort tasks either by deadlines, module codes, or their priority tags | have different views of the App when I require them |
| `* *` | user | edit task attributes | change the details of a task if the task requirements or details have changed |
| `*` | user with many upcoming deadlines | be reminded of deadlines that are approaching | remember when my tasks are due and work on them |
| `*` | user who just finished their semester | clear the application of all tasks | prepare for the next upcoming semester |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `TaskTracker` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a deadline**

**MSS**

1.  User requests to list deadlines
2.  TaskTracker shows a list of deadlines
3.  User requests to delete a specific deadline in the list
4.  TaskTracker deletes the deadline

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. TaskTracker shows an error message.

      Use case resumes at step 2.

**Use case: Edit a deadline**

1. User requests to list deadlines
2. TaskTracker shows a list of deadlines
3. User requests to edit a specific deadline in the list
4. TaskTracker updates the specific deadline in the list

    Use case ends.

**Extensions**

* 2a. The list is empty.
    Use case ends.
* 3a. The given index is invalid
    * 3a1. TaskTracker shows an error message.
        Use case resumes at step 2.
* 3b. Optional fields are not provided
    * 3b1. TaskTracker shows an error message

        Use case resumes at step 2.

**Use case: Setting a priority tag**

1. User requests to list deadlines
2. TaskTracker shows a list of deadlines**
3. User requests to set a priority tag on a specific deadline in the list
4. TaskTracker sets a priority tag to the specific deadline in the list

**Extensions**

* 2a. The list is empty.

    Use case ends.
* 3a. The given index is invalid
    * 3a1. TaskTracker shows an error message

      Use case resumes at step 2
* 3b. The given priority tag is invalid
    * 3b1. TaskTracker shows an error message

        Use case resumes at step 2

**Use case: Adding notes to a deadline**

1. User requests to list deadlines
2. TaskTracker shows a list of deadlines**
3. User requests to add a note to a specific deadline in the list
4. TaskTracker adds a note to the specific deadline in the list

**Extensions**

* 2a. The list is empty.

    Use case ends
* 3a. The given index is invalid
    * 3a1. TaskTracker shows an error message

        Use case resumes at step 2
* 3b. The deadline has existing notes
    * 3b1. TaskTracker requests for confirmation to overwrite previous notes
    * 3b2. User confirms the request to overwrite previous notes
    * 3b3. TaskTracker overwrites the previous notes with a new note

        Use case ends




*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  The software should be portable. So moving from one OS to other OS does not create any problem.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The response to any use action should become visible within 2 seconds.
5.  The source code should be open source.
6.  An application should be able to have up to 1000 deadlines.
7.  The user interface should be intuitive enough for users who are not IT-savvy.
8.  The product is free.
9.  The system should work on both `32-bit` and `64-bit` environments.
10. The deadline should not contain tasks deemed offensive (terrorism, bombing, etc).
11. The application can be used **without** internet.

### Glossary

* **CS:** Computer Science
* **CS-coded module tasks:** Assignments, Quizzes, Projects, and other tasks related to CS-coded modules provided by NUS School of Computing that have deadlines for submission
* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
