# FilmQueryProject

### Overview

This is a menu-based, command-line application that retrieves film data from a MYSQL database and displays it appropriately.

### How to Use

Upon running the program, you will be greeted with the main menu.  From there, you can choose to retrieve information on a specific film using its film ID number, search for films by title and description keywords, or exit the program. 

##### Main Menu

Please make a selection:
1. Look up a film by its ID
2. Look up a film by a search keyword
3. Exit the Application


**1. Look up a film by its ID** - will ask for a film id, then will proceed to connect to the database, retrieve all the correct film information and will encapsulate it in an object.  The pertinent data will then be printed to the screen.  From there, you can choose to view more information or return to the menu.

**2. Look up a film by a search keyword** - will ask for keyword, then will proceed to connect to the database, search for the relevant keyword in all film titles and descriptions, retrieve all the information for all the matching films and will create objects to contain the data.  The pertinent data for each film will then be printed to the screen.

**3. Exit the Application** - will terminate the program.

*Example output*

Film Id: 981               
Title: WOLVES DESIRE, Release Year: 2000, Rating: NC17                        
Description: A Fast-Paced Drama of a Squirrel And a Robot who must Succumb a Technical Writer in A Manhattan Penthouse                         
Language: English                                 
Cast:                           
Sandra Kilmer                          
Jayne Neeson                        
Cameron Wray                            
Jessica Bailey                           
Morgan Hopkins                         
Greta Keitel                           
Garrett Pipes                        


### Technologies Used

* Java Database Connectivity (JDBC)
* MySQL
* Object-Relational Mapping (ORM)
* Maven
* JUnit 5

