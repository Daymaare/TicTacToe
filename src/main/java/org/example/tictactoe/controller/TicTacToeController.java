package org.example.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class TicTacToeController {

    @FXML
    public Button button1;
    @FXML
    public Button button2;
    @FXML
    public Button button3;
    @FXML
    public Button button4;
    @FXML
    public Button button5;
    @FXML
    public Button button6;
    @FXML
    public Button button7;
    @FXML
    public Button button8;
    @FXML
    public Button button9;
    @FXML
    public Label statusLabel;
    List<Button> buttons;

    Random random = new Random();

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

    }

    @FXML
    private void clickedButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();

        playerMove(button);
        checkWinState();
        computerMove();
        checkWinState();
    }

    private void playerMove(Button button) {
        button.setText("X");
        button.setDisable(true);

    }

    private void computerMove() {
        Button button;
        var allEnabledButtons = buttons.stream().filter(b -> !b.isDisable()).toList();
        if (!drawState()) {
            button = allEnabledButtons.get(random.nextInt(allEnabledButtons.size()));
            button.setText("O");
            button.setDisable(true);
        } else statusLabel.setText("It's a tie!");

    }

    public boolean drawState() {
        return buttons.stream().allMatch(Node::isDisable);

    }


    public void reset() {
        if (drawState()) {
            buttons.forEach(b -> b.setDisable(false));
            buttons.forEach(button -> button.setText(""));
            statusLabel.setText("");
        }
    }

    public void checkWinState() {
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
            if (line.equals("XXX")) {
                statusLabel.setText("X is the winner!");
                buttons.forEach(b -> b.setDisable(true));

            } else if (line.equals("OOO")) {
                statusLabel.setText("O is the winner!");
                buttons.forEach(b -> b.setDisable(true));
                return;
            }
        }
    }
}

