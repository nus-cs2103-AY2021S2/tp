---
layout: page
title: Heng Yong Ming's Project Portfolio Page
---

## Project: Taskify

Taskify is a desktop task management application for university students. Using Taskify, students can
manage all their tasks (academics/personal/CCAs) effectively and seamlessly. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLOC.

Given below are my contributions to the project.

* ### Major Contributions:
  * #### Added Sort Functionality
    * What it does: Allows users to sort the tasks on hand.
    * Justification: This feature makes it much easier for the user to view all the task sorted in ascending order from the earliest deadline.
      Users can now prioritize their time accordingly.
    * Highlights: The implementation involved some changes to existing logic and addition of commands and test
      cases.
    * Relevant PRs: [\#37](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/37)

  * #### Added Tab Switching Functionality
    * What it does: Allows users to switch between different tabs. There are 4 tabs : `home`, `expired`, `uncompleted`, `completed`
    * Justification : A single panel to show all the task might be too cluttered. If tasks are separated into their individual status,
      users to switch between different tabs.
    * Highlights: This requires modifying a significant portion of the code base. The implementation involved significant changes 
      to existing logic, UI, Model and addition of commands and test cases.
    * Relevant PRs: [\#68](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/68), [\#84](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/84)
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=hengyongming&tabRepo=AY2021S2-CS2103T-W14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Morphing of AddressBook:**
  * Refactor Phone class to Description class: [\#32](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/32)
  * Delete Address class: [\#54](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/54)
  
* **Test**: 
    * Added comprehensive tests for the `home`, `expired`, `uncompleted`, `completed` commands
    * Relevant PRs: [\#90](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/90)


* **Project management**:
    * Setting up GitHub team organisation and repo
    * Managed releases `v1.3(trial)` on [Github](https://github.com/AY2021S2-CS2103T-W14-4/tp/releases/tag/v1.3-trial)
    * Managed the issue tracker and milestones v1.1 - v1.4 (4 milestones) on GitHub.
    * Miscellaneous GitHub related set-up (product website, protected GitHub branch, set up tests, etc.)

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `home` ,`expired`, `uncompleted`, `completed`: [\#87](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/87/)
    * Added documentation for Target User Profile, User Stories, Non-Functional Requirements: [\#19](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/19)
      , [\#87](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/87/)
      
  * Developer Guide:
    *  Refactor AB3 to Taskify [\#151](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/151)
  
* **Community**:
    * PRs reviewed and merged: [\#174](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/174), [\#160](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/160),
    [\#157](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/157), [\#99](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/99)
    * Contributed to forum discussions (examples:)
    * Reported bugs and suggestions for other teams in the class (examples: [13 bugs/suggestions in ped]
      (https://github.com/hengyongming/ped/issues))
