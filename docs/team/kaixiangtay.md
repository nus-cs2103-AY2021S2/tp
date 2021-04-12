---
layout: page
title: Tay Kai Xiang's Project Portfolio Page
---

## Project: PlanIT

PlanIT is a task managing application made specially for NUS computing students. Our goal is to improve students’ productivity with features and tools to help them manage their schedules. It features simple and short commands that are enhanced for fast typists as well as features which focuses on solving our target audience’s problems.

Given below are my contributions to the project.

* **New Feature**: Added the ability to search a task using description.
  * What it does: Finds tasks from the task list within the planner in PlanIT that has matching description 
    based on the keyword(s) entered by the user and display the matching tasks as a new filtered list to the user.
  * Justification: This feature improves the product significantly because a user can find a task by the word(s) from the multi-line description the task has in the event 
    the user can only remember some words from the description of the task.
  * Highlights: This enhancement is built upon the existing find command without the need of another extra command needed for the user to remember.

* **New Feature**: Added the ability to search a task using single tag or multiple tags.
    * What it does: Finds tasks from the task list within the planner in PlanIT that has matching tag 
      based on the keyword entered by the user and display the matching tasks as a new filtered list to the user.
    * Justification: This feature improves the product significantly because a user can find a task by the category the task is tagged to in the event the user can only remember the tags of the task.
    * Highlights: This enhancement is built upon the existing find command without the need of another extra command needed for the user to remember.

* **New Feature**: Added the ability of a task to be a recurring event that can happen in a weekly or biweekly basis.
  * What it does: Recurring dates up till the end date provided by the user will be generated. 
    The dates are also dependent on the user selection for the week frequency (biweekly or weekly) 
    and day of week (Mon to Sun).
  * Justification: This feature improves the product significantly because a user will be able to have recurring tasks such as lecture, project meetings happening in a weekly or biweekly basis which is a common situation during school semester apart from tasks with deadlines.
  * Highlights: This feature will auto-delete the dates in the recurring task whenever the dates has expired. Once all the dates has expired, the task will not have any recurring dates and the user has the option to reuse the same task by adding deadline, schedule recurring dates for the same task or delete the task from the PlanIT forever.

* **New Feature**: Added the ability to display a list of uncompleted tasks.
  * What it does: Finds tasks with the status `not done` and displays all matching tasks in the output to the user.
  * Justification: This feature improves the product substantially because a user previously can only see all the tasks available in the list. Now, they will be able to see all the uncompleted tasks in a place.
  * Highlights: This enhancement is built upon the existing list command without the need of another extra command needed for the user to remember.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&until=2021-04-08&tabAuthor=kaixiangtay&tabRepo=AY2021S2-CS2103T-T10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

* **Documentation in User Guide**:
  * Added documentation for `ls not done`, `find`, `find t/`, `find d/` and `r/`.
  * In-charge of adding the purpose of each command in the portion `so that` which will
    allows the target user know what can the feature do for him/her.
    
* **Documentation in Developer Guide**:
  * Added sequence and activity diagram of the `find` feature.
  * Added the non-functional requirements, some user stories, use cases and test cases to manual testing.

* **Contributions to team-based tasks**:
    * Managed releases `v1.3 trial`,`v1.3`, `v1.4` on GitHub.
    * Monitored team tasks using Github Project board.
    * Set up issue labels and maintained issue tracker by tagging the issues to the correct team member on Github.
    * Set internal deadlines and managed the milestones of v1.2 to v1.4.
    * Helped to organise the Project Direction documentation at the end of milestones.
    * Necessary general code enhancements: Remove all instances of email attribute from code base since it is not used in PlanIT.

* **Review/mentoring Contributions**:
  * Shared knowledge of regex and how can the regex be used to tighten the constraint check for the user input.
  * Performed testing for the features of the team and identify bugs such as tasks with deadlines cannot be loaded properly
    upon app startup when the deadline has passed so
    gave suggestion to remove the tight constraint check where input date has been passed should be disallowed.
  * Offered the suggestion to implement the planner in a way that can incorporate tasks that are of recurring events
    as well as tasks that are of deadlines together.
  * Recommended detecting leap year, months of 30 and 31 days check for date usages within team project.
  * Advised team members to remove logging at unnecessary places to prevent slow startup of app.
  * Below are some pull requests I have given feedback to the team members:
    [#61](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/61),
    [#77](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/77),
    [#103](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/103),
    [#209](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/209),
    [#213](https://github.com/AY2021S2-CS2103T-T10-2/tp/pull/213)
  
* **Contributions beyond the project team**:
    * Detected bugs as a tester during PED : number of bugs reported is 12.
  
