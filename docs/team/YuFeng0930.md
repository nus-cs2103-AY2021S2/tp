---
layout: page
title: Cheng Yu Feng's Project Portfolio Page
---

# Project: SOChedule

## Overview

SOChedule is a one-stop solution for National University of Singapore (NUS) School of Computing (SoC) students to 
manage their tasks and events effectively. 

This app was created by my teammates and I for CS2103 (Software Engineering) module in NUS SoC. As part of the project 
requirements, SOChedule was morphed from a source code given to us at the start of the project (AddressBook Level 3). 
SOChedule is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User 
Interface (GUI) created with JavaFX.


## Summary of Contributions

          
* **New Feature 1**: Clearing expired task/event `clear_expired_task`, `clear_expired_event` (Pull Requests [#177](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/177))
  * What it does: 
    * For tasks, this feature allows users to clear all tasks with past deadline.
    * For events, this feature allows users to clear all events with past end date and time.
  * Justification: 
    * This feature allows users to clear all tasks with past deadline in one shot regardless of whether the tasks are finished or not. 
    * This feature allows users to clear all ended events (with past end date and time) in one shot.

  * Highlights: The implementations of these two functions require the knowledge of implementing a predicate to check with current date time. When implementing clearing expired event, an issue also be raised that the end date and time should be after start date and time, hence I then create a general date time checking predicate that fits in our attribute classes and could be used for other command to do checking as well.

* **New Feature 2**: Clearing completed task `clear_completed_task` (Pull Requests [#177](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/177))
  * What it does: This feature allows users to clear all tasks that have been marked as done.
  * Justification: This feature allows users to clear all completed tasks in one shot regardless of whether the tasks are expired or not. 
  * Highlights: Compared to clear_expired_task, the implementation of this feature is more straightforward. It also involves the usage of predicate to check against the status of the tasks in task list.
    
* **Enhancement Implemented**:
  * Contributed to the transition from AddressBook to SOChedule. (Morphed parsers, remove the dependencies from AddessBook) (Pull Requests [#54](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/54), [#101](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/101))
  * Updated the commands and the respective parsers from `add`, `delete`, `list`, `edit` to `add_event`, `delete_event`, `list_event`, `edit_event`. (Pull Requests [#54](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/54), [#97](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/97), [#205](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/205), [#362](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/362))
  * Updated unit tests for the commands and parsers classes mentioned in the previous point, and added additional unit tests for the new attributes in SOChedule. (Pull Requests [#135](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/135), [#177](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/177), [#205](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/205), [#210](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/210))

* **Code Contributed**:
  * [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=YuFeng0930&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)
  * [Pull Requests](https://github.com/AY2021S2-CS2103-W16-1/tp/pulls?q=is%3Apr+is%3Aclosed+author%3AYuFeng0930)


* **Project Management**:
  * Finished tasks assigned to me on time and commit on a regular basis.
  * Managed the Issues Tracker to ensure tasks are assigned fairly.
  * Brainstormed ideas for different features and design considerations.


* **Documentation**:
  * User Guide:
    * Added documentation, user cases, user stories, feature lists, command summary for `add_event`, `delete_event`, `list_event`, `edit_event`, `clear_completed_task`, `clear_expired_task`, `clear_expired_event` features. (Pull Requests [#35](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/35), [#227](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/227), [#362](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/362))
    * Added sample usage pictures for `clear_completed_task`, `clear_expired_task`, `clear_expired_event` features. (Pull Requests [#325](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/325))
  * Developer Guide: 
    * Added implementation details with sequence diagrams for `add_event`, `delete_event`, `edit_event`, `clear_completed_task`, `clear_expired_task`, `clear_expired_event` features. (Pull requests [#227](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/227), [#345](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/345), [#362](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/362))
    * Added activity diagram, class diagram, design considerations for `edit_event`. (Pull Requests [#325](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/325), [#345](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/345))
    * Added instructions for manual testing `add_event`, `delete_event`, `edit_event`, `clear_completed_task`, `clear_expired_task`, `clear_expired_event` features. (Pull Requests [#345](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/345))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#99](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/99), [#100](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/100), [#118](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/118), [#222](https://github.com/AY2021S2-CS2103-W16-1/tp/pull/222))
    * Contributed to forum discussions (e.g. [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/224#issuecomment-796444725), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/224#issuecomment-796442422), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/205#issuecomment-792211357), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/189#issuecomment-787414474), [5](https://github.com/nus-cs2103-AY2021S2/forum/issues/176#issuecomment-784889867), [6](https://github.com/nus-cs2103-AY2021S2/forum/issues/172#issuecomment-783866467), [7](https://github.com/nus-cs2103-AY2021S2/forum/issues/166#issuecomment-782711860))
    * Reported bugs and suggestions for other teams in the class (e.g. [1](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/137), [2](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/136), [3](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/134), [4](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/133), [5](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/132), [6](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/131), [7](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/130), [8](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/129), [9](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/128))
