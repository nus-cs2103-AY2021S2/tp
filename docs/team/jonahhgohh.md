---
layout: page
title: Jun Wei's Project Portfolio Page
---

## Project: TutorBuddy

## Overview

TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their
students' contacts, provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation.
TutorBuddy was adapted from an existing desktop Java application [Address Book (Level 3)](https://se-education.org/addressbook-level3/).
My team, consisting of 5 NUS Computer Science students, developed this application over the course of 6 weeks.

## Summary of Contributions

Here is a summary of my personal contributions to the project.
My code contribution can be found [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=JonahhGohh&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=JonahhGohh&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false).

### Major Enhancements

* UI Enhancement with JavaFX [#119](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/119)
    * **What is it:** Major UI overhaul from [Address Book (Level 3)](https://se-education.org/addressbook-level3/).
    * **Highlights:** Added a list view that reflects the sessions for each student. Restructured UI components.
    * **Justification:** There was a need to display a list view in a list view to support the sessions as they are also
      nested in the student class. To allow for more information to be displayed to the user, restructuring of the UI components,
      especially bringing the command box to the bottom of the application was needed to ensure a smoother flow in the application usage.
* Session reminder [#146](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/146)
    * **What it does:** This feature allows users to view upcoming tuition sessions.
    * **Highlights:** Up to 3 days of tuition sessions can be displayed as a reminder to the user.
    * **Justification:** This feature provides a way for tutors to be reminded of their upcoming tuition sessions.

### Intermediate Enhancements

* Implemented `add_session` command that allows user to add sessions to a particular student. [#59](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/59)
* Added regex validation for Session class attributes. [#46](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/46)

### Minor Enhancements

* Added Session class to TutorBuddy that forms the base class of all sessions. [#43](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/43)
* Modified the help window to include command usage information that allow users to refer quickly. [#169](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/169)

### Other Contributions

* Project Management:
    * Responsible for ensuring code quality of the team by reviewing and scrutinising PRs.
    * Set up Github round-robin reviewing feature
    * Managed milestones for team repository

* Documentation:
    * User Guide
        * Standardisation and thorough review of User Guide [#137](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/137)
        * Added sections on `add_session` and `help` commands, Quick Start, View and Glossary. [#131](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/131)
    * Developer Guide
        * Added implementation details for Add Session Feature [#92](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/92)
        * Added implementation details for Model component [#299](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/299)
        * Added section for Manual Testing [#304](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/304)

* Team-Based Tasks & Community:
    * The full list of PRs I have reviewed can be found [here](https://github.com/AY2021S2-CS2103T-T11-1/tp/pulls?q=reviewed-by%3AJonahhGohh).
