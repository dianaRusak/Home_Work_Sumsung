package com.example.app.gameses;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(new GameView(this));
    }
    @Override
    protected void onResume() {
        super.onResume();
        GameView gameView = new GameView(this);
        long before = new Date().getTime();
        long after = new Date().getTime();
        while (true) {
            if (after - before >= 2000) {
                gameView.update();
                after = new Date().getTime();
                before = new Date().getTime();
            }else{
                after = new Date().getTime();
            }
        }
    }
}
