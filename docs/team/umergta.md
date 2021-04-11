---
layout: page
title: Umer's Project Portfolio Page
---

## Project: ModuleBook3.5

ModuleBook3.5 is a desktop module book application used for keeping track of tasks for various NUS modules
in an *Easy, Seamless and Straightforward* manner. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to recur a task.
  * What it does: allows the user to recur tasks either daily, weekly or biweekly.
  * Justification: This feature improves the product significantly as users won't have to add tasks that they have to complete daily, weekly or biweekly.
  * Highlights: Implementation of the feature required modifications to be made to all components and existing commands like `add` and `edit`.
  * Challenges: Designing the recur feature. Initially, I implemented recur such that tasks duplicate and are treated as separate and unique tasks
    with different `doneStatus` set to _notdone_ and `deadline` modified according to the recurrence. However, this resulted in overpopulation of the list of tasks.
    I then decided to implement recur as an attribute of a Task in ModuleBook3.5 that mutates the `deadline` of the task and the `donestatus` accordingly.


* **Modifications to existing features**: Changed the prefixes of the different fields of a Task.
  * What it does: Makes adding of a Task more intuitive for a user.
  * Justification: The prefixes for the fields of AB3 were not related to ModuleBook3.5. As such, they had to be changed to fit our product.
  * Highlights: The feature required modification of the existing commands and how the fields were parsed. 


* **Modifications to existing features**: Changed the way duplicate tasks are handled.
    * What it does: Does not allow users to input a task with `name` and `module` that is the same as any other Task in ModuleBook3.5.
    * Justification: Tasks usually have unique names and are associated to particular modules.
        Hence, to avoid over population, Tasks with the same name and module cannot be added.
<div style="page-break-after: always;"></div>

* **Modifications to existing features**: Made Hours and Minutes field of Time attribute optional for users.
    * What it does: Allow Time to exist in `yyyy-MM-dd` or `yyyy-MM-dd HH:mm` format.
    * Justification: This modification is necessary as users may not know at which exact Hour and Minute should a Task be completed by.
    * Highlight: This feature was tricky as I realized support for parsing of date and time in Java is not great.


* **Project management**:
  * Managed issues on Github issue tracker.
  * Review Pull requests by teammates.
  * Aided in breaking down each iteration and set some deadlines for features that the team was supposed to work on.

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `recur` [\#99](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/99), [\#134](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/134)
    * Modified documentation for the feature `add` [\#75](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/75)
    * Morphed documentation for existing features to remove traces of AB3 [\#52](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/52)
  * Developer Guide:
    * Add sequence diagram and activity diagram for `recur` feature [\#274](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/274)
    * Updated target profile, user stories, value proposition [\#48](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/48)
    * Add use cases for the `recur` feature [\#99](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/99), [\#134](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/134)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#90](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/90), [\#84](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/84), [\#71](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/71)
  * Feature discussion: (Issue [\#136](https://github.com/AY2021S2-CS2103T-T13-2/tp/issues/136))
  
