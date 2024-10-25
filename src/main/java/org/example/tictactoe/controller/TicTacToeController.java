package org.example.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.example.tictactoe.model.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TicTacToeController {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Button button9;
    @FXML
    private Label statusLabel;
    @FXML
    private Label OScore;
    @FXML
    private Label XScore;

    private Model model;

    public void initialize() {
        List<Button> buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));
        model = new Model(buttons);
    }

    @FXML
    private void clickedButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        model.playerMove(button);

        // Check for a win after player's move
        String winner = model.winStates(); // Get the winner
        if (winner == null) { // If there's no winner, computer can move
            model.computerMove();
            winner = model.winStates(); // Check for a win after computer's move
        }
        updateScores(winner);
    }

    private void updateScores(String winner) {
        XScore.setText("Score: " + model.getXScore());
        OScore.setText("Score: " + model.getOScore());

        if (winner != null) {
            statusLabel.setText(winner + " wins!"); // Update status label with the winner
        } else if (model.allButtonsDisabled()) {
            statusLabel.setText("Draw!");
        }
    }

    public void reset() {
        model.reset();
        statusLabel.setText("");
        updateScores(null); // Reset scores without a winner
    }
}
