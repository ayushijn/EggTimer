package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    SeekBar seekBar;
    Boolean counterIsActive=false;
    CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    public void resetTimer() {
        textView.setText("0:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("GO!");
        counterIsActive = false;
    }
   public void buttonClicked(View view)
   { Log.i("button","button is clicked");
       if (counterIsActive) {

           resetTimer();

       } else {

           counterIsActive = true;
           seekBar.setEnabled(false);
           button.setText("STOP!");

           countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
               @Override
               public void onTick(long l) {
                   updateTimer((int) l / 1000);
               }

               @Override
               public void onFinish() {
               Log.i("timer status","timer all done");
               mediaPlayer.start();
               }
           }.start();
       }


   }
   public void updateTimer(int i)
   {
       int minutes;
       int seconds;
       String second;
       minutes = i / 60;
       seconds = i - (minutes * 60);

       if(seconds<=9)
       {
           second="0"+Integer.toString(seconds);
       }
       else
           second=Integer.toString(seconds);

       textView.setText(Integer.toString(minutes)+":"+second);
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Seekbar value",Integer.toString(i));
           updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}