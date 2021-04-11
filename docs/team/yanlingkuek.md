---
layout: page
title: Kuek Yan Ling's Project Portfolio Page
---

## Project: EzManage

EzManage - EzManage is a desktop address book application used for the management of a tuition centre's sessions, students and tutors. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added person ID.
  * What it does: Allows the user to identify persons using a unique ID given to each person when the person is added into EzManage.
  * Justification: This feature improves the product significantly because the user can easily differentiate between a student and a tutor based on the person ID and the user can uniquely identify each person using a unique person ID. This feature is important because the user will need to use the person ID to use all the other person-related features in EzManage.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. 
  * Credits: AddressBook Level 3
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=yanlingkuek&tabRepo=AY2021S2-CS2103-W16-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Enhancements to existing features**:
  * Wrote tests for person ID, delete person and edit person features. (Pull requests [\#183](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/183), [\#72](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/72), [\#181](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/181))
  * Updated the delete person command to be able to delete a person based on their unique person ID and to prevent the person from being deleted if the person is currently assigned to a session. (Pull Request [\#178](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/178))
  * Updated the edit person command to be able to edit a person based on their unique person ID and to prevent the person from being edited if the person is currently assigned to a session. (Pull Request [\#72](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/72))
  * Updated the delete session command to prevent the session from being deleted if students or tutors are currently assigned to the session. (Pull Request [\#176](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/176))
  * Updated the Ui to enable the switching of panes. (Pull Request [\#48](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/48))

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete_person` and `edit_person`. (Pull Request [\#47](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/47), [\#90](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/90))
  * Developer Guide:
    * Added implementation details of the `delete_person` feature. (Pull Request [\#96](https://github.com/AY2021S2-CS2103-W16-4/tp/pull/96))

* **Team-Based Tasks**:
  * Managed releases `v1.1` (1 release) on GitHub
  * Maintained the issue tracker and milestones
  * Updated links and general information about EzManage (e.g. naming, description) on the team website

* **Community**:
  * Reported bugs and suggestions for other teams in the PE Dry Run (Reported 13 bugs).
