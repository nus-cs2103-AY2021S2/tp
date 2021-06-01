---
layout: page
title: Developer Guide
---

<style>
code {
    padding: 1px 1px;
}
</style>

* Table of Contents
{:toc}

<div style="page-break-after: always"></div>

## **Setting up, Getting Started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/docs/diagrams/) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point).

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `deleteP 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI Component

![Structure of the UI Component](images/UiClassDiagram.png)

**API**:
[`Ui.java`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `ContactListPanel`, `StatusBarFooter`, etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses the JavaFX UI framework. The layouts of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic Component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API**:
[`Logic.java`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `ColabParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a project).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object also contains a `UiCommand` object, which encapsulates information needed to instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("deleteP 1")` API call.

![Interactions Inside the Logic Component for the `deleteP 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteProjectCommandParser`  and `DeleteProjectCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

### Model Component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API**: [`Model.java`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user's preferences.
* stores the `ColabFolder` data, which contains data of contacts and projects.
* exposes various `ObservableList`s that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other components.

#### Inner Workings

![Structure of the Contact Component](images/ContactClassDiagram.png)


A `Contact` stores a `Name`, `Email`, `Phone` number, `Address` and zero or more `Tag`s.

![Structure of the Project Component](images/ProjectClassDiagram.png)

A `Project` stores an `EventList`, `DeadlineList`, `TodoList` and a `GroupmateList`. The `EventList`, `DeadlineList`, `TodoList` and `GroupmateList` stores zero or more `Repeatable`, `CompletableDeadline`, `CompletableTodo` and `Groupmate` objects respectively.

### Storage Component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API**: [`Storage.java`](https://github.com/AY2021S2-CS2103T-T11-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in JSON format and read them back.
* can save the user's data in JSON format and read it back.

### Common Classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always"></div>

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### View Projects Feature

This section explains the implementation of the View Project feature. The implementation of other commands that open panels or tabs are similar.

The `ViewProject` command results in the UI displaying the specified project together with all its related information.

The mechanism to issue the command to display a new project is facilitated by `ViewProjectUiCommand`, a concrete implementation of the `UiCommand` abstract class, which encapsulates the project `Index` as well as the logic that determines which methods to call in the `MainWindow`.

Given below is an example usage scenario and how the mechanism behaves at each step.

