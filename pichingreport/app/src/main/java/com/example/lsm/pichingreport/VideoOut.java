package com.example.lsm.pichingreport;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class VideoOut extends Activity {
    ArrayList<Bitmap> bitMapList = new ArrayList<Bitmap>();
    GridView gridView;
    int colWidth;
    final int NUM_COL = 3;                   // four columns in each row
    final float SPACING_PERCENT = 0.1f;      // 10% of image width
    Bitmap frame;
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        gridview 설정  부분
         */
        gridView = new GridView(this);
        gridView.setLayoutParams(new ViewGroup.LayoutParams(
                GridLayout.LayoutParams.MATCH_PARENT, GridLayout.LayoutParams.WRAP_CONTENT));

        int screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        double tmp = (double) screenWidth /
                (NUM_COL * (1 + SPACING_PERCENT) + SPACING_PERCENT);
        colWidth = (int) Math.round(tmp);
        int spacing = (int) Math.round(tmp * SPACING_PERCENT);

        // apply layout parameters for the grid view
        gridView.setPadding(spacing, spacing, spacing, spacing);
        // android:padding
        gridView.setNumColumns(NUM_COL);          // android:numColumns
        gridView.setColumnWidth(colWidth);        // android:columnWidth
        gridView.setVerticalSpacing(spacing);     // android:verticalSpacing
        gridView.setHorizontalSpacing(spacing);   // android:horizontalSpacing
        // apply layout
        setContentView(gridView);

        Intent chooseVideo = new Intent(Intent.ACTION_GET_CONTENT);
        chooseVideo.setType("video/*");
        startActivityForResult(chooseVideo, 1);



    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if(data ==null){

        }
        else
        {
            final Uri videoUri = data.getData();
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
                    if (time < 1500000)  //다리 들기 파트지 보통
                        time += 500000; //0.5 초 간격 자르기
                    else if (time < 3000000 && time > 1500000) // 상체움직임 핵심파트
                        time += 100000;  //0.1초 간격인데
                    else
                        time += 700000; //그외의 시간 0.7초 간격
                }


                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                            long arg3) {

                        Intent i = new Intent(VideoOut.this, FullImage.class);
                        i.putExtra("uri", videoUri);
                        for (int k = 0; k < bitMapList.size(); k++) {
                            bitMapList.get(k).recycle();
                        }
                        startActivity(i);
                        onPause();

                    }

                });


            } catch (Exception e) {
                Log.e("GUN", Log.getStackTraceString(e));
            }

            // instantiate an adapter
            BitmapAdapter adapter = new BitmapAdapter(this, bitMapList, colWidth);

            // bind the adapter to the grid view

            gridView.setAdapter(adapter);
            Toast.makeText(getBaseContext(), "사진을 누르면 확대됩니다", Toast.LENGTH_LONG).show();
        }
        }
protected void onPause(){
    super.onPause();
    }
   }





class BitmapAdapter extends ArrayAdapter<Bitmap> {
        private final List<Bitmap> bitMapList;
        private final int colWidth;

        /**
         * colWidth : in pixels
         */
        public BitmapAdapter(Activity activity,
                             List<Bitmap> dogList, int colWidth) {
            super(activity, 0, dogList);
            this.bitMapList = dogList;
            this.colWidth = colWidth;
        }

        public View getView(int position, View convertView,
                            ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView,
                                  ViewGroup parent) {
            ImageView imageView;
            if (convertView != null) {           // if a view is being recycled
                imageView = (ImageView) convertView;
            } else {                            // if not being recycled
                imageView = new ImageView(getContext());
                imageView.setLayoutParams(
                        new GridView.LayoutParams(colWidth, colWidth));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            }

            imageView.setImageBitmap(bitMapList.get(position));
            return imageView;

        }



}





//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_video_out, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

