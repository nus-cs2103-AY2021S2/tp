---
layout: page
title: Friscilia Sultan's Project Portfolio Page
---
## Project: insurance Sure Can Arrange Meeting (iScam)

iScam is a desktop location book application used for teaching Software Engineering principles.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 18 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to search client according to their insurance plans.
  * What it does: Allows the insurance agent to find their clients filtered by insurance plans.
  * Justification: This feature improves the product significantly because a user can filter their clients according to insurance plans
  so that it is easier to decide which clients to contact for upselling.
  * Highlights:
    * This enhancement adds a new command to the existing set of Client commands.
    It requires an in-depth understanding of the existing flow of function call from UI, Logic and ClientBook model
    as well as the dependencies between the LogicManager, Command Parser, ArgumentMultiMap, ArgumentTokenizer, ParserUtil,
    Command and CommandResult classes.

* **New Feature**: Added the ability to convert Meeting class into JSON format.
  * What it does: Allows the conversion of Meeting into JSON format for saving in local storage as a JSON file.
  * Justification: This feature improves the product significantly because a user can convert all their meetings into JSON
  so that they can store it and retrieve it easily. It also allows more advance users to directly edit the Meeting data from the JSON file,
  expanding the functionality of iScam beyond GUI to the data files in the codebase.
  It further increases the suitability of iScam to fast-typing users, ensuring that they can operate on their meetings faster than a typical GUI application.
  * Highlights:
    * This enhancement affects all instances of the meeting model. It requires an in-depth understanding of the Storage component.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=frisciliasultan&sort=totalCommits%20dsc&sortWithin=totalCommits%20dsc&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=zoom&zA=frisciliasultan&zR=AY2021S2-CS2103-W17-4%2Ftp%5Bmaster%5D&zACS=227.62426822778073&zS=2021-02-19&zFS=&zU=2021-04-10&zMG=false&zFTF=commit&zFGS=groupByRepos&zFR=false)

* **Team Based Tasks**:
  * Add the Branch protection rule to require at least 1 review and prevent merging if any of the checks fails.
  * Created issues to ensure that the milestones are broken down to achievable parts and improves the clarity of what exactly are the to-do list of the iteration. ([\#4](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/4),
  [\#24](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/24), [\#51](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/51), [\#183](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/183), [\#223](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/223),
  [\#224](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/224), [\#225](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/225), [\#229](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/229),
  [\#232](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/232), [\#233](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/233), [\#234](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/234),
  [\#236](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/236), [\#239](https://github.com/AY2021S2-CS2103-W17-4/tp/issues/239))

* **Enhancements to existing features**:
  * Wrote tests for Meeting command classes and addMeeting integration test to significantly increase test coverage on the Meetings feature,
  increasing coverage by 11.25% from 55.87% to 67.12%. ([\#222](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/222))
  * Wrote tests for added feature findPlansCommand and findPlansCommandParser, increasing coverage from 66.87% to 67.58%. ([\#242](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/222))
  * Integrate Insurance Plan field in Client with the existing Client commands and make it optional with the use of Java Optional class.
  Configured EditCommand to remove insurance plan if "/ip" without plan name is specified.
  * Fix the display of all error message to show exactly which parameter causes the error. ([\#189](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/189))
  * Increase the index exception handling to handle numbers beyond the maximum integer value, blank index and non-integers. ([\#189](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/189))

* **Documentation**:
  * User Guide:
    * Wrote and maintained all the Client features under the `Features` section. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
    * Update the screenshots to reflect the UI at the moment. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
    * Update the Command Summary Table for both client and meeting commands. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
    * Fix general user instructions to match the updated iScam book ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
  * Developer Guide:
    * Wrote and maintained the target user profile, value proposition, user stories and sort them according to the priority of `must have`, `nice to have` and `unlikely to have`. ([\#8](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/8), [\#270](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/270))
    * Added use-cases for Client commands. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
  * README:
    * Update the README description and user information to fit the final product and functionality of iScam. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
    * Update the screenshots to match the latest UI. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))
  * Index Page:
    * Update the description of iScam to fit the final product and functionality of iScam. ([\#83](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/83))

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#179](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/179), [\#187](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/187), [\#195](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/195), [\#240](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/240)
