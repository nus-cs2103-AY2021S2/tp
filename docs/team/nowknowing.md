---
layout: page
title: Shion's Project Portfolio Page
---

## Project: TutorBuddy

## Overview

TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their
students' contacts, provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation.
TutorBuddy was adapted from an existing desktop Java application [Address Book (Level 3)](https://se-education.org/addressbook-level3/).
My team, consisting of 5 NUS Computer Science students, developed this application over the course of 6 weeks.

## Summary of Contributions

Here is a summary of my personal contributions to the project.
My code contribution can be found [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=nowknowing&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=nowknowing&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Major Enhancements
- Recurring Session component
    - **What it does**: The class RecurringSession holds information in addition to Session, namely the interval and last date of recurrence.
    - **Justification**: This feature allows for recurring session, which is likely the norm to tuition sessions, which often occur at fixed intervals.
      Because the information of previously-multiple sessions are now abstracted into one recurring session, its presence save space and other resources.
    - **Highlights**:
      Defensive coding is employed, where a recurring session object can be created only if the last session date is consistent
      with the first session date and interval.

      Through the parser of add_rec_session command, the correct last date of a session is suggested if entered wrongly. 

      At the point of the command parser, creation of recurring session that do not in fact recurse, i.e. happens only once,
      is prevented. This is to establish to users that non-recurring single sessions are not a subset of recurring sessions.
      (The creation of recurring session object with zero recurrence is allowed on the system-side as used in
      when deleting one session of recurring session.)

      Some key methods to the recurring session class include:
      - hasSessionOnDate, which checks if recurring session occurs on given date
      - buildSessionOnDate, which returns a single, non-recurring session of the recurring session,
        happening on a valid date
      - lastSessionOnOrBefore, which retrieves the session that would come before a given date.
      - numOfSessionsBetween, which returns the number of sessions that occurs between 2 given dates
      - isOverlapping, which checks if 2 recurring sessions overlap (the deployed method is done by Choon Wei)
    - **Related PR**: [PR #106](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/106)


- Delete Session
    - **What it does**: Deletes a session that belongs to a student under his/her list of sessions.
    - **Justification**: It is a primitive functionality.
    - **Highlights**: Rather basic, the D to CRUD. But not a refactoring of AB3's deletePerson;
    it involves some logic as a session belongs to a list of sessions, belonging to a particular student.
    - **Related PR**: [PR #64](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/64)
    Not merged to master, because pulled to [PR #73](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/73) to combine with storage component for session.

### Other Contribution
- Project Management
    - Responsible for ensuring code logic
- Testing
    - SessionBuilder [PR #64](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/64/)
    - Recurring Session
    
- Non-merged
    - isOverlapDate method for 2 recurring session. Checks in a non-brute-force, mathematical way using extended euclid.
    Reason for no merge: Brute-force method works well in reasonable time. Review can be time-consuming.
    [PR #171](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/171)

- Documentation
    - User Guide
        - For recurring session command ([PR #127](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/127))
        - Purpose of UG([PR #125](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/125/files))
    - Developers Guide
        - Details for deleting session ([PR #103](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/103))
