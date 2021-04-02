---
layout: page
title: En Hao's Project Portfolio Page
---

## Project: TutorBuddy

TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their students’ contacts, provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation. TutorBuddy is also optimized for fast typing users to handle their day-to-day administrative responsibilities effectively.


## Summary of Contributions
Given below is a summary of my contributions to the TutorBuddy Application.

### Major Enhancement:
* Calculates the previous 3 months monthly fee based on existing sessions and recurring sessions and display them on the UI accordingly.
* Fee command to calculate the monthly fee of a particular student in a specific month and year and return the value to the user.
* Refactor the person class from [AB3](https://nus-cs2103-ay2021s2.github.io/tp/) to the student class and enhance
the fields taken to be representative of a student class instead.
* More information can be found [here](#major-enhancement-explanation)

### Code Contributed: 
[All Codes](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=enhao25&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=enhao25&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)

### Other Contribution:
* Project Management:
    * In charge of ensuring that the project is on track for completion by the given dateline.
* Testing:
    * Written or updated more than 1000 lines of test code for the various TutorBuddy feature, based on [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=enhao25&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=enhao25&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=test-code&authorshipIsBinaryFileTypeChecked=true).
    * Non-trivial testing PR includes:
      * [PR #128](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/128)
      * [PR #165](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/165)
* Sample Data:
    * Updating sample data to better reflect our current application. [PR #166](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/166).
* Documentation:
  * Updated User Guide as explained [here](#contributions-to-the-user-guide).
  * Updated Developers Guide as explained [here](#contributions-to-the-developer-guide).

## Contributions to the User Guide
Given below are the sections that I contributed to the User Guide. This showcase my ability of writing user-centric documentations.



## Contributions to the Developer Guide
Given below are sections I contributed to the Developer Guide. This showcase my ability to write technical documentations and how I chose to implement certain features in TutorBuddy.

### Use Cases Updated:

## Major Enhancement Explanation

### 3 Months Fee Feature:

#### Rationale:
Independent tutors might want to keep track of their earnings. However, as different lesson could be priced differently,
we wanted to implement a feature that helps user calculate their expected total current month salary and their previous 2 month
monthly salary.

#### Implementation Details:
Perform the calculation in a separate FeeUtil folders that performs the calculation. Runs a loop for each month to perform and
calculate the monthly fee from the session list, taking into account whether the sessions are individual sessions or recurring sessions.
As the monthly fees are expected to show up in the UI, I had to create a list panel, and a card fxml file that represents the monthly fee.
We had to add a listener to the student list as well, so that whenever the student list updates, the monthly fees would be updated and
accurate everytime.

The 2 PR that is related to this feature can be found here: [PR #118](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/118) and
[PR #145](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/145).

### Get Particular Student's Monthly Fee Feature:

#### Rationale:
When independent tutor collects their monthly fee from the students, it might be difficult for them to keep track of how much would they receive
when their lessons are charge on a per session basis. Hence, we wanted to create a feature that collects how much would a student have to
pay in a particular month and year, so TutorBuddy can help the tutor calculate the fee for them instead.

#### Implementation Details:
Makes use of the methods in FeeUtil to calculate the fees for a particular student. As this is a command based query, I would have to first 
create GetMonthlyFeeCommand class that executes the command, and a parser method for the command. New prefix are define in CliSnytax.java and I
created 2 additional Month and Year class to enforce certain constraints. Constraint includes that the year must be between 1970 to 2037 (To avoid 2038 problem),
and month must be between 1 and 12.

The 2 PR that is related to this feature can be found here: [PR #118](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/118) and
[PR #145](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/145).

### Person class refactoring to Student class:

#### Rationale:
From the base project, we were given the person class to work with. However, as the 
as our project was to be used for students, we will need to refactor all our existing code
to better represent the application that we are working towards. To achieve that, I volunteered to work on this task
as it serves as a good learning opportunity for me.

#### Implementation Details:
I made use of Intellij to help me with refactoring the application. However, as the code base was large, it was difficult to
made changes to the application and ensure that the entire application still works as expected. I had to do small adjustments at 
a time and consistently check that the test cases still works. This has allowed me to explore
all the different components of the application such as storage, model and logic. I further updated some of the 
commands to take in different fields. 
The PR for the successful refactoring of 118 files can be found [here](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/34).
Other PR related to this includes [PR #153](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/153).
