---
layout: page
title: Developer Guide
---

RemindMe Developer Guide
---
RemindMe is a reminder application that features a combination of a list and a calendar. RemindMe makes use of both 
Graphical User Interface(GUI) and Command Line Input(CLI). The target users for RemindMe are National University of 
Singapore(NUS) School of Computing (SOC) students. SOC students can enjoy the benefits of CLI and GUI, which is not 
common in reminder applications in the current market. Users can use RemindMe to store their modules, assignments, exams, 
contacts and general events. RemindMe aims to reorganise students' hectic lives and solve specific needs of SOC students.

<div style="page-break-after: always;"></div>

## Table of Contents

* **[1. Setting up, getting started](#1-setting-up-getting-started)**
* **[2. Design](#2-design)**
* **[3. Implementation](#3-implementation)**
    * **[3.1 Add feature](#31-add-feature)**
    * **[3.2 Find feature](#32-find-feature)**
    * **[3.3 Delete assignment](#33-delete-assignment)**
    * **[3.4 Edit assignment](#34-edit-assignment)**
    * **[3.5 Calendar feature](#35-calendar-feature)**
* **[Documentation, logging, testing and dev-ops](#4-documentation-logging-testing-configuration-dev-ops)**
* **[Appendix-requirements](#appendix-requirements)**
* **[Appendix-instructions-for-manual-testing](#appendix-instructions-for-manual-testing)**

--------------------------------------------------------------------------------------------------------------------

## **1. Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **2. Design**

### 2.1 Architecture

![ArchitectureDiagram](images/ArchitectureDiagram.png)

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-W15-1/tp/tree/master/docs/diagrams/) folder.
 Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/Main.java)
 and [`MainApp`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
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
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

![Sequence diagram of the logic Component](images/ArchitectureSequenceDiagram.png)

The sections below give more details of each component.

### 2.2 UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `panels` and `cards` packages contain respective list panels and cards for `Module`, `Person` and `GeneralEvent`. While
the `calendar` package contains `CalendarBox`, `UpcomingSchedules` and other relevant UI classes that help build the `CalendarWindow`. 

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/resources/view/MainWindow2.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### 2.3 Logic component

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `RemindMeParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("clear m/")` API call.

![Interactions Inside the Logic Component for the `clear m/` Command](images/ClearFeatureSequenceDiagram.png)

### 2.4 Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the RemindMe data.
* exposes an unmodifiable `ObservableList<Person>` `ObservableList<Module>` or `ObservableList<GeneralEvent>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** The details of `Module`, `Assignment`, `Exam`, `Person` and `GeneralEvent` are ommited for greater readability of the diagram.

</div>


### 2.5 Storage component

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S2-CS2103T-W15-1/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the RemindMe data in json format and read it back.
* CalendarStorage is accessed by UI component which is no indicated in the diagram.

### 2.6 Common classes

Classes used by multiple components are in the `seedu.address.commons` package.
<br><br>
--------------------------------------------------------------------------------------------------------------------

## **3. Implementation**

This section describes some noteworthy details on how certain features are implemented.

### 3.1 Add feature

#### Implementation

The proposed add implementation is facilitated by `ModelManager`, which extends `Model`. `ModelManager` contains FilteredList of each entities:
* Persons
* Modules
* General Events

Given below is an example usage scenario and how the find mechanism behaves at each step. Input: `add m/CS2103T`

**Step 1:** Your input is parsed into `RemindMeParser` using the `parseCommand` method.
<br>
<br>

**Step 2:** Based on the command word of your input (i.e., `add`), an AddCommandParser will be used.
<br>
<br>

**Step 3:** In `AddCommandParser#parseCommand`, your input will be tokenized using `ArgumentTokenizer`. `ArgumentTokenizer` uses your input, then searches for the prefixes and returns the `ArgumentMultimap`.
<br>
<br>

**Step 4**: Using the `ArgumentMultimap` checks the prefixes in your input and returns the respective `AddCommandParser`.

    Module: `m/`: `AddModuleCommandPaser`
    Person: `n/`: `AddPersonCommandParser`
    General Event: `g/`: `AddGeneralEventParser`  
    if it is an unknown prefix, `parseCommand` will throw a ParseException and returns a `AddMessageUsage`. Since the input is `m/`, `AddModuleCommandPaser` will be returned.

**Step 5**: In `AddModuleCommandPaser`, `AddModuleCommandPaser#parse` is called. Again `ArgumentMultimap` is created using `ArgumentTokenizer` but only with `Module` prefix: `m/`. The class diagram shows the Parser class diagram when passing your input into the appropriate `AddModuleCommand`.
<br>
<br>

![AddCommandParserClassDiagram](images/AddCommandParserClassDiagram.png)

<br>
<br>

**Step 6:** The `parse` method does a few checks:

* If there isn't the `PREFIX`: `m/` present, or the preamble of the `PREFIX` is not empty, or your search input after the `PREFIX` is whitespaces, then `parse` method will throw `ParseException` and returns a `AddMessageUsage` for `Module`.
* Else your inputs is used to create a `title` which is then used to create a `module`.
<br>
<br>

**Step 7:** `AddModuleCommand` is executed:

* Using the `module` as an input, the `Model#hasModule` method checks if the given `module` is a duplicate or not. If it is, it will throw `CommandException` and return a `MESSAGE_DUPLICATE_MODULE`.
* Else, using the `module` as an input, the `Model#addModule` method is called, and adds the `module` to the `UniqueModuleList` in `RemindMe`.
<br>
<br>

**Step 8:** The `CommandResult` is logged in the `logger` and using `resultDisplay#setFeedacktoUser`, returning `resultDisplay`. Using `resultDisplay#setText` shows the `CommandResult` in the `GUI`.
<br>
<br>

The following sequence diagram shows how the find operation works:

![AddSequenceDiagram](images/AddSequenceDiagram.png)


### 3.2 Find feature

#### Implementation

The proposed find implementation is facilitated by `ModelManager`, which extends `Model`. `ModelManager` contains FilteredList of each entities:
* Persons
* Modules
* General Events

Given below is an example usage scenario and how the find mechanism behaves at each step. Input: `find m/CS2101`

**Step 1:** Your input is parsed into `RemindMeParser` using the `parseCommand` method.
<br>
<br>

**Step 2:** Based on the command word of your input (i.e., `find`), a FindCommandParser will be used.
<br>
<br>

**Step 3:** In `FindCommandParser#parseCommand`, your input will be tokenized using `ArgumentTokenizer`. 
`ArgumentTokenizer` uses your input, then searches for the prefixes and returns the `ArgumentMultimap`.
<br>
<br>

**Step 4:** Using the `ArgumentMultimap` checks the prefixes in your input and returns the respective `FindCommandParser`.

    Module: `m/`: `FindModuleCommandPaser`
    Person: `n/`: `FindPersonCommandParser`
    General Event: `g/`: `FindGeneralEventParser`  
    if it is an unknown prefix, `parseCommand` will throw a ParseException and returns a `FindMessageUsage`. 
    Since the input is `m/`, `FindModuleCommandPaser` will be returned.
<br>

**Step 5:** In `FindModuleCommandPaser`, `FindModuleCommandPaser#parse` is called. Again `ArgumentMultimap` is created 
using `ArgumentTokenizer` but only with `Module` prefix: `m/`. The class diagram shows the Parser class diagram when 
passing your input into the appropriate `FindModuleCommand`.  
<br>
<br>

**Step 6:** The `parse` method does a few checks:

* If there isn't the `PREFIX`: 
    * `m/` present, or 
    * the preamble of the `PREFIX` is not empty, or 
    * your search input after the `PREFIX` is whitespaces, 
  * then `parse` method will throw `ParseException` and returns a `FindMessageUsage` for `Module`.
* Else your inputs is split into individual keywords, and contained as a `List of keywords`.
<br>
<br>

**Step 7:** The keywords will be stored in `TitleContainsKeywordsPredicate` as a `predicate`, then stored in `FindModuleCommand`.
<br>
<br>

**Step 8:** `FindModuleCommand` is executed:
* Using the `predicate`, the `Model#updateFilteredModuleList` is called with `predicate` as input.
* Using the `FilteredList<Module>#setPredicate` returns the filtered list of modules with titles matching to any of the 
  `keywords` as a `CommandResult`.
<br>
<br>

**Step 9:** The `CommandResult` is logged in the `logger` and using `resultDisplay#setFeedacktoUser`, returning 
`resultDisplay`. Using `resultDisplay#setText` shows the `CommandResult` in the `GUI`.
<br>
<br>

The following sequence diagram shows how the find operation works:
![FindSequenceDiagram](images/findcommand/FindSequenceDiagram.png)<br>
*[Find Sequence Diagram for `find m/CS2101`]*

The following activity diagram summarizes what happens when a user executes a `find m/CS2101` command:
![FindActivityDiagram](images/findcommand/FindActivityDiagram.png)<br>
*[Find Activity Diagram for `find m/CS2101`]*
<br>
<br>

### 3.3 Delete Assignment

#### Implementation
RemindMe is able to delete an existing `Assignment` in an existing `Module`

The diagram below shows the relationships between `DeleteAssignmentCommand` and `DeleteAssignmentCommandParser` under 
the `Logic` component and the relationship between `Module` and `Assignment` under the `Model` component.

<br>
<br>
The following example usage scenario describes how the delete mechanism behaves at each step.

    Assuming RemindMe has a Module named CS2103. This Module contains a AssignmentList that stores
    a list of Assignmnets:
    
    [D] Assigment1 due on  01/01/2021 2359
    [X] Assignment2 due on 05/05/2022 2359
    [D] Assignment3 due on 25/03/1021 2359
    
**Step 1:** The user launches the RemindMe application, `LogicManager` and `RemindMeParser` will be initialized.
<br>
<br>

**Step 2:** The user executes `delete m/CS2103 a/3` to delete the assignment at `Index` 3 from the `AssignmentList` of
the `Module` CS2103. This invokes the method `LogicManager#execute(String)` which then invokes the 
`RemindMeParser#parseCommand(String)` method.
<br>
<br>

**Step 3:** RemindMeParser will parse the command word `delete` and will create a DeleteCommandParser. The 
DeleteCommandParser will tokenize the prefixes and will choose to create a deleteAssignmentCommandParser to parse
the `Title` CS2103 and `Index` 3.
<br>
<br>

**Step 4:** The `DeleteAssignmentCommandParser` will create a new `DeleteAssignmentCommand` with the `Title` CS203 and 
`Index` 3 and return it back to the LogicManager.
<br>
<br>

**Step 5:** The `DeleteAssignmentCommand` verifies whether the target `Module` exist in the `FilteredModuleList` 
and whether an assignment exists at `Index` 3. If either fails, `DeleteAssignmentCommand` will throw a CommandException.
If not, it will invoke the method `Module#deleteAssignment(Index)` which removes the assignment at `Index` 3 from the 
`AssignmentList`
<br>
<br>

**Step 6:** A `CommandResult` will be created with a successful message if the user inputs are valid
and returned to `LogicManager`.
<br>
<br>

**Step 7:** Lastly, `LogicManager` saves the updated RemindMe.

The above process is shown in the following sequence diagram:
![DeleteFeatureSequenceDiagram](images/DeleteFeatureSequenceDiagram.png)  

The following activity diagram summarises the general workflow for the Delete Command:
![DeleteFeatureActivityDiagram](images/DeleteFeatureActivityDiagram.png)  


### 3.4 Edit Assignment

#### Implementation
RemindMe is capable of editing an existing `assignment`. 


Below is a class diagram to show the relationship between `EditAssignmentCommand and EditAssignmentCommandParser` under 
the `Logic` component and the relationship between `Module` and `Assignment` under the `Model` component.
<br>
<br>

![EditFeatureClassDiagram](images/EditFeatureClassDiagram.png)
<br>
<br>

Given below is an example usage scenario and how the edit mechanism behaves at each step.

     Assuming RemindMe already has a Module named CS2103 and an Assignment Tut1 with time 01/01/2021 2359 stored.

**Step 1:** The user launches the RemindMe application, `LogicManager` and `RemindMeParser` will be initialized.
<br>
<br>

**Step 2:** The user executes `edit m/CS2103 a/1 d/Tut2` to edit the description of the first assignment
in the CS2103 module. This invokes the method `LogicManager#execute(String)` which then invokes the
 `RemindedParser#parseCommand(String)` method.

<br>
<br>

**Step 3:** RemindMeParser will then create `EditCommandParser` (omitted in the sequence diagram)
which detects the edit conditions and calls `EditAssignmentCommandParser` 
to parse inputs according to the format specified.
<br>
<br>

**Step 4:** The `EditAssignmentCommandParser` will create a new `EditAssignmentCommand` 
with the given module `CS2103` , the given index `1`, the description `Tut2` and a null 
date and return it back to `LogicManager`.
<br>
<br>

**Step 5:** `LogicManager` calls the `EditAssignmentCommand#execute(Model)` method 
which then verifies whether the target module and assignment exists and whether
 the edited content is valid, eg. same content.
<br>
<br>

**step 6:** The `Model` calls `RemindMe#editAssignment(Module, index, Description)` method which retrieves
the module to edit from the `UniqueModuleList` ,retrieves and update the assignment and place the
module back to the list. 
<br>
<br>

**Step 7:** A `CommandResult` will be created with a successful message if the user inputs are valid
and returned to `LogicManager`.
<br>
<br>

**Step 8:** Lastly, `LogicManager` saves the updated RemindMe.

    Note: An EditAssignmentCommand can either change the description or date of an assignment, not both.
    
<br>

The above process is shown in the following sequence diagram:

![EditFeatureSequenceDiagram](images/EditFeatureSequenceDiagram.png)

<br>

Below is the separate sequence diagram for editAssignment(m, 1 , Tut2):

![EditFeatureSequenceDiagram1](images/EditFeatureSequenceDiagram1.png)

<br>

The following activity diagram summarises the general workflow for the Edit Command:

![EditFeatureActivityDiagram](images/EditFeatureActivityDiagram.png)


### 3.5 Calendar feature

#### Implementation

![CalendarSequenceDiagram2](images/CalendarSequenceDiagram2.png)


**Given below is an example of how the calendar UI is created.**

**Step1:** `CalendarWindow` will be created at the start of the program.
<br>
<br>

**Step2:** `CalendarWindow` will then call itself `CalendarWindow#loadCalendar` to load the details into the calendar.
<br>
<br>

**Step3:** In the `loadCalendar` method,  to ensure that the details inside calendar are the latest, 
the method will then call `CalendarStorage#refreshStorage` to update the storage for the calendar.
<br>
<br>

**Step4:** In the `refreshStorage` method, calendar storage will be cleared first by calling `CalendarStorage#clear`
and then the details about events are retrieved from RemindMe model by calling `RemindMe#getFilteredPersonList`
, `RemindMe#getFilteredModuleList` and `RemindMe#getFilteredEventList`.
<br>
<br>

**Step5:** With calendar storage updated, the calendar will then store events to each respective day and then the calendar
will be ready to be displayed as a GUI. 
<br>
<br>

![CalendarSequenceDiagram1](images/CalendarSequenceDiagram1.png)

Given below is an example usage scenario and how the calendar mechanism behaves at each step.  Input: `calendar`.

**Step 1.** Your input is parsed into `RemindMeParser` using the `parseCommand` method.
<br>
<br>

**Step 2:** Based on the command word of your input (i.e., `calendar`), a `CalendarCommand` will be created.
<br>
<br>

**Step 3:** `CalendarCommand` is executed, and that will set boolean `showCalendar` to be true, the boolean is then 
pushed to `MainWindow` to call `MainWindow#handleCalendar` to show the `CalendarWindow`.
<br>
<br>

**Step 4:** `CalendarWindow` loaded by its fxml file and called `CalendarWindow#show` to show its shown 
as a pop-up window for you.

<br>
<br>


--------------------------------------------------------------------------------------------------------------------

## **4. Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------


## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage exams, assignments and events deadlines
* prefer desktop apps over other types
* tend to forget upcoming events/exams
* is reasonably comfortable using CLI and GUI apps

**Value proposition**: Manage deadlines and events in list and calendar view format to remind forgetful users.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                 | I want to …​                | So that I can…​                                                     |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see instructions help page     | refer to help page when I forget how to use the App |
| `* * *`  | user                                       | exit the App                   | use other applications on my computer |
| `* * *`  | student taking a module                    | add module                     | keep track of the module exams, assignments |
| `* * *`  | student having assignments                 | add assignments to module      | keep track of the assignment deadline |
| `* * *`  | student having exams                       | add exams to module            | keep track of the exam start time |
| `* * *`  | user with friends                          | add person and their birthday  | keep track of birthday and wish them happy birthday promptly |
| `* * *`  | user                                       | add general events             | keep track of events happening outside school curriculum |
| `* * *`  | student                                    | edit a module                  | adjust module name if module name changes |
| `* * *`  | student                                    | edit a assignment              | can adjust schedule when there is a change of plan |
| `* * *`  | student                                    | edit a exam                    | can adjust schedule when there is a change of plan |
| `* * *`  | user                                       | edit a person and birthday     | fine tune person name and birthday according |
| `* * *`  | user                                       | edit a general event           | adjust schedule when there is a change of plan |
| `* * *`  | student                                    | delete a module                | I can remove a module I do not require |
| `* * *`  | student                                    | delete a assignment            | I can remove an assignment once it's done |
| `* * *`  | student                                    | delete a exam                  | I can remove an exam after I completed it |
| `* * *`  | user                                       | delete a person and birthday   | I can remove a person I am no longer close to |
| `* * *`  | user                                       | delete a general event         | I can remove an event that is already over |
| `* * `   | student                                    | find a module                  | quickly locate details for module |
| `* * `   | user                                       | find a person                  | quickly locate details for person |
| `* * `   | user                                       | find a general event           | quickly locate details for event |
| `* * `   | user                                       | see all entries after finding command | I can have a big picture of all my entries |
| `* * `   | student                                    | mark my assignments as done    | identify if assignments are done or not |
| `* * `   | user                                       | clear App                      | quickly delete all details in App |
| `* * `   | student                                    | clear modules                  | quickly delete all details for modules |
| `* * `   | user                                       | clear contacts                 | quickly delete all details for person |
| `* * `   | user                                       | clear general events           | quickly delete all details for events |
| `* *`    | user                                       | view events in a calendar view | to have a better sense of the upcoming events |
| `* *`    | forgetful student                          | be reminded about upcoming events | respond to upcoming events accordingly |  

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `RemindMe` and the **Actor** is the `user`, unless specified otherwise)

### Use Case: `UC01` - view help

**MSS:**

1. User enters the command to view help.
2. System shows help and url to copy to user guide webpage for more in depth help.
   <br> Use case ends.

**Extensions:**

* 1a. System detects an error in formatting of command.
    * 1a1. System display error message.
      <br> Use case ends.
      
### Use Case: `UC02` - exit

**MSS:**

1. User enters the command to exit system.
2. System exits by closing all relevant GUI.
   <br> Use case ends.

**Extensions:**

* 1a. System detects an error in formatting of command.
    * 1a1. System display error message.
      <br> Use case ends.
      
### Use Case: `UC03` - add module

**MSS:**

1. User enters command to add a module.
2. System adds module and displays module info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
* 1b. System detects that module is present in system.
    * 1b1. System display duplicate module error message.
    <br> Use case ends.      


### Use Case: `UC04` - add assignment

**MSS:**

1. User enters command to add an assignment for a module.
2. System adds assignment and displays assignments info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
* 1b. System detects that module for assignment is not present in the system.
    * 1b1. System display module missing error message.
    <br> Use case ends.
* 1c. System detects that assignment is present in the module.
    * 1c1. System display duplicate assignment error message.
    <br> Use case ends.
    
### Use Case: `UC05` - add exam

**MSS:**

1. User enters command to add an exam for a module.
2. System adds exam and displays exam info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
* 1b. System detects that module for exam is not present in the system.
    * 1b1. System display module missing error message.
    <br> Use case ends.
* 1c. System detects that exam is present in the module.
    * 1c1. System display duplicate exam error message.
    <br> Use case ends.

## Use Case: `UC06` - add person and birthday

**MSS:**

1. User enters command to add a person and his/her birthday.
2. System adds person with his/her birthday and displays person info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
* 1b. System detects that person is present in system.
    * 1b1. System display duplicate person error message.
    <br> Use case ends.      

### Use Case: `UC07` - add general events

**MSS:**

1. User enters command to add a general event.
2. System adds general event and displays general event info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
* 1b. System detects that general event is present in system.
    * 1b1. System display duplicate event error message.
    <br> Use case ends. 

### Use Case: `UC08` - edit module

**MSS:**

1. User enters command to edit a module.
2. System edits module and displays edited module info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
 
### Use Case: `UC09` - edit assignment

**MSS:**

1. User enters command to edit an assignment for a module.
2. System edits assignment and displays edited assignment info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends. 
        
### Use Case: `UC10` - edit exam

**MSS:**

1. User enters command to edit an exam for a module.
2. System edits exam and displays edited exam info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.      
    
### Use Case: `UC11` - edit person and birthday

**MSS:**

1. User enters command to edit the birthday of a person.
2. System edits birthday and displays edited birthday info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.     

### Use Case: `UC12` - edit general event

**MSS:**

1. User enters command to edit a general event.
2. System edits general event and displays edited event info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends. 

### Use Case: `UC13` - delete module

**MSS:**

1. User enters command to delete a module.
2. System deletes module and displays deleted module info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.

### Use Case: `UC14` - delete assignment

**MSS:**

1. User enters command to delete an assignment for a module.
2. System deletes assignment and displays deleted assignment info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends. 
        
### Use Case: `UC15` - delete exam

**MSS:**

1. User enters command to delete an exam for a module.
2. System deletes exam and displays deleted exam info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.      
    
### Use Case: `UC16` - delete person

**MSS:**

1. User enters command to delete a person.
2. System deletes person and displays deleted person info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.     

### Use Case: `UC17` - delete general event

**MSS:**

1. User enters command to delete a general event.
2. System deletes general event and displays deleted event info.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends. 

### Use Case: `UC18` - find module

**MSS:**

1. User enters command to find a module with keyword.
2. System display modules found by the keyword.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
 
### Use Case: `UC19` - find person

**MSS:**

1. User enters command to find a person with keyword.
2. System display person found by the keyword.
<br> Use case ends.

**Extensions:**

* 1a. System detects formatting error in command.
 * 1a1. System display formatting error message.
     <br> Use case ends.
 
### Use Case: `UC20` - find general event

**MSS:**

1. User enters command to find a general event with keyword.
2. System display general event found by the keyword.
<br> Use case ends.
 
**Extensions:**

* 1a. System detects formatting error in command.
  * 1a1. System display formatting error message.
      <br> Use case ends.    

### Use Case: `UC21` - list

**MSS:**

1. User enters list command.
2. System display full list for module, person and events.
<br> Use case ends.
 
**Extensions:**

* 1a. System detects formatting error in command.
  * 1a1. System display formatting error message.
      <br> Use case ends.    

### Use Case: `UC22` - mark assignment as done

**MSS:**

1. User enters command to mark an existing assignment as done.
2. System marks assignment as done and displays assignment info.
<br> Use case ends.
 
**Extensions:**

* 1a. System detects formatting error in command.
  * 1a1. System display formatting error message.
      <br> Use case ends.

### Use Case: `UC23` - clear App

**MSS:**

1. User enters command to clear App.
2. System clears the App.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.

### Use Case: `UC24` - clear modules

**MSS:**

1. User enters command to clear modules.
2. System clear modules and displays empty module list.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.
 
### Use Case: `UC25` - clear contacts

**MSS:**

1. User enters command to clear contacts (person).
2. System clear contacts and displays empty person list.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.

### Use Case: `UC26` - clear events

**MSS:**

1. User enters command to clear general events.
2. System clear general events and displays empty event list.
<br> Use case ends.
   
**Extensions:**

* 1a. System detects formatting error in command.
    * 1a1. System display formatting error message.
        <br> Use case ends.

### Use Case: `UC27` - view calendar

**MSS:**

1. User enters the command to view calendar.
2. System shows calendar.
   <br> Use case ends.

**Extensions:**

* 1a. System detects an error in formatting of command.
    * 1a1. System display error message.
      <br> Use case ends.
 
### Use Case: `UC28` - reminder

**MSS:**

1. User starts the system.
2. System shows upcoming events as a reminder.
   <br> Use case ends.
      
*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons and tasks without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  RemindMe should be able to respond within one seconds.
5.  RemindMe should be usable by novice who has no prior experience with coding.

*{More to be added}*

### Glossary

Term | Meaning
---|---
**Mainstream OS** | Windows, Linux, Unix, OS-X.
**Module** | A school module consisting of module name/module id. 
**Examination** | Consists of a start time and date which it occurs on under a relevant module.
**Event** | Consists of a start time and date which it occurs on.
**Assignment** | Consists of a deadline under a relevant module.
**GUI** | Graphic User Interface, the visible interface the user sees for the application.

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



### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
