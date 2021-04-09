---
layout: page
title: Zhou Yi Kelvin's Project Portfolio Page
---

## Project: iScam (insurance Sure Can Arrange Meeting)

iScam is a desktop location book application used by insurance agents to keep track of their clients and meetings. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 18 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added a meeting list to the iScam user interface.
  * What it does: allows the user to view all the meetings the user has in chronological order. The meeting list will be updated as soon as any meeting is being added/edited/deleted/completed in the CLI.
  * Justification: This feature improves the product significantly because a user can now have an easy way of keeping track of their meetings with various clients and be able to manage their time more effectively.
  * Highlights: This enhancement affects the existing user interfaces and any changes to user interface that were implemented in the future. It required an in-depth understanding of JavaFx and FXML classes. The implementation was manageable as there were existing code in JavaFx and FXML to reference from, but there were still challenges as new techniques such as Observables were required (only in the earlier versions).
  
* **New Feature**: Added meeting cards to the meeting lists to the i-Scam user interface under meeting list.
  * What it does: allows the user to view a singular meeting inside the meeting list. The meeting card will be updated as soon as the meeting is being added/edited/deleted/completed in the CLI.
  * Justification: This feature improves the product significantly because a user can now view the meeting they added/edited/deleted/completed visually, with the relevant information on their screen.
  * Highlights: This enhancement affects the existing user interfaces and any changes to user interface that were implemented in the future. It required an in-depth understanding of JavaFx and FXML classes. The implementation was manageable as there were existing code in JavaFx and FXML to reference from, but there were still challenges as in order to improve the design and appearance of the meetings.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&since=&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false)

* **Project management**:
  * Created and closed relevant issues on GitHub
  * Merged and approved pull request from team members
  
* **Enhancements to existing features**:
  * Increased size of Result Display to allow the error message to be seen fully without scrolling.
  * Updated Client Card to include the insurance plan(s) that the client subscribed to.

* **Documentation**:
  * User Guide:
    * Changed User Guide to suit the content of iScam in Week 7 [\#9](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/9)
    * Added a documentation for the features `listmeet` [\#54](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/54).
    * Added warning to users not to add a corrupted image into the files of iScam [\#205](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/205).
    * Corrected grammatical errors in the User Guide [\#205](https://github.com/AY2021S2-CS2103-W17-4/tp/pull/205).
  * Developer Guide:
    * Drafted the UI diagram (first version).

* **Team Based Tasks**:
  * Created and managed the team Google Drive with essential documents.
  * Performed the demo for both v1.2 and v1.3.
  * Renamed all instances of AddressBook to ClientBook, and Client's Address to Location.
