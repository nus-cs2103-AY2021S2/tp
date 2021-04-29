---
layout: page
title: En Hao's Project Portfolio Page
---

## Project: TutorBuddy

TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their students’ contacts, provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation. TutorBuddy is also optimized for fast typing users to handle their day-to-day administrative responsibilities effectively.

## Summary of Contributions
Given below is a summary of my contributions to the development of TutorBuddy.

### Major Enhancement:
* Getting the current month and previous 2 months monthly fees.
  * **What it does:** This feature calculates the current month, and the previous 2 months expected monthly fee based on existing sessions and recurring sessions. We then display the results on the UI accordingly.
  * **Highlights:** Calculation are performed in a separate FeeUtil class. It performs the calculation using a loop and takes into account whether the sessions are individual sessions or recurring sessions.
    As the monthly fees are expected to show up in the UI, I had to create a list panel, and a card fxml file that represents the monthly fee.
    A student list's listener was added, to enforce accuracy, even when the student list was updated.
  * **Related PR:** [PR #118](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/118) and
    [PR #145](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/145).
* Feature to get a particular student's monthly fee.
    * **What it does:** Fee command to calculate the monthly fee of a particular student in a specific month and year and return the value to the user.
    * **Highlights:** This feature makes use of the methods in FeeUtil to calculate the fees for a particular student. As this is a command based query, I would have to first
      create GetMonthlyFeeCommand class that executes the command, and a parser method for the command. New prefixes are defined, and the
      Month and Year class was created to enforce our project constraints.
    * **Related PR:** [PR #254](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/254), [PR #118](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/118) and
      [PR #145](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/145).
* Refactor the person class from [AB3](https://nus-cs2103-ay2021s2.github.io/tp/) to the student class and enhance
the fields taken to be representative of a student class instead.
    * **What it does:** Refactor the person class from [AB3](https://nus-cs2103-ay2021s2.github.io/tp/) to the student class and enhance
      the fields taken to be representative of a student class instead.
    * **Highlights:** I made use of Intellij to help me with refactoring the application. However, as the code base was large, it was difficult to
      made changes to the application and ensure that the entire application still works as expected. I had to do small adjustments at
      a time and consistently check that the test cases still works. This has allowed me to explore all the different components of the application such as storage, model and logic.
    * **Related PR:** [Successful refactor PR #34](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/34) and
      [PR #153](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/153).

### Minor Enhancement:
* Added checks to ensure that start time + duration does not exceed the day itself [PR #176](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/176).
* Combining list_student and list_session command to list command [PR #167](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/167).
* Updating sample data to include sample sessions as well to better reflect our current application [PR #166](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/166).

### Other Contribution:
* Testing:
    * Written test for many of the classes such as `Month`, `Fee`, `GetMonthlyFeeCommand` and `GetMonthlyFeeCommandParser`.
    * Non-trivial testing PR includes: [PR #128](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/128), [PR #165](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/165), [PR #271](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/271)
* Documentation:
  * User Guide:
    * Added explanation for the `Fee` command [PR #85](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/85), [PR #254](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/254) and [PR #255](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/255).
    * Added help and clear command explanation [PR #120](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/120).
    * Added tips for overlapping session and `find_student` command [PR #120](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/120).
    * Updated `find_student` description [PR #70](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/70/files).
    * Added the screenshots of student commands, fees and sample data [PR #311](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/311).
    * Improved overall user guide quality based on comments [PR #273](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/273) and [PR #302](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/302).
  * Developers Guide:
    * Added implementation details for the `add_student` command with the appropriate activity diagram [PR #93](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/93) and [PR #308](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/308).
    * Updated the application value proposition and use cases [PR #19](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/19/files), [PR #270](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/270) and [PR #279](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/279).
    * Updated implementation details for students and fee command [PR #274](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/274).
* [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=enhao25&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=enhao25&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
