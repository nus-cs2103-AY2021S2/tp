---
layout: page
title: Heng Yong Ming's Project Portfolio Page
---

## Project: Taskify

Taskify is a desktop task management application for university students. Using Taskify, students can
manage all their tasks (academics/personal/CCAs) effectively and seamlessly. The user interacts with it using a CLI,
and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLOC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=hengyongming&tabRepo=AY2021S2-CS2103T-W14-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Added Sort Functionality:
  * What it does: Allow user to sort the tasks in the tasklist.
  * Justification: This feature makes it easier for the user to view all the tasks sorted in ascending date order.
    User can now prioritize their time accordingly.
  * Highlights: The implementation involved some changes to existing logic and addition of commands and test cases.
  * Relevant PRs: [\#37](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/37)

* **New Feature**: Added Tab Switching Functionality:
  * What it does: Allow user to switch between 4 different tabs. There are 4 commands: `home`, `expired`, `uncompleted`, `completed`
  * Justification : A single panel to show all the tasks of a user might be too cluttered. If tasks are separated into their 
    individual status apart from the `home` tab, user can switch between different tabs to better visualize their tasks.
    With this easy tab switching functionality, there is no need for user to scroll through the entire list in the `home` tab just to 
    find a specific task.
  * Highlights:
    This enhancement required an in-depth analysis of design alternatives. A change to the `taskify` structure was also required
    The implementation was challenging as I had to loop through the tasklist and separate the tasks into their respective
    tasklist. Depending on the different tasklist, different action is required. I also had to integrate the tab 
    switching logic into the UI component. This is important as it needs to update the GUI when a tab switching command is executed. 
  * Relevant PRs: [\#68](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/68), [\#84](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/84)
  * Bug fix： [\#82](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/82), [\#147](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/147),
    [\#148](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/148) , [\#179](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/179)

* **Enhancement:** 
  * Enhancement 1:
    * What it does: User cannot edit the current date and time of a task to a past date and time.
    * Justification: It doesn't make sense for user to input a date and time that have already passed.
      
  * Enhancement 2:
    * What it does: Initially, users cannot add a task with the same name into taskify. A task is considered duplicate if
      it's name, description and date and time is the same.
    * Justification: User's task might have the same name, but it's description and date and time might be different.
      Therefore, it doesn't make sense if user cannot add that particular task. This provides more flexibility to the user.
  
* **Morphing of AddressBook3:**
  * Refactor Phone class to Description class: [\#32](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/32)
  * Delete Address class: [\#54](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/54)

* **Test**:
  * Added comprehensive tests for the `home`, `expired`, `uncompleted`, `completed` commands
  * Relevant PRs: [\#90](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/90)


* **Project management**:
  * Setting up GitHub team organisation and repo
  * Managed releases `v1.3(trial)` on [Github](https://github.com/AY2021S2-CS2103T-W14-4/tp/releases/tag/v1.3-trial)
  * Managed the issue tracker and milestones v1.1 - v1.4 (4 milestones) on GitHub.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `home` ,`expired`, `uncompleted`, `completed`: [\#87](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/87/)
    * Added documentation for Target User Profile, User Stories, Non-Functional Requirements: [\#19](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/19)
      , [\#87](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/87/)

  * Developer Guide:
    * Refactor AB3 to Taskify [\#151](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/151)
    * Updated `BetterModelClassDiagram.png`, `DeleteSequenceDiagram.png`, `ArchitectureSequenceDiagram.png`,`LogicClassDiagram.png`
      and `ModelClassDiagram.png` [\#151](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/151)
    * Provide implementation of tab switching functionality [\#194](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/194)

* **Community**:
  * PRs reviewed and merged: [\#174](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/174), [\#160](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/160),
    [\#157](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/157), [\#99](https://github.com/AY2021S2-CS2103T-W14-4/tp/pull/99)
  * Take part in forum discussions (examples:[1](https://github.com/nus-cs2103-AY2021S2/forum/issues/132), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/74)))
  * Reported bugs and suggestions for other teams in the class (examples: [13 bugs/suggestions in ped](https://github.com/hengyongming/ped/issues))
