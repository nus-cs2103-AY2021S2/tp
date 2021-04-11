---
layout: page
title: Ding You Jia, Danelynn's Project Portfolio Page
---

## Project: SOChedule

SOChedule is a one-stop solution for NUS School of Computing (SoC) students to manage their tasks and events effectively. Targeted at users who can type fast and prefer typing to mouse input, SOChedule is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature 1**: Added the ability to find the free time slots of a given date.
  * What it does: allows the users to get a list of free time slots of a given date.
  * Justification: This enhancement is useful for users to locate the time which they can slot a new task or event in. This also allows users to have an overall view of his or her free time for the day.
    This is especially pertinent when users have a significant number of tasks, and scrolling becomes unpractical.
  * Highlights: The implementation requires knowledge from date structure course, and the logic involved is complex. Many helper functions were implemented in the `UniqueEventList` class.

* **New Feature 2**: Added the ability to get a summary for task completion status and the events happening in the next 7 days.
  * What it does: allows the users to have a summary of task completion status up to current date and the events happening in the next 7 days.
  * Justification: This enhancement is useful for users to have an overview of their progress in completing the tasks as well as what events are going to happen in the next 7 days.
  * Highlights: The implementation was non-trivial as it required several helper functions in counting different types of tasks, namely, `completed tasks`, `overdue tasks` and `tasks to be completed before deadline`,
    to multiple parts of SOChedule. Helper functions in counting the number of events happening in the next 7 days were also implemented.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=icelenaugust&tabRepo=AY2021S2-CS2103-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Ensured tasks are assigned fairly
  * Brainstormed ideas for different features

* **Enhancements to existing features**:
  * Contributed to the overhaul of previous codebase to current needs - Model (Pull requests [\#45](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/45), [\#65](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/65), [\#77](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/77))
  * Continuous addition and update of unit tests throughout development to improve test coverage (Pull requests [\#76](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/76), [\#121](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/121), [\#159](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/159), [\#168](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/168), [\#222](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/222))
  * Updated UI to suit SOChedule (Pull requests [\#107](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/107), [\#115](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/115), [\#185](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/185), [\#196](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/196), [\#204](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/204))
  * Squashed bugs as and when they are found (Pull requests [\#313](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/313), [\#314](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/314), [\#315](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/315))

* **Documentation**:
  * User Guide:
    * Added documentation for `free_time`, `summary`, `clear` features (Pull requests [\#229](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/229))
    * Crafted user stories (Pull requests [\#339](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/339))
  * Developer Guide:
    * Added implementation details of the `free_time`, `summary`, `clear`, `delete_task` features (Pull requests [\#212](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/212), [\#214](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/214))
    * Updated implementation details of `UI` component (Pull requests [\#183](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/183))

* **Community**:
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-T11-2/tp/issues/313), [2](https://github.com/AY2021S2-CS2103T-T11-2/tp/issues/315))
  
