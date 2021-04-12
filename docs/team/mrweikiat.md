# Overview

## Introduction

**semester.config is a desktop application for managing your tasks.**
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

## Code Contributed
You can see my contributions [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=)

## Enhancements implemented.
1) Implemented the PriorityTag feature for semesterconfig
2) Assist in creating sorting comparator to sort the tasks according to Priority levels
3) Reconfigure Storage component to ensure data are correctly stored

### Enhancement 1
* It allows the user to classify tasks into 3 different category, `LOW/MEDIUM/HIGH`, with `HIGH` having the most priority
* Justification : User can view the different priority on the Ui and each priority levels are color coded distinctively from one another
* Highlights: It was difficult to implement the priority tag feature as we have to take note of the feature ability to be sorted, to handle that issue. I introduced `states` as an attribute to the priority tag class, and assign values to each of those states such that it was easier to implement the comparator feature that was handled by another of my team mate.
* Worked on the PriorityTag files, but are not restricted to
        1. `PriorityTag`
        2. `State`
        3. `Tag`
        4. `AddCommandParser`
        5. `EditCommandParser`
        6. `CliSyntax`
        7. `TaskTrackerParser`
        8. `AddCommand`
        9. `EditCommand`
        10. `ArgumentMultiMap`
        11. `ParserUtil`
        12. `Prefix`

### Enhancement 2
* Sorting the priority tag based on priority levels can help the user view the most prioritised tasks at the top of the task list. 
* Justification : User can better organise and plan which tasks to do first using the priority levels.
* Highlights : User can use the `sort` feature to identify tasks that requires more attention so that user can better organise their tasks.
* Worked on the PriorityTag files and Sorting files, but are not restricted to
        1) `PriorityTagComparator`
        2) `SortCommand`
        3) `SortCommandParser`

### Enhancement 3
* Ensure testing can be properly function after every deliverables to identify any possible bugs.
* Justification : Help to identify possible bugs in newly written features.
* Highlights: Worked on the Storage component files, they are mainly but no restricted to
        1) `JsonAdaptedTag`
        2) `JsonAdaptedTask`
        3) `JsonSerializableTaskTracker`
        4) `JsonTaskTrackerStorage`
        5) `JsonUserPrefsStorage`
        6) `Storage`
        7) `StorageManager`
        8) `TaskTrackerStorage`
        9) `UserPrefsStorage`


## Testing
* I was the IC for testing component. I have to vet the test cases of my team mates and occasionally edit and configure the test cases to ensure that the test cases are well written. 
* I was in charge of testing for the Storage component. I wrote test cases for storage classes they are:
    1. `JsonAdaptedTagTest`
    2. `JsonAdaptedTaskTest`
    3. `JsonSerializableTaskTrackerTest`
    4. `JsonTaskTrackerStorageTest`
    5. `JsonUserPrefsStorageTest`
    6. `StorageManagerTest`
* I also edit and vetted the data component to ensure that the data under data folder used for testing are up-to-date they are mainly but not restricted to:
    1. `data\ConfigUtilTest`
    2. `data\JsonSerializableTaskTrackerTest`
    3. `data\JsonTaskTrackerStorageTest`
    4. `data\sandbox`
* I wrote test cases for Model component correctly they are mainly but not restricted to:
    1. `PriorityTagTest`
    2. `TagTest`
    3. `SortingFlagTest`
* Maintained the `testUtil` folder so that the data stored and used for testing are correct. 

## Contributions to the UG
* Was one of the initial team member to start editing and ensuring UG quality.
* I added the UG component for Priority Tag, the feature that I was in-charge of. 
* I also helped to standardize the team's UG formatting and language. 
* I vetted the UG before every submission to ensure quality. 

## Contributions to the DG
* I was in charge of the Storage component.
    1. Drew up all the UML diagrams related to storage.
    2. Edited all the contents of DG: Storage component. 
* Was one of the inital team member to start editing and ensuring DG quality
* I also helped to standardize the teams's DG formatting and language.
* I vetted the DG before every submission to ensure quality.
* Contributed to the DG, they include but are not restricted to:
    1. Manual Testing component. I wrote every feature for the user who will be using the DG for testings, these includes but are not restricted to:
        * All the feature, commands, test cases and invalid test cases.
        * Ensuring a step-by-step guide that user can use as they read through the DG appendix
    1. Appendix: Effort. I wrote all the difficulties, challenges and achievements for the DG. 
        * Ensuring that our team's effort gets recognised by the evaluators.
        * Ensuring that we document our challenges and diffculties properly. 

## Contributions to team-based tasks
* Refactored person classes in the Person package
* Refactored Storage classes 
* Refactor Tag class
* Worked on Comparator class 
* Worked on Sorting class
* Worked on Command class
* Worked on resolving Ui bugs after practical dry runs
* Helped to standardize UG and DG
* Wrote test cases when possible
* Approve and merge PRs when possible

## Contributions beyond the project team

