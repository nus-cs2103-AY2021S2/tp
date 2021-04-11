---
layout: page
title: Dinesh S/O Magesvaran's Project Portfolio Page
---

## Project: The Food Diary

The Food Diary is a desktop app for managing food diary entries, optimized with a Command Line Interface (CLI) and
packaged with a Graphical User Interface (GUI). The Food Diary caters to food-passionate NUS students who would ideally
benefit from keeping records of food options tasted in the vicinity of NUS. The Food Diary will allow students to save
time and effort when finding places to eat around the NUS vicinity.

The app is built upon an existing app AddressBook AB3, which is a desktop application used for teaching Software
Engineering Principles. It is written in Java and has about 10-15 kLoC.

Given below are my contributions to the project.

* **Find Feature**: A modified version of the existing find command
    * What it does: The Find Command allows the user to search for Food Diary entries by providing keywords to find with
    * Justification: The existing find command only searched by the entry name, while the modified version searches
    through various entry fields, such as name, rating, price, address, categories & schools. The improved version
    significantly improves the entire product, as there is more flexibility in finding for food options, and helps the
    user better manage a larger number of entries in the Food Diary
    * Highlights: One of the specialities of this feature is the ability to search by price ranges. This was
    significantly harder to implement than the other fields due to the complexity of handling ranges versus a simple
    comparison. However, its implementation greatly improved the ability to perform more complex searches
      

* **FindAll Feature**: A new command that implements a specialised version of the find command
    * What it does: The FindAll Command allows the user to search for specific Food Diary entries by providing keywords
    to find with, with an additional condition to the find command that all keywords must be present in the Food Diary
    entry
    * Justification: The find command returns a Food Diary entry as a search result as long as any of the provided
    keywords match successfully. This is useful when performing generic searches, but falls short when the user wants
    multiple keywords to be satisfied. The FindAll command improves the overall product, by greatly complimenting
    the existing find command to provide the user with more overall flexibility in the way they wish to search for
    food Diary entries
    * Highlights: The FindAll command also features the speciality of searching by price ranges
    

* **Price Feature**: A new FoodDiary entry field
    * What it does: The price field enables users to store a price or price range for a particular food entry
    * Justification: As the target audience of the app are NUS students who are still studying, budget is an important
    concern for them when eating at various places. By introducing the ability for users to log the prices of the
    various food places, it significantly improves the product for the target audience as they can now make more
    informed choices regarding their budget when choosing for a place to eat at
    * Highlights: The price field can contain price ranges, which enables the user to store better price data for future
    reference
      

* **Code Contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=&tabOpen=true&tabType=authorship&tabAuthor=DineshMagesvaran&tabRepo=AY2021S2-CS2103-T14-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)


* **Contributions to the UG**:
    * Update the details for the Find command
    * Update the details for the FindAll command
    * Update the description of the app
    * Update the FAQ
    

* **Contributions to the DG**:
    * Update the description of the app
    * Update the FindAll command details, Use Cases, User Stories
    * Update UML diagrams for FindAll command
  

* **Contributions to team-based tasks**:
    * Helped maintain the issue tracker
    * Refactored parts of the code to fit the new product, with a focus on test code
    * Improved the error messages and tests across various parts of the code
  

* **Mentoring Contributions**:
    * Reviewed PRs: [PRs Reviewed](https://github.com/AY2021S2-CS2103-T14-2/tp/pulls?q=is%3Apr+commenter%3ADineshMagesvaran+)
    * Gave suggestions to narrow down the target audience to be limited to NUS students
    * Gave suggestions to customise the app to specifically meet the needs of the target audience
      * Pre-populate the app with Food Diary entries of food places within NUS for the students to be better informed
        of the food options available to them
      * Add a new price field to help address the needs of students who tend to have limited budgets
