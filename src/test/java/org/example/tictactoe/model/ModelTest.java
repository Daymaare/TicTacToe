package org.example.tictactoe.model;

import javafx.application.Platform;
import javafx.scene.control.Button;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

  private Model model;
  private List<Button> buttons;

  @BeforeAll
  static void initJavaFX() throws InterruptedException {
    CountDownLatch latch = new CountDownLatch(1);
    Platform.startup(latch::countDown);
    latch.await();
  }

  @BeforeEach
  void setUp() {
    buttons = new ArrayList<>();
    Platform.runLater(() -> {
      for (int i = 0; i < 9; i++) {
        buttons.add(new Button());
      }
    });

    CountDownLatch latch = new CountDownLatch(1);
    Platform.runLater(latch::countDown);
    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    model = new Model(buttons);
  }

  @Test
  void whenPlayerMakesAMove() {
    Button button = buttons.getFirst();
    model.playerMove(button);

    assertEquals("X", button.getText(), "The button text should be set to 'X' after player move");
    assertTrue(button.isDisable(), "The button should be disabled after player move");

    model.playerMove(button);
    assertEquals("X", button.getText(), "Button text should remain 'X' if clicked again");
  }

  @Test
  void whenComputerMakesAMove() {
    model.computerMove();

    long oCount = buttons.stream().filter(b -> "O".equals(b.getText()) && b.isDisable()).count();
    assertEquals(1, oCount, "Exactly one button should be marked as 'O' after computer move");
  }

  @Test
  void whenThereIsThreeInARow() {
    buttons.get(0).setText("X");
    buttons.get(1).setText("X");
    buttons.get(2).setText("X");

    String winner = model.winStates();
    assertEquals("X", winner, "The model should recognize 'X' as the winner");
  }
}


