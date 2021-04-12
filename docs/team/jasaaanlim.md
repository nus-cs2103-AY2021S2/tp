---
layout: page
title: Jason Lim Jian Shen's Project Portfolio Page
---

<h1 class="post-title">{{ page.title | escape }}</h1>

## Project: DietLAH!

DietLAH! is a desktop diet-tracking application that uses CLI-based inputs to allow for typists to easily record their meals and track their weight-loss/gain journey.

Given below are my contributions to the project.

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=jasaaanlim&tabRepo=AY2021S2-CS2103T-T12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

* **New Feature**: Implemented the design of FoodIntake and FoodIntakeList models to facilitate food intake recording
    * What it does: Models a food intake in the real world, to be used in the application to handle food intakes and the food intake list of the user.
    * Justification: Based on OOP principles, allows for easier handling and storage of the FoodIntakes and FoodIntakeList.

* **New Feature**: Implemented `food_intake_add`, `food_intake_update`, `food_intake_delete`, `food_intake_query` alongside Soon Wee
    * What it does: Lets the user record their daily food intake using `food_intake_add` (3 variants). Users can also update the nutrient values for existing food intakes using `food_intake_update` and only the specified nutrient value(s) will be updated while the rest remain unchanged. `food_intake_delete` deletes the specified food intake, and re-orders the duplicate count, if any, so that the count is always up-to-date. `food_intake_query` lists the food intakes within the specified period.
    * Justification: The food intake is one of the core features of the macronutrients tracker and stores the user's food intakes and is used by the progress calculator to calculate the progress of the user.

* **New Feature**: Implemented food intake duplicate count enhancement
    * What it does: Appends a duplicate count behind duplicate food intake names e.g. chicken rice, chicken rice #2
    * Justification: Much like file names in macOS and Windows, when there are multiple food intake names with the same name, the user needs an intuitive way to identify them and allow them to quickly delete/update the specific food intake they want. We decided to not use the index for food and foodintake commands because we want the user to be sure of what they are updating, hence, a duplicate count is the easiest way to identify the food intake they are entering.

* **New Feature**: Updated Storage to store the list of FoodIntakes in the FoodIntakeList
    * What it does: Allows for the saving and loading of food intake records from the .json file.
    * Justification: This is required as we want our users to be able to save their food intake lists and regain them when they reopen the application.

* **New Feature**: Implemented part of `TemplateInitializer` alongside Soon Wee + `reset` command
    * What it does: The TemplateInitializer initialises a set of default sample template data used during the first load or when the reset command is used. The reset command resets the data in the application to blank, or the provided sample data. 
    * Justification: When the user loads DietLAH! for the first time or without an existing data, sample data will be loaded to the application to give the user a feel of how it works and test the functionalities. Reset command: At any time, the user may wish to reset the data in the application to either the blank state, or to the sample data provided (e.g. to test out the application for the first time). This provides an easy way to do so, without having to manually edit the .json files which may be unfamiliar to some.

* **Project management**:
    * Created issues for relevant issues and user stories and linked to relevant PRs

* **Documentation**:
	* User Guide:
        * Added documentation for part of food intake features. Reset command, date format, some cosmetic tweaks (PRs [#133](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/133), [#190](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/190), [#110](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/110))
        * Update descriptions to meet user-centric requirement (PRs [#133](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/133))
	* Developer Guide:
        * Added documentation for FoodIntake, FoodIntakeList Object design, food intake and reset features, added UML class and acitvity diagrams (Food, FoodIntake, FoodIntakeList etc.) for some key classes, some cosmetic tweaks (PRs [#192](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/192), [#195](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/195), [#131](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/131), [#110](https://github.com/AY2021S2-CS2103T-T12-2/tp/pull/110))

* **Contributions to team-based tasks**:
    * Raised issues and bugs related to certain features
	* Feedback and approving of team members' pull requests
    * PED bug fixes

* **Community**:
	* Several approved bug reports for other teams: [https://github.com/AY2021S2-CS2103-W16-2/tp/issues?q=is%3Aissue+in%3Abody+jasaaanlim](https://github.com/AY2021S2-CS2103-W16-2/tp/issues?q=is%3Aissue+in%3Abody+jasaaanlim)
