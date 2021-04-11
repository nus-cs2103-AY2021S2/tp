---
layout: page
title: Sylvia Ong's Project Portfolio Page
---

## Project: TutorsPet

TutorsPet is a desktop app designed for private tutors to manage students’ information by helping to improve the efficiency 
and effectiveness of student management by categorizing relevant contact information and keeping track of both lesson schedules
and important dates. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature**: Added a schedule command and schedule GUI, and lesson storage.
  User is able to open a schedule window that displays weekly schedule.
  Pull Requests:  [\#63](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/67), [\#93](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/93)
    [\#103](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/103), [\#114](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/114),
    [\#227](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/227), [#275](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/227)
  * What it does: allows the user to view their weekly schedule of tuition lessons.
  * Justification: This feature adds value to the product because since a user can view their weekly schedule, they can then
    plan their time around this schedule, and they will be more organised since they are well aware of the hectic tuition timings.
    Lessons now also stores a list of person, which enables each lesson to have more than one student.
  * Highlights: The implementation was challenging as it centered around lessons instead of students. 
    Since only the student contacts were stored in storage, I decided to implement a storage for lessons, and modified lessons to 
    have a list of students. In addition, the implementation of this feature caused several bugs, since this feature makes use of an ObservableList,
    which was used by other commands as well.
  * Credits: *{UI code was from AB3}*

* **New Feature**: Added a detection of duplicate lessons during add and edit commands.
  Pull Requests: [\#105](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/105), [\#231](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/231)
  * What it does: the user will be given a choice whether to proceed with 
    adding or editing the contact that will result in duplicate lessons. 
  * Justification: This feature adds value and flexibility to the product because the user will be warned when a contact 
    with the same name or lesson is already in TutorsPet. This gives the user more control over TutorsPet, as the user
    is able to choose whether to have contacts with the same names or lessons. It could also warn the user of duplicate 
    lessons, and the user can rearrange lesson timings with the student.
  * Highlights: The implementation was challenging as it involved many classes, and had quite some bugs due to 
    its complexity. It also required an in-depth analysis of design alternatives.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=sylviaokt&tabRepo=AY2021S2-CS2103T-T11-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false )

* **Project management**:
  * Set up GitHub team repo
  * Managed releases `v1.1` - `v1.3` (3 releases) on GitHub
  * Updated documents not specific to a feature: about us page: [\#24](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/24),
    site-wide settings: [\#14](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/14), 
    glossary for DG: [\#22](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/22)
  * Managed milestones, labels and assignees on github issues and pull requests for team, where necessary.

* **Enhancements to existing features**:
  * Changed the search feature to search using prefixes instead, so that users can search by more specific fields such as name and subject.
    (Pull Requests: [\#43](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/43), [\#61](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/61))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `search` and `schedule`. (Pull Requests: [\#19](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/19), [\#19](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/19), [\#48](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/48), 
      [\#61](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/61), [\#95](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/95), [\#193](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/193))
    * Updated the existing documentation of features for `add`, `edit` to include the detection of duplicate name and lesson. (Pull Request: [\#232](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/232))
  * Developer Guide:
    * Added implementation details and UML diagrams of the `search` and `schedule` feature: (Pull Requests: [\#73](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/73), [\#233](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/233), [\#234](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/234), [\#241](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/241))
    * Added use cases, instructions for manual testing. (Pull Requests: [\#362](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/362), [\#360](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/360))

* **Community**:
  * PRs reviewed: [\#20](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/20), [\#21](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/21), [\#42](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/42), [\#100](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/100), [\#108](https://github.com/AY2021S2-CS2103T-T11-3/tp/pull/108)
  * Reported bugs and suggestions (examples: [1](https://github.com/sylviaokt/ped/issues/1), [2](https://github.com/sylviaokt/ped/issues/2), [3](https://github.com/sylviaokt/ped/issues/3), [4](https://github.com/sylviaokt/ped/issues/4), [5](https://github.com/sylviaokt/ped/issues/5), [6](https://github.com/sylviaokt/ped/issues/6))
