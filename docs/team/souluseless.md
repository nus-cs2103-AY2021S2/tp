---
layout: page
title: Jing Kang's Project Portfolio Page
---

## Project: SOChedule

SOChedule is a one-stop solution for NUS School of Computing (SoC) students to manage their tasks and events effectively. Targeted at users who can type fast and prefer typing to mouse input, SOChedule is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

Given below are my contributions to the project.

* **New Feature 1**: Added the ability to sort tasks within the task list.
    * What it does: allows the users to sort the tasks within the task lists based on a select number of allowed parameters.
    * Justification: This enhancement is useful for users to sort tasks based on their preference. This is especially pertinent when users have a significant number of tasks, and scrolling becomes unpractical.
    * Highlights: The implementation was non-trivial as it required significant addition of code to the `Model` class of  SOChedule.
        * Model: Required to update Task to make it implement Comparable<Task> to allow it to be sorted easily using a comparator
        * Implemented a new component, `TaskComparator` to facilitate sorting of tasks.
    
* **New Feature 2**: Added the ability to pin and unpin tasks within the task list
    * What it does: allows the users to pin tasks to the top of task list.
    * Justification: This enhancement is useful for users to re-arrange their tasks. This is especially useful when users have a lot of tasks, and wish to pin one to the very top to prevent it from being left out.
    * Highlights: The implementation was non-trivial as it required addition of code to multiple parts of SOChedule.
        * Model: Added a new parameter to a Task, modified the existing `sort_task` functionality to prioritise pinned tasks
        * Storage: Added a isPinned parameter to the json-adapted Tasks to ensure persistence.
    

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=SoulUseless&tabRepo=AY2021S2-CS2103-W16-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
    * Managed milestones within the group to ensure deliverables are on time
    * Managed the Issues Tracker to ensure tasks are assigned fairly
    * Brainstormed ideas for different features and design considerations

* **Enhancements to existing features**:
    * Contributed to the overhaul of previous codebase to current needs - Model & Storage (Pull requests [\#66](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/66), [\#78](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/78), [\#84](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/84), [\#95](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/95))
    * Continuous addition and update of unit tests throughout development to improve test coverage (Pull requests [\#96](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/96), [\#127](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/127), [\#132](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/132), [\#136](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/136), [\#161](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/161), [\#184](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/184), [\#192](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/192)) 
    * Improve code quality to adhere more closely to coding principles (Pull requests [\#320](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/320))
    * Squashed bugs as and when they are found (Pull requests [\#197](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/197), [\#317](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/317))

* **Documentation**:
    * User Guide:
        * Created and improved readability for feature list and public parameters (Pull requests [\#317](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/317))
        * Added documentation for `sort_task`, `pin_task`, `unpin_task` features (Pull requests [\#161](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/161), [\#192](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/192))
        * Did cosmetic standardisation of user guide formatting (Pull Request [\#44](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/44))
    * Developer Guide:
        * Created a skeleton for developer guide (Pull request [\#161](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/161))
        * Added implementation details of the `sort_task`, `pin_task`, `unpin_task` feature (Pull requests [\#161](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/161), [\#192](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/192))
        * Updated implementation details of `Storage` component (Pull requests [\#161](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/161))

* **Community**:
    * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/120), [2](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/121), [3](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/122), [4](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/124), [5](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/125), [6](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/126), [7](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/127), [8](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/128), [9](https://github.com/AY2021S2-CS2103T-W15-2/tp/issues/123))
