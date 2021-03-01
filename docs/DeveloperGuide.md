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

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* NUS students
* has a hard time organising and planning what modules to take
* prefer desktop apps over phone apps
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* want to have a good way to check all MCs
* wants to have a good way to check all fulfilled pre-requisites
* wants to have a good way to plan for all their modules

**Value proposition**: 
* managing study plan is much easier than existing choices (i.e. WHAT-IF report)
* planning for modules is more automatic/convenient than manual inputs (NUSMOD)


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                   | I can     …​                    | So that I can…​                                                     |
| ---------| ---------------------------------------------| -----------------------------------| -----------------------------------------------------------------------|
| `* * *`  | new user                                     | see usage instructions             | refer to instructions when I forget how to use the App                 |
| `* * *`  | student user                                 | have multiple study plans          | prepare for different study scenarios in university                    |
| `* * *`  | student user                                 | add multiple semesters to a plan   | create plans that involve multiple semesters                           |
| `* * *`  | student user                                 | add multiple modules to a semester | plan for what modules i want to do in a given semester                 |
| `* * *`  | student user                                 | delete a study plan                | remove plans that i no longer need                                     |
| `* * *`  | student user                                 | delete a semester from a plan      | remove semesters that i no longer need                                 |
| `* * *`  | student user                                 | delete a module from a semester    | remove modules that i no longer need                                   |
| `* * *`  | student user                                 | view summary information of a plan | conveniently understand the plan without having to open it             |
| `* *`    | student user                                 | check if my plan contains 160MCs   | know whether my plan allows me to graduate                             |
| `* * *`  | student user                                 | view a module's prerequisites      | know what modules need to be done before hand                          |
| `* *`    | student user                                 | mark semesters as done             | advance my study plan according to the semesters that i have completed |
| `*`      | forgetful student                            | add and view grades of past modules| keep track of how well i did for different modules without remembering |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `AddressBook` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Delete a plan**

**MSS**

1.  User requests to list plans
2.  AddressBook shows a list of plans
3.  User requests to delete a specific plan in the list
4.  AddressBook deletes the plan

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
2.  Should be able to hold up to 1000 plans without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------
