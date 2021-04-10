---
layout: page
title: Zechariah Tan's Project Portfolio Page
---

## Project: Green Mileage Efforts
Green Mileage Efforts - is a shared desktop application used to make finding groups of people to carpool with easier.
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about
10 kLoC.

Given below are my contributions to the project.

* **New Feature**:
  * 

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=zechariah&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=ZechariahTan&tabRepo=AY2021S2-CS2103T-W10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs)

* **Project management**:
  * Managed releases `v1.4` (1 release) on GitHub

* **Enhancements to existing features**:
  * Model:
    * Added TripDay, TripTime, Person, Driver, and Pool classes to the model to allow for further extension of previous versions
    * Refactored existing code to fit new model format
  * Logic:
    * Added listPool command to fix feature bug where pool list cannot be refreshed to show all pools after filtering the list with findPool command
  * Testing:
    * Added test code for TripDay, TripTime, Person, and Driver classes.
    * Added test code for Pool, and Unpool classes, as well as their respective parsers

* **Documentation**:
  * User Guide:
    * Added documentation for the features `Search` [\#72]()
    * Modified examples and standardised the formatting[\#74]()
    * Added iconography formatting and introduction paragraph
  * About Us:
    * Added relevant profile pictures and modified AboutUs.md to include relevant teammembers information

* **Community**:





* **User Guide Additions**:
  <div markdown="block" class="alert alert-info">
  GreenMileageEfforts (GME) is a platform that helps the HR executive of any company quickly arrange carpooling among its employees in order to lower the carbon footprint of the company.
  </div>
  <div markdown="block" class="alert alert-info">
  1.1 About Green Mileage Efforts

  Green Mileage Efforts(GME) is an efficient carpooling management solution designed to help corporations reduce their carbon footprint. The GME system allows for the simple creation and management of weekly carpooling groups of employees looking to carpool to and from their office. These pools of employees can then carpool from the office regularly on the specified days and times every week. Through the GME system, users can find employees based on their carpooling preferences and quickly group them with drivers. The system also maintains a database of the arranged carpooling groups for easy management.

  GME is a platform that follows a Command-Line Interface (CLI) such that power users that are familiar can efficiently navigate the program.
  </div>
  <div markdown="block" class="alert alert-info">
  #6. Glossary
  Term used | Meaning
  --------|------------------
  Pool | A group of employees carpooling together. Consists of one driver and at least one passenger. The pools generated for a specifc day and time are the same every week unless reorganised by the user.
  Passenger | An employee carpooling with at least one driver.
  TripDay | Day of the intended carpooling trip.
  TripTime | Time of the intended carpooling trip.
  Tag | A miscellaneous piece of information about the pool, passenger, or driver that isn't captured by the other fields but is good to have.
  Command Line Interface (CLI) | An interface that relies primarily on text input and little to no point and click UI elements exist
  </div>
* **Developer Guide Additions**:
  <div markdown="block" class="alert alert-info">
  Design considerations include the `findPool` command being able to be used in conjunction with the `unpool` command. For instance, the user might first use `findPool n/Alice` and then followed by `unpool 1`.
  The `findPool n/Alice` command first filters the list of displayed pools, such that only pools in which there is a passenger named Alice will be displayed. Calling the `unpool` command would then remove the pool specified by the provided indices from the currently displayed list, removing it from the system. The `findPool` command works similarly to the `find` command, except that it currently only supports the use of the name prefix: "/n"

  The activity diagram below encapsulates the user workflow of adding passengers, finding passengers and then pooling the passengers:
  
  ![Activity Diagram for a user using Unpool](../images/UnpoolActivityDiagram.png)
  
  The rationale behind this implementation was because once the GME terminal is populated with a large number of pools, it would be rather difficult for the user to find a specific pool with a specific passenger.
  By allowing the user to first filter the pools before subsequently removing the pool from the filtered list, the findPool feature greatly enhances the unpool feature, thereby making the product much more cohesive as features work well together.
  </div>
  <div markdown="block" class="alert alert-info">
  ### findPool feature
  This feature allows users to find a pool that contains a passenger with a provided keyword in their name.
  
  Given below is the Sequence Diagram for interactions within the Logic component for the `execute("findPool n/Alice")` command.
  ![Interactions Inside the Logic Component for the `findPool n/Alice` Command](../images/FindPoolSequenceDiagram.png)
  
  <div markdown="span" class="alert alert-info">:information_source: **Note:**  The `command` argument that is passed into
  `execute()`, represents the string `"findPool n/Alice"`, and has been abstracted for readability.
  <br>
  The lifeline for `FindPoolCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
  </div>
  
  From the diagram illustrated above:
  1. `LogicManager` has its `execute()` method called when a user enters the `"findPool n/Alice"` command.
  1. `AddressBookParser` class is then created, which subsequently creates `FindPoolCommandParser` class to help parse the user's command.
  1. `AddressBookParser` would then have its `parse()` method invoked to parse the argument `"n/Alice"` and passes it to
     `FindPoolCommandParser`.
  1. `FindPoolCommandParser` parses the argument `"n/Alice"` and creates a PooledPassengerContainsKeywordPredicate which is returned to the FindPoolCommandParser.
  1. `FindPoolCommandParser` then creates a `FindPoolCommand`, and provides the newly created `PooledPassengerContainsKeywordPredicate` to it. The FindPoolCommand object is then returned to LogicManager.
  1. `LogicManager` would subsequently invoke the `execute()` method of the `FindPoolCommand`, which turn calls the `updateFilteredPoolList()` method in `Model`, causing the shown pool list to be updated according to the predicate in the FindPoolCommand object.
  1. A `Pool` object is then created with the list of passengers returned by `getPassengersFromIndexes()`, and then added to the model by the `addPool()` method.
  1. Finally, a `CommandResult` would be returned back to `LogicManager` to indicate the completion status of the command.

  </div> 
