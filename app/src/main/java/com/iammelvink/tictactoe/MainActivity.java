package com.iammelvink.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
     * 0 = yellow
     * 1 = red
     * 2 = empty*/
    private int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    private int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    private int yellowWins = 0;
    private int redWins = 0;

    private int activePlayer = 0;

    private boolean gameActive = true;

    private boolean isVisible = false;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        Log.i("Tag ", counter.getTag().toString() + " was pressed!");


        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setTranslationY(-1500);

                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setTranslationY(-1500);

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(1800).setDuration(350);

            for (int[] winningPos : winningPositions) {

                if (gameState[winningPos[0]] == gameState[winningPos[1]] &&
                        gameState[winningPos[1]] == gameState[winningPos[2]] &&
                        gameState[winningPos[0]] != 2) {

                    gameActive = false;

                    String winner;

                    if (activePlayer == 1) {
                        yellowWins++;
                        winner = "Yellow";

                    } else {
                        redWins++;
                        winner = "Red";
                    }

                    winner += " has won!";
                    isVisible = true;

                    if (isVisible) {
                        Button goAgn = (Button) findViewById(R.id.btnGo);
                        Button resScore = (Button) findViewById(R.id.btnReset);

                        Log.i("Yellow wins: ", Integer.toString(yellowWins));
                        Log.i("Red wins: ", Integer.toString(redWins));

                        TextView winnerTextView = (TextView) findViewById(R.id.txtWon);
                        TextView yWins = (TextView) findViewById(R.id.btnYWins);
                        TextView rWins = (TextView) findViewById(R.id.btnRWins);

                        winnerTextView.setText(winner);
                        yWins.setText("Yellow wins: " + Integer.toString(yellowWins));
                        rWins.setText("Red wins: " + Integer.toString(redWins));

                        goAgn.setVisibility(View.VISIBLE);
                        resScore.setVisibility(View.VISIBLE);

                        winnerTextView.setVisibility(View.VISIBLE);
                        yWins.setVisibility(View.VISIBLE);
                        rWins.setVisibility(View.VISIBLE);

                        goAgn.animate().alpha(1).setDuration(500);
                        resScore.animate().alpha(1).setDuration(500);
                        winnerTextView.animate().alpha(1).setDuration(500);
                        yWins.animate().alpha(1).setDuration(500);
                        rWins.animate().alpha(1).setDuration(500);
                    }

                    Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();
                }

            }
        } else if (gameState[tappedCounter] != 2 && gameActive) {
            Toast.makeText(this, "Tap in a different block!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
        }
    }

    public void goAgain(View view) {

        Button goAgn = (Button) findViewById(R.id.btnGo);
        Button resScore = (Button) findViewById(R.id.btnReset);

        Log.i("Game state: ", gameState.toString());

        TextView winnerTextView = (TextView) findViewById(R.id.txtWon);
        TextView yWins = (TextView) findViewById(R.id.btnYWins);
        TextView rWins = (TextView) findViewById(R.id.btnRWins);

        goAgn.animate().alpha(0).setDuration(500);
        resScore.animate().alpha(0).setDuration(500);
        winnerTextView.animate().alpha(0).setDuration(500);

        goAgn.setVisibility(View.INVISIBLE);
        resScore.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        isVisible = false;

        /*
         * Use: android.support.v7.widget.GridLayout
         * Instead of GridLayout
         * For version lower than Android 7.1.1*/
        android.support.v7.widget.GridLayout layoutGrid =
                (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

//        GridLayout layoutGrid =
//                (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < layoutGrid.getChildCount(); i++) {
            ImageView counter = (ImageView) layoutGrid.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        activePlayer = 0;

        gameActive = true;
    }

    public void resetScore(View view) {

        Button goAgn = (Button) findViewById(R.id.btnGo);
        Button resScore = (Button) findViewById(R.id.btnReset);

        Log.i("Game state: ", gameState.toString());
        Log.i("Yellow wins: ", Integer.toString(yellowWins));
        Log.i("Red wins: ", Integer.toString(redWins));

        TextView winnerTextView = (TextView) findViewById(R.id.txtWon);
        TextView yWins = (TextView) findViewById(R.id.btnYWins);
        TextView rWins = (TextView) findViewById(R.id.btnRWins);

        goAgn.animate().alpha(0).setDuration(500);
        resScore.animate().alpha(0).setDuration(500);

        winnerTextView.animate().alpha(0).setDuration(500);
        yWins.animate().alpha(0).setDuration(500);
        rWins.animate().alpha(0).setDuration(500);

        goAgn.setVisibility(View.INVISIBLE);
        resScore.setVisibility(View.INVISIBLE);

        winnerTextView.setVisibility(View.INVISIBLE);
        yWins.setVisibility(View.INVISIBLE);
        rWins.setVisibility(View.INVISIBLE);

        isVisible = false;

        /*
         * Use: android.support.v7.widget.GridLayout
         * Instead of GridLayout
         * For version lower than Android 7.1.1*/
        android.support.v7.widget.GridLayout layoutGrid =
                (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

//        GridLayout layoutGrid =
//                (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < layoutGrid.getChildCount(); i++) {
            ImageView counter = (ImageView) layoutGrid.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        yellowWins = 0;
        redWins = 0;

        yWins.setText("Yellow wins: " + Integer.toString(yellowWins));
        rWins.setText("Red wins: " + Integer.toString(redWins));

        activePlayer = 0;

        gameActive = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
