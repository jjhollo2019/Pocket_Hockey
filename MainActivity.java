package com.example.planeshooter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SoundBank soundBank = new SoundBank(getApplicationContext());
        soundBank.playTheme();

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        TextView newGame = findViewById(R.id.newGameTP);
        newGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Splash.class);
                startActivity(intent);
            }
        });

        TextView htp = findViewById(R.id.howToPlayTP);
        htp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HowToPlay.class);
                startActivity(intent);
            }
        });

        TextView lead = findViewById(R.id.leaderTP);
        lead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HighscoreActivity.class);
                startActivity(intent);
            }
        });
    }
}
