---
layout: page
title: Qiyuan's Project Portfolio Page
---

## Project: ModuleBook3.5

ModuleBook3.5 is a desktop module book application used for keeping track of tasks for various NUS modules
in an *Easy, Seamless and Straightforward* manner. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added the ability to sort the tasks by one attribute.
  * What it does: allows the user to sort the tasks by workload/deadline/module/number of tags and so on.
  * Justification: This feature improves the product significantly because a user may want to see which task he wants to solve firstly. 
  * Highlights: Since this command is not implemented in the AB3, the implementation is a bit challenging which needs going through all the components. 
	Custom compareTo method is used for all attributes of tasks to provide more logical sorting tasks sequence. 

* **New Feature**: Added the ability to show module list and workload analysis feature of each module.
  * What it does: calculates the workload distrubition of tasks with diffrent workload, and allows the user to directly see the list of modules.
  * Justification: This is a key feature of ModuleBook3.5 as the CS student is able to know the distribution of tasks for different workloads of one module.
  * Highlights: The feature requires re-designed the 'Module' feature in the ModuleBook3.5 to monitor the variation of the workload. 

* **New Feature**: Added the ability to visualize the workload distribution among modules.
  * What it does: uses pie chart to directly show the workload distribution.
  * Justification: This is another key feature of ModuleBook3.5 which distinguishes it from other task managment application, as the CS student the workload of different module which makes it easier to manage the tasks.
  * Highlights: The feature requires strong javaFX skills, modifying and decoupling all the components apart from storage.

* **Project management**:
  * Established and maintained the google drive.
  * Scheduled the meeting and the corresponding tasks of each week.

* **Enhancements to existing features**:
  * Morphed the basic features from AddressBook 3 to ModuleBook3.5, such as morphing the attributes of the class Person to the attributes of the class Task.
  * Modifed the GUI layouts and implemented the visulization framework of the application interface.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `sort` [\#82](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/82), [\#130](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/130), [\#218](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/218)
    * Morphed the basic description from AddressBook 3 to ModuleBook3.5 [\#39](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/39)
  * Developer Guide:
    * Updated NFR and glossary parts of ModuleBook3.5 [\#37](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/37), [\#56](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/56)
    * Added the diagram of sort tasks and ModuleManager [\#133](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/133) 
        * SortSequenceDiagram
    	* ModelClassDiagram
    	* UiClassDiagram
    	* WorkloadDistributionClassDiagram
    * Added the descripition of feature Workload Distribution [\#275](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/275) 
    * Updated Use Case for sort [\#268](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/268)

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#90](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/90), [\#84](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/84), [\#64](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/64), [\#83](https://github.com/AY2021S2-CS2103T-T13-2/tp/pull/83)
  * Contributed to forum discussions (examples: [1](https://github.com/nus-cs2103-AY2021S2/forum/issues/137), [2](https://github.com/nus-cs2103-AY2021S2/forum/issues/245), [3](https://github.com/nus-cs2103-AY2021S2/forum/issues/206), [4](https://github.com/nus-cs2103-AY2021S2/forum/issues/15))
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/220), [2](https://github.com/AY2021S2-CS2103T-W10-4/tp/issues/219))
  
