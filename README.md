
# Tema POO  - GwentStone
* Drugea Diana Ioana - 323CA


#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema)

## File Structure

* src/
  * cardsclasses/ - contains all the classes that are related to cards
    * envclasses/ - contains all the classes that are related to Environment cards
      * EnvironmentCard -> the base class used for all the Environment cards
    * heroclasses/ - contains all the classes that are related to Hero cards
      * HeroCard -> the base class used for all the Hero cards
    * minionclasses/ - contains all the classes that are related to Minion cards
      * MinionCard -> the base class used for all the Minion cards
    * Card -> the abstract class used for the cards
    * Deck -> the class used for the deck of cards  
  * constants - contains the class that's related to the constants used in
                the code 
  * checker/ - checker files
  * fileio/ - contains classes used to read data from the json files
  * gameclasses/ - contains all the classes that deal with the logic of the game
    * ActionHandler - the class that deals with parsing the action and calling
                      the specific commands
    * CardCreator - the class that creates every card in the game depending on
                    what's needed
    * Debug - contains the implementation of all the commands that the AI uses
              to show information about the game at specific moments
    * Game - the class for the game itself which implements the logic of one
              game
    * GameCommands - contains the implementation of all the commands that
                    directly affect the game
    * Player -> the class that saves the data about each player
    * PlayerPreparer -> the class that deals with creating the decks and
                        initializing the players for each game
  * main/
* input/ - contains the tests in JSON format
* ref/ - contains all reference output for the tests in JSON format

## The program logic

Once the game starts, the program goes through the input. It checks how many
games we have to play and starts the main loop (present in Main). Once it
initializes the players (at the first game) and makes sure that the
corresponding decks are created, the Game class starts going through the list
of actions, calling the ActionHandler for each of them. This class basically
looks at the action names and calls either Debug or GameCommands, depending
on what type of command is. Later, the two classes deal with each command,
executing the specific input they've got and then put the specific output
for each command.
For example: The ActionHandler detects that the AI wants to see what cards
the player has in their hand. It will call the Debug class and the
"getCardsInHand" command, specificing the player they want to look at.
The function in Debug will put the information in the ObjectNode and then
attach it to the ArrayNode for out output, to later be put in the json file
once the game has ended.

## Flow scheme

Main -> Game -> Game-> PlayerPreparer -> CardCreator -> Card (and every child of it)
                    -> ActionHandler -> Debug -> Card (and every child of it)
                                     -> GameCommands -> Card (and every child of it)
                    -> Player (sent as parameter for most of the commands)

"->" = x calls y class

## Problems encountered

Trying to understand how writing in the Json file works while using the
correct annotations to make the output look as close as possible to the ref ._.

