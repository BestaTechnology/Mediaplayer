package voice_to_text.sandy148101.com.mediapayer;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    MediaPlayer player;
    SeekBar seekBar;
    AudioManager audioManager;
    private Button playBtn, pauseBtn, stopBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        seekBar =(SeekBar)findViewById(R.id.seekBar);

        volumecntl();


    }
  public void volumecntl()
  {

      try{
          audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
          seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
          seekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
          seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
              @Override
              public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                  audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
              }

              @Override
              public void onStartTrackingTouch(SeekBar seekBar) {

              }

              @Override
              public void onStopTrackingTouch(SeekBar seekBar) {

              }
          });



      }catch (Exception e)
      {

      }
  }


    public void play(View view)
    {

        if(player==null){

            player= MediaPlayer.create(this, R.raw.song);


            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }

        player.start();
    }

    public void pause(View view)
    {
        if(player!=null)
        {
            player.pause();

        }
    }

    public void stop(View view)
    {
        stopPlayer();

    }

    public void stopPlayer()
    {

        if(player!=null){

            player.release();
            player=null;

            Toast.makeText(this,"Media player release",Toast.LENGTH_SHORT).show();
        }

    }

    protected void onStop()
    {

        super.onStop();
        stopPlayer();
    }


}
