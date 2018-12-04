package com.example.alexisferret.moodtracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private final Mood[] moods = {
            new Mood(R.drawable.smileysuperhappy, R.color.banana_yellow),
            new Mood(R.drawable.smileyhappy, R.color.light_sage),
            new Mood(R.drawable.smileynormal, R.color.cornflower_blue_65),
            new Mood(R.drawable.smileydisappointed, R.color.warm_grey),
            new Mood(R.drawable.smileysad, R.color.faded_red)};

    private int height;
    private int position = 2;
    private ImageView smileyImg;
    private ConstraintLayout backgroundColor;
    GestureDetector gestureScanner;


    public static class Mood {
        @DrawableRes
        int smileyImg;
        @ColorRes
        int backgroundColor;


        Mood(@DrawableRes int smileyImg, @ColorRes int backgroundColor) {

            this.smileyImg = smileyImg;
            this.backgroundColor = backgroundColor;
        }
    }


    public void commentbutton (View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Commentaire");

        final EditText input = new EditText(MainActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Commentaire annulé", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Commentaire ajouté", Toast.LENGTH_SHORT).show();
            }
        });
        builder.show();
    }

        public void historybutton (View view){

        Intent myIntent = new Intent(view.getContext(), HistoryActivity.class);
        startActivityForResult(myIntent, 0);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gestureScanner = new GestureDetector(this);

        smileyImg = findViewById(R.id.smiley_img);
        backgroundColor = findViewById(R.id.background_color);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return gestureScanner.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.e("test", String.format("velocityY : %.2f", velocityY));
        double ratio = (((double) velocityY) / ((double) height));
        if (ratio < -0.15 && position > 0) {
            // humeur plus joyeuse
            position--;
        } else if (ratio > 0.15 && position < moods.length-1) {
            // humeur moins joyeuse
            position++;
        }
        smileyImg.setImageResource(moods[position].smileyImg);
        backgroundColor.setBackgroundResource(moods[position].backgroundColor);
            return false;

    }
}