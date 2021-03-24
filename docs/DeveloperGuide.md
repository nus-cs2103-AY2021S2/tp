---
layout: page
title: Developer Guide
---
## Table of Contents

* [Setting up, getting started](#setting-up-getting-started)
* [Design](#design)
    * [Architecture](#architecture)
    * [UI component](#ui-component)
    * [Logic component](#logic-component)
    * [Model component](#model-component)
    * [Storage component](#storage-component)
* [Implementation](#implementation)
    * [Sort feature](#implemented-sort-feature)
* [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
* [Appendix: Requirements](#appendix-requirements)
    * [Product scope](#product-scope)
    * [User stories](#user-stories)
    * [Use cases](#use-cases)
    * [Non-Functional Requirements](#non-functional-requirements)
    * [Glossary](#glossary)
* [Appendix: Instructions for manual testing](#appendix-instructions-for-manual-testing)
    * [Launch and shutdown](#launch-and-shutdown)
    * [Finding flashcards](#finding-flashcards)
    * [Filtering flashcards](#filtering-flashcards)
* [Appendix: Effort](#appendix-effort)
    * [Find feature](#find-feature)
    * [Filter feature](#filter-feature)

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of
each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in
the [diagrams](https://github.com/se-edu/addressbook-level3/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML
Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit
diagrams.

</div>

**`Main`** has two classes
called [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java)
and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java). It
is responsible for,

* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding
  API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and
exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues
the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`
, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are
in the `src/main/resources/view` folder. For example, the layout of
the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
is specified
in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `AddressBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
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
API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the address book data.
* exposes an unmodifiable `ObservableList<Person>` that can be 'observed' e.g. the UI can be bound to this list so that
  the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>
![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>

### Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**
API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,

* can save `UserPref` objects in json format and read it back.
* can save the address book data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Implemented\] Sort feature
The sort mechanism is managed by `ModelManager`. As `Flashcard` contains
`Question` and `Priority`, these are utilised along with the enum `SortOptions` which
comprises comparators needed for respective sort options.

It implements the following operations:

* `ModelManager#SortFilteredCardList(Comparator<Flashcard> cmp)` - Sorts the flashcard list
according to an option and shows the updated list.
* `SortOptions#getOption(String option)` - Returns the enum according to the specified option

The following diagram illustrates how the sort function operates:
![SortSequenceDiagram](images/SortSequenceDiagram.png)

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo
history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the
following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()`
and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the
initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th person in the address book. The `delete` command
calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes
to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book
state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new person. The `add` command also calls `Model#commitAddressBook()`
, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the person was a mistake, and decides to undo that action by executing
the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer`
once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once
to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such
as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`.
Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not
pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be
purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern
desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

![CommitActivityDiagram](images/CommitActivityDiagram.png)

#### Design consideration:

##### Aspect: How undo & redo executes

* **Alternative 1 (current choice):** Saves the entire address book.
    * Pros: Easy to implement.
    * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by itself.
    * Pros: Will use less memory (e.g. for `delete`, just save the person being deleted).
    * Cons: We must ensure that the implementation of each individual command are correct.

### Implementation of Filter feature

The implemented filter mechanism is facilitated by `LogicManager` and `ModelManager`. The user inputs the filter 
command with specified fields (e.g. question, category, priority, and tag), the `LogicManager` receives and parse this 
input, and the `ModelManager` updates FlashBack with a list of filtered flashcards matching all specified fields.

The filter command is executed by parsing the user input through `FlashBackParser#parseCommand(String userInput)`, which 
continues to parse the user input through `FilterCommandParser#parse(String args)`. This creates a new `FilterCommand` 
object that takes in a `FlashcardFilterPredicate` object.

The `FlashcardFilterPredicate` class implements `Predicate<Flashcard>` and takes in:
* `List<String> questions` — List of question keywords to filter by.
* `List<String> categories` — List of category keywords to filter by.
* `List<String> priorities` — List of priority keywords to filter by.
* `List<String> tags` — List of tag keywords to filter by.
  
The filter feature implements the following operations:
* `FilterCommand#execute(Model model)` — executes filter function to update `Model` to display filtered flashcards.
* `FlashcardFilterPredicate#test(Flashcard flashcard)` — Compares the flashcard fields with user specified filter fields
  and returns true only if the flashcard fields matches all the user specified filter fields.
* `ModelManager#updateFilteredFlashcardList(Predicate<Flashcard> predicate)` — Updates the predicate of 
  `FilteredList<Flashcard> filteredFlashcards` field in `ModelManager`.
  
Given below is an example usage scenario and how the filter mechanism behaves at each step.

Step 1. The user launches the application that contains existing flashcards.

Step 2. The user executes `filter q/formula p/mid` command to filter and display all flashcards that have both `formula`
contained in its question field and `mid` in its priority field. The `LogicManager#execute(String commandText)` is
called, which then calls the `FlashBackParser#parseCommand(String userInput)` to parse the user input. This would result
in calling `FilterCommandParser#parse(String args)` to further parse the user input.

Step 3. `FilterCommandParser#parse(String args)` parse the user input, creating four lists of keywords, one for
each specified field. A `FlashcardFilterPredicate` object would be created using these four lists, and this 
`FlashcardFilterPredicate` would be used to create and return a `FilterCommand` object.

Step 4. After returning the `FilterCommand` object to the `LogicManager`, `FilterCommand#execute(Model model)` is called.

Step 5. `FilterCommand#execute(Model model)` then calls 
`ModelManager#updateFilteredFlashcardList(Predicate<Flashcard> predicate)` which will update the predicate of 
`FilteredList<Flashcard> filteredFlashcards` field in `ModelManager`.

Step 6. FlashBack is then updated with the new filtered flashcard list.

The following sequence diagram shows how the filter operation works:
![FilterSequenceDiagram](images/FilterSequenceDiagram.png)
<div markdown="span" class="alert alert-info">

:information_source: **Note:** The lifeline for `FilterCommandParser` and `FilterCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The following activity diagram summarizes what happens when a user executes the filter command:
![FilterActivityDiagram](images/FilterActivityDiagram.png)


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

* student revising for examinations
* has a hard time remembering concepts from different fields of study.
* prefers desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: Offers productive study sessions to students via improved accessibility and organisation of
flashcards, as well as faster management of flashcards compared to a typical mouse/GUI driven app

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                     |I want to …​                     | So that I can…​
| -------- | ------------------------------------------ | ------------------------------ | ----------------------------------------------------------------------
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App
| `* * *`  | new user | use the app with sample data | see how the app look like
| `* * *`  | student                                    | add a new card                 | take down important concepts for future reference
| `* * *`  | long-time user                             | delete an old card             | remove some notes that I no longer need
| `* * *`  | student                                    | view all my notes   | easily refer to all my study materials
| `* * *`  | student                                    | view the question and answer of a specific card | check if I remember the concepts correctly
| `* * * ` | careless user | modify the details of a flash card | avoid having to delete and add a new card when I wish to update card information
| `* *` | multi-discipline student | put my notes under different tags and categories | easily organize them
| `* *`     | angry student   | undo my actions| my emotion will not cloud my judgements
| `* *` | student | review my own performance after each study session | know what to improve on
| `* *` | long-time user | find what I need easily | search through the list of decks without doing it manually
| `* *` | student studying many modules | Filter cards according to subjects | it is easier to learn|
| `*`   | student | export a part of my materials | share it with others
| `*`   | experienced user | define my own aliases for commands | use them faster
| `*`   | chemistry/biology Student | use subscripts | see the chemical formula easier
| `*`   | careless student | archive my notes | easily restore them
| `*`   | student | look at the statistics | focus on topics that I am not good at

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `FlashBack` and the **Actor** is the `user`, unless specified
otherwise) <br /><br />
**Use case: UC01 - Add a flash card**

**MSS**

1. User requests to add a new flash card into the list.
2. FlashBack adds the new flashcard.

   Use case ends.

**Extensions**

* 1a. The given fields are empty.
    * 1a1. FlashBack shows an error message.

      Use case ends.

**Use case: UC02 - Remove a flash card**

**MSS**

1. FlashBack shows a list of flash cards
2. User requests to delete a specific flash card from the list
3. FlashBack deletes the specified flash card

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.
    * 2a1. FlashBack shows an error message.

      Use case resumes at step 1.

**Use case: UC03 - Edit a flash card**

**MSS**

1. FlashBack shows a list of flash cards.
2. User requests to edit a specific flash card on the list.
3. User enters the details of updated flash card.
4. FlashBack updates the flash card with the given details.

   Use case ends

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.
    * 2a1. FlashBack shows an error message.

      Use case resumes at step 1.

* 3a. The updated flash card is a duplicate of an existing card.
    * 3a1. FlashBack shows an error message.

      Use case resumes at step 1.

**Use case: UC04 - View a flash card**

**MSS**

1. FlashBack shows the list of flashcards.
1. User requests to view a specific flashcard on the list.
1. FlashBack shows the requested card.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 2a. The given index is invalid.
    * 2a1. FlashBack shows an error message.

      Use case resumes at step 1.

**Use case: UC05 - Find flashcards**

**MSS**

1. User requests to find flashcards with given keywords.
1. FlashBack shows a list of flashcards matching given keywords.

   Use case ends.

**Extensions**

* 1a. The list is empty.

  Use case ends.

* 1b. The input format is invalid.
    * 1b1. FlashBack shows an error message.

      Use case ends.
    
* 1c. The keywords are empty.
    * 1c1. FlashBack shows an error message.
    
      Use case ends.

**Use case: UC06 - List all flash cards**

**MSS**

1. User requests to list all flash cards.
1. FlashBack shows a list of flash cards.

   Use case ends.

**Use case: UC07 - Undo an undoable command**

**MSS**

1. User requests to delete a flash card from the list.
1. FlashBack deletes the specified flashcard.
1. User requests to undo delete command.
1. FlashBack reverts to its previous state before delete command.

   Use case ends.

**Use case: UC08 - Filter flashcards**

**MSS**

1. User requests to filter flashcards according to keywords of specified fields.
1. FlashBack shows a list of flashcards matching keywords of specified fields.

   Use case ends.

**Extensions**

* 1a. The list is empty.
  
  Use case ends.
  
* 1b. The input format is invalid.
  * 1b1. FlashBack shows an error message.
    
    Use case ends.
    
* 1c. The keywords of specified fields are empty.
  * 1c1. FlashBack shows an error message.
    
    Use case ends.

### Non-Functional Requirements

1. Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2. Should be able to respond to user commands within 3 seconds for up to 1000 cards.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be
   able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Flash card**: A card that contains study materials with its topic name
* **Undoable Command**: A command that modifies the content of FlashBack

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">

:information_source: **Note:** 
These instructions only provide a starting point for testers to work on;
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

1. Exiting the application

    1. Prerequisites: The application is already launched.
    
    1. Test case: `exit`<br>
       Expected: The application exits and window closes.

### Finding flashcards

1. Finding flashcards in FlashBack

    1. Prerequisites: There must be at least one flashcard in the list.
       
    1. Test case: `find`<br>
       Expected: The list will not be updated, and an invalid command format error will be displayed in the result display.
       
    1. Test case: `find equa`<br>
       Expected: The list will be updated, listing the flashcards that have `equa` contained any of its fields (e.g. question, answer, category, priority, tags). The result display will state the number of flashcards listed.
       
    1. Test case: `find newton random`<br>
       Expected: The list will be updated, listing the flashcards that have either `newton` or `random` contained in any of its fields. The result display will state the number of flashcards listed.
       
### Filtering flashcards

1. Filtering flashcards in FlashBack

    1. Prerequisites: There must be at least one flashcard in the list.
    
    1. Test case: `filter`<br>
       Expected: The list will not be updated, and an invalid command format error will be displayed in the result display.
       
    1. Test case: `filter q/newton`<br>
       Expected: The list will be updated, listing the flashcards that have `newton` contained in its question. The result display will state the number of flashcards listed.
       
    1. Test case: `filter q/new p/mid t/formula`<br>
       Expected: The list will be updated, listing the flashcards that have `new` contained in its question, `mid` contained in its priority, and `formula` contained in any of its tags. The result display will state the number of flashcards listed.
       
    1. Test case: `filter c/math physics p/mid`<br>
       Expected: The list will be updated, listing the flashcards that have either `math` or `physics` contained in its question, and `mid` containted in its priority. The result display will state the number of flashcards listed.
       
------------------------------------------------------------------------------------------------------------------------

## **Appendix: Effort**

### Find feature

* Challenge was to use multiple keywords provided by user, look through every field of the flashcard, and displaying any flashcards that have any fields (e.g. question, answer, category, priority, tags) matching any of the provided keywords.
* AB3 have a `find` feature, but it was only able to search by names and did not have the ability to search through all fields.
* Enhanced the feature to allow users to search for flashcards using multiple keywords matching any of its fields.
* With reference from the `find` feature available in AB3, learnt and implemented the feature to search through all fields.

### Filter feature

* Challenge was to parse user input with multiple specified fields (e.g. question, category, priority, tags) regardless of order with multiple keywords, and only displaying flashcards that matched the keywords of all specified fields.
* AB3 did not have a `filter` feature.
* Implemented the `filter` feature to allow users to filter flashcards matching the specified fields with multiple keywords.
* With little reference from the `find` and `add` feature available in AB3, learnt and implemented the feature to match all specified fields.