![View Project Sequence Diagram](images/ViewProjectCommandSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ViewProjectCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

<div style="text-align: left">

**Step 1.** The user issues the command `project 1` to display a panel containing information about the first project in the project list.

**Step 2.** A `CommandResult` object is created (see the section on [Logic Component](#logic-component)) containing a `ViewProjectUiCommand` object. The `ViewProjectUiCommand` object stores the `Index` of the first project in the project list.

**Step 3.** The `CommandResult` is passed to the `MainWindow`, which gets the `UiCommand` by calling `CommandResult#getUiCommand`.

**Step 4.** The `MainWindow` now calls `UiCommand#execute`, which will result in a call to the overridden method `ViewProjectUiCommand#execute`.

**Step 5.** Execution of this method will result in a call to `MainWindow#selectProject` with the `Index` of the first project as an argument. This will display the first project in the project list.

</div>

#### Design Considerations

##### Aspect: How to Store and Pass Around UI Related Instructions

<div style="text-align: left">

* **Alternative 1 (current choice):** Encapsulate instructions using `UiCommand` Object.
    * Pros:
        * Design allows behavior of `UI` to be extended without (or with minimal) changes to the `MainWindow` and `CommandResult`. This makes it relatively easy to add many `UiCommand`s.
        * `UiCommand` encapsulates all information needed to execute the instruction (e.g. `Index` of the project). It is easy to add new commands that store different types of information.
        * Easy to support complex `UiCommand`s that perform multiple instructions or contain more complex logic.

    * Cons:
        * Many classes required.
        * `MainWindow` and `UiCommand` are highly coupled, as `MainWindow` both invokes the command and performs the requested action.

* **Alternative 2 (implementation used in AB3):** Store instructions in `CommandResult` as boolean fields.
    * Pros:
        * Easy to implement.
        * Minimal changes needed if the new instruction is a combination of already existing instructions as the already existing boolean fields can be set to true.
        * No need for extra classes.
    * Cons:
        * `MainWindow` and `CommandResult` are not closed to modification. A new instruction to change the UI might require the addition of fields to `CommandResult` (boolean fields for instructions and other fields for related data) as well as a new conditional statement in `MainWindow#execute` to handle the new instruction. This makes it relatively difficult to add new instructions.

</div>

### Add Event Feature

This section explains the mechanism used to add an `Event` to a `Project`. The mechanism for adding `Project`s, `Deadline`s, `Todo`s and `Contact`s are similar.

The `AddEventCommand` results in the specified event being added to the application. This command requires a compulsory field Project Index to specify which project the event is to be added to.

When the command is executed, a concrete `AddEventCommand` is created containing the specified Project Index and a valid `Event` object.

The `AddEventCommand` implements `AddEventCommand#execute` method, which calls the appropriate methods in `Project` to update its `EventList` and appropriate methods in `Model` to update the Project List.

Below is a sequence diagram and explanation of how the `AddEventCommand` is executed.

![Add Event Sequence Diagram](images/AddEventCommandSequenceDiagram.png)

<div style="text-align: left">

**Step 1.** The user executes the command `addE 1 d/Project meeting on/24-04-2021 at/1730 w/Y`.

**Step 2.** User input is passed to the `ColabParser` and `ColabParser` will call `ÀddEventCommandParser#parse`, which creates a new `AddEventCommand`.

**Step 3.** The `AddEventCommand` will then be executed by calling its `execute` method.

**Step 4.** Since the `Model` is passed to `AddEventCommand#execute`, it is able to call a method `Model#getFilteredProjectList` to get the last project list shown.

**Step 5.** From this project list, we can find the correct `Project` to add `Event` by calling `get` function with a specified `Index`.

**Step 6.** This `Project` will add the `Event` to its `EventList` by calling `addEvent` function.

**Step 7.** After the `Event` is successfully added, the `Model` will call `Model#updateFilteredProjectList` to update the Project List based on the current change.

</div>

#### Design Considerations

##### Aspect: How to Add a New `Event` to a `Project`

<div style="text-align: left">

* **Alternative 1 (current choice):** `Project` tells its `EventList` to update the list of Events stored.
    * Pros:
        * This implementation requires no additional time and space (for the creation of new `Project` and `EventList` object).
    * Cons:
        * This implementation will not work with an immutable implementation of `EventList`.

* **Alternative 2:** A new `Project` object is initialized with a new `EventList` object containing the added `Event`.
    * Pros:
        * If the implementation of `EventList` becomes immutable, this implementation still works.
    * Cons:
        * This implementation requires more time and space (for the creation of new `Project` and `EventList` objects).

</div>

### Update Feature

CoLAB has several update commands for `Project`s, `Contact`s, `Event`s, `Deadline`s, `Todo`s and `Groupmate`s respectively. They are used to update details of entities that have already been created.

Below is a sequence diagram of how an `updateP` (update project) command is executed.

![UpdateP command sequence diagram](images/UpdateProjectCommandSequenceDiagram.png)

<div style="text-align: left">

**Step 1.** The user types an update project command `updateP 1 n/Group Project`.

**Step 2.** User input is passed to the `colabParser`, which creates a new `UpdateProjectCommand`.

**Step 3.** The `UpdateProjectCommand` will then be executed by calling its `execute` method.

**Step 4.** Since the `ModelManager` is passed to `UpdateProjectCommand#execute`, it is able to call a method `ModelManager#setProject` to change an existing project of a given `Index` in the `ProjectsFolder` to the modified version.

**Step 5.** After the project gets updated, `Model#saveProjectsFolder` is called to save the list of projects to files.

</div>

The other commands for `Event`s, `Deadline`s, `Todo`s and `Groupmate`s require some more work because these are sub-components of a `Project`. It is therefore necessary to specify a project in the command so that edits can be applied to that project. Below is a sequence diagram of how an `updateG` (update groupmate) command is executed.

![UpdateP command sequence diagram](images/UpdateGroupmateCommandSequenceDiagram.png)

<div style="text-align: left">

**Step 1.** The user types an update groupmate command `updateG 1 i/1 n/Sylphiette Greyrat`.

**Step 2.** User input is passed to the `colabParser`, which creates a new `UpdateGroupmateCommand`.

**Step 3.** The `UpdateGroupmateCommand` will then be executed by calling its `execute` method.

**Step 4.** It will then get the list of projects through `Model#getFilteredProjectList`, and use the project `Index` to get the project to be updated.

**Step 5.** It will then call a method `Project#setGroupmate` to change an existing groupmate of a given `Index` in the `GroupmateList` to the modified version.

**Step 6.** After the project gets updated, `Model#saveProjectsFolder` is called to save the list of projects to files.

</div>

#### Design consideration:

##### Aspect: How the Target Contact is Specified When Updating Contacts

<div style="text-align: left">

* **Alternative 1 (current choice):** Pass the `Index` object down to `UniqueContactList#setContact`.
    * Pros: More Consistent in how to pass indexes and locate an element in a `List` throughout the codebase.
    * Cons: Higher coupling since `UniqueContactList` now relies on `Index`.

* **Alternative 2 (implementation used in AB3):** Pass the target `Contact` object to be edited to `UniqueContactList#setContact`.
    * Pros: Lower coupling since `Index` is not a dependency of `UniqueContactList`.
    * Cons: Extra computation of index from the `Contact` object since the index is already provided in the command. Passing the original project around does not provide more information than passing only the index.

* **Alternative 3:** Pass the zero-based index as an integer down to `UniqueContactList#setContact`.
    * Pros: Will use less memory (only needs memory for an integer instead of a `Contact` object or an `Index` object), no reliance on `Index`.
    * Cons: May be confusing for new developers since some other parts of the code use one-based indexes instead.

</div>

### Delete Todo Feature

This section explains the implementation of the Delete Todo feature. As the implementation of deleting Deadlines, Events and Groupmates are similar, this section will focus only on the implementation of the deletion of Todos.

The `DeleteTodoCommand` results in the specified todo being removed from the application. This command requires two compulsory fields Project Index & Todo Index to specify which project the todo is to be deleted from.

This is done through the use of the `ParserUtil#parseIndex` method inside the `seedu.address.logic.parser` package, which checks and extracts the index field from the provided command string. As depicted in the sequence diagram below, the `ParserUtil#parseIndex` method is called twice for Project Index & Todo Index respectively.

If the provided project index and todo index are valid, then `DeleteTodoCommandParser` creates a `DeleteTodoCommand` object. The sequence diagram below shows how the `DeleteTodoCommand` object is created.

For a better understanding, take a look at the Logic Class Diagram in the [Logic Component](#logic-component) section of the DG where you can see `DeleteTodoCommandParser` being represented as `XYZCommandParser`.

![Delete Todo Parser Sequence Diagram](images/DeleteTodoParserSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteTodoCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

The `DeleteTodoCommand` has been successfully created and its execute method would be called by `LogicManager#execute`, which was called by `MainWindow#executeCommand`.

Depicted below is another sequence diagram that shows the interaction between `StorageManager`, `ModelManager`, `LogicManager` and `DeleteTodoCommand`, when `DeleteTodoCommand#execute` is called.

![Delete Todo Sequence Diagram](images/DeleteTodoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteTodoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of the diagram.
</div>

As shown, the original todo in CoLAB's Model Component has been deleted. Moreover, the updated list of todos has been saved to the Storage Component of CoLAB. As the operation comes to an end, the `CommandResult` object returned is used for UI purposes, where a message is displayed to the user to inform him/her about the status of their input command and the deleted todo.

With this, the Delete Todo command finishes executing and CoLAB's UI displays the status messages for the user to see.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always"></div>

## **Documentation, Logging, Testing, Configuration, DevOps**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always"></div>

## **Appendix A: Requirements**

### Product Scope

**Target user profile**:

Our target users are students currently enrolled in a university who,

* need to manage school projects
* prefer using desktop apps over other types
* can type fast and prefer typing to mouse interactions
* are reasonably comfortable using CLI apps

**Value proposition**:

* Organize information by projects
    * Unlike other similar applications, CoLAB organizes our users' tasks by project rather than by week. We believe this is better as students are likely already familiar with their weekly schedule. Grouping it by project allows us to give more emphasis to project tasks and deadlines rather than weekly recurring classes.

* Minimalistic and Designed for Students
    * CoLAB is designed to be simple and clutter-free. We only add features students are likely to use and use terms that are appropriate for students. This improves the user experience for students.

* Faster compared to other applications
    * Users who are comfortable using a CLI can potentially do their project management tasks much faster than traditional applications as they can do everything from the keyboard.

### User Stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​ | I want to …​ | So that I can…​ |
| -------- | ---------- | --------------- | ------------------ |
| `* * *` | University Student | add a new project| keep track of my projects |
| `* * *` | University Student | edit a project's details | update the project's details when there is a change |
| `* * *` | University Student | delete a project | remove projects that I no longer need |
| `* * *` | University Student | view a project | see details of that project |
| `* * *` | University Student | view the overview of a project | see my deadlines, events and groupmates of that project |
| `* * *` | University Student | view todos of a project| see my todos of that project |
| `* * *` | University Student | add a new todo to a project| keep track of my project's todos |
| `* * *` | University Student | delete a todo from a project | remove todos that I no longer need |
| `* * *` | University Student | edit a todo's details| update the todo's details when there is a change |
| `* * *` | University Student | mark a todo as done| know when a todo is done |
| `* * *` | University Student | add a new deadline to a project| keep track of my project's deadline |
| `* * *` | University Student | delete a deadline from a project | remove deadlines that I no longer need |
| `* * *` | University Student | edit a deadline's details| update the deadline's details when there is a change |
| `* * *` | University Student | mark a deadline as done| know when a deadline is done |
| `* * *` | University Student | keep track of a deadline's date| know when the deadline is due |
| `* * *` | University Student | add a new event to a project| keep track of my project's events |
| `* * *` | University Student | delete an event from a project | remove events that I no longer need |
| `* * *` | University Student | edit an event's details| update the event's details when there is a change |
| `* *` | University Student | indicate an event as weekly| have events that repeat weekly |
| `* * *` | University Student | keep track of an event's date| know when the event is |
| `* * *` | University Student | add a new groupmate to a project | keep track of my project's groupmates |
| `* * *` | University Student | delete a groupmate from a project| remove groupmates that I no longer need |
| `* * *` | University Student | edit a groupmate's details | update the groupmate's details when there is a change |
| `* * *` | University Student | add roles to a groupmate | easily keep track of the groupmate's roles |
| `* * *` | University Student | add a new contact| keep track of details from peers I have crossed paths with |
| `* * *` | University Student | edit a contact's details | update the contact's details when there is a change |
| `* * *` | University Student | delete a contact | remove contacts that I no longer need |
| `* * *` | University Student | find a contact by name | locate details of contacts without having to go through an entire list |
| `* * *` | University Student | tag a contact with tags| easily keep track of who the contact is |
| `* * *` | University Student | view all my contacts | see the overall contents of my contact list |
| `* *` | University Student | undo an action made in the app | revert an unintended action |
| `* *` | University Student | redo an action made in the app | redo an action that was previously undone |
| `* *` | University Student | clear all data in the app| start my application from fresh |

### Use Cases

For all use cases below, the **System** is `CoLAB` and the **Actor** is the `user`, unless specified otherwise.

(Similar use cases are grouped together)

<ul id="use-cases-toc"></ul>
<script>
function getLastH4Sibling(element) {
    while (element && element.tagName != document.createElement('h4').tagName) {
        element = element.previousElementSibling;
    }
    return element;
}
document.addEventListener('DOMContentLoaded', () => {
    // get all use case headings as an array
    const usecase_h5s = Array.prototype.slice.call(
        document.getElementsByTagName("h5")
    ).filter(h => h.textContent.startsWith('UC'));
    const categories = {};
    const category_links = {};
    for (const usecase_h5 of usecase_h5s) {
        const category = getLastH4Sibling(usecase_h5);
        if (!(category.textContent in categories)) {
            categories[category.textContent] = [];
            category_links[category.textContent] = '#' + category.getAttribute('id');
        }
        categories[category.textContent].push({
            'usecase_text': usecase_h5.textContent,
            'usecase_link': '#' + usecase_h5.getAttribute('id')
        });
    }
    for (const category_text of Object.keys(categories)) {
        const category_link = category_links[category_text];
        const category_li = document.createElement('li');
        category_li.innerHTML = `<a href="${category_link}">${category_text}</a>`;
        category_ul = document.createElement('ul');
        for (const usecase of categories[category_text]) {
            const usecase_li = document.createElement('li');
            usecase_li.innerHTML = `<a href="${usecase.usecase_link}">${usecase.usecase_text}</a>`;
            category_ul.appendChild(usecase_li);
        }
        category_li.appendChild(category_ul);
        document.getElementById('use-cases-toc').appendChild(category_li);
    }
});
</script>

#### Add Entries

##### UC1 - Add a Project/Contact

**MSS**

1. User requests to add a project/contact.
2. CoLAB adds the project/contact.

   Use case ends.

**Extensions**

* 1a. The given name argument is invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 2a. User decides to undo the add action.

    * 2a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

##### UC2 - Add a Todo/Deadline/Event/Groupmate to a Project

**MSS**

1. User switches to the project panel of a specific project.
2. User lists all todos/deadlines/events/groupmates under the project.
3. User requests to add a todo/deadline/event/groupmate to the project.

   Use case ends.

**Extensions**

* 1a. The given project index is invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 3a. The given arguments are invalid.

    * 2a1. CoLAB shows an error message.

      Use case resumes at step 3.

* 4a. User decides to undo the add action.

    * 4a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

#### Delete Entries

##### UC3 - Delete a Project/Contact

**MSS**

1. User requests to delete a specific project/contact in the list of projects/contacts.
2. CoLAB deletes the project/contact.

   Use case ends.

**Extensions**

* 1a. The given project index is invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 2a. User decides to undo the delete action.

    * 2a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

##### UC4 - Delete a Todo/Deadline/Event/Groupmate From a Project

**MSS**

1. User switches to the project panel of a specific project.
2. User lists all todos/deadlines/events/groupmates under the project.
3. User requests to delete a specific todo/deadline/event/groupmate in the list.
4. CoLAB deletes the todo/deadline/event/groupmate.

   Use case ends.

**Extensions**

* 1a. The given project index is invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 2a. The list of todos/deadlines/events/groupmates is empty.

  Use case ends.

* 3a. The given todo/deadline/event/groupmate index is invalid.

    * 3a1. CoLAB shows an error message.

      Use case resumes at step 3.

* 4a. User decides to undo the delete action.

    * 4a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

#### Modify Existing Entries

##### UC5 - Modify Information About a Project/Contact

**MSS**

1. User requests to edit information about a project/contact.
2. CoLAB updates the entry with new information.

   Use case ends.

**Extensions**

* 1a. The given arguments are invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 2a. User decides to undo the update action.

    * 2a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

##### UC6 - Modify Information About a Todo/Deadline/Event/Groupmate in a Project

**MSS**

1. User switches to the project panel of a specific project.
2. User lists all todos/deadlines/events/groupmates under the project.
3. User requests to edit information about a todo/deadline/event/groupmate.
4. CoLAB updates the entry with new information.

   Use case ends.

**Extensions**

* 1a. The given project index is invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 2a. The list of todos/deadlines/events/groupmates is empty.

  Use case ends.

* 3a. The given arguments are invalid.

    * 3a1. CoLAB shows an error message.

      Use case resumes at step 3.

* 4a. User decides to undo the update action.

    * 4a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

#### Mark an Entry as Done

##### UC7 - Mark a Todo/Deadline in a Project as Done

**MSS**

1. User switches to the project panel of a specific project.
2. User lists all todos/deadlines under the project.
3. User requests to mark a todo/deadline as done.
4. CoLAB marks the given todo/deadline as done.

   Use case ends.

**Extensions**

* 1a. The given project index is invalid.

    * 1a1. CoLAB shows an error message.

      Use case resumes at step 1.

* 2a. The list of todos/deadlines is empty.

  Use case ends.

* 3a. The given arguments are invalid.

    * 3a1. CoLAB shows an error message.

      Use case resumes at step 3.

* 4a. User decides to undo the mark action.

    * 4a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

#### Search for Entries

##### UC8 - Find a Specific Contact

**MSS**

1. User requests to find a contact.
2. CoLAB shows a list of contacts that match user's query.

   Use case ends.

**Extensions**

* 2a. The list of contacts is empty.

  Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

#### Others

##### UC9 - Manage Today's Deadlines and Events

**MSS**

1. User switches to the today panel.
2. User <u>adds a new deadline (<a href="#uc2---add-a-tododeadlineeventgroupmate-to-a-project">UC2</a>)</u>.
3. User <u>adds a new event (<a href="#uc2---add-a-tododeadlineeventgroupmate-to-a-project">UC2</a>)</u>.
4. User <u>marks a deadline as done (<a href="#uc7---mark-a-tododeadline-in-a-project-as-done">UC7</a>)</u>.

   Use case ends.

**Extensions**

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

##### UC10 - Purge All Entries From the App

**MSS**

1. User requests to delete all entries from the app.
2. CoLAB deletes all data from the app.

   Use case ends.

**Extensions**

* 2a. User decides to undo the purge action.

    * 2a1. CoLAB reverses the effects of the previous command.

      Use case ends.

* *a. At any time, User <u>requests to view help (<a href="#uc11---view-help">UC11</a>)</u>.

##### UC11 - View Help

**MSS**

1. User opens the help page.
2. CoLAB shows a summary of commands as well as a link to the online User Guide.

   Use case ends.

### Non-Functional Requirements

* Technical requirements:
    * CoLAB should work on any _mainstream OS_ on both 32-bit and 64-bit architectures as long as it has Java `11` or above installed.
    * CoLAB should work under _common screen resolutions_. (i.e. the window should not be out of boundary)
* Performance requirements:
    * CoLAB should be able to hold _up to 1000 contacts_ and _1000 projects_ without a noticeable sluggishness in performance for typical usage.
    * The response to any command should be shown _within 1 second_.
* Constraints:
    * CoLAB should be _backward compatible_ with data files produced by earlier versions as much as possible. If one release is not compatible with earlier versions, a migration guide should be provided.
    * CoLAB must be open source under the [MIT License](https://raw.githubusercontent.com/AY2021S2-CS2103T-T11-2/tp/master/LICENSE).
* Quality requirements:
    * A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
    * A user familiar with CLI tools should find CoLAB commands very intuitive.
    * A user who has no experience with CLI tools should be able to find CoLAB easy to use with the help of the [_User Guide_](UserGuide.md).
* Process requirements:
    * the project is expected to adhere to a schedule that delivers a feature set every two weeks.

### Glossary

* **CLI**: Command Line Interface
* **Common screen resolutions**: minimum _1024×786_, maximum _3840×2160_
* **Mainstream OS**: Windows, Linux, macOS
* **MSS**: Main Success Scenario

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always"></div>

## **Appendix B: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and Shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder.

    1. Double-click the jar file.<br>
       Expected: Shows the GUI with a set of sample entries. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location are retained.

### Deleting a Contact

1. Deleting a contact while all contacts are being shown

    1. Prerequisites: List all contacts using the `contacts` command. Multiple contacts in the list.

    1. Test case: `deleteC 1`<br>
       Expected: First contact is deleted from the list. Details of the deleted contact are shown in the status message.

    1. Test case: `deleteC 0`<br>
       Expected: No contact is deleted. Error details shown in the status message.

    1. Other incorrect delete commands to try: `deleteC`, `deleteC x`, `...` (where x is larger than the list size)<br>
       Expected: Similar to previous.

### Adding a Project

1. Test case: `addP n/My Project`<br>
    Expected: A new project named "My Project" is added to the list. The Overview Tab of the newly created project is shown.

1. Test case: `addP 1 n/My Project`<br>
    Expected: No project is created. Error details shown in the status message.

1. Other incorrect delete commands to try: `addP`, `addP My Project`, `...`<br>
    Expected: Similar to previous.

### Saving Data

1. Dealing with missing/corrupted data files

    1. Make sure there is a `./data/colab.json` file.<br>
       If not, open the app, make some changes (e.g. ), and close the app.

    1. Open `./data/colab.json` in a text editor (preferably not Windows Notepad).

    1. Remove the starting `{` character of the JSON file and save the file.

    1. Launch the app by running `java -jar CoLAB.jar` in the console.<br>
       Expected: The GUI should pop up with no entry. The console output should give warnings about incorrect data file format.

--------------------------------------------------------------------------------------------------------------------

<div style="page-break-after: always"></div>

## **Appendix C: Effort**

If the effort required to create **AB3** is 100, we would place the effort required to implement the current version of **CoLAB** at 200.

Our team has put in a significant amount of effort to get CoLAB to the current state. As evidence, we currently have over [20,000 lines of code contributed](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=AY2021S2-CS2103T-T11-2%2Ftp&sort=totalCommits%20dsc&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19) and over 500 automated tests. Below, we list some notable changes that required a significant amount of effort to implement.

### Notable Changes

1. **Adding projects model**

    In order to support projects, our team had to add a projects model. While some sections of the code could be adapted from AB3's Person model, the majority had to be redesigned.

    Firstly, we had to create a new model for `Event`, `Deadline`, `Todo` and `Groupmate`. Each of these models exposes its functionality via an interface.

    Secondly, we had to create the projects model itself. This required the creation of the `EventList`, `DeadlineList`, `TodoList` and `GroupmateList` classes. These classes had their own challenges, as we had to maintain each of these lists in sorted order.

    Lastly, we had to integrate all these models with the existing code to save persons to a data file in AB3. This required the creation of many classes and major refactoring of existing classes to support multiple models.

2. **Redesigned GUI**

    Compared to AB3, CoLAB has more than double the number of UI components. Each of these components had to be painstakingly designed and styled.

    In addition, in line with our focus on user experience, we had to design a new `UiCommand` class to allow each command to display its own combination of UI components.

    We also had to implement a combination of listeners to ensure CoLAB's UI is also navigable using a mouse. This involved a complex combination of event listeners to ensure that the behavior of each button is consistent with the corresponding command, and the correct buttons or list cells are highlighted in the side panel.

    Each of the Ui components is also responsive and works on a large range of screen sizes. To handle edge cases, we had to find a way to allow horizontal scrolling of each component individually without affecting the ability to scroll the entire window vertically.

3. **Automated GUI Testing**

    We implemented automated GUI testing using the TestFX library. Although we were able to reference AB4's codebase, we still had to spend many hours integrating it with our project because,
        - Libraries in AB4 were outdated and had to be upgraded to the newest versions.
        - As AB4 did not use github actions, we had to spend time to make sure automated GUI tests ran properly with github actions.
        - Since our GUI and Model were so different compared to AB4, we had to spend countless hours adapting AB4's code for our project. We also had to write our own GUI tests, which took up a lot more effort than expected.

4. **CRUD operations**

    We had to implement create, read, update, and delete (CRUD) operations for each of the new models we created. This was not as simple as expected due to the complexity and size of our models. For example, we had to ensure that the operations were executed on the correct task as the index shown to the user may not be the same as the index in the list (as the list displayed to the user is sorted). We also had to ensure that the many lists were all updated correctly and kept in sync with each other.

5. **Undo/Redo**

    We implemented the `Undo/Redo` feature in CoLAB. Unlike other similar projects where the `Undo/Redo` command simply reverts the data to the previous state, we wanted our Undo/Redo command to keep track of the state of the UI when a command is executed. This meant that we could not take reference from existing projects and had to design our own implementation of the `Undo/Redo` command.

    In addition, since many of our commands did not change any data, we had to design a way to save data only after executing some selected commands.

6. **Command History**

    Keeping in mind that CoLAB is a CLI-based application, we implemented the `Command History` feature to provide an authentic CLI experience. This required designing a way to store previously executed commands. In addition, our solution had to take into account the current command the user was typing in order to better mimic a real command line.
