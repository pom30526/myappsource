package com.example.lsm.pichingreport;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class FullImage extends Activity {
    Intent i;
    ArrayList<Bitmap> bitMapList = new ArrayList<Bitmap>();
    ImageView imageview;
    int clickCount=0;
    Bitmap frame;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        i = new Intent(this.getIntent());
        final Uri videoUri = i.getParcelableExtra("uri");
        imageview = (ImageView)findViewById(R.id.FullImage);
        Button prev =(Button)findViewById(R.id.prev);
        Button next=(Button)findViewById(R.id.nextpt);
        Button line=(Button)findViewById(R.id.line);
        Button animation=(Button)findViewById(R.id.animation);
        Button linedelte=(Button)findViewById(R.id.linedelte);
        final TextView textvv =(TextView)findViewById(R.id.Textvw);

        try {
            // get duration of the video (unit: microsecond)
            MediaPlayer player = new MediaPlayer();
            player.setDataSource(this, videoUri);
            player.prepare();
            int duration = player.getDuration() * 1000;

            // instantiate MediaMetadataRetriever for the video
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(this, videoUri);

            int time = 0;       // unit: microsecond


            while (time < duration) {
                // get frame for every 1 second and save in bitMapList
                frame = retriever.getFrameAtTime(time);
                bitMapList.add(frame);
                time += 700000; //0.3 초 간격 자르기
            }

            imageview.setImageBitmap(bitMapList.get(0)); //초기값 0

            prev.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    if(clickCount == 0){
                        // Toast.makeText(getBaseContext(),"처음장입니다",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    --clickCount;
                    imageview.setImageBitmap(bitMapList.get(clickCount));
                    //Toast.makeText(getBaseContext(),clickCount+"",Toast.LENGTH_SHORT).show();
                }
            });
            next.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {

                    if(clickCount ==bitMapList.size()){
                        //    Toast.makeText(getBaseContext(),"마지막장입니다",Toast.LENGTH_SHORT).show();
                        return;
                    }

                    imageview.setImageBitmap(bitMapList.get(clickCount));
                    clickCount++;
                    //Toast.makeText(getBaseContext(),clickCount+"",Toast.LENGTH_SHORT).show();
                }
            });


            line.setOnClickListener(new Button.OnClickListener(){
                @TargetApi(Build.VERSION_CODES.HONEYCOMB)
                @Override
                public void onClick(View v) {
                    textvv.setTextColor(Color.RED);
                    textvv.setAlpha(10f);
                    textvv.setText(
                            "―――――――――――――――┃　　　　　┃　　　　　┃―――――――――――――――\n" +
                                    "\n"+
                                    "\n"+
                                    "―――――――――――――――┃　　　　　┃　　　　　┃―――――――――――――――\n" +
                                    "\n"+
                                    "\n"+
                                    "―――――――――――――――┃　　　　　┃　　　　　┃―――――――――――――――\n" +
                                    "\n"+
                                    "\n"+
                                    "―――――――――――――――┃　　　　　┃　　　　　┃―――――――――――――――\n");
                }


            });
            linedelte.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View v) {
                    textvv.setText("");
                }
            });
            animation.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent last = new Intent(FullImage.this,study.class);
                    frame.recycle();
                    startActivity(last);

                }
            });
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_full_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
        switch(keyCode){
            case KeyEvent.KEYCODE_BACK:
                String alertTitle = getResources().getString(R.string.app_name);

                new AlertDialog.Builder(FullImage.this)
                        .setTitle(alertTitle)
                        .setMessage("종료하겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener(){

                            public void onClick(DialogInterface dialog, int which){
                                moveTaskToBack(true);
                                finish();

                            }
                        })
                        .setNegativeButton("아니오", null)
                        .show();
        }

        return true;
    }


}

