---
layout: page
title: Choon Wei's Project Portfolio Page
---
## Project: TutorBuddy
TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their students’ contacts,
provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation.
TutorBuddy is also optimized for fast typing users to handle their day-to-day administrative responsibilities effectively.
## Summary of Contributions
Given below is a summary of my contributions to the development of TutorBuddy.
### Major Enhancement

* Overlapping Sessions Check
    - **What it does**: This feature helps to validate the specified session date in the `add_session` and `add_rec_session` command.
    - **Justification**: This feature provides an important check that informs the tutor if he/she already has a session that falls within the specified date.
      This prevents the tutor from scheduling two students on the same date and time, reducing the possibilities of awkward session cancellation/postponement.
    - **Highlights**: The intricacies of this feature was very much underestimated. Everytime `add_session` and `add_rec_session` is called, the check will take place.
      We will use the specified date and time to check with all existing session to see if they fall within an existing session. Comparisons between two normal sessions and 
      a normal session with a recurring session is simple; however, comparing a recurring session with another recurring session is complicated. For comparisons between
      recurring sessions, TutorBuddy runs a walk-through from the earlier recurring session, and compares with all the subsequent dates of both recurring session.
      Appropriate validations are present. Test cases were also introduced to ensure that the overlapping checks work as expected under extreme recurring lengths.
    - **Related PR**: [PR #112](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/112), [PR #150](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/150)

- Delete Single Session from Recurring Session Command
    - **What it does**: This feature allows tutors to remove a single session from a valid recurring session.
    - **Justification**: This feature provides a more convenient way of removing a single session from a currently recurring session if a student decides to cancel a class on a certain date.
      Rather than having to delete the entire recurring session then creating separate recurring session past the specified deleted date, TutorBuddy helps split the recurring session into two recurring sessions.
    - **Highlights**: As this feature involves the use of a command, a new command class `DeleteRecurringSessionCommand` was created. The logic behind this command is relatively simple.
      TutorBuddy will split the recurring session period into two, creating a recurring session prior to the specified deleted date, and a recurring session after the specified date.
      Defensive code were implemented in several areas. Appropriate validations are present to deal with incorrect specified session and invalid session date that does not belong to the recurring session.
      Test cases were also introduced to ensure that the command works as expected.
    - **Related PR**: [PR #155](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/155)

### Minor Enhancement
* Added a `Session` list panel that observes changes to the student list and reflects the sessions contained in the list of students on the UI
* Added list of session attribute to `Student` class
* Added a `Tuition` class to bind `Student` with `Session` utilised in the Calendar page
* Modifying several command feedback description to be in check with user guide specification

### Other Contribution
* Project Management
    * In charge of the overall project coordination
    * Responsible for the integration of different components
    * Set up the GitHub team organisation and repository
* Testing
    * Written test cases for `Tuition` class ([PR #97](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/97))
    * Added relevant test cases for overlapping sessions check ([PR #155](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/155))
    * Added some test cases for `list_session` command ([PR #75](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/75)) (Deprecated)
    * Added relevant test cases for `DeleteRecurringSessionCommand` ([PR #300])(https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/300)
* Documentation
    * User Guide
        * Added non-functional requirements from group discussion ([PR #44](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/44))
        * Updated TutorBuddy's description to better describe the features that TutorBuddy promises and its targeted users ([PR #129](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/129))
        * Added sub-headers that groups commands into different categories to improve readability.
    * Developer Guide
        * Added implementation details for the `list` command along with the relevant sequence and activity diagram ([PR #100](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/100))
        * Added implementation details for the `delete_rec_session` command along with the relevant sequence and activity diagram ([PR #303])(https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/303)
    * Codes Contributed
        * [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=yungweezy&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
