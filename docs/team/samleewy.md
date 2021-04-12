---
layout: page
title: Samuel's Project Portfolio Page
---

## Project: TutorBuddy

TutorBuddy is a desktop application made for freelance tutors who give one-to-one tuition to efficiently manage their students’ contacts,
provide a quick overview of scheduled tuition sessions, and handle monthly tuition fees calculation.
TutorBuddy is also optimized for fast typing users to handle their day-to-day administrative responsibilities effectively.

## Summary of Contributions

Given below is a summary of my contributions to the development of TutorBuddy.

### Major Enhancement

- Email Command
    - **What it does**: This feature allows tutors to retrieve a concatenated string of student's email addresses, delimited by a semi-colon `;`.
    - **Justification**: This feature provides a quick way for tutors to send mass emails to all their students using their preferred email client (e.g. Microsoft Outlook).
    All they have to do is simply input the `emails` command, and copy the string of email addresses displayed (e.g.`sam@gmail.com;tom@gmail.com`).
    This saves them the trouble of finding every student's email address.
    - **Highlights**: As this feature involves the use of command, a new command class EmailCommand was created. The logic behind this command is relatively simple:
    looping through the list of students and concatenating their email addresses with StringBuilder. Defensive coding was also employed to ensure that each
    student indeed has an email. Appropriate validations were also present, such as throwing an error when no student has been added and filtering duplicate emails.
    Test cases were also introduced to ensure feature is working as expected and to prevent against regression.
    - **Related PR**: [PR #57](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/57), [PR #69](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/69)

- Calendar Component (UI + Feature)
    - **What it does**: Calendar exist as a standalone tab in TutorBuddy. It provides an intuitive all-in-one experience for tutors to view their tuition sessions throughout the weeks.
    - **Justification**: Initially, TutorBuddy was displaying tuition sessions in a list format. While this is sufficient enough to provide tutors with the approriate information about
    their tuition sessions, it might be counter-intuitive when there is a long list of tuition sessions, or when the tutor wants to view the tuition session he/she has for the particular day or week.
    Therefore, Calendar was introduced to solve this problem.
    - **Highlights**: The Calendar component involved two areas: the creation of the user interface (UI) and the logic of the component for others to interface with. The UI is kept simple: it makes use of Labels
    to indicate dates, Buttons for users to toggle between weeks and ListViews to display the tuition session for the particular day. This involved creation of new FXMLs, CalendarView.fxml and CalendarCard.fxml.
    The former contains the design of the "main frame" while the latter contains the design of each ListView item.
    The logic of the Calendar largely bases itself on the "start date" variable. The start date (Monday) would indicate which week the Calendar has to be on, allowing it to
    populate the relevant dates, as well as the tuition sessions associated with it. Therefore, to view another week, it's as simple as changing the start date. This makes the Calendar Component
    really easy to interface with.
    - **Related PR**: [PR #142](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/142)

### Minor Enhancement
- Modifying JSON to nest sessions within students ([PR #73](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/73))
- Modifying default JSON from `addressbook.json` to `tutorbuddy.json` to move away from AB3 ([PR #149](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/149))
- Modifying default window title from 'Address App' to 'TutorBuddy' ([PR #86](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/86))
- Modifying SessionListPanel to be responsive upon new addition/deletion/update ([PR #68](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/68))

### Other Contribution
- Project Management
    - Responsible for ensuring code quality throughout the whole project (reviewing PRs meticulously)
- Testing
    - Written test cases for `EmailCommand`
- Documentation
    - User Guide
        - Initial conversion from Microsoft Word to Markdown format ([PR #16](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/16))
        - Added explanation for the `emails` command ([PR #69](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/69))
        - Fixed printing header title ([PR #116](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/116))
    - Developer Guide
        - Added implementation details for the `emails` command with appropriate sequence and activity diagram ([PR #101](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/101))
        - Added implementation details for `Storage` component ([PR #298](https://github.com/AY2021S2-CS2103T-T11-1/tp/pull/298))
    - Codes Contributed
        - [RepoSense Link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=samleewy&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=enhao25&tabRepo=AY2021S2-CS2103T-T11-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
