package org.example.tictactoe.model;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class Model {
    private List<Button> buttons;
    private int xScore = 0;
    private int oScore = 0;
    private Random random = new Random();

    public Model(List<Button> buttons) {
        this.buttons = buttons;
    }

    public void playerMove(Button button) {
        if (button.isDisable() || winStates()) return;

        button.setText("X");
        button.setDisable(true);
        button.setTextFill(Color.RED);
    }

    public void computerMove() {
        if (winStates() || allButtonsDisabled()) return;

        var allEnabledButtons = buttons.stream().filter(b -> !b.isDisable()).toList();
        if (allEnabledButtons.isEmpty()) return;

        Button button = allEnabledButtons.get(random.nextInt(allEnabledButtons.size()));
        button.setText("O");
        button.setDisable(true);
        button.setTextFill(Color.BLUE);
    }

    public boolean allButtonsDisabled() {
        boolean allDisabled = buttons.stream().allMatch(Button::isDisable);
        return allDisabled;
    }

    public void reset() {
        buttons.forEach(b -> {
            b.setDisable(false);
            b.setText("");
        });
    }

    public boolean winStates() {
        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> buttons.get(0).getText() + buttons.get(1).getText() + buttons.get(2).getText();
                case 1 -> buttons.get(3).getText() + buttons.get(4).getText() + buttons.get(5).getText();
                case 2 -> buttons.get(6).getText() + buttons.get(7).getText() + buttons.get(8).getText();
                case 3 -> buttons.get(0).getText() + buttons.get(3).getText() + buttons.get(6).getText();
                case 4 -> buttons.get(1).getText() + buttons.get(4).getText() + buttons.get(7).getText();
                case 5 -> buttons.get(2).getText() + buttons.get(5).getText() + buttons.get(8).getText();
                case 6 -> buttons.get(0).getText() + buttons.get(4).getText() + buttons.get(8).getText();
                case 7 -> buttons.get(2).getText() + buttons.get(4).getText() + buttons.get(6).getText();
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
            updateScore("X");
            disableAllButtons();
            return true;
        } else if (line.equals("OOO")) {
            updateScore("O");
            disableAllButtons();
            return true;
        }
        return false;
    }

    private void updateScore(String player) {
        if (player.equals("X")) {
            xScore++;
        } else if (player.equals("O")) {
            oScore++;
        }
    }

    private void disableAllButtons() {
        buttons.forEach(b -> b.setDisable(true));
    }

    public int getXScore() {
        return xScore;
    }

    public int getOScore() {
        return oScore;
    }
}
