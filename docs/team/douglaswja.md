---
layout: page
title: Douglas Allwood Project Portfolio Page
---

## Project: NUS Module Planner

NUS Module planner is a brownfield project based on AddressBook - Level 3. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

The application is for managing study plans at NUS. Students can create their own study plans to map out what modules to take for each semester to meet their graduation requirements. The application also highlights pre-requisites for each module to ensure that the Student does not miss them out in their planning.

Given below are my contributions to the project:

### New feature: Master Plan and Current Semester Commands
- What they do: Allows users mark one of their study plans as their 'master' one then mark one of their semesters in the master plan as their current one. 

- Justification: The master plan identifies the plan containing modules the student has actually done in the past and intends to do in the future.  The current semester identifies the semester that the student is currently undertaking. Being able to correctly identify this semester allows the app to distinguish between past (previous semesters), present (current semester), and future (prospective semesters) and is a prerequisite for any commands that require knowledge of the student's university progress.

- Highlights: Implementing this feature exposed me to AB3 in an end-to-end manner as its implementation involved user inputs, storage for persistence beyond a single session, and several other internally used classes.

- Credits: The `ModelManager`, `ArgMultiMap`, and JSON Storage, classes were instrumental to my proper implementation of the feature.

### New feature: History Command
- What it does: Shows the user summary information about all semesters prior to the **current semester** of their **master plan**.

- Justification: The History command is important as it allows students to quickly check their progress in the past semesters, and shows the student important summary information such as the total Modular Credits (MCs) they have taken and what their current Cumulative Average Points (CAP) is. (See **Show CAP** for more information)

- Highlights: This feature utilised commands that I implemented (**current semester**, **master plan**, **show MCs**, **show CAP**) highlighting the benefits of these features.

- Credits: The `ModelManager` and `ArgMultiMap` classes and commands that I implemented.

### New feature: Show MCs and Show CAP commands
- What they do: Shows the user relevant information about semesters prior to the **current semester** of their **master plan**.

- Justification: It is important for students to be able to quickly gather accurate information about past semesters as such information is often required when applying for internships or exchange programmes.

- Highlights: This feature utilised commands that I implemented highlighting their benefits.

- Credits: The `ModelManager`, `ArgMultiMap`, and `Ui` classes and commands that I implemented.


### Code Contributed
Please reference [`douglaswja`'s RepoSense](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=douglaswja&tabRepo=AY2021S2-CS2103-W17-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) to view my code contributions.

Notably, I contributed 1114 lines of code to the project.

These are some other code contributions I made:

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
