# [Tic-Tac-Toe](https://en.wikipedia.org/wiki/Tic-tac-toe)
Tic-Tac-Toe Game developed on Java with JavaFX package.

- Tic-tac-toe is played on a three-by-three grid by two players, who alternately place the marks X and O in one of the nine spaces in the grid.
  
- Game is played between two players 'X' and 'O'

<p align="center">
  <img src="https://user-images.githubusercontent.com/24220136/230802954-b721df7b-ff41-4857-8254-d0cf720e3b5d.png" alt="Image">
</p>

- 'X' starts the game, whichever side gets three figure in a row wins. Below the main window, outputs displays the winner side

<p align="center">
  <img src="https://user-images.githubusercontent.com/24220136/230802982-7dff198b-7517-4411-881f-f8267fd78a12.png" alt="Image">
</p>

------------------

## Tic-Tac-Toe Over Network

- Developing a Tic-Tac-Toe Game, that enables two players to play the game on the same machine. Following is the development of the distributed tic-tac-toe game using multithreads and networking with socket streams. A distributed tic-tac-toe game enables users to play on different machines from anywhere on the Internet. We need to develop a server for multiple clients. The server creates a server socket and accepts connections from every two players to form a session. Each session is a thread that
communicates with the two players and determines the status of the game. The server can establish any number of sessions, as shown in Figure. 

- For each session, the first client connecting to the server is identified as player 1 with token X, and the second client connecting is identified as player 2 with token O. The server notifies the players of their respective tokens. Once two clients are connected to it, the server starts a thread to facilitate the game between the two players by performing the steps repeatedly, as shown in the following Figure:

<p align="center">
  <img src="https://github.com/af4092/Tic-Tac-Toe/assets/24220136/b3c2c808-a3aa-4b39-b1de-086b6fb012e1" alt="Image">
</p>

- The client is responsible for interacting with the players. It creates a user interface with nine cells and displays the game title and status to the players in the labels. The client in this example does not determine the game status (win or draw); it simply passes the moves to the server and receives the game status from the server. 

![image](https://github.com/af4092/Tic-Tac-Toe/assets/24220136/f67f4d0a-71d6-41ca-8591-6ed0814b30f9)

- The implementation of Java network programs at the socket level is tightly synchronized. An operation to send data from one machine requires an operation to receive data from the other machine. As shown in this example, the server and the client are tightly synchronized to send or receive data.

- Source code is given in `TicTacToeOverNetwork` folder, and the sample run outcome is as following:

![image](https://github.com/af4092/Tic-Tac-Toe/assets/24220136/0441ff2e-c0bd-4685-ac00-dd570e871959)

-------------------------

- `TicTacToeServer.java` code explanation, this code is designed to be used as a server for a multiplayer Tic-Tac-Toe game. It is expected to be used in conjunction with a client application that connects to this server. The client application would handle the user interface and interaction with the server:

  - The code is in a package named `com.example.javafxprojects2`.

  - The `TicTacToeServer` class extends the Application class provided by `JavaFX`.

  - The start method is overridden to create the user interface for the server application.

  - The server application has a `TextArea` component to display logs.

  - The server listens for incoming client connections on `port:8000` using a `ServerSocket`.

  - When a client connects, a new session is created, and the client is assigned as `Player 1`.

  - The IP address of the connected client is displayed in the log.

  - Another client is accepted as `Player 2`, and its IP address is also displayed.

  - A new thread is started to handle the current session. The thread is an instance of the `HandleASession class`, which implements the Runnable interface.

  - The HandleASession class represents a session between two players.

  - The cell variable is a `2-dimensional` array representing the Tic-Tac-Toe game board.

  - The run method of the HandleASession class handles the game logic for the session.

  - It reads moves from the players, updates the game board, checks for a winner, and sends the game state to the players.

  - The `sendMove` method is used to send the player's move (row and column) to the opponent.

  - The `isFull` method checks if the game board is full.

  - The `isWon` method checks if a player has won the game by checking the rows, columns, and diagonals of the game board.

  - The game continues until a player wins or the game board is full.

 -------------------------

- `TicTacToeClient.java`  is designed to be used as a client for a multiplayer Tic-Tac-Toe game. It is expected to be used in conjunction with a server application that handles the game logic and communication with the clients. The server application would listen for client connections and manage the game state:

  - The code is in a package named `com.example.javafxprojects2`.

  - The `TicTacToeClient` class extends the Application class provided by `JavaFX`.

  - The start method is overridden to create the user interface for the client application.

  - The user interface consists of a 3x3 grid of cells, represented by the Cell class, a title label (`lbTitle`), and a status label (`lbStatus`).

  - The `connectToServer` method is called to establish a connection with the server.

  - The client connects to the server using the host address "localhost" and `port:8000`.

  - A thread is started to handle the communication with the server.

  - The client receives the player number (PLAYER1 or PLAYER2) from the server.

  - If the client is PLAYER1, it sets its token as 'X', displays the appropriate messages, and starts its turn.

  - If the client is PLAYER2, it sets its token as '0' and displays the appropriate messages.

  - The client enters a loop to continue playing until the game is over.

  - If it is the client's turn (based on the player number), it waits for the player to make a move by calling the `waitForPlayerAction` method.

  - The client sends the selected move (row and column) to the server by calling the `sendMove` method.

  - The client receives game status information from the server by calling the `receiveInfoFromServer` method.

  - If the client wins, loses, or the game ends in a draw, the appropriate message is displayed, and the game continues or ends based on the received status.

  - If it is not the client's turn, it receives the opponent's move from the server by calling the `receiveMove` method.

  - The Cell class represents a cell in the Tic-Tac-Toe grid. It extends the Pane class and handles mouse clicks.

  - Each cell can have a token ('X', '0', or ' ') and is displayed using JavaFX shapes (lines for 'X' and an ellipse for '0').

  - When a cell is clicked and it is the client's turn, the cell's token is set to the client's token, and the selected move is stored.

  - The client application is started by launching the JavaFX application using the main method.
