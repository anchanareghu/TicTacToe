package com.example.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private GridLayout gridLayout;
    private ImageView[][] imageView;
    private ImageView reset;
    private TextView status;
    private int currentPlayer;
    private int[][] gameState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tictactoe);

        gridLayout = findViewById(R.id.grid);
        imageView = new ImageView[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                imageView[i][j] = (ImageView) gridLayout.getChildAt(3 * i + j);
                imageView[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        makeMove(v);
                    }
                });
            }
        }

        reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        status = findViewById(R.id.status);

        currentPlayer = 1;
        gameState = new int[3][3];
        resetGame();
    }

    private void makeMove(View v) {
        int row = gridLayout.indexOfChild(v) / 3;
        int col = gridLayout.indexOfChild(v) % 3;

        if (gameState[row][col] == 0) {
            imageView[row][col].setImageResource(currentPlayer == 1 ? R.drawable.player_x : R.drawable.player_0);
            gameState[row][col] = currentPlayer;

            if (checkWinner()) {
                status.setText("Player " + currentPlayer + " wins!");
                return;
            }

            if (isDraw()) {
                status.setText("Draw!");
                return;
            }

            currentPlayer = currentPlayer == 1 ? 2 : 1;
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (gameState[i][0] == gameState[i][1] && gameState[i][1] == gameState[i][2] && gameState[i][0] != 0) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (gameState[0][i] == gameState[1][i] && gameState[1][i] == gameState[2][i] && gameState[0][i] != 0) {
                return true;
            }
        }

        if (gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2] && gameState[0][0] != 0) {
            return true;
        }

        if (gameState[0][2] == gameState[1][1] && gameState[1][1] == gameState[2][0] && gameState[0][2] != 0) {
            return true;
        }

        return false;
    }

    private boolean isDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameState[i][j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameState[i][j] = 0;
                imageView[i][j].setImageResource(0);
            }
        }

        status.setText("");
        currentPlayer = 1;
    }

}
