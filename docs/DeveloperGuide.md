---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------
## Note
AB3 DG links here for references:

[AB3 DG markdown](https://github.com/se-edu/addressbook-level3/blob/master/docs/DeveloperGuide.md)

[AB3 DG html](https://se-education.org/addressbook-level3/DeveloperGuide.html)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to test a significant number of APIs
* prefers a quick means of testing APIs
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable with APIs
* is keen to develop software products that involve APIs
* requires testing of APIs in projects or work-related tasks

**Value proposition**:

* beautify response
* save requests for quick execution again
* general analysis/recommendation system based on certain metrics
* no need to create any account
* easy to get started
* unintrusive
* great user experience

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                         |
| -------- | ------------------------------------------ | ------------------------------ | --------------------------------------------------|
| `* * *`  | new user                                   | view a quick description of APIs| quickly review the concepts of APIs              |
| `* * *`  | long time user                             | test my APIs on the fly               | run API tests anytime                      |
| `* * *`  | long-term user                             | quickly load my previous APIs |  save time and not have to type them all out again |
| `* * *`  | experienced developer                      | test out my API multiple times repeatedly |  know if my API can cope under significant traffic |
| `* * *`  | new API developer                          | clear error messages                | quickly learn where I went wrong             |
| `* * *`  | API tester                                 | a focused simple design          | quickly validate the state of an endpoint       |
| `* * *`  | new user                                   | have an optional features walkthrough   | have a broad overview of functionalities |
| `*`      | expert user                                | have API recommendations | help to optimise or are more suited for my product      |


*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `ImPoster` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Add an API route**

**MSS**

1.  User inputs formatted command 
2.  ImPoster adds the command

    Use case ends.

**Extensions**

* 2a. The given command is invalid.

    * 2a1. ImPoster shows an error message.

      Use case resumes at step 1.

**Use case: Locate an saved API by name**

**MSS**

1.  User inputs formatted command 
2.  ImPoster searches the existing records
3.  ImPoster returns all matching API records

    Use case ends.

**Extensions**

* 2a. The given search result is empty.

    * 2a1. ImPoster shows a friendly message about mistyped keywords.

      Use case resumes at step 1.

**Use case: Run a saved API**

**MSS**

1.  User inputs formatted command calling on a saved API
2.  ImPoster runs the API
3.  ImPoster saves the outputs in a file that the user can view

    Use case ends.

**Extensions**

* 2a. There is no return result as the API call resulted in an error.

    * 2a1. ImPoster shows a message informing the user that an error has occured.

      Use case resumes at step 1.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

*{More to be added}*
