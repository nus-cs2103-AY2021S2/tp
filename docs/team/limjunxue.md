---
layout: page 
title: Lim Junxue's Project Portfolio Page
---

## Project: PlanIT

**PlanIT** is a task managing application made specially for NUS computing students. Our goal is to improve students'
productivity with features and tools to help them manage their schedules. It features a calendar to provide a clear
perspective of the user's schedule and common commands that are enhanced for fast typists.

Summary of Contributions:

* **Code contributed**: [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=limjunxue&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **New Feature**: Added the ability to type multiline descriptions in tasks.
    * What it does: allows users to add descriptions to describe their tasks, and use keys to create multiple lines.
    * Justification: This feature improves the use case of adding tasks to the planner because users may want to add
      long descriptions, and it provides a way to format their text better.

* **New Feature**: Created a visual tag list.
    * What it does: Keeps track of the current tags used in the task list.
    * Justification: This feature is important in saving time spent looking for tags to reuse, because tags help to organise
      tasks and the user should not need to scroll through the entire task list to find a tag to reuse.
    * Highlights: This feature required a seperate `UniqueTagList` similar to `UniqueTaskList` stored in the planner of
      the `Model` because many commands can change tags. This is implemented with the Observer's pattern such that the UI
      is able to track its changes through the `Model`.

* **New Feature**: Added a view date command.
    * What it does: allows users to view the tasks that fall on a specific date. Brings the calendar to that date.
    * Justification: This feature is essential in helping users get their schedules for the day, and enables further
      task management and tracking.

* **New Feature**: Created a calendar, and `prev` and `next` commands for navigation.
    * Justification: This feature complements the view day command in providing a clear picture of the tasks on the day
      and aid the user in scheduling for the upcoming days.
    * Highlights: The calendar also uses the Observer's pattern as it implements an `Observer` interface that
      communicates with an `Observable` date that the view day command provides. Thus, the calendar can react to changes
      in this date, or otherwise remain in the same state.

* **Enhancements to existing features**:
    * Made the rendering of fields to task cards dynamic, and refactored `TaskCard` (Pull
      requests: [\#68](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/68)
      , [\#89](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/89))
    * Wrote additional tests for all the above features to increase coverage (Pull request: [\#216](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/216))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `mk`, `edit`, `view` and calendar controls.
        * Repackaged the attributes of `Task` into a neater table.
    * Developer Guide:
        * Updated all class diagrams under "Design" to correspond to PlanIT.
        * Added implementation details of the tag list, and the `view` feature coupled with the calendar.

* **Contribution to team-based tasks**:
    * Renamed all of `AddressBook`/AB3 to `Planner`/PlanIT. (Pull
      request: [\#39](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/39))
    * Renamed `Person` to `Task`, its `Name` field to `Title`, and disabled most fields to get the team started. (Pull
      requests: [\#34](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/35)
      , [\#59](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/59))
    * Made and delivered v1.2 and v1.3 feature demos for tutorials.

* **Community**:
    * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/247))

