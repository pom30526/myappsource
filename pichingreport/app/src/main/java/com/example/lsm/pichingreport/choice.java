package com.example.lsm.pichingreport;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class choice extends Activity {
    private Button record,report,probt,help;
    public Intent intent2,intent3;
    RelativeLayout choicelayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Button record = (Button) findViewById(R.id.record);
        Button report = (Button) findViewById(R.id.confirm);
        Button help=(Button)findViewById(R.id.help);
        Intent logo = new Intent(choice.this,logo.class);
        startActivity(logo);
        choicelayout =(RelativeLayout)findViewById(R.id.choicelayout);
        //choicelayout.setBackgroundResource(R.drawable.logo);
        record.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent;
                //사진으로 바꿈
        intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5);//maximum 5초
                intent.putExtra(MediaStore.EXTRA_MEDIA_FOCUS,5);
                startActivity(intent);
//            intent = new Intent(choice.this,RecordActivity.class);
//                startActivityForResult(intent,0);

    }
});
        report.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //동영상 선택버튼 이벤트 , 해당 메소드는 내장 메소드 이므로 새로 만들어야함
                intent2 = new Intent(choice.this, VideoOut.class);
                startActivityForResult(intent2, 1);
            }
        });

        help.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent3 = new Intent(choice.this,study.class);
                startActivity(intent3);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK)
        {

                Uri uri = intent.getData();
                String path = getPath(uri);
                String name = getName(uri);
                String uriId = getUriId(uri);
                Log.e("###", "실제경로 : " + path + "\n파일명 : " + name + "\nuri : " + uri.toString() + "\nuri id : " + uriId);

        }
    }

    // 실제 경로 찾기
    private String getPath(Uri uri)
    {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // 파일명 찾기
    private String getName(Uri uri)
    {
        String[] projection = { MediaStore.Images.ImageColumns.DISPLAY_NAME };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DISPLAY_NAME);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    // uri 아이디 찾기
    private String getUriId(Uri uri)
    {
        String[] projection = { MediaStore.Images.ImageColumns._ID };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choice, menu);
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
