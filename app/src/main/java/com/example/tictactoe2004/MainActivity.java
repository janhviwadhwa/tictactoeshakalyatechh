package com.example.tictactoe2004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int boxPosition;
    private final List<int[]> combinationsList = new ArrayList<>();
    private int [] boxPositions = {0,0,0,0,0,0,0,0,0};
    private int playerTurn=1;
    private int totalselectedboxes =1;
    private LinearLayout playerOneLayout,playerTwoLayout;
    private TextView playerOneName,playerTwoName;
    private ImageView image1,image2,image3,image4,image5,image6,image7,image8,image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main);
        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        playerOneLayout= findViewById(R.id.PlayerOneLayout);
        playerTwoLayout= findViewById(R.id.PlayerTwoLayout);

        image1= findViewById(R.id.image1);
        image2= findViewById(R.id.image2);
        image3= findViewById(R.id.image3);
        image4= findViewById(R.id.image4);
        image5= findViewById(R.id.image5);
        image6= findViewById(R.id.image6);
        image7= findViewById(R.id.image7);
        image8= findViewById(R.id.image8);
        image9= findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});


        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)) {
                    performAction(image1, 0);
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)) {
                    performAction(image2, 1);
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)) {
                    performAction(image3, 2);
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)) {
                    performAction(image4, 3);
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)) {
                    performAction(image5, 4);
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)) {
                    performAction(image6, 5);
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)) {
                    performAction(image7, 6);
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)) {
                    performAction(image8, 7);
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)) {
                    performAction(image9, 8);
                }
            }
        });

    }
  private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPositions[selectedBoxPosition]= playerTurn;
        if(playerTurn==1){
            imageView.setImageResource(R.drawable.crossimages);
            if(checkPlayerWin()){
                windialog windialog= new windialog(MainActivity.this, playerOneName.getText().toString()+" has won the match",MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            } else if (totalselectedboxes ==9) {
                windialog windialog= new windialog(MainActivity.this, "It is a draw!",MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            }
            else{
               changePlayerTurn(2);
               totalselectedboxes++;
            }
        }
        else {
            imageView.setImageResource(R.drawable.zeroimage);
            if(checkPlayerWin()){
                windialog windialog= new windialog(MainActivity.this, playerTwoName.getText().toString()+" has won the match",MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            } else if (selectedBoxPosition==1) {
                windialog windialog= new windialog(MainActivity.this, "It is a draw!",MainActivity.this);
                windialog.setCancelable(false);
                windialog.show();
            }
            else {
                changePlayerTurn(1);
                totalselectedboxes++;
            }
        }
  }
  private void changePlayerTurn(int currentPlayerTurn){
        playerTurn= currentPlayerTurn;
        if(playerTurn==1){
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
        else{
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
  }
    private boolean checkPlayerWin() {
        boolean response = false;
        for (int i = 0; i < combinationsList.size(); i++) {
            final int[] combination = combinationsList.get(i);
            if (boxPositions[combination[0]] == playerTurn &&
                    boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn){
                response = true;
                break; // no need to continue checking if one winning combination is found
            }
        }
        return response;
    }


    private boolean isBoxSelectable(int boxPosition) {
        boolean response= false;
        if(boxPositions[boxPosition] == 0){
            response= true;
        }
        return response;
    }
    protected void restartMatch(){
        boxPositions = new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        totalselectedboxes=1;
        image1.setImageResource(R.drawable.transparent_picture);
        image2.setImageResource(R.drawable.transparent_picture);
        image3.setImageResource(R.drawable.transparent_picture);
        image4.setImageResource(R.drawable.transparent_picture);
        image5.setImageResource(R.drawable.transparent_picture);
        image6.setImageResource(R.drawable.transparent_picture);
        image7.setImageResource(R.drawable.transparent_picture);
        image8.setImageResource(R.drawable.transparent_picture);
        image9.setImageResource(R.drawable.transparent_picture);
    }
}
