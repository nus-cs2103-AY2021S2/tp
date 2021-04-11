### Difficulties and Challenges

The Difficulty level of implementing PartyPlanet was high, owing mainly to two reasons: 1) refactoring the command set and 2) implementing and integrating the event book.

1. The previous command set had several redundancies. We thus refactored the entire command set to be more concise, cohesive and disjoint, condensing all the operations into the essential `add`, `edit`, `delete` and `list` (along with the event book equivalents) to support a more streamlined and consistent workflow. This is arguably comparable to the implementation for AB3.<br><br>
   In order to create this new and improved command set, we had to think about which commands would facilitate a fast workflow for users. After determining the 4 main commands, we had to craft optional tags to these commands, to fine tune the effects of the command.


2. The difficulty of implementing the event book is self-explanatory. Beyond the difficulties of implementation, we also had to integrate it properly and cohesively with the existing address book, so that PartyPlanet would feel like a refined product, as opposed to an application with two disjoint books.<br><br>
   This integration is facilitated by having both books appear on the same GUI, allowing users to easily cross reference the details of the CCA members or vendors, and then add planning remarks to the events being planned.


### Achievements of the project

* **Concise, cohesive command set:** <br><br>

   The 4 main commands that we ended up with, namely `add`, `edit`, `delete` and `list` (and the event book equivalents) all have disjoint, non-overlapping roles, which helps to streamline a user’s workflow, as it is clear which command to use for a given purpose. Furthermore, the added options help a user to fine tune the effects of the commands to suit their specific purposes, thus augmenting the user-friendliness of our command set.<br><br>
   Once a user is used to the command set and its additional options, this will facilitate effective and efficient party planning.


* **Cohesive product:** <br><br>

   The address book and event books complement each other well, and offer users the ability to gather relevant details easily to plan for a celebration.


*  **User friendly extras to aid workflow:**<br><br>

    In addition to the above, we have implemented some features in PartyPlanet to streamline a user’s workflow. From prompts when a command syntax is not recognized, to `undo` and `redo` commands for the occasional wrong command, the time taken for a user to correct any mistake made is minimized.
    PartyPlanet also provides keyboard shortcuts for `undo` and `redo`, and it includes an input history, accessed with the up and down arrow keys, for users to quickly use commands with a high degree of similarity.
    Overall, we feel that we have overcome the challenges faced well, and PartyPlanet is a cohesive and user friendly application that will help users plan a party that is out of this world.
