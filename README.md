# Event Planner

##### A website to help Hack-A-Thon coordinators keep track of their teams.

##### By _**Pablo Barreyro**_

### Description

This application will provide would-be attendees of a hypothetical Hack-a-Thon event with information about the event and its teams, as well as allow them to edit team details and add members.
#### Specifications

##### Backend logic
| Behavior | Input | Output |
| --- | --- | --- |
| Instantiate a team with name, members and description | "Aqua PDX"<br>"Carlos Dos Santos"<br>"Internet-of-things app to help farmers track real-time soil moisture info" | models.Team: Aqua PDX<br>Members:<ul><li>Carlos Dos Santos</li></ul>Product:Internet-of-things app to help farmers track real-time soil moisture info. |
| Add members to team | "Linda Rosaros" | models.Team: Aqua PDX<br>Members:<ul><li>Carlos Dos Santos</li><li>Linda Rosaros</li></ul><br>Product:Internet-of-things app to help farmers track real-time soil moisture info. |
| Update team name | "AquaPDX" | models.Team: AquaPDX<br>Members:<ul><li>Carlos Dos Santos</li><li>Linda Rosaros</li></ul><br>Product:Internet-of-things app to help farmers track real-time soil moisture info. |


##### RESTful Frontend
| Endpoint | Method | Description |
| --- | --- | --- |
| /teams | GET | Show all teams  |
| /teams/new  | POST | Create new team |
| /teams/:id  | GET | Show single team by ID |
| /teams/:id/update  | POST | Update single team |



### Setup/Installation Requirements

* Clone [this repo](https://github.com/Pabarreyro/hack-planner)
* Install Java [SDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) & [JRE](http://www.java.com/en/)
* Run App.java in local IDE
* Install [handlebars](http://handlebarsjs.com/)

### Known Bugs

N/A

### Support and contact details

For questions or to report a bug, start issue in this repo.

### Technologies Used

* Java 1.8
* [Spark](http://sparkjava.com/) 2.6.5


### License

This software is licensed under the MIT license.

Copyright © 2018 **Pablo Barreyro**
