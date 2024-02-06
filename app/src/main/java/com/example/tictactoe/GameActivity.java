package com.example.tictactoe;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends Activity {

    int currentImageIndex = 0;

    ImageView view1;
    ImageView view2;
    ImageView view3;
    ImageView view4;
    ImageView view5;
    ImageView view6;
    ImageView view7;
    ImageView view8;
    ImageView view9;

    private ImageView[] imageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tictactoe);

        view1 = (ImageView) findViewById(R.id.view01);
        view2 = (ImageView) findViewById(R.id.view02);
        view3 = (ImageView) findViewById(R.id.view03);
        view4 = (ImageView) findViewById(R.id.view04);
        view5 = (ImageView) findViewById(R.id.view05);
        view6 = (ImageView) findViewById(R.id.view06);
        view7 = (ImageView) findViewById(R.id.view07);
        view8 = (ImageView) findViewById(R.id.view08);
        view9 = (ImageView) findViewById(R.id.view09);


        int[] imageResources = {R.drawable.player_x, R.drawable.player_0};

        imageViews = new ImageView[]{view1, view2, view3, view4, view5, view6, view7, view8, view9};

        for (final ImageView imageView : imageViews) {

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageView.getDrawable() == null) {
                        imageView.setImageResource(imageResources[currentImageIndex]);
                        currentImageIndex = (currentImageIndex + 1) % imageResources.length;

                        if (view1.getDrawable() == view2.getDrawable() && view2.getDrawable() == view3.getDrawable()) {
                            wonGame(view1);
                        } else if (view4.getDrawable() == view5.getDrawable() && view5.getDrawable() == view6.getDrawable()) {
                            wonGame(view4);
                        } else if (view7.getDrawable() == view8.getDrawable() && view7.getDrawable() == view9.getDrawable()) {
                            wonGame(view7);
                        }

                    }
                }
            });

        }
        ImageView reset_button = findViewById(R.id.reset);
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void wonGame(ImageView view) {
        TextView status = findViewById(R.id.status);
        Drawable wonPlayer = view.getDrawable();
        Drawable player0 = getResources().getDrawable(R.drawable.player_0);
        Drawable playerX = getResources().getDrawable(R.drawable.player_x);
        if (wonPlayer == player0) {
            status.setText("Hurray !! player-O won!!");
        } else if (wonPlayer == playerX) {
            status.setText("Hurray !! player-X won!!");
        }
    }

    private void resetGame() {
        for (int i = 0; i < imageViews.length; i++) {
            imageViews[i].setImageResource(0);
        }
    }
}


