---
layout: page
title: Developer Guide
---

- Table of Contents
  {:toc}

---

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

## **Design**

### Architecture

![Architecture Diagram](images/ArchitectureDiagram.png)

The **_Architecture Diagram_** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S2-CS2103T-W14-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/budgetbaby/Main.java) and [`MainApp`](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/budgetbaby/MainApp.java). It is responsible for,

- At app launch: Initializes the components in the correct sequence, and connects them up with each other.
- At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

- [**`UI`**](#ui-component): The UI of the App.
- [**`BudgetBabyLogic`**](#logic-component): The command executor.
- [**`BudgetBabyModel`**](#model-component): Holds the data of the App in memory.
- [**`BudgetBabyStorage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

- defines its _API_ in an `interface` with the same name as the Component.
- exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `BudgetBabyLogic` component (see the class diagram given below) defines its API in the `BudgetBabyLogic.java` interface and exposes its functionality using the `BudgetBabyLogicManager.java` class which implements the `BudgetBabyLogic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The _Sequence Diagram_ below shows how the components interact with each other for the scenario where the user issues the command `add-fr d/Lunch a/10`.

[To be updated]
![Class Diagram of the Logic Component](images/ArchitectureSequenceDiagram.png)

The sections below give more details of each component.

### UI component

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S2-CS2103T-W14-2/tp/tree/master/src/main/java/seedu/budgetbaby/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts. It is made up of a `HelpWindow`, `BudgetDisplay`, `FinancialRecordListPanel`, `CommandBox`, `ResultDisplay` and a `StatusBarFooter`. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

- Executes user commands using the `Logic` component.
- Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component

![Structure of the logic component](images/LogicClassDiagram.png)

**API**:
[`BudgetBabyLogic.java`](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/budgetbaby/logic/BudgetBabyLogic.java)

1. `BudgetBabyLogic` uses the `BudgetBabyParser` class to parse the user command.
2. This results in a `BudgetBabyCommand` object which is executed by the `BudgetBabyLogicManager`
3. The command execution can affect the `BudgetBabyModel` (e.g. adding a financial record).
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
5. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the sequence diagram for interactions within the `Logic` component for the `execute("add-fr d/Lunch a/10")` API call.

![Interactions Inside the Logic Component for the `add-fr d/Lunch a/10` Command](images/AddFinancialRecordSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `AddFrCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`BudgetBabyModel.java`](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/budgetbaby/model/BudgetBabyModel.java)

The `BudgetBabyModel`,

- stores a `UserPref` object that represents the user’s preferences.
- stores the budget tracker data.
- exposes an unmodifiable `ObservableList<Month>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
- exposes an unmodifiable `ObservableList<FinancialRecord>` that can be 'observed' (same as above)
- does not depend on any of the other three components.

### Storage component [To be updated]

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`BudgetBabyStorage.java`](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/budgetbaby/storage/BudgetBabyStorage.java)

The `Storage` component,

- can save `UserPref` objects in json format and read it back.
- can save the budget tracker data in json format and read it back.

### Common classes

Classes used by multiple components are in the `seedu.budgetbaby.commons` package.

---

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Month Management Feature

#### `view-month` command

To be updated by Xinyue

### Financial Record Management Feature

To be updated by Xinyue

### Budget Management Feature

#### `set-bg` command

To be updated by Yu Heem

### Filter Feature

#### `filter` command (accepts d/ a/ c/)

#### `reset-filter` command

To be updated by Jaryl

### Statistics Feature

To be updated

---

[comment]: <> (## **Documentation, logging, testing, configuration, dev-ops**)

[comment]: <> (\* [Documentation guide]&#40;Documentation.md&#41;)

[comment]: <> (\* [Testing guide]&#40;Testing.md&#41;)

[comment]: <> (\* [Logging guide]&#40;Logging.md&#41;)

[comment]: <> (\* [Configuration guide]&#40;Configuration.md&#41;)

[comment]: <> (\* [DevOps guide]&#40;DevOps.md&#41;)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

## **Appendix: Requirements**

### Product scope

**Target user profile**:
University student who needs to manage their finances.

**Value proposition**: <br>
Most university students have a limited budget every month and are moving towards financial independence.
During this transition, university students may seek external tools to manage their finances.
Hence, we believe a budget tracker application that records monthly expenses would benefit university
students as they adjust themselves, easing into adulthood.

- Optimised for university students by
  - setting monthly psending goals as university students have limited budgets
  - allowing university students to categorize their spendings with custom categories suiting their diverse lifestyles
  - providing statistics to help university students better visualize their spending habits and make future plans
    (i.e. to cut down on costs incurred on food next month)
  - sending reminders to keep university students on track (i.e. how much money is left in their budget) as they are often busy with school

### User stories

[comment]: <> (Priorities: High &#40;must have&#41; - `* * *`, Medium &#40;nice to have&#41; - `* *`, Low &#40;unlikely to have&#41; - `*`)

v1.2

| As a …​                                                               | I want to …​                                    | So that I can…​                                                                       |
| --------------------------------------------------------------------- | ----------------------------------------------- | ------------------------------------------------------------------------------------- |
| university student who wants to manage my finances                    | add an FR                                       | track my spending history easily                                                      |
| university student who wants to manage my finances                    | delete an FR                                    | recover from mistakes from adding wrong entries of my spending history                |
| university student who wants to manage my finances                    | view all FRs                                    | quickly glance at all my past spendings                                               |
| university student who has difficulties in managing expenses          | set a monthly budget                            | keep track of my expenses and reduce chances of overspending                          |
| university student who has difficulties in managing expenses          | view my monthly budget                          | quickly glance at budget set for the given month                                      |
| university student who wants to know how much money I can still spend | view my remaining budget for a particular month | be aware of my spending and decide whether I need to be more prudent with my spending |

v1.3

| As a …​                                                                    | I want to …​                                        | So that I can…​                                                                       |
| -------------------------------------------------------------------------- | --------------------------------------------------- | ------------------------------------------------------------------------------------- |
| university student who wants to manage my finances                         | add an FR                                           | track my spending history easily                                                      |
| university student who wants to manage my finances                         | delete an FR                                        | recover from mistakes from adding wrong entries of my spending history                |
| university student who wants to manage my finances                         | view all FRs                                        | quickly glance at all my past spendings                                               |
| university student who wants to manage my finances                         | view all FRs in a particular month                  | quickly glance at my spending history of a specific month                             |
| university student who wants to manage my finances                         | filter FRs based on category                        | quickly glance at my spending history of a specific category                          |
| university student who wants to manage my finances                         | reset filters on FRs                                | quickly glance at the original list of financial records                              |
| university student who has difficulties in managing expenses               | set a monthly budget                                | keep track of my expenses and reduce chances of overspending                          |
| university student who has difficulties in managing expenses               | view my monthly budget                              | quickly glance at budget set for the given month                                      |
| university student who wants to know how much money I can still spend      | view my remaining budget for a particular month     | be aware of my spending and decide whether I need to be more prudent with my spending |
| university student who wants to visualise my data in a more concise manner | view the past 6 months' expenditure and budgets     | quickly glance and gain insight from my spending patterns                             |
| university student who wants to visualise my data in a more concise manner | view the total expenses of the current visible list | quickly glance and gain insight from my spending patterns                             |
| university student who wants to visualise my data in a more concise manner | view the top 5 categories that I spend the most on  | quickly glance and gain insight from my spending patterns                             |

_{More to be added}_

### Use cases

(For all use cases below, the **System** is the `BudgetTracker` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add a Financial Record**

**MSS**

1.  Actor requests to add a financial record
2.  Actor inputs description and amount
3.  System shows the most recently added financial record
4.  Actor acknowledges the addition

    Use case ends.

**Use case: Delete a Financial Record**

**MSS**

1.  Actor requests to delete a financial record
2.  Actor inputs desired index of financial record to be deleted
3.  System shows the most recently deleted financial record
4.  Actor acknowledges the deletion

    Use case ends

**Extensions**

- 2a. The given index is invalid.

  - 2a1. System shows an error message.

    Use case resumes at step 1.

**Use case: View a Financial Record**

1.  Actor requests to view a financial record
2.  Actor inputs desired index of financial record to view
3.  System shows the financial record with corresponding index
4.  Actor completes viewing the desired financial record

    Use case ends

**Extensions**

- 2a. The given index is invalid.

  - 2a1. System shows an error message.

    Use case resumes at step 1.

**Use case: View the current month's Financial Records**

1.  Actor requests to view the current month's financial records
2.  System shows the current month's financial records
3.  Actor completes viewing the current month's financial record

    Use case ends

**Extensions**

- 1a. The current month does not contain any financial records.

  - 1a1. System shows an error message.

    Use case ends.

**Use case: Set a budget**

1.  Actor requests to set budget
2.  Actor inputs desired budget amount
3.  System shows newly set monthly budget
4.  Actor acknowledges newly set monthly budget

    Use case ends

**Extensions**

- 1a. The given budget amount is invalid.

  - 1a1. System shows an error message.

    Use case resumes at step 1.

**Use case: View monthly budget**

1.  Actor requests to view monthly budget
2.  System shows monthly budget
3.  Actor completes viewing monthly budget

    Use case ends

**Extensions**

- 1a. Monthly budget is not set.

  - 1a1. System shows an error message.

    Use case ends.

**Use case: View remaining budget for the current month**

1.  Actor requests to view remaining budget for the current month
2.  System shows the remaining budget for the current month
3.  Actor completes viewing the remaining budget for the current month

    Use case ends

**Extensions**

- 1a. Monthly budget is not set.

  - 1a1. System shows an error message.

    Use case ends.

**Use case: Filter financial records of the current month by category**

1.  Actor requests to filter by `Food` category
2.  System shows all financial records with `Food` tagged as category
3.  Actor completes viewing the filtered list for the current month

    Use case ends

**Extensions**

- 1a. No financial records with `Food` category found

  - 1a1. System shows an error message.

    Use case ends.

- 2a. Actor wishes to view original list of financial records without filter

  - 2a1. Actor requests to reset filter
  - 2a2. System shows original list of financial records for the current month
  - 2a3. Actor completes viewing the list of financial records

    Use case ends.

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should work without internet connection.
3.  Should be _cross-platform_.
4.  Should be able to hold up to 1000 financial records without a noticeable sluggishness in performance for typical usage.
5.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Cross-platform**: Able to transfer the software and its data from one OS to another without creating any problem

---

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
