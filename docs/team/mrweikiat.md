### Introduction

**semester.config is a desktop application for managing your tasks.**
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

You can see my contributions [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=)

#### Enhancements implemented.

*Enhancement 1 : Implemented the PriorityTag feature for semesterconfig*
* It allows the user to classify tasks into 3 different category, `LOW/MEDIUM/HIGH`, with `HIGH` having the most priority
* Justification : User can view the different priority on the Ui and each priority levels are color coded distinctively from one another
* Highlights: It was difficult to implement the priority tag feature as we have to take note of the feature ability to be sorted. I introduced `states` as an attribute to the priority tag class, and assign values to each of those states such that it was easier to implement the comparator feature. Worked on the following files: `PriorityTag`, `State`, `Tag`, `AddCommandParser`, `EditCommandParser`, `CliSyntax`, `TaskTrackerParser`, `AddCommand`, `EditCommand`, `ArguementMutliMap`, `ParserUtil`, `Prefix`.
  
*Enhancement 2 : Assist in creating sorting comparator to sort the tasks according to Priority levels*
* Sorting the priority tag based on priority levels can help the user view the most prioritised tasks at the top of the task list. 
* Justification : User can better organise and plan which tasks to do first using the priority levels.
* Highlights : User can use the `sort` feature to identify tasks that requires more attention so that user can better organise their tasks. Worked on the PriorityTag files and Sorting files: `PriorityTagComparator`, `SortCommand`, `SortCommandParser`.

*Enhancement 3 : Reconfigure Storage component to ensure data are correctly stored*
* Ensure testing can be properly function after every deliverables to identify any possible bugs.
* Justification : Help to identify possible bugs in newly written features.
* Highlights: Worked on the Storage component files: `JsonAdaptedTag`, `JsonAdaptedTask`, `JsonSerializableTaskTracker`, `JsonUserPrefsStorage`, `Storage`, `StoreManager`, `TaskTrackerStorage`, `UserPrefsStorage`.

#### Testing
* I was the IC for testing component. I have to vet the test cases of my team mates before every submission.
* I was in charge of testing for the Storage component. I wrote test cases for storage classes they are: `JsonAdaptedTagTest`, `JsonAdaptedTaskTest`, `JsonSerializableTaskTrackerTest`, `JsonTackTrackerStorageTest`, `JsonUserPrefsStorageTest`, `StorageManagerTest`.
* I also edit and vetted the data component to ensure that the data under data folder used for testing are up-to-date: `data\ConfigUtilTest`, `data\JsonSerializableTaskTrackerTest`, `data\JsonTaskTrackerStorageTest`, `data\sandbox`.
* I wrote test cases for Model component: `PriorityTagTest`, `TagTest`, `SortingFlagTest`
* Maintained the `testUtil` folder so that the data stored and used for testing are correct. 

#### Contributions to the DG and UG
* I added the UG component for Priority Tag, the feature that I was in-charge of. 
* I also helped to standardize the team's UG formatting and language and vetted the UG before every submission to ensure quality.
* I was in charge of the Storage component where I drew up all the UML diagrams related to storage and edited all the contents of DG: Storage component. 
* I also helped to standardize the teams's DG formatting and language and I vetted the DG before every submission to ensure quality.
* Contributed to the DG, they include but are not restricted to:
    1. Manual Testing component. I wrote every feature for manual testing on the DG.
    1. Appendix: Effort. I wrote all the difficulties, challenges and achievements for the DG. 

#### Contributions to team-based tasks
* Refactored person classes in the Person package, refactored storage classes, refactor tag classes.
* Worked on Comparator class, sorting class, Tag class, Command class, Parser class
* Worked on resolving Ui bugs after practical dry runs
* Helped to standardize UG and DG, wrote test cases when possible, approve and merge PRs when possible
