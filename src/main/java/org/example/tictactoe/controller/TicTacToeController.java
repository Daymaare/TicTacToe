package org.example.tictactoe.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

import java.security.KeyStore;
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

    List<Button> buttons;

    Random random = new Random();

    public void initialize() {
        buttons = new ArrayList<>(Arrays.asList(button1, button2, button3, button4, button5, button6, button7, button8, button9));

    }

    @FXML
    private void clickedButton(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();

        playerMove(button);
        if (!drawState()) {
            computerMove();
        }
    }

    private void playerMove(Button button) {

        if (!button.getText().isEmpty()) {
            return;
        }
        button.setText("X");
        button.setDisable(true);

    }

    private void computerMove() {
        Button button;
        var allEnabledButtons = buttons.stream().filter(b -> !b.isDisable()).toList();
        button = allEnabledButtons.get(random.nextInt(allEnabledButtons.size()));
        button.setText("O");
        button.setDisable(true);

    }

    public boolean drawState() {
        return buttons.stream().allMatch(Node::isDisable);


    }
}
