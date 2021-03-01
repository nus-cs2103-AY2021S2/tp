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

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | new user                                   | see usage instructions         | refer to instructions when I forget how to use the App                 |
| `* * *`  | user                                       | add a new plan               |                                                                        |
| `* * *`  | user                                       | delete a plan                | remove entries that I no longer need                                   |
| `* * *`  | user                                       | find a plan by name          | locate details of plans without having to go through the entire list |
| `* *`    | user                                       | hide private contact details   | minimize chance of someone else seeing them by accident                |
| `*`      | user with many plans in the description book | sort plans by name           | locate a plan easily                                                 |

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
