---
layout: page
title: Yuanzhe's Project Portfolio Page
---

## Project: ModuleBook3.5

ModuleBook3.5 is a desktop module book application used for keeping track of tasks for various NUS modules
in an *Easy, Seamless and Straightforward* manner. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability add tags to a task.
  * What it does: Allows the user to add tags to the set of existing tags of a task.
  * Justification: This feature improves the product significantly. Before the addition of this feature, user can only
    add tags with editCommand. With editCommand, if a user want to add 1 tag to a task containing 10 tags, he or she must
    re-enter 11 tags. With tagCommand, he or she can just enter 1 tag, and it will be added in to the set of 10 tags.

* **New Feature**: Added optional field to tasks.
  * What it does: Allows optional fields such as startTime and recurrence to exist.
  * Justification: This feature is necessary for the existence of startTime and recurrence. We were not using java Optional
    instead of my implementation of OptionalField. This is because passing java Optional as parameters or as field of class
    is not the intended design.
  * Highlights: Without OptionalField, startTime or recurrence cannot exist.
  
  * **New Feature**: Added startTime field to tasks.
    * What it does: Allows optional fields of startTime to exist.
    * Justification: This feature is necessary as a task may have a start time and a deadline. For example, a lecture.
      With this new feature, users can now add tasks with period.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=t13-2&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19)

* **Project management**:
  * Managed bug reports in issue tracker and google drive.

* **Enhancements to existing features**:
  * Checked and improved code quality of the entire project. (Pull requests [\#110](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/110))
  * Fixed bugs. (Pull requests [\#115](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/115))

* **Documentation**:
  * User Guide:
    * Added user guide for the features `delete`, `done` and `notdone` (Pull request [\#43](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/43/files))
    * Updated all other features implemented by me in the user guide.
  * Developer Guide:
    * Added use cases in developer guide. (Pull request [\#44](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/44))

* **Community**:
  * PRs reviewed (with non-trivial review comments): (Pull request [\#84](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/84))
  * Bug reported (with justification): (Issue [\#136](https://github.com/AY2021S2-CS2103T-T13-2/tp/issues/136))
  
