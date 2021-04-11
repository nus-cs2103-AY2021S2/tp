---
layout: page
title: Lau Cheuk Ying's Project Portfolio Page
---

## Project: PlanIT

**PlanIT** is an application for managing tasks, tailored specifically for NUS Computing students. Our goal is to improve
students' productivity with features and tools designed to help them manage their schedules effectively and conveniently.
It features simple command formats that are catered towards fast-typists. The user interacts with it using a CLI, 
and it has a GUI created using JavaFX. It is written in Java.



Given below are my contributions to the project.


* **New Feature**: Added the ability remove a field from a task.
    * What it does: Allows the user remove a specific field from a task in the planner.
    * Justification: This feature is important in allowing the users to conveniently manage the details in each task without having to remove the entire task and adding it again.

* **New Feature**: Added the ability to countdown to a task's date.
    * What it does: Allows the user to see how many days there are left to a task's date. 
    * Justification: This feature is important in helping users schedule their time and prioritise their their time better as they will be able to know how 
                      long they have left until a task is happening or due.

* **New Feature**: Added a statistics command that allows the user to view key statistics of the entire planner.
    * What it does: Allows the user to see three key statistics of the planner, namely the total number of tasks, percentage of tasks completed and number of tasks due in the next 7 days.
    * Justification: This feature is helpful for users when they want to know their overall progress, and have a better sense of when they are falling behind on work.
  

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=mesyeux&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=mesyeux&tabRepo=AY2021S2-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)


* **Enhancements to existing features**:
    * Refactored previous command formats to new command formats (Pull request [\#104](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/104/files))
    * Implemented ability of the make task command to accept dates in LocalDate format and time in LocalTime format (Pull request [\#79](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/79/files))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `rmf`, `count` and `stat` (Pull requests [\#113](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/113/files), [\#45](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/45))
        * Replaced previous command formats to new command formats (Pull requests [\#211](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/211/files), [\#104](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/104/files))
        * Fixed minor grammar mistakes [\#212](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/212/files)
    * Developer Guide:
        * Added implementation details of the `rmf` feature with relevant UML diagrams. (Pull requests [\#97](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/97/files), [\#45](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/45))
        * Update use cases for `rmf`, `count` and `stat` commands (Pull request [\#211](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/211/files))


* **Community**:
    * Reported bugs and suggestions for other teams in the module (examples: [1](https://github.com/AY2021S2-CS2103T-T11-4/tp/issues/145), [2](https://github.com/AY2021S2-CS2103T-T11-4/tp/issues/143))
  
