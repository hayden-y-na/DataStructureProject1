• It is likely that your board representation will either consist of one or more linear collections of connectors, or a two-dimensional structure that is first indexed by one endpoint of a connector and then indexed by the other. Indicate which organization more closely matches your solution, and describe why you chose it rather than an alternative.

We decided to use two ArrayLists, one to store all connectors, and the other to store all the corresponding colors. 
Built-in methods of ArrayList, such as get and set, allowed us to store and attain the connectors and colors using index.
It was convenient and straightforward because the matching connectors and colors have the same index.
We thought that this organization is better than two-dimentional structure because 
it is inefficient because it requires the endpoints of the connectors every time we need to index into the structure,
and the number of connectors much less than what is made by the structure.
There are only 15 connectors, but the structure actually has 49 slots if made from the endpoints as index 0 to 6.
In that case, any connector that has index 0 is invalid, and there are too many duplicates.

• Describe the "clever" tactic that you implemented for choosing a move, and explain how it improves the play of the program.

We had many ideas that could possibly be used as a strategy but decided to settle down with the one which takes three conditions; the computer 

1. must avoid forming a triangle with other blue connectors. At the same time it must not interrupt a user making his triangle.

2. If there is no connector fulfilling the first condition, then take a connector that would never form a triangle with other blue connectors and interferes the user with making his red triangle. 

3. choose anything because there is no way to win the user at this point. 

For this strategy, we created three different ArrayLists that store connectors that fit each condition, tempConnectors1, 2, and 3.
If there are any connectors stored in tempConnectors1, choice method returns a random connector from the ArrayList.
It is better not to return the first one on the list because if it does, computer is at a disadvantange 
because it always chooses the connector with 1 as one of its endpoint first because of how the connectors are organized in the beginning 
(connectorStore private instance variable is initialized in the constructor).
Therefore, returning the first one increases the chance of making triangles later.
For the next cases, choice method just returns the first one on the ArrayList, because the order almost means nothing at this point.

• Describe your development sequence—i.e. the steps you took to construct a working pro- gram. What did you code first, and what did you test first? What did you do next? How did you choose test cases at each stage? And so on.

First, we built BoardTest.java for test-driven development.
However, as we gained better understandings of the methods from Board.java,
we modified and added different tests.
Then we finished Board.java, except for choice and isOK.
After it passed the given tests and a few other tests that we added in the beginning in BoardTest.java,
we did toConnector method from Connector.java.
We then worked on choice from Board.java, because we wanted to try playing the game.
At first our strategy was not very developed, so we kept on modifying it.
After that, we worked on ConnectorTest.java and isOK from Board.java.
While working with isOK, we added a new private method called "isFormingTriangle"
because of formsTriangle's constraint that it takes uncolored connector as its first argument.
For ConnectorTest.java, we added the tests based on each case that toConnector method
throws IllegalFormatException. 
For the final version of BoardTest.java, we did not add the tests based on each method.
Instead, we decided to divide tests based on the number of connectors and
included the tests of all the methods in the divided tests.

• Finally, explain what each of your team members contributed to the solution.

We have worked on the project together rather than working on it seperately. 
Since we have been working together, we could fill up each one's weakness as adopting each other's strength. It was really great to have the interaction from both of us. 
For Board.java, we worked on it together except that Yoonho did formsTriangle and provided the idea for isFormingTriangle and Yeji did iterators. 
Yoonho did BoardTest.java and Yeji did ConnectorTest.java and toConnector method.
So we would say that the contribution for the project was equal. 


