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
        computerMove();
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

    public boolean winningStates() {
        if (button1 == button2 && button2 == button3)
            return true;

        if (button4 == button5 && button5 == button6)
            return true;

        if (button7 == button8 && button8 == button9)
            return true;

        if (button1 == button4 && button4 == button7)
            return true;

        if (button2 == button5 && button5 == button8)
            return true;

        if (button3 == button6 && button6 == button9)
            return true;

        if (button1 == button5 && button5 == button9)
            return true;

        if (button3 == button5 && button5 == button7)
            return true;

        else {
            return false;
        }
    }
}
