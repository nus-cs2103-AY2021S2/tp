---
layout: page
title: Samuel Fang's Project Portfolio Page
---

## Project: CoLAB

CoLAB (Collaboration Lab) is a desktop app for university students to manage their school projects. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **Undo/Redo Feature**:
  * Added the ability to undo/redo previous commands(Pull request [\#209](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/209)).
  * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.
  * Highlights: Although a similar implementations exist in other similar projects, I decided to go with my own implementation after considering the pros and cons of each solution. My solution took into account the user experience, and has the ability undo not only the data but also the GUI to the previous state.

* **Command History Feature**:
  * Added a history command that allows the user to navigate to previous commands using up/down keys (Pull request [\#204](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/204)).
  Justification: This feature improves the user experience and makes it easy to execute multiple commands in a row.

* **Redesigned GUI**:
  * Redesigned GUI with new colour scheme and design (Pull requests [\#153](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/153), [\#121](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/121), [\#51](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/51)).
  * Many hours spent considering the user experience.
  * Required designing a modular `UICommands` class to encapsulate data and instructions needed to display different UI panels at the correct time (Pull requests [\#153](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/159), [\#83](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/159)).

* **GUI Testing**:
  * Integrated a third party library [(TestFX)](https://github.com/TestFX/TestFX) to the project (Pull request [\#61](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/61)) to facilitate automated GUI testing.
  * Many hours spent integrating TestFX with github actions.
  * Credits: Address Book 4's [codebase](https://github.com/se-edu/addressbook-level4) was referenced.

* **Other features and enhancements to existing features**:
  * Implemented add project command (Pull request [\#70](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/70)).
  * Implemented help panel with summary of commands (Pull request [\#207](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/207)).
  * Designed projects model (Pull request [\#64](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/64)).
  * Modified existing `AddressBook` class to store `Projects` as well (Pull request [\#175](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/175)).
  * Added various commands to navigate the UI (Pull requests [\#89](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/89), [\#90](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/90)).
  * Implemented sorted ordering for Events, Deadlines, Todos and Groupmates (Pull request [\#239](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/239)).
  * Wrote a test utility to facilitate generation of sample projects (Pull request [\#93](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/93)).

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=samuelfangjw&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=samuelfangjw&tabRepo=AY2021S2-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed releases `v1.1` - `v1.4` (6 releases) on GitHub
  * Managed deadlines and deliverables for the group, including updating the issue tracker and milestones.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `undo/redo` and `add project`
    * Added section on command parameters.
  * Developer Guide:
    * Added implementation details of the `project` feature.

* **Community**:
  * PRs reviewed with non-trivial review comments (some examples: [\#66](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/66#discussion_r590305059), [\#133](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/133#discussion_r596271704), [\#134](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/134#discussion_r596096725))
  * Contributed to forum discussions (some examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/14#issuecomment-762001887), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/166#issuecomment-782832950), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/178#issuecomment-785672104), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/175#issuecomment-784275365))
  * Helped solve classmates issues in forum (some examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/152#issuecomment-780571317), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/180#issuecomment-785819204), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/215#issuecomment-792492685), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/284#issuecomment-812535574))
