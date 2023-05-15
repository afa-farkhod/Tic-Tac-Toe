# Tic-Tac-Toe
Tic-Tac-Toe Game developed on Java with JavaFX package.

Game is played between two players 'X' and 'O'

![image](https://user-images.githubusercontent.com/24220136/230802954-b721df7b-ff41-4857-8254-d0cf720e3b5d.png)

'X' starts the game, whichever side gets three figure in a row wins. Below the main window, outputs displays the winner side

![image](https://user-images.githubusercontent.com/24220136/230802982-7dff198b-7517-4411-881f-f8267fd78a12.png)

------------------

## Tic-Tac-Toe Over Network

Developing a Tic-Tac-Toe Game, that enables two players to play the game on the same machine. Following is the development of the distributed tic-tac-toe game using multithreads and networking with socket streams. A distributed tic-tac-toe game enables users to play on different machines from anywhere on the Internet. We need to develop a server for multiple clients. The server creates a server socket and accepts connections from every two players to form a session. Each session is a thread that
communicates with the two players and determines the status of the game. The server can establish any number of sessions, as shown in Figure. 

For each session, the first client connecting to the server is identified as player 1 with token X, and the second client connecting is identified as player 2 with token O. The server notifies the players of their respective tokens. Once two clients are connected to it, the server starts a thread to facilitate the game between the two players by performing the steps repeatedly, as shown in the following Figure:

![image](https://github.com/af4092/Tic-Tac-Toe/assets/24220136/b3c2c808-a3aa-4b39-b1de-086b6fb012e1)

- The client is responsible for interacting with the players. It creates a user interface with nine cells and displays the game title and status to the players in the labels. The client in this example does not determine the game status (win or draw); it simply passes the moves to the server and receives the game status from the server. 

![image](https://github.com/af4092/Tic-Tac-Toe/assets/24220136/f67f4d0a-71d6-41ca-8591-6ed0814b30f9)

- The implementation of Java network programs at the socket level is tightly synchronized. An operation to send data from one machine requires an operation to receive data from the other machine. As shown in this example, the server and the client are tightly synchronized to send or receive data.

- Source code is given in `TicTacToeOverNetwork` folder, and the sample run outcome is as following 
