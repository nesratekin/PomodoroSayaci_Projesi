package com.example.pomodorosayaci;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button startButton;
    private SeekBar seekBar;
    private CountDownTimer countDownTimer;
    private boolean isPomodoroRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        startButton = findViewById(R.id.button);
        seekBar = findViewById(R.id.seekBar);
        textView.setText("25:00");
        seekBar.setEnabled(false);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPomodoroRunning) {
                    durdurZamanlayici();
                } else {
                    pomodoroZamanlayiciyiBaslat();
                }
            }
        });
    }

    private void pomodoroZamanlayiciyiBaslat() {
        countDownTimer = new CountDownTimer(25 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                zamanlayiciyiGuncelle(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                textView.setText("00:00");
                molaZamanlayiciyiBaslat();
            }
        };

        countDownTimer.start();
        isPomodoroRunning = true;
        startButton.setText("Durdur");
        seekBar.setEnabled(true);
        seekBar.setMax(300);
        seekBar.setProgress(300);
    }

    private void durdurZamanlayici() {
        countDownTimer.cancel();
        isPomodoroRunning = false;
        startButton.setText("Başlat");
        textView.setText("25:00");
        seekBar.setEnabled(false);
    }

    private void molaZamanlayiciyiBaslat() {
        countDownTimer = new CountDownTimer(5 * 60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                molaZamanlayiciyiGuncelle(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                pomodoroZamanlayiciyiBaslat();
            }
        };

        countDownTimer.start();
    }

    private void zamanlayiciyiGuncelle(long millisUntilFinished) {
        int dakika = (int) (millisUntilFinished / 1000) / 60;
        int saniye = (int) (millisUntilFinished / 1000) % 60;

        String formatliSure = String.format("%02d:%02d", dakika, saniye);
        textView.setText(formatliSure);

        seekBar.setProgress((int) (millisUntilFinished / 1000));
    }

    private void molaZamanlayiciyiGuncelle(long millisUntilFinished) {
        int dakika = (int) (millisUntilFinished / 1000) / 60;
        int saniye = (int) (millisUntilFinished / 1000) % 60;

        String formatliSure = String.format("%02d:%02d", dakika, saniye);
        textView.setText(formatliSure);

        seekBar.setProgress((int) (millisUntilFinished / 1000));
    }
}