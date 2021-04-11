---
layout: page
title: Huang Po-Wei's Project Portfolio Page
---

## Project: Link.me

Link.me is a desktop client managing book application aimed for insurance agents to manage their contacts. 
The basic Link.me codebase is adapted from AddressBook Level 3, an address book app created under the Seedu Initiative.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 14 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to schedule meeting.
  * What it does: allows user to schedule meetings with specific clients.
  * Justification: This feature improves the product significantly because it now includes an entirely new aspect -- meeting scheduling into the application.
  * Highlights: This command required a separate list to store meetings. Allowing list displays for the person and the meeting in order of time was mildly tricky.

* **New Feature**: Added a notification function.
  * What it does: reminds the user of upcoming birthdays and meetings.
  * Justification: This feature allows users to be reminds of important events without having to go through the entire list of information.
  * Highlights: This feature required a separate window to be displayed. 

* **Enhancements to existing features**:
  * Did most of the GUI changes from AB-3 to the current version, including integrating of the meeting list, 
    arranged the current layout, and other beautifications, including insurance plan tags.
    
* **Other code contributions**:
  * Fully wrote tests for new features implemented on my part.
  * Came up with the input syntax for combining multiple features into one command.
  * Drafted the OOP implementation framework for notification and notes windows.

* **Code contributed**: 
  [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=georgepwhuang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=georgepwhuang&tabRepo=AY2021S2-CS2103T-W12-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)
* **Documentation**:
  * User Guide:
    * Added the layout introduction section.
    * Drafted the functionality overview.
    * Added documentation for the features `schedule`, `unschedule`, and `notif`.
  * Developer Guide:
    * Added implementation details of the `schedule` and `notif` feature.
    * Drafted three sequence diagrams and updated two class diagrams.
    * Drafted three use cases: adding a person, editing a person, and filtering by tag.
  * Other Documents:
    * Drafted [index.md](index.md).
    * Drafted [README.md](https://github.com/AY2021S2-CS2103T-W12-3/tp#readme).
  
* **Team Tasks**:
  * Came up with the meeting aspect of the application
  * Designed and created the product icon.
  * Maintained the issue tracker
  * Managed releases `v1.3` (1 release) on GitHub

* **Community**:
  * PRs reviewed (comments given in person or offline): 
    [\#12](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/12), 
    [\#21](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/21), 
    [\#26](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/26), 
    [\#37](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/37),
    [\#38](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/38),
    [\#47](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/47),
    [\#52](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/52),
    [\#61](https://github.com/AY2021S2-CS2103T-W12-3/tp/pull/61)
  * Contributed to forum discussions 
    [\#26](https://github.com/nus-cs2103-AY2021S2/forum/issues/26#issuecomment-764430687), 
    [\#55](https://github.com/nus-cs2103-AY2021S2/forum/issues/55#issuecomment-768124175), 
    [\#65](https://github.com/nus-cs2103-AY2021S2/forum/issues/65#issuecomment-769633356), 
    [\#106](https://github.com/nus-cs2103-AY2021S2/forum/issues/106#issuecomment-773179460),
    [\#108](https://github.com/nus-cs2103-AY2021S2/forum/issues/108#issuecomment-773184636),
    [\#145](https://github.com/nus-cs2103-AY2021S2/forum/issues/145#issuecomment-779881367)
  * Reported potential bug in JavaFX [\#267](https://github.com/nus-cs2103-AY2021S2/forum/issues/267)
