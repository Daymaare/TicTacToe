package org.example.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    List<Button> buttons;
    int xScore = 0;
    int oScore = 0;

    Random random = new Random();

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

    }

    @FXML
    private void clickedButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        playerMove(button);
        computerMove();
    }

    private void playerMove(Button button) {
        if (button.isDisable() || winStates()) return;

        button.setText("X");
        button.setDisable(true);
        button.setTextFill(Color.RED);
    }

    private void computerMove() {
        if (winStates() || allButtonsDisabled()) return;

        var allEnabledButtons = buttons.stream().filter(b -> !b.isDisable()).toList();
        if (allEnabledButtons.isEmpty()) return;

        Button button = allEnabledButtons.get(random.nextInt(allEnabledButtons.size()));
        button.setText("O");
        button.setDisable(true);
        button.setTextFill(Color.BLUE);

        winStates();
    }

    private void disableAllButtons() {
        buttons.forEach(b -> b.setDisable(true));
    }

    public boolean allButtonsDisabled() {
        boolean allDisabled = buttons.stream().allMatch(Node::isDisable);
        if (allDisabled && !winStates()) {
            statusLabel.setText("Draw!");
        }
        return allDisabled;
    }

    public void reset() {
        buttons.forEach(b -> {
            b.setDisable(false);
            b.setText("");
        });
        statusLabel.setText("");
    }

    public boolean winStates() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> button1.getText() + button2.getText() + button3.getText();
                case 1 -> button4.getText() + button5.getText() + button6.getText();
                case 2 -> button7.getText() + button8.getText() + button9.getText();
                case 3 -> button1.getText() + button4.getText() + button7.getText();
                case 4 -> button2.getText() + button5.getText() + button8.getText();
                case 5 -> button3.getText() + button6.getText() + button9.getText();
                case 6 -> button1.getText() + button5.getText() + button9.getText();
                case 7 -> button3.getText() + button5.getText() + button7.getText();
                default -> null;
            };

            if (winnerIs(line)) {
                return true;
            }
        }
        return false;
    }

    private boolean winnerIs(String line) {
        if (line.equals("XXX")) {
            statusLabel.setText("X is the winner!");
            disableAllButtons();
            updateScore("X");

            return true;
        } else if (line.equals("OOO")) {
            statusLabel.setText("O is the winner!");
            disableAllButtons();
            updateScore("O");
            return true;
        }
        return false;
    }

    private void updateScore(String player) {
        if (player.equals("X")) {
            XScore.setText("Score: " + (++xScore));
        } else if (player.equals("O")) {
            OScore.setText("Score: " + (++oScore));
        }
    }
}