---
layout: page
title: Developer Guide
---
* Table of Contents
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

[comment]: <> (* [Documentation guide]&#40;Documentation.md&#41;)

[comment]: <> (* [Testing guide]&#40;Testing.md&#41;)

[comment]: <> (* [Logging guide]&#40;Logging.md&#41;)

[comment]: <> (* [Configuration guide]&#40;Configuration.md&#41;)

[comment]: <> (* [DevOps guide]&#40;DevOps.md&#41;)

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

* Optimised for university students by
    * setting monthly psending goals as university students have limited budgets 
    * allowing university students to categorize their spendings with custom tags suiting their diverse lifestyles
    * providing statistics to help university students better visualize their spending habits and make future plans 
      (i.e. to cut down on costs incurred on food next month)
    * sending reminders to keep university students on track (i.e. how much money is left in their budget) as they are often busy with school

    
### User stories

[comment]: <> (Priorities: High &#40;must have&#41; - `* * *`, Medium &#40;nice to have&#41; - `* *`, Low &#40;unlikely to have&#41; - `*`)

| As a …​                                    | I want to …​                           | So that I can…​                                                    |
| -------------------------------------------------- | -------------------------------------| ----------------------------------------------------------------------|
| university student who wants to manage my finances | add an FR                            | track my spending history easily                                      |
| university student who wants to manage my finances | delete an FR                         | recover from mistakes of adding wrong entries of my spending history  |
| university student who wants to manage my finances | view all FRs                         | quickly glance at all my past spendings                               |
| university student who wants to manage my finances | view all FRs in a particular month   | quickly glance at my spending history of a specific month             |
| university student who has difficulties in managing expenses | set a monthly budget       | keep track of my expenses and reduce chances of overspending          |
| university student who has difficulties in managing expenses | view my monthly budget     | quickly glance at budget set for the given month                      |
| university student who wants to know how much money I can still spend | view my remaining budget for a particular month | be aware of my spending and decide whether I need to be more prudent with my spending |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a person**

**MSS**

1.  User requests to list persons
2.  AddressBook shows a list of persons
3.  User requests to delete a specific person in the list
4.  AddressBook deletes the person

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. AddressBook shows an error message.

      Use case resumes at step 2.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

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
