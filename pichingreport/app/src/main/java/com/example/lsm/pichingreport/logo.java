package com.example.lsm.pichingreport;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;


public class logo extends Activity {
    static Handler hd;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        ImageView logo =(ImageView)findViewById(R.id.logo);
        Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.logo);
        Bitmap resize = Bitmap.createScaledBitmap(orgImage, 900, 675, true);

        logo.setImageBitmap(resize);

        hd = new Handler();
        hd.postDelayed(new Runnable() {



            @Override

            public void run() {

                finish();       // 3 초후 이미지를 닫아버림

            }

        }, 3000);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logo, menu);
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
}
