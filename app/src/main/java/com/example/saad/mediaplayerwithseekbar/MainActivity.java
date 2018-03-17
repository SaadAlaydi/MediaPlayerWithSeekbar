package com.example.saad.mediaplayerwithseekbar;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar1;
    int SeekValue;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar1 = (SeekBar) findViewById(R.id.seekBar);
        mediaPlayer = MediaPlayer.create(this, R.raw.assalamu_alaika);


        ImageView play = (ImageView) findViewById(R.id.playMp);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekBar1.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(MainActivity.this, "Song finished", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        ImageView pause = (ImageView) findViewById(R.id.pauseMp);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });



        ImageView stop = (ImageView) findViewById(R.id.stopMp);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.pause();

            }
        });


        ImageView reply = (ImageView) findViewById(R.id.replyMp);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });


        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                SeekValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(SeekValue);
            }
        });


        mythread my= new mythread();
        my.start();
    }


    class mythread extends Thread {
        public void run() {


            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mediaPlayer != null)
                            seekBar1.setProgress(mediaPlayer.getCurrentPosition());
                    }
                });


            }
        }
    }
}
