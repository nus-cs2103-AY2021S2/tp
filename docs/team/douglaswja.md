---
layout: page
title: Douglas Allwood Project Portfolio Page
---

## Project: NUS Module Planner

NUS Module planner is a brownfield project based on AddressBook - Level 3, a desktop address book application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

The application is for managing study plans at NUS. Students can create their own study plans to map out what modules to take for each semester to meet their graduation requirements. The application also highlights pre-requisites for each module to ensure that the Student does not miss them out in their planning.

Given below are my contributions to the project:

### New feature: Master Plan Command
- What it does: Allows users mark one of their study plans as their 'master' one.

- Justification: The master plan identifies the modules the student has actually done (in the past) and primarily intends to do (in the future). This feature, in conjunction with the **current semester** command, is a prerequisite for any commands that require knowledge of the student's university progress.  It can be thought of as labelling one's master branch in GitHub.

- Highlights: Implementing this feature exposed me to AB3 in an end-to-end manner as its implementation involved user inputs, storage for persistence beyond a single session, and several other internally used classes.

- Credits: The `ModelManager`, `ArgMultiMap`, and JSON Storage, classes were instrumental to my proper implementation of the feature.

### New feature: Current Semester Command
- What it does: Allow users to mark a semester in their master plan as their current one.

- Justification: The current semester identifies the semester that the student is currently undertaking. Being able to correctly identify this semester allows the app to distinguish between past (previous semesters), present (current semester), and future (prospective semesters). This feature, in conjunction with the **master plan** command, is a prerequisite for any commands that require knowledge of the student's university progress. 

- Highlights: Similar to the master plan command, implementing this feature exposed me to AB3 in an end-to-end manner as its implementation involved user inputs, storage for persistence beyond a single session, and several other internally used classes.

- Credits: The `ModelManager`, `ArgMultiMap`, JSON Storage, and `Semester` classes were instrumental to my proper implementation of the feature. Notably, I implemented the `Semester` class.

### New feature: History Command
- What it does: Shows the user summary information about all semesters prior to the **current semester** of their **master plan**.

- Justification: The History command is important as it allows students to quickly check their progress in the past semesters, and shows the student important summary information such as the total Modular Credits (MCs) they have taken and what their current Cumulative Average Points (CAP) is. (See **Show CAP** for more information)

- Highlights: This feature utilised commands that I implemented (**current semester**, **master plan**, **show MCs**, **show CAP**) highlighting the benefits of these features.

- Credits: The `ModelManager` and `ArgMultiMap` classes and the relevant commands that I implemented.

### New feature: Show MCs Command
- What it does: Shows the user the total number of MCs they have taken in semesters prior to the **current semester** of their **master plan**.

- Justification: It is important for students to be able to quickly check how many MCs they have completed as completing a certain minimum number of MCs is a requirement for graduation from NUS.

- Highlights: This feature utilised commands that I implemented (**current semester**, **master plan**) highlighting the benefits of these features. Additionally, it was then used by the **history** command, demonstrating its importance.

- Credits: The `ModelManager`, `ArgMultiMap`, and `Ui` classes and the relevant commands that I implemented.

### New feature: Show CAP Command
- What it does: Shows the user the total CAP of grades they have gotten for modules taken in semesters prior to the **current semester** of their **master plan**.

- Justification: It is important for students to be able to quickly and accurately check what their CAP is as this information is often required when applying for Exchange Programmes or internships.

- Highlights: Required defensive programming as I had to handle boundary cases such as if the student had not taken any modules and required careful computation to handle for modules with unconventional MC counts (e.g. 5 or 6 MC modules), requiring the weighted average to be carefully computed. 

- Credits: The `ModelManager`, `ArgMultiMap`, and `Ui` classes and the relevant commands that I implemented.


### Code Contributed
Please reference [`douglaswja`'s RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=douglaswja&tabRepo=AY2021S2-CS2103-W17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) to view my code contributions.

Notably, I contributed 1114 lines of code to the project.


### Enhancements implemented
- Wrote tests for some of the features I implemented using both Equivalence Partitioning and Boundary Value Analysis.
- Helped with bug fixing after the NUS Module Planner was tested by several peers.
- Included logging for most of the features I implemented.


### Contributions to the UG
- I wrote the portions of the UserGuide pertaining to the `current semester`, `master plan`, `history`, `show mcs`, and `show cap` commands.


### Contributions to the DG
- Wrote the TOC and included hyperlinks to the referenced portions.
- Explained the implementation of the `history` command and included object diagrams to show how it was structured in relation to other classes, and sequence diagrams to show how it operated when called.
- Structured the DG to match that of AB3's.


### Community
- Teamwork: I personally visited one of my group mates to help him implement a feature he was having troubles implementing by explaining to him how the classes involved worked and what the best way to go about implementing the feature was.
- Forum participation:
  - [Testing: Do we test private methods? #51](https://github.com/nus-cs2103-AY2021S2/forum/issues/51)
  - [Gradle: Unable to create Root Module: config ..., classpath ... #131](https://github.com/nus-cs2103-AY2021S2/forum/issues/131)
  - [Gradle java.lang.UnsupportedOperationException: Unable to open DISPLAY #133](https://github.com/nus-cs2103-AY2021S2/forum/issues/133)


### Team Task Contribution
- Created Milestones for `v1.2`, `mid-v1.2` and `v1.3`
- Maintained the issue tracker
- Finalised the Developer Guide
