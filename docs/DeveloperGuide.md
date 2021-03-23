---
layout: page
title: Developer Guide
---

- Table of Contents
  {:toc}

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## **Setting up, getting started**)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## **Design**)

[comment]: <> (### Architecture)

[comment]: <> (### UI component)

[comment]: <> (### Logic component)

[comment]: <> (### Model component)

[comment]: <> (### Storage component)

[comment]: <> (### Common classes)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## **Implementation**)

[comment]: <> (This section describes some noteworthy details on how certain features are implemented.)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

[comment]: <> (## **Documentation, logging, testing, configuration, dev-ops**)

[comment]: <> (\* [Documentation guide]&#40;Documentation.md&#41;)

[comment]: <> (\* [Testing guide]&#40;Testing.md&#41;)

[comment]: <> (\* [Logging guide]&#40;Logging.md&#41;)

[comment]: <> (\* [Configuration guide]&#40;Configuration.md&#41;)

[comment]: <> (\* [DevOps guide]&#40;DevOps.md&#41;)

[comment]: <> (--------------------------------------------------------------------------------------------------------------------)

## **Design**

### Logic component

![Structure of the logic component](./images/BudgetBabyLogicClassDiagram.png)

**API**:
[`BudgetBabyLogic.java`](https://github.com/AY2021S2-CS2103T-W14-2/tp/blob/master/src/main/java/seedu/budgetbaby/logic/BudgetBabyLogic.java)

1. `BudgetBabyLogic` uses the `BudgetBabyParser` class to parse the user command.
2. This results in a `BudgetBabyCommand` object which is executed by the `BudgetBabyLogicManager`
3. The command execution can affect the `BudgetBabyModel` (e.g. adding a financial record).
4. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
5. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

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

| As a …​                                                               | I want to …​                                    | So that I can…​                                                                       |
| --------------------------------------------------------------------- | ----------------------------------------------- | ------------------------------------------------------------------------------------- |
| university student who wants to manage my finances                    | add an FR                                       | track my spending history easily                                                      |
| university student who wants to manage my finances                    | delete an FR                                    | recover from mistakes from adding wrong entries of my spending history                |
| university student who wants to manage my finances                    | view all FRs                                    | quickly glance at all my past spendings                                               |
| university student who wants to manage my finances                    | view all FRs in a particular month              | quickly glance at my spending history of a specific month                             |
| university student who wants to manage my finances                    | filter FRs based on category                    | quickly glance at my spending history of a specific category                          |
| university student who has difficulties in managing expenses          | set a monthly budget                            | keep track of my expenses and reduce chances of overspending                          |
| university student who has difficulties in managing expenses          | view my monthly budget                          | quickly glance at budget set for the given month                                      |
| university student who wants to know how much money I can still spend | view my remaining budget for a particular month | be aware of my spending and decide whether I need to be more prudent with my spending |

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

**Use case: View current month's Financial Records**

1.  Actor requests to view current month's financial records
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

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should work without internet connection.
3.  Should be _cross-platform_.
4.  Users must specify their monthly budget upon _first execution_.
5.  Should be able to hold up to 1000 financial records without a noticeable sluggishness in performance for typical usage.
6.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **Cross-platform**: Able to transfer the software and its data from one OS to another without creating any problem
- **First execution**: The very first time the software is set up

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
