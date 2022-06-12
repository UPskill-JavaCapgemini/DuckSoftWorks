# Final Project - Language Detection Application 
## DuckSoftWorks & Co.


### Course - UpSkill-Java - Capgemini
Class of January of 2022 to June of 2022

## The Team 

| **Team Members**   |
|--------------------|
| Daniel Lima        |
| Daniel Machado     |
| Inês Clavel        |
| Thales Lemos       |


## Summary

This web application is meant to identify a language of a certain text.

The user will provide an URL to a text file that will be analyzed.

The user should also provide the Category (by a list given) that the text is themed as, and the time limit that they are willing to wait for the task to be completed. The task will be terminated if not cocluded by the time it reaches the limit applied.

The user has the possibility of checking the stutus of the analysis, on request, and the possibility of canceling the task.

The task will start with the status in progress by default and it will analyze the language of the text. That process, at the moment, is made through the Lucene library and the Aspell dictionaries.

When done, it will set the status as finished.


## Resources

### People
| **Role** | **Responsible Entity** |                                       
|:-----------|:-----------|
| Client | Capgemini | 
| Product Owner | Professor Paulo Proença |
| Srum Master | Inês Clavel |
| Repository Team Manager | Daniel Machado |


### Themes Studied
* Aspell - Spell checker with dictionaries
* Lucene - Java tool that tokenizes a text 
* Lingua - Spell checker with dictionaries that use N-Grams
* Tokenization - by words and by N-Grams
* Threads - Multi-threading
* Bootstrap
* Springboot

## Documentation


## Sprints timeline

| Sprint nº  | Start Date | End Date |                                       
|:-----------|:-----------|:---------|
| 1 | 2022-05-19 | 2022-05-30 |
| 2 | 2022-05-19 | 2022-06-15 |
| 3 | 2022-06-17 | 2022-07-01 |

## Tasks Division
The tasks were splited as equally as possible throughout all the team members.

It is descriminted in the Tickets Chart.

## Things to take in consideration
We are using a Remote Oracle Database provided by Professor Nuno Castro.
Please be aware that it might be unavaible during tests. 
Kindly understand the situation as unfortunately this is not under our control.

Also take in consideration that, our database is reseted everytime the code runs for the first time in that machine.
Therefore, some conflicts may happen if more than one person runs the program during the performance of each other.


## Future prospects and possibilities
The project is though with scalability in a way that allows to change from Lucene to other API in a easy way.
