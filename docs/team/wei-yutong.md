---
layout: page
title: Wei Yutong's Project Portfolio Page
---

## Project: Pawbook

Pawbook is a desktop application for dog school managers to facilitate their bookkeeping of puppies and dogs in the
school, optimized for use via a **Command Line Interface (CLI)** which caters to fast-typers who prefer to use a
keyboard. You can navigate the application with ease and execute instructions by typing text-based commands in the
command box provided.

Given below are my contributions to the project.

* **New Feature**: Enrol Command
    * Implemented the Enrol Command that allows users to enrol specified dogs into specified programs using the respective
    Dog IDs and Program IDs.
    * In the process, I implemented `EnrolCommand`, `ProgramRegistrationCommand` and `EnrolDropCommandParser`.
    * After my teammate Shaelyn implemented `DropCommand`, we realized that there were a lot of overlaps between `EnrolCommand`
    and `DropCommand`, hence I abstracted out the common parts into `ProgramRegistrationCommand` to improve code quality by
    reducing duplicate code.
    * Adds Dog IDs into the respective Programs, identifies whether dogs were already previously enrolled.
    * Supports batch enrolment, i.e. enrolling one dog into multiple programs and multiple dogs into one program.

* **Code Refactoring**: Renamed `Person` to `Owner` on a project wide scale
    * This kicked off our feature where we support multiple entities instead of just storing `Person` as the only one.
    * In the process, I also modified the argument parser to accept a second keyword to support commands like `add owner`
    and `delete program` etc. This is a highlight of our project as we maximize the efficiency of our code by extending
    the base classes of `AddCommand` and `DeleteCommand` for each of the 3 entities, and parse the second keyword to
    identify the specific entity.

* **Code Testing** *: Contributed significantly to testing
    * Wrote a significant amount of test cases throughout the project.
    * Wrote multiple test cases in the logic section, such as `AddDogCommandTest`, `DeleteDogCommandTest`, `EnrolCommandTest`
    and their respective parser tests, as well as `ParserUtilTest` and `DropParserTest`.
    * Added testutil for `DogBuilder`, `DogUtil` and `TypicalEntities`, which allowed the testing of dog related commands
    to begin.
    * Added `ProgramTest` and `SessionTest` under model.
    
* **Enhancements to existing features**: Help Command
    * Made minor changes to suit `HelpCommand` for Pawbook.
    
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=2021-02-19&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=wei-yutong&tabRepo=AY2021S2-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=)

* **Project management**:
    * Assigned issues to team members.
    * Checked tP dashboard regularly to ensure we were on track.
    * Took notes for team meetings.

* **Enhancements to existing features**: Help Command
    * Updated the existing `HelpCommand` to show our user guide link, as well as included an informative screenshot of
    command instructions.

* **Documentation**:
    * User Guide:
    * Contributed significantly to Enrol Command and Help Command, and edited other commands to ensure
  cohesivenss. 
    * Improved the introduction of the user guide.

  * Developer Guide:
    * Completed Enrol Command and Help Command, and their respective activity diagrams and seqeunce diagram.
    * Added test cases for Enrol and Drop command for manual testing.
    * Contributed to user stories.

* **Community**:
    * Reviewed and merged pull requests of other teammates.
    * Reported bugs and provided suggestions to other teams in the class.
