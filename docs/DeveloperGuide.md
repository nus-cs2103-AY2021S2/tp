---
layout: page
title: Developer Guide
---

- Table of Contents {:toc}

---

## Note

AB3 DG links here for references:

[AB3 DG markdown](https://github.com/se-edu/addressbook-level3/blob/master/docs/DeveloperGuide.md)

[AB3 DG html](https://se-education.org/addressbook-level3/DeveloperGuide.html)

---

## **Appendix: Requirements**

### Product scope

**Target user profile**:

- has a need to test a significant number of APIs
- prefers a quick means of testing APIs
- can type fast
- prefers typing to mouse interactions
- is reasonably comfortable with APIs
- is keen to develop software products that involve APIs
- requires testing of APIs in projects or work-related tasks

**Value proposition**:

- beautify response
- save requests for quick execution again
- general analysis/recommendation system based on certain metrics
- no need to create any account
- simple and easy to get started
- unintrusive
- great user experience

### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low
(unlikely to have) - `*`

| Priority | As a …​               | I want to …​                              | So that I can…​                                                 |
| -------- | --------------------- | ----------------------------------------- | --------------------------------------------------------------- |
| `* * *`  | new user              | view a quick description of APIs          | quickly review the concepts of APIs                             |
| `* * *`  | long time user        | test my APIs on the fly                   | run API tests anytime                                           |
| `* * *`  | long time user        | quickly load my previous APIs             | save time and not have to type them all out again               |
| `* * *`  | experienced developer | test out my API multiple times repeatedly | know if my API can cope under significant traffic               |
| `* * *`  | new API developer     | clear error messages                      | quickly learn where I went wrong                                |
| `* * *`  | API tester            | a focused simple design                   | quickly validate the state of an endpoint                       |
| `* * *`  | new user              | have an optional features walkthrough     | have a broad overview of functionalities                        |
| `* * *`  | moderate user         | find/locate saved API endpoints           | can easily view the information for the endpoint of my interest |
| `*`      | experienced developer | export my saved API endpoints             | can easily port or integrate the data with other platforms      |
| `*`      | expert user           | have API recommendations                  | help to optimise or are more suited for my product              |
| `*`      | moderate user         | learn to optimise my usage                | can have a faster and smoother workflow                         |

_{More to be added}_

### Use cases

(For all use cases below, the **System** is the `imPoster` and the **Actor** is
the `user`, unless specified otherwise)

**Use case: Add an API endpoint**

**MSS**

1.  User enters command to save an API endpoint
2.  imPoster saves the API endpoint

    Use case ends.

**Extensions**

- 2a. The given command/format is invalid

  - 2a1. imPoster shows an error message

    Use case resumes at step 1.

**Use case: Locate a saved API endpoint by name**

**MSS**

1.  User enters command to locate a saved API endpoint
2.  imPoster searches the existing records
3.  imPoster returns a list of matching API endpoints

    Use case ends.

**Extensions**

- 2a. The given search result is empty

  - 2a1. imPoster shows a friendly message about mistyped keywords

    Use case resumes at step 1.

**Use case: Call a saved API endpoint**

**MSS**

1.  User enters command to call a saved API endpoint
2.  imPoster makes a call to the desired API endpoint
3.  API call is successful and response is shown to the user
4.  imPoster saves the response to a file that the user can view

    Use case ends.

**Extensions**

- 2a. imPoster receives a status code indicating an error

  - 2a1. imPoster forwards and shows the error message (from the server, if any)
    to the user

    Use case resumes at step 1.

**Use case: Call an API endpoint directly without saving**

**MSS**

1.  User enters command to call an API endpoint
2.  imPoster makes a call to the desired API endpoint
3.  API call is successful and response is shown to the user
4.  imPoster saves the response to a file that the user can view

    Use case ends.

**Extensions**

- 2a. imPoster receives a status code indicating an error

  - 2a1. imPoster forwards and shows the error message (from the server, if any)
    to the user

    Use case resumes at step 1.

_{More to be added}_

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above
    installed.
2.  Should be able to hold up to 1000 API endpoints without a noticeable
    sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not
    code, not system admin commands) should be able to accomplish most of the
    tasks faster using commands than using the mouse.

_{More to be added}_

### Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X
- **API endpoint**: The point of entry in a communication channel for two
  systems to interact

_{More to be added}_
