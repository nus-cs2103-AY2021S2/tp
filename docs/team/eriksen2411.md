---
layout: page
title: Nguyen Duc Danh's Project Portfolio Page
---

## Project: CoLAB

CoLAB (Collaboration Lab) is a desktop app for university students to manage their school projects. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added a addE command that allows the user to add an event to a project specified by project index.
  * What it does: This feature allows the user to add an event to a project.
  * Justification: This feature improves the product significantly as it is a core part of project management application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it involved the project and event model, which are new models in this version.

* **New Feature**: Added a deleteP command that allows the user to delete a project specified by project index.
  * What it does: This feature allows the user to delete a project from CoLAB.
  * Justification: This feature improves the product significantly as it is a core part of project management application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives.

* **New Feature**: Added a updateP command that allows the user to change project name of a project specified by project index.
  * What it does: allows the user to change project name of a project.
  * Justification: This feature improves the product significantly as it is a core part of project management application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives.

* **New Feature**: Added a updateE command that allows the user to change details of a specified event inside a project specified by project index.
  * What it does: allows the user to change details of an event inside a project.
  * Justification: This feature improves the product significantly as it is a core part of project management application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because the some of the input are optional, which makes the creation of updated event harder to implement. 

* **New Feature**: Added a updateD command that allows the user to change details of a specified deadline inside a project specified by project index.
  * What it does: allows the user to change details of a deadline inside a project.
  * Justification: This feature improves the product significantly as it is a core part of project management application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because the some of the input are optional, which makes the creation of updated event harder to implement. Also, it is important to make sure that the status "isDone" of deadline is not affected.  

* **New Feature**: Added a updateT command that allows the user to change details of a specified Todo inside a Project specified by project index.
  * What it does: allows the user to change details of a todo inside a project.
  * Justification: This feature improves the product significantly as it is a core part of project management application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it is important to make sure that the status "isDone" of todo is not affected.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=eriksen2411&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=Eriksen2411&tabRepo=AY2021S2-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **Project management**:
  * Managed deadlines and deliverables for the group, including updating the issue tracker and milestones.
  * Reassigned milestone for optional issues not done in previous milestone, changed milestone of not essential issues to focus better on the current milestone.

* **Enhancements implemented**:
  * Model
    * Implemented some helper functions inside EventList, TodoList, DeadlineList, Project.
  * Logic
    * Implemented parse function for description, date in ParserUtil.
    * Implemented add event command.
    * Implemented delete project command.
    * Implemented update project/deadline/event/todo command.
  * Testing
    * Added tests for add event command and its respective parsers.
    * Added tests for project command and its respective parsers.
    * Added tests for update project/deadline/event/todo command and their respective parsers.


* **Documentation**:
  * User Guide:
    * Added documentation for the features `updateP`, `updateD`, `updateE`, `updateT`: [\#242](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/242)
    * Updated the format of some parts of the documentation.
    * Discussed and proposed ideas for various parts of the User Guide
    * Checked the User Guide several time to spot errors, did some of those chages (others done by teammate).
  * Developer Guide:
    * Added implementation details of the `addE` feature. [\#158](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/158)
    * Added UML sequence diagram for `addE` feature [\#364](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/364)
    * Discussed and proposed ideas for various parts of the Developer Guide
    * Checked the Developer Guide several time to spot errors, did some of those chages (others done by teammate).

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#70](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/70#discussion_r592522775), [\#89](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/89#discussion_r594836780), [\#92](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/92#discussion_r593797414), [\#136](https://github.com/AY2021S2-CS2103T-T11-2/tp/pull/136#discussion_r600474816)
  * Closed duplicate and redundant issues after reviewing.
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/23), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/260), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/108)

* **Contribution to Other Projects**
  * Helped Team CS2103-W10-4 find bugs during the PE dry run, during which I found and reported 25 errors and gave the team clear description on how to regenerate the bugs.
  * Helped Team CS2103-W10-4 find the root of the bugs and when these bugs occur.
  * Gave my suggestions regarding design and documentation for Team CS2103-W10-4 in my bug reports.
