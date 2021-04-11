#Overview

**semester.config is a desktop application for managing your tasks.**
While it has a GUI, most of the user interactions happen using a CLI (Command Line Interface).

#Summary Of Contributions

## Code Contributed
You can see my contributions [here](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=)

##Enhancements implemented.
1) I implemented the PriorityTag feature for semesterconfig
2) Assist in creating sorting comparator to sort the tasks according to Priority levels

Enhancements
* It allows the user to classify tasks into 3 different category, `LOW/MEDIUM/HIGH`, with `HIGH` having the most priority 
* User can view the different priority on the Ui and each priority levels are color coded distinctively from one another
* User can use the `sort` command to view the tasks in priority levels 
* It was difficult to implement the priority tag feature as we have to take note of the feature ability to be sorted, to handle that issue. I introduced `states` as an attribute to the priority tag class, and assign values to each of those states such that it was easier to implement the comparator feature that was handled by another of my team mate. 

##Contributions to the UG
Added documentation for `priority tag` feature, as well as standardising the team's markdown and language style 

##Contributions to the DG
* I was incharge of the Storage component and therefore I drew up the Storage UML diagrams on the DG.
* Edited the exisiting UML diagram to fit our new features into the Storage component, taking care of the refactored componenets as well
* Wrote additional information under the Storage component with regards to our new features

##Contributions to team-based tasks
* Refactored person classes in the Person package 
* Helped to standardize UG and DG 
* Wrote test cases for Storage component
* Approve and merge PRs when possible

##Contributions beyond the project team

hello world!
