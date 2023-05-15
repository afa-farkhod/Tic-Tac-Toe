package com.example.javafxprojects2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TicTacToeServer extends Application implements TicTacToeConstants {
    private int sessionNo = 1;

    @Override
    public void start(Stage stage) {
        TextArea txtLog = new TextArea();

        Scene scene = new Scene(new ScrollPane(txtLog), 450, 200);
        stage.setTitle("Tic-Tac-Toe-Server");
        stage.setScene(scene);
        stage.show();

        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> txtLog.appendText(new Date() + ": Server started at socket 8000\n"));

                while (true) {
                    Platform.runLater(() -> txtLog.appendText(new Date() + ": Wait for players to join session " + sessionNo + '\n'));

                    Socket player1 = serverSocket.accept();

                    Platform.runLater(() -> {
                        txtLog.appendText(new Date() + ": Player1 joined session " + sessionNo + '\n');
                        txtLog.appendText("Player1's IP address " + player1.getInetAddress().getHostAddress() + '\n');
                    });

                    new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);

                    Socket player2 = serverSocket.accept();

                    Platform.runLater(() -> {
                        txtLog.appendText(new Date() + ": Player2 joined session " + sessionNo + '\n');
                        txtLog.appendText("Player2's IP address " + player2.getInetAddress().getHostAddress() + '\n');
                    });
                    new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);

                    Platform.runLater(() ->
                            txtLog.appendText(new Date() + ": Start a thread for session " + sessionNo++ + '\n'));

                    new Thread(new HandleASession(player1, player2)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
        class HandleASession implements Runnable, TicTacToeConstants{
            private Socket player1;
            private Socket player2;
            private char[][] cell = new char[3][3];
            private DataInputStream fromPlayer1;
            private DataOutputStream toPlayer1;
            private DataInputStream fromPlayer2;
            private DataInputStream toPlayer2;
            private boolean continueToPlay = true;

            public HandleASession(Socket player1, Socket player2){
                this.player1 = player1;
                this.player2 = player2;

                for(int i=0; i<3; i++)
                    for(int j=0; j<3; j++)
                        cell[i][j]=' ';
            }

            public void run() {
                try{
                    DataInputStream fromPlayer1 = new DataInputStream(player1.getInputStream());
                    DataOutputStream toPlayer1 = new DataOutputStream(player1.getOutputStream());
                    DataInputStream fromPlayer2 = new DataInputStream(player2.getInputStream());
                    DataOutputStream toPlayer2 = new DataOutputStream(player2.getOutputStream());

                    toPlayer1.writeInt(1);

                    while(true){
                        int row = fromPlayer1.readInt();
                        int column = fromPlayer1.readInt();
                        cell[row][column] = 'X';

                        if(isWon('X')){
                            toPlayer1.writeInt(PLAYER1_WON);
                            toPlayer2.writeInt(PLAYER1_WON);
                            sendMove(toPlayer2, row, column);
                            break;
                        }else if(isFull()){
                            toPlayer1.writeInt(DRAW);
                            toPlayer2.writeInt(DRAW);
                            sendMove(toPlayer2, row, column);
                            break;
                        }else{
                            toPlayer2.writeInt(CONTINUE);
                            sendMove(toPlayer2, row, column);
                        }
                        row = fromPlayer2.readInt();
                        column = fromPlayer2.readInt();
                        cell[row][column] = '0';

                        if(isWon('0')){
                            toPlayer1.writeInt(PLAYER2_WON);
                            toPlayer2.writeInt(PLAYER2_WON);
                            sendMove(toPlayer1, row, column);
                            break;
                        }else {
                            toPlayer1.writeInt(CONTINUE);
                            sendMove(toPlayer1, row, column);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            private void sendMove(DataOutputStream out, int row, int column) throws IOException{
                out.writeInt(row);
                out.writeInt(column);
            }
            private boolean isFull(){
                for(int i = 0; i < 3; i++)
                    for(int j = 0; j < 3; j++)
                        if(cell[i][j] == ' ')
                            return false;
                return true;
            }
            private boolean isWon(char token){
                for(int i = 0; i < 3; i++)
                    if((cell[i][0] == token) && (cell[i][1] == token) && (cell[i][2] == token)){
                        return true;
                    }
                for (int j=0; j<3; j++)
                    if((cell[0][j]==token) && (cell[1][j]==token) && (cell[2][j]==token)){
                        return true;
                    }
                if((cell[0][0]==token) && (cell[1][1]==token) && (cell[2][0]==token)){
                    return true;
                }
                return false;
            }
        }
    }

