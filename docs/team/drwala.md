---
layout: page
title: Azeem's Project Portfolio Page
---

## Project: SunRez

SunRez is a resident management system for use in hostels and residential colleges (RCs). The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 12 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to manage rooms (CRUD Rooms)
  * What it does: Allows the user to manage the rooms of the hostel/RC.
  * Justification: This feature is a core feature of SunRez, which allows for the user to CRUD rooms. Without rooms, there would be no hostel to manage. 
  * Highlights: This feature is a core part of SunRez. Significant effort went into the creation of the models, commmands (`oadd`, `oedit`, `odel`, `olist`, `ofind`) and supporting structures (read/write from storage, tests) to ensure that it integrates well with the existing infrastructure. There was also some challenge in dealing with enum values for RoomType to ensure that they are displayed in a user friendly manner but can also be easily added.

* **New Feature**: Worked with Colin to design and plan the development of the Resident-Room allocation feature. There were various alternative implementations and I assisted in working through each possibility to arrive at the one we decided to use.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=DrWala&tabRepo=AY2021S2-CS2103-T14-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed bug triaging, allocation, and fix timelines after PE-D
  * Managed team pacing, priorities, and deadlines for release `v1.4` 
  * Managed release `v1.4` on GitHub

* **Enhancements to existing features**:
  * Updated how command parsing deals with invalid inputs to present clearer messages to the user. [\#330](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/330)
      * This was challenging to do as it was cross-command, and required a heavy refactoring of the `Parser`'s interaction with all commands that use the `INDEX`
  * Wrote additional tests for command parsing to support the change

* **Documentation**:
  * User Guide:
    * Added documentation for all the room features: `oadd`, `oedit`, `olist`, `ofind` and `odel`: [\#150](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/150), [\#289](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/289) 
    * Created a section to explain how command parsing works, how parameter values are processed, and how errors are handled: [\#290](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/290), [\#382](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/382) 
  * Developer Guide:
    * Added implementation of overarching Model component: [\#380](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/380)
    * Added explanation of how the Room class works, and fits in to the broader SunRez system:  [\#115](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/115)
    * Added implementation details of the `AddRoomCommand` command: [\#115](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/115)
    * Wrote use cases UC-006 - UC-010
    * Added manual testing instructions for the Room commands [\#371](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/371)

* **Team tasks**:
  * Assisted in planning and implementation of forking workflow, including the norms the group will agree to
  * Assisted members in using Intellij to manage and resolve merge conflicts
  * Set up system of reviewing PRs within the team to enable a faster PR response time
  * Set up [user stories](https://github.com/AY2021S2-CS2103-T14-1/tp/issues?q=is%3Aissue+author%3Adrwala+as+a) and [tasks](https://github.com/AY2021S2-CS2103-T14-1/tp/issues?q=is%3Aissue+author%3Adrwala+implement) for the team in various sprints
  * Created the script and recorded the demo video for all releases

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#116](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/116), [\#103](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/103), [\#285](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/285), [\#318](https://github.com/AY2021S2-CS2103-T14-1/tp/pull/318); with full list of all [PRs reviewed](https://github.com/AY2021S2-CS2103-T14-1/tp/pulls?q=is%3Apr+reviewed-by%3Adrwala)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/114), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/242), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/254), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/305))
  * Bug reports made for other teams were fully accepted during PE-D: [PE-D](https://github.com/DrWala/ped)
  * Filed [bug reports](https://github.com/AY2021S2-CS2103-T14-1/tp/issues?q=is%3Aissue+author%3Adrwala+bug) for my own team
