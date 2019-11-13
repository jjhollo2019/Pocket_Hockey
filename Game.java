package com.example.pockethockey;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class Game extends AppCompatActivity {

    GamePanel gameView;
    FrameLayout game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GamePanel(this);
        game = new FrameLayout(this);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new GamePanel(this));
    }
}
