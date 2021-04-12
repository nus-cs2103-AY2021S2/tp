---
layout: page
title: Vevek's Project Portfolio Page
---

## Project: CoLAB

CoLAB (Collaboration Lab) is a desktop app for university students to manage their school projects. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Added add todo/deadline commands.
  * What it does: This feature allows the user to add a todo/deadline to a project.
  * Justification: This feature improves the product significantly as it is a core part of the application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it involved thinking through the implementation of todo/deadline models.

* **New Feature**: Added delete todo/deadline/event commands.
  * What it does: This feature allows the user to delete a todo/deadline/event from a project.
  * Justification: This feature improves the product significantly as it is a core part of the application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it involved the index of the project and the todo/deadline/event within the project.

* **New Feature**: Added mark todo/deadline commands.
  * What it does: This feature allows the user to mark a todo/deadline in a project as done.
  * Justification: This feature improves the product significantly as it is a core part of the application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it involved thinking through the implementation of todo/deadline models as well as the implications of having a todo/deadline marked as done within the context of a project.

* **New Feature**: Added the ability for events to repeat weekly.
  * What it does: This feature allows the user to create an event that repeats weekly with a specified start date and start time.
  * Justification: This feature improves the product significantly as it is a core part of the application.
  * Highlights: This enhancement required an in-depth analysis of design alternatives. The implementation was challenging because it involved thinking through the implementation of event model.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=vevek&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=vevek&tabRepo=AY2021S2-CS2103T-T11-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&since=)

* **Project management**:
  * Managed releases `v1.4` on GitHub (1 release).
  * Managed some deadlines and deliverables.
  * Updated issue tracker and milestones.

* **Enhancements implemented**:
  * Commons
    * Added DateUtil.
    * Added TimeUtil.
    * Added DateConversionException.
    * Added TimeConversionException.
  * Model
    * Added CompletableTodo, CompletableDeadline, Repeatable, Todo, Deadline and Event classes to the model to allow for further extension of previous versions.
    * Refactored existing code to fit new model format.
  * Logic
    * Add todo/deadline command.
    * Delete todo/deadline/event command.
    * Mark todo/deadline command.
  * Testing
    * Added tests for todo/deadline/event models.
    * Added tests for add/delete/mark todo/deadline/event commands and as well as their respective parsers.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Deadlines`, `Events` and `Todos` and other features [\#72]()
    * Did cosmetic tweaks to various parts of the documentation.
    * Update user guide introduction and quick start.
    * Update documentation for the command summary and FAQ.
  * Developer Guide:
    * Added implementation details of the `Deadlines`, `Events` and `Todos` models.

* **Contributions to Team Based Tasks**:
  * Set up the GitHub team org and repo.
  * Set up the issue tracker with required labels.
  * Updated documentation and config for different product.
  * Set up tools.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#338](), [\#270](), [\#252](), [\#227]()
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/175), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/178), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/186), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/161))
  * Reviewed issues to close duplicates.

* **Contribution to Other Projects**:
  * I helped to find bugs for Team CS2103-W16-4 during the PE dry run, during which I found and reported more than 16 errors and gave various suggestions regarding design and documentation.
