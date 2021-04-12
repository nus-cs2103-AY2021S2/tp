shizheng001.md---
layout: page
title: Shi Zheng's Project Portfolio Page
---

## Project: Car@leads

#Overview Of Product

Car@leads is a **desktop app for a car salesperson to manage customer contacts** (CLI), to facilitate easy access to
clients and enabling them to maintain a good relationship with their clients without hassle.

## Contribution

This is the [code](https://nus-cs2103-ay2021s2.github.io/tp-dashboard/?search=shizheng&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-02-19&tabOpen=true&tabType=authorship&tabAuthor=rajobasu&tabRepo=AY2021S2-CS2103T-W12-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false) I contributed.

- I have implemented the email interface to allow for user to send emails via a simplified interface. The email functions opens an interface that is adapted for car sales and comes with a prefilled text to help safe time. The email feature uses fxml for interface and javax mail api library for smtp connection for login authentication to gmail. 

- I have also implemented the email command which allows for opening of email interface through keyboard typing.

- I have also updated the relevant classes which are required for a functional email feature.

- I have also implemented some filters such as the address filter and the tag filter.

- I enhanced the delete function to delete customer in the contact list base on his name rather than index, this is more simplified as one may simply delete the customer with a matching name typed in. As a salesperson, user may have hundreds of contacts in his list, and it will be troublesome for him to scroll through the list or to use filter function to first filter the list then delete by index. 

- In the UG, I have done GUI testing and make sure all example commands works well and added on details and screenshots to provided better explanations. I have also updated delete function and added email feature in UG.

- In the DG, I have added Filter package and email window into class diagrams.
