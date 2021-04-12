---
layout: page
title: Lee Han Bin's Project Portfolio Page
---

## Project: PlanIT

**PlanIT** is a task scheduling application targeted at NUS computing students. Our goal is to improve students'
productivity with features and tools to help them manage their schedules. It features simple and short commands
that are enhanced for fast typists as well as features which focuses on solving our target audience's problems.

Given below are my contributions to the project.


* **New Feature**: 
  Added the ability to add dates to a given task
  * What it does: allows the user to have deadlines for each task
  * Justification: This feature is one of the core of the product as a task manager requires task to have deadlines
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Testing: Added Unit tests to ensure code coverage and ensures that future changes will still be correct with CI.

* **New Feature**:
  Added the ability to sort dates in the given list of task
  * What it does: allows the user to have see whats the next task that is due
  * Justification: This feature allows user to organise their task based on when it is due, giving users the ability to see due dates in ascending or descending order.
  * Highlights:
   The `sort` feature sorts the tasks based on the different dates of the tasks from the earliest task to the last task in chronological order. For tasks with no dates, they would appear last.
    1. When the command is executed by the user, the input it passed into
      the `LogicManager` and gets parsed and identified in `PlannerParser`.
    2. Upon identifying the relevant `COMMAND_WORD`,
      the corresponding `SortCommandParser` object is formed. The user input then goes
      through another level of parsing in `SortCommandParser`
    3. The `SortCommandParser` identifies the order in which the tasks are to be sort     and creates a
   `SortComparator` comparator object accordingly.
    4. The ```SortCommandParser``` creates a ```SortCommand``` with the above comparator. The command is returned to the ```LogicManager```.
    5. The ```LogicManager``` calls ```SortCommand#execute()```, which adds a new duplicate list of tasks that is sorted in chronological order in ```List``` via the ```Model``` interface.
    6. Finally, a ```CommandResult``` with the relevant feedback is returned to the ```LogicManager```.
  * Testing: Added Unit tests to ensure code coverage and ensures that future changes will still be correct with CI.

* **New Feature**:
  Added the ability to add duration to task
  * What it does: allows the user to have a dedicated duration to finish the task
  * Justification: This feature allows user to cater a period of time for the task
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands since it would have dependencies such as adding a duration would mean a required date to be added first.
  * Testing: Added Unit tests to ensure code coverage and ensures that future changes will still be correct with CI.



* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=habi39&tabRepo=AY2021S2-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed releases `v1.2` - `v1.4` (3 releases) on GitHub
    *	Necessary general code enhancements. e.g. Code Refactoring
    *	Maintain issue tracker.

* **Enhancements to existing features**:
    * Updated phone field to deadline field (Pull requests [\#44](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/44), [\#49](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/49), [\#77](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/77),
      [\#87](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/87))
    * Updated start time to duration (Pull requests [\#95](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/95))
    * Updated deadline to date (Pull requests [\#111](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/111))
    * Wrote additional tests for existing features to increase coverage (Pull requests [\#49](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/49), [\#127](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/127), [\#242](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/242))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `date` and `find` :[\#209](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/209)
        * Added documentation for `sort by a` and `sort by d` feature :[\#119](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/119)
        * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
    * Developer Guide:
        * Added implementation details of the `deadline` feature : [\#86](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/86)
        * Added implementation details of `sort` feature : [\#225](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/225)
  
* **Contribution to team-based tasks**:
  * In charge of ensuring the fork workflow throughout the project.
  * In charge of merging pull requests and ensuring that reviews for PRs are valid.
  * Help set up the GitHub team org and repo.
  * Managed issue tracker, assigning of issues and tagging of issues for PE-D.
  * Organise team discussion to keep the project moving forward
  * Ensured features implemented were user story focused and catered to the needs of users
  

* **Community**:
    * Asked questions in Gitter, one of which was about the convention of code integration.
    * Reported bugs and suggestions for other teams in the module (examples: [1](https://github.com/AY2021S2-CS2103T-W13-3/tp/issues/200), [2](https://github.com/AY2021S2-CS2103T-W13-3/tp/issues/201), [3](https://github.com/AY2021S2-CS2103T-W13-3/tp/issues/196))
    * Provide feedback to peers on their pull requests.
      

* **Tools**:
  * Used JavaFX library and CSS and Canva extensively for the Graphic User Interface.
 

