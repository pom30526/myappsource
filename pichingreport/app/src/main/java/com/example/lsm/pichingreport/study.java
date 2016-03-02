package com.example.lsm.pichingreport;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class study extends Activity {
    TextView head,leftarm,rightarm,core,leftleg,rightreg,elbow;
    PopupWindow mPopupWindow;
    ImageView popupImage;
    Button popupButton;
    TextView popuptext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        ImageView basic=(ImageView)findViewById(R.id.basicmotion);
        Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.basicmotion);
       final Bitmap resize = Bitmap.createScaledBitmap(orgImage, 590, 394, true);

        basic.setImageBitmap(resize);
        /*
        도움말 부분 인디케이터역할
         */
        head =(TextView)findViewById(R.id.headpart);
        core=(TextView)findViewById(R.id.Core);
        leftarm =(TextView)findViewById(R.id.lelftarm);
        rightarm=(TextView)findViewById(R.id.rightarm);
        leftleg =(TextView)findViewById(R.id.leftleg);
        rightreg=(TextView)findViewById(R.id.rightleg);
        elbow=(TextView)findViewById(R.id.elbow);
        /*
        popup View 부분
         */
        final View popupView=getLayoutInflater().inflate(R.layout.popuppart,null);
        mPopupWindow = new PopupWindow(popupView, RelativeLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*
       popup 변수설정
         */
        popupImage=(ImageView)popupView.findViewById(R.id.popupimage);
        popuptext=(TextView)popupView.findViewById(R.id.popuptext);
        popupButton=(Button)popupView.findViewById(R.id.popupclose);
        popupButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                resize.recycle();
                mPopupWindow.dismiss();
            }
        });
        head.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
            popuptext.setText("\n\n투구시 머리의 위치는 내닫는 \n발의 위치와 일직선이 될수록 유리합니다");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.headpart);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
            popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);

            }
        });
        leftleg.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptext.setText("왼 발끝은 반드시 던지려는 목표지점으로 향해야합니다. 왼무릎이열리면 힘이 분산됩니다.");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.legtleg);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
                popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);
            }
        });
        rightreg.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptext.setText("오른쪽 발은 중심이동을 함에 따라 자연스럽게 따라와야합니다");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.rightreg);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
                popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);
            }
        });
        leftarm.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptext.setText("왼(오른)팔은 최종 던지는 목표를 향해 겨낭하는 총구역할을 합니다 \n 왼(오른)팔은 던지려는 방향과 수평이되어야합니다");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.leftarm);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
                popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);
            }
        });
        rightarm.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptext.setText("투구시 가장 중요한 부분이며 힘을 전달하는 최종매체가 되는 부분이 오른팔입니다 \n " +
                        "팔 스윙시 최대한 부드러운 형태로 스윙을 하여야합니다");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.rightarm);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
                popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);
            }
        });
        elbow.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptext.setText("가장 초보자에게서 많이나타나는 부분으로 팔꿈치는 어깨선보다 낮으면 안됩니다. \n" +
                        "어깨선과 수평을 이룬상태에서 끌고 나올수 있는 최대한 끌고나와 던져야합니다");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.elbowlast22);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
                popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);
            }
        });
        core.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                popuptext.setText("허리는 힘을 전달하는 코어역할을 합니다. 허리를 돌릴시 어깨는 수평을 유지한상태에서 중심이동의 힘으로 자연스럽게 회전하는 것이" +
                        "핵심입니다. 허리의 힘을 잘쓰느냐가 공의 힘을 더해줍니다.");
                Bitmap orgImage = BitmapFactory.decodeResource(getResources(), R.drawable.core);
                Bitmap resize = Bitmap.createScaledBitmap(orgImage, 800, 550, true);
                popupImage.setImageBitmap(resize);
                mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 100, 100);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_study, menu);
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
