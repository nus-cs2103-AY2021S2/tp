---
layout: page
title: Princess Priscilla Paulson's Project Portfolio Page
---

## Project: AddressBook Level 3

CakeCollate - Level 3 is a desktop cakecollate application used for teaching Software Engineering principles. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

[comment]: <> (* **New Feature**: Added the ability to always list orders sorted by delivery date)

[comment]: <> (    * What it does: allows the user to undo all previous commands one at a time. Preceding undo commands can be reversed by using the redo command.)

[comment]: <> (    * Justification: This feature improves the product significantly because a user can make mistakes in commands and the app should provide a convenient way to rectify them.)

[comment]: <> (    * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.)

[comment]: <> (    * Credits: *{mention here if you reused any code/ideas from elsewhere or if a third-party library is heavily used in the feature so that a reader can make a more accurate judgement of how much effort went into the feature}*)

* **New Feature**: Added the ability to map order item index in user inputs to order description 
  * What it does: The add command takes in a new parameter of order item indexes (indexes referring to the order items listed on the right of the GUI), which will be then mapped to order descriptions  
  * Justification: This allows the user to save keystrokes if they've already entered a particular order description e.g. `chocolate cake` before.
  * Highlights: This feature required creating an AddOrderDescriptor, and resulted in lots of refactoring for the AddCommand and AddCommandParser, which affected majority of the tests that use addcommand.
  * Credits: The AddOrderDescriptor used was similar to the EditOrderDescriptor, though there are some different methods included.


* **New Feature**: (Related to previous) Added the ability to store order descriptions inputted using `o/` prefix in the order items table * What it does: order descriptions inputted using `o/` by the user are added in the order items table automatically by the app
  * Justification: Users can save some time and effort trying to use `addItem` to populate the order item table 
  * Highlights: This led to the realisation that the `OrderDescription` and `OrderItem` objects were closely related, as whatever `OrderDescription`s were added needed to appear in the `OrderItems` model as an `OrderItem`.  So the validation of `OrderDescription` was changed to refer to the validation constraints of the `OrderItem` field (in `Type.java`). Also this feature broke some existing v1.2/early v1.3 tests that did not preserve this close relationship, e.g. there were some `OrderDescriptions` in the model that did not appear in the `OrderItem` model, so they required fixing.
  * Credits:

* **New Feature**: Added the ability to always list orders by a default comparator (that compares orders by status and delivery date)
  * What it does: Changes to the model always result in a sorting of the orders by its compareTo method, such that the orders on the GUI is always sorted by status and delivery date
  * Justification: This feature improves the usefulness of the GUI to the user immensely as their screen will not be cluttered by `delivered` or `cancelled` orders, or orders that are far into their future. They'll be able to focus on the orders that are more important and urgent to take care of. 
  * Highlights:


[comment]: <> (As the list passed exposed by the CakeCollate model is unmodifiable, there is a need to create a sort method from inside)

[comment]: <> (  * What it does:)

[comment]: <> (  * Justification:)

[comment]: <> (  * Highlights:)

[comment]: <> (  * Credits:)

* **Code contributed**: [RepoSense link]()

* **Project management**:
    * Managed scheduling of meetings and tasks and provided weekly summaries of the tasks due for that week
  * Managed project demo (screenshots) in two releases and set up team org repo

* **Enhancements to existing features**:
    * Added order description field to order 
        * this involved a lot of fixing existing tests involving the `Order` object.
    
* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`. Explained some concepts like `indexes` and quantities in more detail for users[\#226](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/226)
    
  * Developer Guide:
        * Added implementation details of the `add` feature.

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#79](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/79), [\#85](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/85), [\#56](https://github.com/AY2021S2-CS2103T-T11-4/tp/pull/56)
    * Contributed to forum discussions (examples: [211](https://github.com/nus-cs2103-AY2021S2/forum/issues/211))

* _{you can add/remove categories in the list above}_
