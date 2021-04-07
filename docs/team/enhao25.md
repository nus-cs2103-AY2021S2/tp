---
layout: page
title: En Hao's Project Portfolio Page
---

## Project: TutorBuddy

TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their students’ contacts, provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation. TutorBuddy is also optimized for fast typing users to handle their day-to-day administrative responsibilities effectively.

## Summary of Contributions
Given below is a summary of my contributions to the TutorBuddy Application.

### Major Enhancement:
* Getting the current month and previous 2 months monthly fees.
  * **What it does:** This feature calculates the current month, and the previous 2 months expected monthly fee based on existing sessions and recurring sessions. We then display the results on the UI accordingly.
  * **Justification:** Independent tutors might want to keep track of their earnings. However, as different lesson could be priced differently,
    we wanted to implement a feature that helps user calculate their expected total current month salary and their previous 2 months
    monthly salary.
  * **Highlights:** Calculation are performed in a separate FeeUtil folders that performs the calculation. Runs a loop for each month to perform and
    calculate the monthly fee from the session list, taking into account whether the sessions are individual sessions or recurring sessions.
    As the monthly fees are expected to show up in the UI, I had to create a list panel, and a card fxml file that represents the monthly fee.
    We had to add a listener to the student list as well, so that whenever the student list updates, the monthly fees would be updated and
    accurate everytime.
  * **Related PR:** [PR #118](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/118) and
    [PR #145](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/145).
* Feature to get a particular student's monthly fee.
    * **What it does:** Fee command to calculate the monthly fee of a particular student in a specific month and year and return the value to the user.
    * **Justification:** When independent tutor collects their monthly fee from the students, it might be difficult for them to keep track of how much would they receive
      when their lessons are charge on a per session basis. Hence, we wanted to create a feature that collects how much would a student have to
      pay in a particular month and year, so TutorBuddy can help the tutor calculate the fee for them instead.
    * **Highlights:** This feature makes use of the methods in FeeUtil to calculate the fees for a particular student. As this is a command based query, I would have to first
      create GetMonthlyFeeCommand class that executes the command, and a parser method for the command. New prefixes are defined, and I
      created 2 additional Month and Year class to enforce certain constraints. Constraint includes that the year must be between 1970 and 2037 (To avoid 2038 problem that can be understood [here](https://en.wikipedia.org/wiki/Year_2038_problem)),
      and month must be between 1 and 12.
    * **Related PR:** [PR #254](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/254), [PR #118](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/118) and
      [PR #145](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/145).
* Refactor the person class from [AB3](https://nus-cs2103-ay2021s2.github.io/tp/) to the student class and enhance
the fields taken to be representative of a student class instead.
    * **What it does:** Refactor the person class from [AB3](https://nus-cs2103-ay2021s2.github.io/tp/) to the student class and enhance
      the fields taken to be representative of a student class instead.
    * **Rationale:** From the base project, we were given the person class to work with. However, as the
      as our project was to be used for students, we will need to refactor all our existing code
      to better represent the application that we are working towards. To achieve that, I volunteered to work on this task
      as it serves as a good learning opportunity for me.
    * **Highlights:** I made use of Intellij to help me with refactoring the application. However, as the code base was large, it was difficult to
      made changes to the application and ensure that the entire application still works as expected. I had to do small adjustments at
      a time and consistently check that the test cases still works. This has allowed me to explore
      all the different components of the application such as storage, model and logic. I further updated some of the
      commands to take in different fields.
    * **Related PR:** [Successful refactor PR #34](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/34) and
      [PR #153](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/153).

### Minor Enhancement:
* Added checks to ensure that start time + duration does not exceed the day itself [PR #176](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/176).
* Combining list_student and list_session command to list command [PR #167](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/167).
* Updating sample data to include sample sessions as well to better reflect our current application [PR #166](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/166).

### Other Contribution:
* Project Management:
    * In charge of ensuring that the project is on track for completion by the given dateline.
* Testing:
    * Written test for many of the classes such as `Month`, `Fee`, `GetMonthlyFeeCommand` and `GetMonthlyFeeCommandParser`.
    * Non-trivial testing PR includes:
      * [PR #128](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/128)
      * [PR #165](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/165)
* Documentation:
  * User Guide:
    * Added explanation for the `FEE` command [PR #85](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/85) and [PR #254](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/254), [PR #255](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/255).
    * Added help and clear command explanation [PR #120](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/120).
    * Added tips for overlapping session and `find_student` command [PR #120](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/120).
    * Updated `find_student` description [PR #70](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/70/files).
  * Developers Guide:
    * Added implementation details for the `add_student` command with the appropriate activity diagram [PR #93](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/93).
    * Updated the application value proposition and added 5 different use cases [PR #19](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/19/files).
  * Codes Contributed:
    * [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=enhao25&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=enhao25&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
